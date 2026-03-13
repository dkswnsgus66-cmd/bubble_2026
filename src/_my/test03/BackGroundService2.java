package _my.test03;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackGroundService2 implements Runnable {
    // ImageIcon / Image
    // 화면에 그려서 보여주기 위한 이미지
    // --> 픽셀 데이터에 직접 접근이 불가능 함
    // BufferedImage
    // --> 메모리에 픽셀 배열로 저장된 이미지
    // --> getRGB(x, y)로 특정 좌표의 색상값을 직접 읽을 수 있음

    private BufferedImage image;
    private Player2 player;


    public BackGroundService2(Player2 player) {
        // 의존성 주입 DI(Dependency Injection)
        // player 를 생성자를 통해서 외부에서 주입 받음
        // 즉, 이 서비스가 직접 플레이어를 생성하지 않고 외부에서 주입 받아 사용 됨.
        this.player = player;
        try {
            image = ImageIO.read(new File("img/backgroundMapService.png"));
        } catch (IOException e) {
            throw new RuntimeException("충돌 감지 이미지를 찾을수 없습니다." + e);
        }
    }



    @Override
    public void run() {
        while (true) {
            Color leftColor = new Color(image.getRGB(player.getX(), player.getY() + 25)); // 플레이어 이동할때마다 색깔을 가짐
            Color rightColor = new Color(image.getRGB(player.getX() + 60, player.getY() + 25));
            // 왼쪽벽 충돌감지 판단
            // leftColor --> 255 0 0   ./  255 255 255
            if (isRed(rightColor)) {
                player.setRightWallCrash(true); // 충돌상태 ON
                player.setRight(false); // while(right) 종료 --> 이동멈춤 Thread 종료
            }else {
                player.setRightWallCrash(false); // 벽에서 벗어나면 즉시 헤제
            }
        }
    }
    private boolean isRed(Color color){
        return color.getRed() == 255 && color.getGreen() ==0 && color.getBlue() ==0;
    }
}
