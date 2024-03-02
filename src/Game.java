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
     * Stores the index of the current player.
     */
    private int currentPlayer = 0;

    /**
     * True if the game is still running.
     */
    private boolean isGameRunning = true;

    /**
     *
     */
    public Game(int playersNumber){
        if (playersNumber < 2) {
            playersNumber = 2;
        }
        players = new Player[playersNumber];
    /**
     * Return the next player's index, based on the current player.
     *
     * @return the next player's index in `players`
     */
    private int getNextPlayer(){
        return (currentPlayer + 1) % players.length;
    }
    /**
     * <p>
     * Initializes the game by reading the game settings from the user.
     * </p>
     * <p>
     * In this method, players are first assigned a name and a character, by reading
     * the input from the user, on the console.
     * </p>
     * <p>
     * The bank distributes then 2000.- to each player.
     * </p>
     */
    public void init() {
        for (int i = 0; i < this.players.length; i++) {
            this.players[i] = new Player(
                    this.scannerUtils.readStringAndEnsureIsNotEmptyOrWhiteSpaces(),
                    this.scannerUtils.readCharAndEnsureIsNotEmptyOrWhiteSpaces()
            );
            this.bank.setAmount(bank.getAmount() - Constant.PLAYER_START_AMOUNT);
            System.out.printf("Player %s created with character %s\n\n", players[i].getName(), players[i].getSymbol());
        }
    }
    public void start(){
//        for(int i = 0; i < Constant.PLAYER_NUMBER; i++) {
//            System.out.println("Insert the player n" + (i+1) + " informations");
//            players[i] = new Player(scannerUtils.readStringAndEnsureIsNotEmptyOrWhiteSpaces(), scannerUtils.readCharAndEnsureIsNotEmptyOrWhiteSpaces());
//            bank.setAmount(bank.getAmount()-Constant.PLAYER_START_AMOUNT);
//            System.out.println("Player " + players[i].getName() + " created with character " + players[i].getSymbol() + "\n");
//        }
    }
}
