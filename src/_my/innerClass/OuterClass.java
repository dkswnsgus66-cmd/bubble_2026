package _my.innerClass;

public class OuterClass {
    private int num = 10;
    // 클래스 파일안에 내부에 또 클래스 선언 (중첩클래스) // 인스턴스 내부 클래스
    class InnerClass {
        public void display() {
            System.out.println("num : " + num);
        }
    }


    public static void main(String[] args) {
        // 내부 클래스가 일반 멤버 클래스로 설계된 경우
        // 외부 클래스 객체가 생성되고 내부 클래스를 생성할 수 있다.
        OuterClass outerClass = new OuterClass(); // 외부 클래스 객체 먼저 생성
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();// 그다음 내부 클래스 객체 생성
        innerClass.display();
    }
}
