package ch.supsi.game.monopoly;

public class Bank {
    private int amount;
    public Bank(){
        this.amount = Constant.BANK_START_AMOUNT;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
