package ch.supsi.game.monopoly;

import java.util.Scanner;

/**
 * <p>
 * This class is in charge of managing the interaction
 * between the console (user) and the program.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * ScannerUtils scannerUtils = new ScannerUtils();                      // Instantiate a new Scanner
 * int i = scannerUtils.readIntInRange(1, 6, "Enter a number [1;6]: "); // Read a bound integer
 * char c = scannerUtils.readNonBlankChar("Enter a character: ");       // Read a character
 * String s = scannerUtils.readNonBlankString("Enter a string: ");      // Read a string
 * scannerUtils.readKey("Press enter to continue...");                  // Read a key
 * scannerUtils.readBoolean();                                          // Read y/n input
 * scannerUtils.closeScanner();                                         // Close the scanner
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @version 1.3.0
 */
public class ScannerUtils {

    /**
     * Scanner instance.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * <p>
     * This method is in charge of reading an integer in a determined
     * range from the user.
     * </p>
     * <p>
     * When the user enters a number not in the range, an error message
     * is displayed and the input is read again.
     * </p>
     * <p>
     * When the input is not a number, an error message is displayed and
     * the input is read again.
     * </p>
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param msg the directive message to display the user
     * @return the integer entered
     */
    public int readIntInRange(final int min, final int max, final String msg) {
        int input = 0;
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print(msg);
            if (this.scanner.hasNextInt()) {
                input = this.scanner.nextInt();
                if (input < min || input > max)
                    System.out.println("Error: number not in range.");
                else
                    correctInput = true;
            } else {
                System.out.println("Error: input is not a number.");
                emptyTheScanner();
            }
        }
        this.emptyTheScanner();
        return input;
    }

    /**
     * <p>
     * Reads a boolean input in the "yes/no" form.
     * </p>
     *
     * @return true if the user input is yes, false if it is no
     */
    public boolean readBoolean() {
        String input;
        do {
            System.out.print("Y/n: ");
            input = this.scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'n'.");
            }
        } while (true);
    }

    /**
     * <p>
     * This method is in charge of reading a non-blank,
     * non-empty string from the user.
     * </p>
     * <p>
     * When the user enters a empty/blank string, an error message
     * is displayed and the input is read again.
     * </p>
     *
     * @param msg the directive message to display the user
     * @return the string entered
     */
    public String readNonBlankString(String msg) {
        String input = "";
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print(msg);
            input = this.scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Error: string is empty or contains only white spaces.");
            } else {
                correctInput = true;
            }
        }
        return input;
    }

    /**
     * <p>
     * This method is in charge of reading a non-blank,
     * non-empty character from the user.
     * </p>
     * <p>
     * When the user enters a empty/blank character, an error message
     * is displayed and the input is read again.
     * </p>
     *
     * @param msg the directive message to display the user
     * @return the character entered
     */
    public char readNonBlankChar(String msg){
        String input = "";
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print(msg);
            input = this.scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Error: character is empty or contains only white spaces.");
            } else if(input.length()!=1){
                System.out.println("Error: input is not a character");
            }else{
                correctInput = true;
            }
        }
        return input.charAt(0);
    }

    /**
     * <p>
     * Reads an option from the user
     * </p>
     * <p>
     * The user is asked to choose one of the three options:
     * 1. Roll the dice
     * 2. View your balance
     * 3. View your proprieties
     * 4. Quit
     * </p>
     *
     * @return the user's option
     */
    public int readOption(){
        System.out.println("What do you want to do?");
        return this.readIntInRange(1,4,
                "1. Roll the dice\n2. View your balance\n3. View your proprieties\n4. Quit\nOption: ");
    }

    /**
     * <p>
     * Reads any key followed by enter from the user.
     * </p>
     * <p>
     * Used to give the user time to read the message and
     * confirm to go forward in the game.
     * </p>
     *
     * @param msg the message to display
     */
    public void readKey(String msg){
        System.out.print(msg);
        this.scanner.nextLine();
    }

    /**
     * <p>
     * Closes the {@link ScannerUtils#scanner} used by this class.
     * </p>
     */
    public void closeScanner() {
        this.scanner.close();
    }

    /**
     * <p>
     * Empties the {@link ScannerUtils#scanner}'s buffer.
     * </p>
     */
    public void emptyTheScanner() {
        this.scanner.nextLine();
    }
}
