package thread;

public class Mother extends  Thread{

    BankAccount bankAccount;

    public Mother(BankAccount bankAccount){
        this.bankAccount = bankAccount;

    }

    @Override
    public void run() {
        // 출금
        bankAccount.withDraw(5000); // 하드코딩임
    }
}
