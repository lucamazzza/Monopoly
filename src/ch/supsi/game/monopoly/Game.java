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
    private void getNextPlayer(){
        this.currentPlayer = (this.currentPlayer + 1) % this.players.length;
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
            Player tmp = new Player(
                    this.scannerUtils.readNonBlankString("Player #"+(i+1)+" name: "),
                    this.scannerUtils.readNonBlankChar("Player #"+(i+1)+" symbol: ")
            );
            if (i > 0 && this.isNotUniquePlayer(tmp, i)) {
                ANSIUtility.setForegroundColor(ANSIUtility.RED);
                System.out.println("Player name already taken, please choose another one.\n");
                ANSIUtility.reset();
                i--;
                continue;
            }
            this.players[i] = tmp;
            this.bank.withdraw(Constant.PLAYER_START_AMOUNT);
            this.players[i].receive(Constant.PLAYER_START_AMOUNT);
            ANSIUtility.printcf("Player %s (%c) created%n%n", ANSIUtility.GREEN , players[i].getName(), players[i].getSymbol());
        }
    }

    /**
     *
     * @param player
     * @return
     */
    private boolean isNotUniquePlayer(Player player, int j) {
        for (int i = 0; i < this.players.length; i++) {
            if (i != j && this.players[i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints the start message on the console.
     */
    private void printStartMessage() {
        String text = """
                        8b    d8  dP"Yb  88b 88  dP"Yb  88""Yb  dP"Yb  88     Yb  dP
                        88b  d88 dP   Yb 88Yb88 dP   Yb 88__dP dP   Yb 88      YbdP
                        88YbdP88 Yb   dP 88 Y88 Yb   dP 88""\"  Yb   dP 88  .o   8P
                        88 YY 88  YbodP  88  Y8  YbodP  88      YbodP  88ood8  dP
                 """;
        String copyright = "Copyright © 2024 - Mazza, Masciocchi, Herceg\n";
        ANSIUtility.clearScreen();
        ANSIUtility.setBold();
        ANSIUtility.printcf("%s", ANSIUtility.RED, text);
        System.out.print("       ");
        ANSIUtility.printcf("%s", ANSIUtility.WHITE, copyright);
        ANSIUtility.setBold();
        System.out.print("       ");
        this.scannerUtils.readKey("Press enter to start...");
        ANSIUtility.reset();
    }

    /**
     * <p>
     * Sorts the players by their balance, in order to display them accordingly
     * in the leaderboard.
     * </p>
     * @return the sorted players array
     */
    private void sortPlayersByBalance() {
        for (int i = 0; i < players.length - 1; i++) {
            for (int j = 0; j < players.length - i - 1; j++) {
                if (this.players[j].getBalance() < this.players[j + 1].getBalance()) {
                    players[j] = this.players[j + 1];
                    players[j + 1] = this.players[j];
                }
            }
        }
    }

    private void printLeaderboard() {
        ANSIUtility.clearScreen();
        ANSIUtility.setBold();
        ANSIUtility.printbcf("Leaderboard%n", ANSIUtility.RED);
        this.sortPlayersByBalance();
        for (int i = 0; i < this.players.length; i++) {
            System.out.printf("%-20s: %d.–%n", this.players[i].getName(), this.players[i].getBalance());
        }
    }

    /**
     *
     */
    //TODO: Implement
    private void printUI() {
        System.out.println();
        ANSIUtility.printbcf("%s's Turn%n", ANSIUtility.GREEN, this.players[this.currentPlayer].getName());
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
                    ANSIUtility.printcf("Rolled: %s%n", ANSIUtility.BRIGHT_YELLOW, this.dice);
                    // TODO: Implement further
                    break;
                case 2:
                    this.players[this.currentPlayer].print();
                    ANSIUtility.printcf("%s", ANSIUtility.BRIGHT_YELLOW, this.players[this.currentPlayer]);
                    break;
                default:
                    ANSIUtility.printcf("Invalid option, try again", ANSIUtility.RED);
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
