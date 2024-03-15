package ch.supsi.game.monopoly;

public class Bank {

    private int balance;

    public Bank(){
        this.balance = Math.max(Constant.BANK_START_AMOUNT, 1_000_000);
    }

    public int getBalance() {
        return this.balance;
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
