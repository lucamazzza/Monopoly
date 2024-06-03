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
 * The bank has a static implementation, use it accordingly.
 * </p>
 * <p>
 * <b>Usage</b>:
 * </p>
 * <pre>
 * {@code
 * Bank bank = Bank.getInstance();          // get the bank instance
 * bank.deposit(500);                       // give some money to the bank
 * bank.withdraw(100);                      // take some money from the bank
 * System.out.print(bank.getBalance());     // prints: "Bank: 1000400.00"
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @author Luca Mazza
 * @version 1.4.0
 */
public final class Bank {

    /**
     * The instance of the bank.
     */
    private static Bank instance;

    /**
     * The amount of money held in the bank.
     */
    private double balance;

    /**
     * <p>
     * Private constructor for class Bank.
     * Prevents instantiation, as for Singleton specification.
     * </p>
     */
    private Bank() {
        this.balance = Math.max(Constant.BANK_START_AMOUNT, 1_000_000);
    }

    /**
     * <p>
     * Returns the singleton instance of the bank.
     * </p>
     * <p>
     * If the bank is not yet instantiated, it is instantiated,
     * otherwise the existing instance is returned.
     * </p>
     *
     * @return the singleton instance of the bank
     */
    public static Bank getInstance() {
        if (instance == null) instance = new Bank();
        return instance;
    }

    /**
     * <p>
     * Deposits to the bank some money, defined in `amount`.
     * </p>
     * <p>
     * Used to pay the bank the fee of the current player.
     * </p>
     *
     * @param amount the amount of money to deposit
     */
    public void deposit(final double amount) {
        if (amount < 1) {
            return;
        }
        this.balance += amount;
    }

    /**
     * <p>
     * Withdraws some money, defined in `amount`, from the bank.
     * </p>
     * <p>
     * Used to give money to the player when a cell specifies so.
     * </p>
     *
     * @param amount the amount of money to withdraw
     */
    public void withdraw(final double amount){
        if (amount < 1) {
            return;
        }
        this.balance -= amount;
    }

    /**
     * <p>
     * Prints the current balance of the bank.
     * </p>
     *
     * @return the current balance as a String
     */
    public String getBalance() {
        return String.format("Bank: %.2f", this.balance);
    }
}
