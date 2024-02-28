public class Game {
    private Player[] players;

    public Game(){
        players = new Player[Constant.PLAYER_NUMBER];
    }

    public void start(){
        ScannerUtils scannerUtils = new ScannerUtils();
        for(int i = 0; i < Constant.PLAYER_NUMBER; i++){
            players[i] = new Player(scannerUtils.readStringAndEnsureIsNotEmptyOrWhiteSpaces(), scannerUtils.readCharAndEnsureIsNotEmptyOrWhiteSpaces());
            System.out.println("Player " + players[i].getName() + " created");
        }
    }
}
