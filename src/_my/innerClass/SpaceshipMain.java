package _my.innerClass;

public class SpaceshipMain {

    public static void main(String[] args) {

        // 정적 내부클래라에 바로 객체 생성으로 접근가능 바로 생성 가능함
        Spaceship.Engine engine1 = new Spaceship.Engine(); // 내부클래스 객체 생성
        Spaceship spaceship = new Spaceship(); // 외부 클래스 객체 생성
        spaceship.addEngine(engine1);
        spaceship.startSpaceShip();

    } // end of main
}