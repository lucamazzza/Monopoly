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
                    this.scannerUtils.readNonBlankString("Player #"+(i+1)+" name: "),
                    this.scannerUtils.readNonBlankChar("Player #"+(i+1)+" symbol: ")
            );
            this.bank.setAmount(bank.getAmount() - Constant.PLAYER_START_AMOUNT);
            System.out.printf("Player %s created with character %s\n\n", players[i].getName(), players[i].getSymbol());
        }
    }

    /**
     * <p>
     * Prints the start message on the console.
     * </p>
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
    }
}
