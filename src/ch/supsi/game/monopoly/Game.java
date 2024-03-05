package ch.supsi.game.monopoly;

/**
 *
 */
public class Game {

    /**
     * List of players in the game
     */
    private Player[] players;

    /**
     * The bank in the game
     */
    private Bank bank;

    /**
     * The board of the game
     */
    private Board board;

    /**
     * The dice in the game
     */
    private Dice dice;

    /**
     * Utility class managing user interaction, through the console, with the game.
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
    // TODO: Alternative constructors
    // TODO: Check if there is a better way to manage players number
    public Game(int playersNumber){
        if (playersNumber < 2) {
            playersNumber = 2;
        }
        this.players = new Player[playersNumber];
        this.bank = new Bank();
        this.board = new Board(5,5);
        this.dice = new Dice(1,4);
        this.dice = new Dice(Constant.DICE_MIN_VALUE,Constant.DICE_MAX_VALUE);
        this.scannerUtils = new ScannerUtils();
    }

    /**
     * Return the next player's index, based on the current player.
     *
     * @return the next player's index in `players`
     */
    private int getNextPlayer(){
        return (this.currentPlayer + 1) % this.players.length;
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
                    this.scannerUtils.readNonBlankString("gruppo1.game.Player #"+(i+1)+" name: "),
                    this.scannerUtils.readNonBlankChar("gruppo1.game.Player #"+(i+1)+" symbol: ")
            );
            this.bank.setAmount(bank.getAmount() - Constant.PLAYER_START_AMOUNT);
            System.out.printf("gruppo1.game.Player %s created with character %s\n\n", players[i].getName(), players[i].getSymbol());
        }
    }

    /**
     * Prints the start message on the console.
     */
    private void printStartMessage() {
        String text = """
                            ooo_____ooo____oooo____ooo____oo____oooo____ooooooo_____oooo____oo______oo____oo_
                            oooo___oooo__oo____oo__oooo___oo__oo____oo__oo____oo__oo____oo__oo______oo____oo_
                            oo_oo_oo_oo_oo______oo_oo_oo__oo_oo______oo_oo____oo_oo______oo_oo_______oo__oo__
                            oo__ooo__oo_oo______oo_oo__oo_oo_oo______oo_oooooo___oo______oo_oo_________oo____
                            oo_______oo__oo____oo__oo___oooo__oo____oo__oo________oo____oo__oo_________oo____
                            oo_______oo____oooo____oo____ooo____oooo____oo__________oooo____ooooooo____oo____
                """;
        System.out.println(text);
        this.scannerUtils.readKey("Press any key to start");
    }

    /**
     * <p>
     * Sorts the players by their balance, in order to display them accordingly
     * in the leaderboard.
     * </p>
     * @return the sorted players array
     */
    private Player[] getPlayersSorted() {
        Player[] playersSorted = new Player[this.players.length];
        for (int i = 0; i < this.players.length - 1; i++) {
            for (int j = 0; j < this.players.length - i - 1; j++) {
                if (this.players[j].getBalance() < this.players[j + 1].getBalance()) {
                    playersSorted[j] = this.players[j + 1];
                    playersSorted[j + 1] = this.players[j];
                }
            }
        }
        return playersSorted;
    }

    private void printLeaderboard() {

    }

    /**
     *
     */
    //TODO: Implement
    private void printUI() {
        //this.board.print();

    }

    /**
     * <p>
     * Executes the game cycle.
     * </p>
     * <p>
     * The game starts by initializing players, then enters a loop
     * on which every user gets to play (by choosing if to see their
     * balance, roll the dice or quit the game) and then the next player is chosen.
     * </p>
     * <p>
     * The game ends when a user quits or when a user runs out of money.
     * </p>
     */
    public void start(){
        this.printStartMessage();
        this.init();
        while (this.isGameRunning) {
            this.printUI();
            int option = this.scannerUtils.readOption();
            switch (option) {
                case 1:
                    int roll = this.dice.roll();
                    // TODO: Implement further
                    break;
                case 2:
                    this.players[this.currentPlayer].print();
                    break;
                default:
                    System.out.println("Invalid option, try again");
                    continue;
            }
            this.currentPlayer = this.getNextPlayer();
        }
    }

    /**
     * When the game is over, this method closes the scanner.
     */
    public void quit() {
        this.scannerUtils.closeScanner();
    }
}
