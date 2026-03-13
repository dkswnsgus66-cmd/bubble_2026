package bubble;

public interface Moveable {
    void left();
    void right();
    void up();
    // Addapter 클래스가 너무 많이 생겨서 default 문법을
    // 인터페이스에서 사용할수 있도록 만들어 줫다.
    // 즉 default 키워드를 사용하면 인터페이스 안에서 일반 메서드를 선언할수 있다.
   default void down(){};

}
