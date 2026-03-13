package _my.test03;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame2 extends JFrame {

   private Player2 player;
   private JLabel backgroundMap;
   private BackGroundService2 backGroundService;


   public BubbleFrame2(){

       initData();
       setLayout();
       eventListener();
   }


    private void initData() {
        setTitle("버블버블 게임");
        setSize(1000,640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       backgroundMap = new JLabel(new ImageIcon("img/backgroundMap.png"));
       player = new Player2();
       backGroundService = new BackGroundService2(this.player);
       new Thread(backGroundService).start();
       setContentPane(backgroundMap);//


    }

    private void setLayout() {

        setLayout(null);
        setResizable(false); // 창 크기 고정
        setLocationRelativeTo(null); // 화면 정중앙 고정
        backgroundMap.add(player);
        setVisible(true);


    }

    private void eventListener(){
       this.addKeyListener(new KeyAdapter(){

           @Override
           public void keyPressed(KeyEvent e) {
               switch (e.getKeyCode()) {
                   case KeyEvent.VK_LEFT:
                       if(!player.isLeftWallCrash() && !player.isLeft()) { // is left는 left 반환 boolean은 get 을 is 로 본다 즉 getleft로 보면된다 isLeftWallCrash가 빨간색이면 ture이고 false조건인
                           // if 조건에서 멈춘다
                           player.left();
                       }
                       break;
                   case KeyEvent.VK_RIGHT:
                       if(player.isLeftWallCrash() == false && player.isLeft() == false){
                           player.right();
                       }
                       break;
                   case KeyEvent.VK_UP:
                       if(player.isLeftWallCrash() == false && player.isLeft() == false){
                           player.up();
                       }
                       break;
               }
           }

           @Override
           public void keyReleased(KeyEvent e) {
               super.keyReleased(e);
           }
       });
    }

    public static void main(String[] args) {
        new BubbleFrame2();
    }
}
