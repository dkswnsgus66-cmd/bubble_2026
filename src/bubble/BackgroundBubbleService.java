package bubble;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundBubbleService implements Runnable{

    private BufferedImage image;
    private Bubble bubble;

    BackgroundBubbleService(Bubble bubble){
        // 외부에서 버블을 주입받아야 한다 새로운 버블을 생성할 필요가 없기 때문이다.
        this.bubble = bubble;
        try {
            image = ImageIO.read(new File("img/backgroundMapService.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void run() {
        // 게임 끝날 때 까지 실행 돼야함
        while (true) {
            Color leftColor = new Color(image.getRGB(bubble.getX(), bubble.getY() + 25));
            Color rightColor = new Color(image.getRGB(bubble.getX() + 60, bubble.getY() + 25));

            if (isRed(leftColor)) {
                // 충돌 상태 변수 ON
                bubble.setLeftWallCrash(true);
                bubble.setLeftMoving(false); // while(false) 종료 --> 이동 멈춤 (Thread 멈춤)

            } else {
                // 벽에서 벗어나면 즉시 해제 --> 다시 이동 가능하게 설정
                bubble.setLeftWallCrash(false);
            }

            // 오른쪽 벽 충돌 감지
            if (isRed(rightColor)) {
                // 충돌 상태 변수 ON
                bubble.setRightWallCrash(true); // 충돌 상태 ON --> 이동 멈춤
                bubble.setRightMoving(false); // while (right) 종료
            } else {
                bubble.setRightWallCrash(false); // 벽에서 벗어나면 즉시 해제
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
