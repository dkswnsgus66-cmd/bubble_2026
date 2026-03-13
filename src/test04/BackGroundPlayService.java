package test04;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [클래스 역할] - 플레이어 충돌 감지 서비스(백그라운드에서 계속 돌아감) - 메인쓰레드는 너무 바쁨
 *
 */

public class BackGroundPlayService implements Runnable {


    // ImageIcon / Image
    // 화면에 그려서 보여주기 위한 이미지
    // --> 픽셀 데이터에 직접 접근 불가능
    // BufferedImage
    // --> 메모리에 픽셀 배열로 저장된 이미지
    // --> getRGB(x,y);로 특정 좌표의 색상값을 직접 읽을수 있음 색깔로 라벨리 움직일 범위 지정할려고 한다
    private BufferedImage image;
    private Player player;

    // 의존성 주입 DI(Defendency Injection)
    // Player 를 생성자를 통해서 외부에서 주입받음 즉 이 서비스가 직접 플레이어를 생성하지 않고 외부에서 주입받아 사용됨.
    public BackGroundPlayService(Player player) {
        this.player = player;

        try {// 이미지가 없을수 있을 경우도 있기때문에 trycatch로 방어
            image = ImageIO.read(new File("img/backgroundMapService.png"));
        } catch (IOException e) {
            throw new RuntimeException("충돌감지 이미지를 찾을수 없습니다." + e);
        }
    }


    @Override
    public void run() {

        // 게임 끝날깨까지 계속 실해잉 되어야함.
        while (true) {

            Color leftColor = new Color(image.getRGB(player.getX(), player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 60, player.getY() + 25));

            // 왼쪽벽 충돌 감지 판단
            if(isRed(leftColor)){
                // 충돌상태 변수 ON -> 추가예정
                player.setLeftWallCrash(true);
                player.setLeft(false); // while(left) true면 이동 false면 이동멈춤
            }else {
                // 벽에서 벗어나면 즉시 해제 --> 다시 이동 가능하게 설정
                player.setLeftWallCrash(false);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 오늘쪽벽 충돌감지 판단
            if (isRed(rightColor)){
                // 충돌상태 변수
                player.setRightWallCrash(true); // 충돌상태 ON
                player.setRight(false); // while (right) 종료 --> 이동멈충 Thread 종료
            }else {
                player.setRightWallCrash(false); // 벽에서 벗어나면 즉시 해제
            }
        }

    }
    private boolean isRed (Color color){
        return color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0; // true가 응답하면 이건 빨간색
    }

}// end of class
