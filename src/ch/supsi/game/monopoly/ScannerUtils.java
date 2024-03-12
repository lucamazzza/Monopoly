package ch.supsi.game.monopoly;

import java.util.Scanner;
public class ScannerUtils {
    private final Scanner scanner = new Scanner(System.in);

    public int readIntInRange(int min, int max, String msg) {
        int input = 0;
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print(msg);
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

    public String readNonBlankString(String msg) {
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
    public char readNonBlankChar(String msg){
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

    public int getNumberOfPlayers(){
        return readIntInRange(2, 6, "How many players will play?");
    }

    public int readOption(){
        System.out.println("What do you want to do?");
        return this.readIntInRange(1,3,"1. Roll the dice\n2. View your balance\n3. Quit\nOption: ");
    }

    public void readKey(String msg){
        System.out.print(msg);
        scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void emptyTheScanner() {
        scanner.nextLine();
    }
}
