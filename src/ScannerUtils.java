import java.util.Scanner;
public class ScannerUtils {
    Scanner scanner = new Scanner(System.in);

    int readIntInRange(int min, int max, String msg) {
        int input = 0;
        boolean correctInput = false;
        while (!correctInput) {
            System.out.println(msg + min + " and " + max + ":");
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

    String readNonBlankString(String msg) {
        String input = "";
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print(msg);
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Error: string is empty or contains only white spaces.");
            } else {
                correctInput = true;
            }
        }
        return input;
    }
    char readNonBlankChar(String msg){
        String input = "";
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print(msg);
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

    int readOption(){
        System.out.println("What do you want to do?");
        return this.readIntInRange(1,2,"1. View your balance \n 2. Roll the dice");
    }

    void closeScanner() {
        scanner.close();
    }

    void emptyTheScanner() {
        scanner.nextLine();
    }
}
