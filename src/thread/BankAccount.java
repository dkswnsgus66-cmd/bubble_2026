package thread;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankAccount {
    // static 은 프로그램 끝날때까지 메모리에서 지워지지 않는다
    // heap영역의 데이터들은 가비지 컬렉터에 들어간다.

    private int money = 100000;

    // 기능 , 입금하다 출금하다

    // 엄마 클래스와 아빠 클래스는 서로 다른 메서드를 쓰지만 BankAccount라는 하나의 객체를 사용하는 메서드이다
    // 이때 서로 이 객체를 사용하는 순서를 정해주는게synchronized (동기화)이다 Main을 보면 father.start();먼저 쓰기에
    // father 먼저 동작이 끝난후에 mother 메서드가 동작하게 만든것 이다.


    // synchronized --> 동기화 이 메서드가 실행처리 다하기전까지는 다른쓰레드가 접근불가
     // 실행 시켜보면 father 먼저 동작하고 mother 동작하는게 보인다 추가로 같은 자원을 공유하는 BankAccount 까지 갱신이 된다.일단 여기까지만 알아보자
    public synchronized void saveMoney(int money) {
        // 현재 금액을 지역변수에 저장
        int currentsMoney = getMoney(); // 현재 돈 반환 지역변수라 10만원이 들어옴
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 3만원
        setMoney(currentsMoney + money);
        System.out.println("현재 계좌잔액 : " + getMoney()); // 10만원이 들어갔기에 1만원을 더함
    }

    // 출금 기능
    public int withDraw(int money) {
        // 블럭을 사용해서 동기화 처리
        synchronized (this) {
            int cutrrentsMoney = getMoney(); // 지역변수라 10만원이 들어옴 하지만 동기화를 해주면 cutrrentsMoney를 동기화한 다른 쓰레드와 공유함
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 만약 10 만원에서 1 만원 출금하려면
            // 방어적 코드 생략
            setMoney(cutrrentsMoney - money);
            System.out.println("출금후 현재계좌 잔액:" + getMoney());
            return money;
        }
    }
}
