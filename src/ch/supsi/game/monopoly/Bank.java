package ch.supsi.game.monopoly;

public class Bank {

    private int amount;

    public Bank(){
        this.amount = Constant.BANK_START_AMOUNT;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount < 1) {
            return;
        }
        this.balance += amount;
    }

    public void withdraw(int amount){
        if (amount < 1) {
            return;
        }
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return String.format("Bank: %d.–", this.balance);
    }
}
