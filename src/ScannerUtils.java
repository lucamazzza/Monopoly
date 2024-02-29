import java.util.Scanner;
public class ScannerUtils {
    Scanner scanner = new Scanner(System.in);

    int readIntegerInRange(int min, int max) {
        int input = 0;
        boolean correctInput = false;
        while (!correctInput) {
            System.out.println("Please enter a number between " + min + " and " + max + ":");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input < min || input > max)
                    System.out.println("Error: number not in range.");
                else
                    correctInput = true;
            } else {
                System.out.println("Error: input is not a number.");
                emptyTheScanner();
            }
        }
        emptyTheScanner();
        return input;
    }

    String readStringAndEnsureIsNotEmptyOrWhiteSpaces() {
        String input = "";
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print("Please enter a string: ");
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Error: string is empty or contains only white spaces.");
            } else {
                correctInput = true;
            }
        }
        return input;
    }
    char readCharAndEnsureIsNotEmptyOrWhiteSpaces(){
        String input = "";
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print("Please enter a character: ");
            input = scanner.nextLine();
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

    int getNumberOfPlayers(){
        System.out.println("How many players will play?");
        int num = readIntegerInRange(2, 6);
        return num;
    }

    void closeScanner() {
        scanner.close();
    }

    void emptyTheScanner() {
        scanner.nextLine();
    }
}
