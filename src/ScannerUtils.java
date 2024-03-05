import java.util.Scanner;
public class ScannerUtils {
    private final Scanner scanner = new Scanner(System.in);

    public int readIntInRange(int min, int max, String msg) {
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
        int num = readIntInRange(2, 6, "How many players will play?");
        return num;
    }

    public int readOption(){
        System.out.println("What do you want to do?");
        return this.readIntInRange(1,2,"1. View your balance \n 2. Roll the dice");
    }

    public void readKey(String msg){
        System.out.println(msg);
        scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void emptyTheScanner() {
        scanner.nextLine();
    }
}
