public class Game {
    private Player[] players;

    public Game(){
        players = new Player[Constant.PLAYER_NUMBER];
    }

    public void start(){
        ScannerUtils scannerUtils = new ScannerUtils();
        Bank bank = new Bank();

        for(int i = 0; i < Constant.PLAYER_NUMBER; i++) {
            players[i] = new Player(scannerUtils.readStringAndEnsureIsNotEmptyOrWhiteSpaces(), scannerUtils.readCharAndEnsureIsNotEmptyOrWhiteSpaces());
            System.out.println("Player " + players[i].getName() + " created");
            bank.setAmount(bank.getAmount()-Constant.PLAYER_START_AMOUNT);
        }
    }
}
