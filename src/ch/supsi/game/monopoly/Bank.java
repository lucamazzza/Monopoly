package ch.supsi.game.monopoly;

/**
 * <p>
 * This class represent the bank, of the game "Monopoly".
 * </p>
 * <p>
 * The bank is in charge of giving and taking money from players
 * based on their movements on the board.
 * </p>
 * <p>
 * It has a starting fund of 1'000'000.
 * </p>
 * <p>
 * <b>Usage</b>:
 * </p>
 * <pre>
 * {@code
 * Bank bank = new Bank();
 * bank.deposit(500);       // give some money to the bank
 * bank.withdraw(100);      // take some money from the bank
 * System.out.print(bank);  // prints: "Bank: 1'000'400.–"
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @author Luca Mazza
 * @version 1.1.0
 */
public class Bank {

    /**
     * The amount of money held in the bank.
     */
    private int balance;

    /**
     * <p>
     * Constructor of the Bank class.
     * </p>
     * <p>
     * The starting amount of the bank is set to 1'000'000,
     * using the {@link Constant} class' constant `BANK_START_AMOUNT`.
     * </p>
     * <p>
     * If this value, for any reason, is set to less than 1'000'000
     * the value is automatically set to 1'000'000.–
     * </p>
     */
    public Bank(){
        this.balance = Math.max(Constant.BANK_START_AMOUNT, 1_000_000);
    }

    /**
     * Returns the current balance of the class.
     *
     * @return the current balance
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Deposits to the bank some money, defined in `amount`.
     * Used to pay the bank the fee of the current player.
     *
     * @param amount the amount of money to deposit
     */
    public void deposit(int amount) {
        if (amount < 1) {
            return;
        }
        this.balance += amount;
    }

    /**
     * Withdraws some money, defined in `amount`, from the bank.
     * Used to give money to the player when a cell specifies so.
     *
     * @param amount the amount of money to withdraw
     */
    public void withdraw(int amount){
        if (amount < 1) {
            return;
        }
        this.balance -= amount;
    }

    /**
     * Prints the current balance of the bank.
     *
     * @return the current balance as a String
     */
    @Override
    public String toString() {
        return String.format("Bank: %d.–", this.balance);
    }
}
