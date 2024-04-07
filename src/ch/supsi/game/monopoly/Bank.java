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
 * Bank.deposit(500);                       // give some money to the bank
 * Bank.withdraw(100);                      // take some money from the bank
 * System.out.print(Bank.getBalance());     // prints: "Bank: 1000400.00"
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @author Luca Mazza
 * @version 1.2.0
 */
public final class Bank {

    /**
     * The amount of money held in the bank.
     */
    private static double balance = Math.max(Constant.BANK_START_AMOUNT, 1_000_000);

    private Bank() {
        throw new IllegalStateException("Static class");
    }

    /**
     * Deposits to the bank some money, defined in `amount`.
     * Used to pay the bank the fee of the current player.
     *
     * @param amount the amount of money to deposit
     */
    public static void deposit(double amount) {
        if (amount < 1) {
            return;
        }
        balance += amount;
    }

    /**
     * Withdraws some money, defined in `amount`, from the bank.
     * Used to give money to the player when a cell specifies so.
     *
     * @param amount the amount of money to withdraw
     */
    public static void withdraw(int amount){
        if (amount < 1) {
            return;
        }
        balance -= amount;
    }

    /**
     * Prints the current balance of the bank.
     *
     * @return the current balance as a String
     */
    public static String getBalance() {
        return String.format("Bank: %.2f", balance);
    }
}
