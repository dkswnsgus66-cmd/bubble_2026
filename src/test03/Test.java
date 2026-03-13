package test03;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test implements Runnable{

   private BufferedImage dataImage;
   private Player2 player;


   public Test(Player2 player) throws IOException {

       this.player = player;
       try {
           dataImage = ImageIO.read(new File("img/backgroundMapService.png"));
       }catch (IOException e){
           throw new RuntimeException("충돌 감지 이미지를 찾을수 없습니다." + e);
       }
   }


    @Override
    public void run() {
        while (true) {
            Color leftColor = new Color(dataImage.getRGB(player.getX(), player.getY() + 25)); // 플레이어 이동할때마다 색깔을 가짐
            Color rightColor = new Color(dataImage.getRGB(player.getX() + 60, player.getY() + 25));
            // 왼쪽벽 충돌감지 판단
            // leftColor --> 255 0 0   ./  255 255 255
            if (isRed(rightColor)) {
                player.setRightWallCrash(true);// 충돌상태 ON
                player.setRight(false); // while(right) 종료 --> 이동멈춤 Thread 종료
            }else {
                player.setRightWallCrash(false); // 벽에서 벗어나면 즉시 헤제
            }
            if(isRed(leftColor)){
                player.setLeftWallCrash(true);
                player.setLeft(false);
            }else{
                player.setLeftWallCrash(false); // 빨간색이 아닐때 즉 벽에서 벗어날때
            }
        }
    }

    private boolean isRed(Color color){

       return color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() ==0; // 색깔이 빨간색이면 반환 true 이다

    }
}
