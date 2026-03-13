package test04;

import javax.swing.*;

public class Bubble extends JLabel implements Moveable{

    private int x;
    private int y;
    private ImageIcon bubbleIcon =new ImageIcon("img/bubble.png");
    private Player player;


    public Bubble(Player player){
        initData();
        setInitLayout();
        this.player = player;
    }

    private void initData() {

        setIcon(bubbleIcon);

    }
    private void setInitLayout() {

        x = player.getx();
        y = player.gety();
        setSize(50,50);
        setLocation(x,y);
        setVisible(true);

    }
// getter
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
// setter

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }




    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }
}
