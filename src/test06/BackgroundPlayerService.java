package test06;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [클래스 역할]
 * 플레이어 충돌 감지 서비스 (백그라운드에서 계속 돌아감) - 메인 쓰레드는 너무 바쁨
 */
public class BackgroundPlayerService implements Runnable {
    // ImageIcon / Image
    // 화면에 그려주기 위한 이미지
    // --> 실제 픽셀 데이터에 직접 접근 불가
    // BufferedImage
    // --> 메모리에 픽셀 배열로 저장된 이미지
    // --> getRGB(x, y)로 특정 죄표의 색상값을 직접 읽을 수 있음
    private BufferedImage image;
    private Player player;

    // 의존성 주입 DI(Dependency Injection)
    // player를 생성자를 통해서 외부에서 주입 받음
    // 즉, 이 서비스가 직접 플레이어를 생성하지 않고 외부에서 주입 받아 사용됨
    public BackgroundPlayerService(Player player) {
        this.player = player;
        try {
            image = ImageIO.read(new File("img/backgroundMapService.png"));
        } catch (IOException e) {
            throw new RuntimeException("충돌 감지 이미지를 찾을 수 없습니다 " + e);
        }

    }


    @Override
    public void run() {
        // 게임 끝날 때 까지 실행 돼야함
        while (true) {
            Color leftColor = new Color(image.getRGB(player.getX(), player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 60, player.getY() + 25));
            // 바닥층 감지 좌표 (플레이어 발 아래 두정)
            int bottomLeft = image.getRGB(player.getX() + 20, player.getY() + 55);
            int bottomRight = image.getRGB(player.getX() + 50, player.getY() + 55);
            System.out.println("bottomLefte: " + bottomLeft);
            System.out.println("bottomRight: " + bottomRight);
            if(bottomLeft + bottomRight == -2){ // => 허공 상태
                 // 계속 낙하 시킬 예정
                // 발 아래가 허공 -> 아직 점프중 이거나 / 낙하

                if(player.isUp() == false && player.isDown() == false){
                    // player.isUp() == false 점프중이 아님
                    // player.isDown() == false 낙하 중이 아님
                    player.down(); // 쓰레드로 인한 가속 방지 1번호출

                }else {
                    // 발 아래가 바닥/ 층 -> 낙하 즉시 중단
                    player.setDown(false); // while(false) --> false -> while 종료 , Thread 종료
                    // 멤버변수 down에 false 삽입
                }
            }
            // 정수값이 -1 이면 흰색
            // 정수값이 -65536 이면 빨간색


            // 왼쪽 벽 충돌 감지 판단
            if (isRed(leftColor)) {
                // 충돌 상태 변수 ON
                player.setLeftWallCrash(true);
                player.setLeft(false); // while(false) 종료 --> 이동 멈춤 (Thread 멈춤)

            } else {
                // 벽에서 벗어나면 즉시 해제 --> 다시 이동 가능하게 설정
                player.setLeftWallCrash(false);
            }

            // 오른쪽 벽 충돌 감지
            if (isRed(rightColor)) {
                // 충돌 상태 변수 ON
                player.setRightWallCrash(true); // 충돌 상태 ON --> 이동 멈춤
                player.setRight(false); // while (right) 종료
            } else {
                player.setRightWallCrash(false); // 벽에서 벗어나면 즉시 해제
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    private boolean isRed(Color color) {
        return color.getRed() == 255
                && color.getGreen() == 0
                && color.getBlue() == 0;
    }
}
