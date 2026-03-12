package _my.test01;

import test01.Enemy;
import test01.Player;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame extends JFrame implements Runnable{

    private test01.Player player; // 연관관계
    private JLabel backGroundMap;
    private final int MOVE_POINT = 10;
    private test01.Enemy enemy;



    public BubbleFrame(){
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() { // 데이터 최초 초기화
        setTitle("버블버블 게임");
        setSize(1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        backGroundMap = new JLabel(new ImageIcon("ing/backgroundMap.png"));
        setContentPane(backGroundMap);
        player = new Player(); // 버블 프레임이 내려가면 플레이어도 내려감
        enemy = new Enemy();

    }

    private void setInitLayout() {
        setLayout(null);
        setResizable(false); // 창크기 고정
        setLocationRelativeTo(null); // 화면 정중앙 배치 (프레임)
        backGroundMap.add(player);
        backGroundMap.add(enemy);
        setVisible(true);

    }

    private void addEventListener() {

        this.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                // 방향키 코드를 Player의 이동 메서드로 연결
                switch (e.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                        player.left();
                        player.setLocation(player.getX() - MOVE_POINT,player.getY());
                        if(player.getX() <0){
                            player.setLocation(0, player.getY());
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.right();
                        player.setLocation(player.getX() + MOVE_POINT,player.getY());
                        if(player.getX() > 950){
                            player.setLocation(950,player.getY());
                        }
                        break;
                    case KeyEvent.VK_UP:
                        player.up();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });
    }
    @Override
    public void run() {

    }


    // 테스트 코드
    public static void main(String[] args) {
        new BubbleFrame();
    }
}
