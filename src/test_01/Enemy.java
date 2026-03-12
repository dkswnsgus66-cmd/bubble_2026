package test_01;

import javax.swing.*;

public class Enemy extends JLabel {

    private int x;
    private int y;
    private ImageIcon enemyR;
    private ImageIcon enemyL;



    public Enemy(){

        initData();
        setInitData();


    }

    private void initData() {
        enemyL = new ImageIcon("ing/enemyL.png");
        enemyR = new ImageIcon("ing/enemyR.png");
    }

    private void setInitData() {
        // 위치지정
        x = 150;
        y = 400;
        setSize(50,50);
        setIcon(enemyL);
        setLocation(x,y);

    }

}
