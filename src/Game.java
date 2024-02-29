/**
 *
 */
public class Game {
    /**
     *
     */
    private Player[] players;
    /**
     *
     */
    private Bank bank;
    /**
     *
     */
    private Board board;
    /**
     *
     */
    private Dice dice;
    /**
     *
     */
    private ScannerUtils scannerUtils;
    /**
     *
     */
    public Game(int playersNumber){
        if (playersNumber < 2) {
            playersNumber = 2;
        }
        players = new Player[playersNumber];
    }
    /**
     *
     */
    public void start(){
//        for(int i = 0; i < Constant.PLAYER_NUMBER; i++) {
//            System.out.println("Insert the player n" + (i+1) + " informations");
//            players[i] = new Player(scannerUtils.readStringAndEnsureIsNotEmptyOrWhiteSpaces(), scannerUtils.readCharAndEnsureIsNotEmptyOrWhiteSpaces());
//            bank.setAmount(bank.getAmount()-Constant.PLAYER_START_AMOUNT);
//            System.out.println("Player " + players[i].getName() + " created with character " + players[i].getSymbol() + "\n");
//        }
    }
}
