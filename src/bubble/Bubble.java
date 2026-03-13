package bubble;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
@Getter
@Setter
public class Bubble extends JLabel implements Moveable {
    private int x;
    private int y;

    private ImageIcon bubbleIcon;
    private Player player;


    // 이동 상태 플래그
    private static final int HORIZONTAL_DISTANCE = 400; // 수평 이동 거리
    private static final int BUBBLE_SPEED_MS = 1; // 이동간격 (ms)
    private static final int SCREEN_TOP = 0; // 화면 상단 경계
    private boolean leftMoving = false;
    private boolean rightMoving = false;
    private boolean upMoving = false;

    private boolean LeftWallCrash = false;
    private boolean RightWallCrash = false;



    public Bubble(Player player) {
        this.player = player;
        initData();
        setInitLayout();
        bubbleStartThread(); // 생성과 동시에 플레이어 방향 판단해서 바로 이동 시작
        // 버블과 플레이너는 동시에 동작해야 하기에 쓰레드를 새로 만든것이다.

    }

    // 물방울 이동 쓰레드
    public void bubbleStartThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (player.getPlayerWay() == PlayerWay.LEFT) {
                    // 그리고 player.left가 동작했으니 getPlayerWay() 에는PlayerWay.LEFT가 들어가있으니 조건충족 PlayerWay.LEFT enum 은 기능은 따로없고 판별을 위한 문법일 뿐이다
                    left(); // 왼쪽 으로 400픽셀 이동 완료후 --> up()호출
                } else { // 여기 동작이 안된다
                    right(); // 오른쪽 으로 400픽셀 이동 완료후 --> up()호출
                }
            }
        }).start();
    }

    private void initData() {

        bubbleIcon = new ImageIcon("img/bubble.png");

    }

    private void setInitLayout() {
        x = player.getX();
        y = player.getY();
        setIcon(bubbleIcon);
        setSize(50, 50);
        setLocation(x, y);
        setVisible(true);
    }

    @Override
    public void left() {
        leftMoving = true;
        for (int i = 0; i < HORIZONTAL_DISTANCE; i++) { // 사거리 만큼 for문
            x--;
            setLocation(x, y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        leftMoving = false; // for 문 끝난후 false 반환
        up(); // 수평이동 완료후 상승시작
    }

    @Override
    public void right() {
        rightMoving = true;
        for (int i = 0; i < HORIZONTAL_DISTANCE; i++) {
            x++;
            setLocation(x, y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        rightMoving = false;
        up(); // 수평 완료후 상승시작
    }

    @Override
    public void up() {
        upMoving = true;

        while (y > SCREEN_TOP){ // 제일위까지 버블이 올라가게
            y--;
            setLocation(x,y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        upMoving = false;
    }
}
