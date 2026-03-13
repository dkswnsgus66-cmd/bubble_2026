package test03;

import javax.swing.*;

public class Player2 extends JLabel implements MoveAble2 {
    // 플레이어 좌표
    private int x;
    private int y;
    // 속도 상수
    private final int SPEED = 10;
    private final int HEIGHT = 130; // 최고 높이
    private final int UP_SPEED = 2; // 점프 스피드

    // 좌우 방향 이미지(방향키에 따라서 이미지 전환)
    private ImageIcon playerR;
    private ImageIcon playerL;


    // 이동 상태 플래그
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    // true = 해당 방향으로 이동 중 (while 루프 조건)
    // false = 멈춤 (while 루프 탈출 -> Thread 종료)

    private boolean leftWallCrash;
    private boolean rightWallCrash;

    // getter
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public boolean isLeftWallCrash() {
        return leftWallCrash;
    }

    public boolean isRightWallCrash() {
        return rightWallCrash;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }

    // setter

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeftWallCrash(boolean leftWallCrash) {
        this.leftWallCrash = leftWallCrash;
    }

    public void setRightWallCrash(boolean rightWallCrash) {
        this.rightWallCrash = rightWallCrash;
    }



    public Player2(){

        initData();
        setInitData();

    }


    private void initData() {
        playerR = new ImageIcon("img/playerR.png");
        playerL = new ImageIcon("img/playerL.png");
    }
    private void setInitData() {
         x = 55;
         y = 535;
        setSize(50,50);
        setIcon(playerR);
        setLocation(x,y);
    }


    @Override
    public void left() {
        if(left){
            return;
        }
        left = true;
        setIcon(playerL);
        new Thread (new Runnable(){

            @Override
            public void run() {
                while (left){
                    x = x - SPEED;
                    setLocation(x,y);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    left = false;
                }

            }
        }).start();
    }
    @Override
    public void right() {
        if(right){
            return; // Thread 객체 한번만 생성
        } // right = false
        right = true; // true 받고
        setIcon (playerR);
        new Thread(new Runnable() { // 쓰레드 생성
            @Override
            public void run() {
                while (right){
                    x = x + SPEED;
                    setLocation(x,y);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    right = false; // 다시 false
                }

            }
        }).start();
    }

    @Override
    public void up() {
        if(up == true){ // 만약 up 기본이 true 이면 쓰레드 동작 x
            return;
        }
        up = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
               for (int i = 0 ; i < HEIGHT / UP_SPEED; i++){
                   y  = y - UP_SPEED; // 좌표값을 바꿔준다
                   setLocation(x,y); // 현재 위치값 에 바뀐것을 반영
                   try {
                       Thread.sleep(2);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
               down();
               up = false;
            }
        }).start();
    }

    @Override
    public void down() {
        if(down){
            return;
        }
        down = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0 ; i < HEIGHT/UP_SPEED; i++){
                    y= y + UP_SPEED;
                    setLocation(x,y);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                down = false;
            }
        }).start();
    }
}
