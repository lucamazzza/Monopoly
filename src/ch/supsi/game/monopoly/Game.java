package ch.supsi.game.monopoly;
import ch.mazluc.util.ANSIUtility;

/**
 * <p>
 * This class represents the game "Monopoly" and
 * manages the game cycle.
 * </p>
 * <p>
 * The game contains a {@link Board}, {@link Bank}, {@link Dice},
 * a list of {@link Player}s and an instance of {@link ScannerUtils}.
 * </p>
 * <p>
 * Firstly the players are created, by assigning them a name and a symbol,
 * and then the game starts.
 * The game is then played.
 * Players play in turns until the game is over, rolling a dice and moving to a new cell.
 * When the game is over a winner is declared, and a leaderboard is displayed.
 * The game is over when a player has no more money.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Game game = new Game(2); // Instantiate a new game with 2 players
 * game.start();            // Start the game
 * }
 * </pre>
 *
 * @author Luca Mazza
 * @version 1.1.0
 */
public class Game {

    /**
     * List of players in the game
     */
    private final Player[] players;

    /**
     * The bank in the game
     */
    private final Bank bank;

    /**
     * The board of the game
     */
    private final Board board;

    /**
     * The dice in the game
     */
    private final Dice[] dices;

    /**
     * Utility class managing user interaction, through the console, with the game.
     */
    private final ScannerUtils scannerUtils;

    /**
     * Stores the index of the current player.
     */
    private int currentPlayer = 0;

    /**
     * True if the game is still running.
     */
    private boolean isGameRunning = true;

    /**
     * <p>
     * Constructor of the Game class.
     * </p>
     * <p>
     * The number of players is set to {@link Constant#PLAYER_NUMBER} by default,
     * then all the game's components are instantiated.
     * </p>
     *
     *  @param playersNumber the number of players
     */
    public Game(int playersNumber) {
        playersNumber = Math.max(playersNumber, Constant.PLAYER_NUMBER);
        this.board = new Board(Constant.BOARD_HEIGHT, Constant.BOARD_WIDTH);
        this.players = new Player[playersNumber];
        this.bank = new Bank();
        this.dices = new Dice[Constant.NUMBER_OF_DICES];
        for (int i = 0; i < Constant.NUMBER_OF_DICES; i++) {
            this.dices[i] = new Dice(Constant.DICE_MIN_VALUE, Constant.DICE_MAX_VALUE);
        }
        this.scannerUtils = new ScannerUtils();
    }

    /**
     * Sets the next player's index, based on the current player, in field
     * {@link Game#currentPlayer}.
     */
    private void getNextPlayer() {
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
                    this.scannerUtils.readNonBlankString("Player #" + (i + 1) + " name: "),
                    this.scannerUtils.readNonBlankChar("Player #" + (i + 1) + " symbol: ")
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
            ANSIUtility.printcf(
                    "Player %s (%c) created%n%n",
                    ANSIUtility.GREEN,
                    this.players[i].getName(),
                    this.players[i].getSymbol()
            );
            this.initPlayer(i);
        }
    }

    /**
     * Checks if the player name is not already taken.
     *
     * @param player the player to check
     * @return true if the name is not taken, false if is
     */
    private boolean isNotUniquePlayer(Player player, int j) {
        for (int i = 0; i < this.players.length; i++) {
            if (this.players[i] == null) {
                continue;
            }
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
                       88YbdP88 Yb   dP 88 Y88 Yb   dP 88""'  Yb   dP 88  .o   8P
                       88 YY 88  YbodP  88  Y8  YbodP  88      YbodP  88ood8  dP
                """;
        String copyright = "Copyright © 2024 - Mazza, Masciocchi, Herceg\n";
        ANSIUtility.clearScreen();
        ANSIUtility.setBold();
        ANSIUtility.printcf("%s       ", ANSIUtility.RED, text);
        ANSIUtility.printcf("%s       ", ANSIUtility.WHITE, copyright);
        ANSIUtility.setBold();
        this.scannerUtils.readKey("Press enter to start...");
        ANSIUtility.reset();
    }

    /**
     * <p>
     * Sorts the players by their balance, in order to display them accordingly
     * in the leaderboard.
     * </p>
     */
    private void sortPlayersByBalance() {
        for (int i = 0; i < players.length - 1; i++) {
            for (int j = 0; j < players.length - i - 1; j++) {
                if (this.players[j].getBalance() < this.players[j + 1].getBalance()) {
                    Player tmp = this.players[j];
                    players[j] = this.players[j + 1];
                    players[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * Prints the leaderboard on the console, with the players ordered by balance.
     */
    private void printLeaderboard() {
        ANSIUtility.clearScreen();
        ANSIUtility.setBold();
        ANSIUtility.printbcf("Leaderboard%n", ANSIUtility.RED);
        this.sortPlayersByBalance();
        for (Player player : this.players) {
            System.out.printf("%-20s: %d.–%n", player.getName(), player.getBalance());
        }
    }

    /**
     * <p>
     * Prints the UI on the console.
     * </p>
     * The UI consists of a banner, depicting the current player's name and
     * their balance, followed by the bank's balance and the board.
     */
    private void printUI() {
        System.out.println();
        ANSIUtility.printbcf(
                "%s's Turn [Balance: %d]%n",
                ANSIUtility.GREEN,
                this.players[this.currentPlayer].getName(),
                this.players[this.currentPlayer].getBalance()
        );
        ANSIUtility.printcf("%s%n", ANSIUtility.WHITE, this.bank);
        System.out.println(this.board);
    }

    /**
     * Checks if any player has lost.
     * When so the game is set over by toggling the {@link Game#isGameRunning}.
     */
    private void hasPlayerLost() {
        for (Player player : this.players) {
            if (player.getBalance() < 0) {
                this.isGameRunning = false;
                return;
            }
        }
    }

    /**
     * Moves the player to the next cell, based on the dice.
     */
    private void movePlayer() {
        this.board.getCells()[this.players[currentPlayer].getPosition()].removePlayer(this.players[this.currentPlayer]);
        this.players[this.currentPlayer].setPosition(getDicesValue());
        this.board.getCells()[this.players[this.currentPlayer].getPosition()].setPlayer(this.players[this.currentPlayer]);
    }

    /**
     * Initializes the player's position on the board,
     * setting it to the start cell.
     *
     * @param i the index of the player.
     */
    private void initPlayer(int i) {
        this.board.getCells()[this.players[i].getPosition()].removePlayer(this.players[i]);
        this.players[i].setPosition(0);
        this.board.getCells()[this.players[i].getPosition()].setPlayer(this.players[i]);
    }

    /**
     * <p>
     * Manages the behaviour of the dice roll case.
     * </p>
     * <p>
     * When called the dice is rolled and the player's position is moved.
     * Once the player is moved the cell he' onto gets checked and the relative fee
     * is applied to the player.
     * </p>
     */
    private void diceRollCase() {
        for (int i = 0; i < dices.length; i++){
            this.dices[i].roll();
            ANSIUtility.printcf("Dice " + (i+1) + " rolled: %s%n", ANSIUtility.BRIGHT_YELLOW, this.dices[i]);
        }
        this.movePlayer();
        // TODO: FIX WITH HIERARCHICAL IMPLEMENTATION
        // int tmpFee = Math.abs(this.board.getCells()[this.players[this.currentPlayer].getPosition()].getFee());
        if (this.hasPlayerPassedStart()){
            // this.players[this.currentPlayer].receive(this.board.getCells()[0].getFee());
            // this.bank.withdraw(tmpFee);
        }
        // if (this.board.getCells()[this.players[this.currentPlayer].getPosition()].getType() == CellType.TOLL) {
        //     this.players[this.currentPlayer].pay(tmpFee);
        //     this.bank.deposit(tmpFee);
        // }
        this.scannerUtils.readKey("Press enter to continue...");
        this.getNextPlayer();
    }

    /**
     * Checks if the player has passed the start cell.
     *
     * @return true if the player has passed the start cell, false otherwise.
     */
    private boolean hasPlayerPassedStart() {
        int previousPosition = this.players[this.currentPlayer].getPosition() - getDicesValue();
        return previousPosition < 0;
    }

    /**
     * Returns the sum of the dices.
     *
     * @return the sum of the dices
     */
    private int getDicesValue() {
        int dicesValue = 0;
        for (Dice dice : dices) {
            dicesValue += dice.getCurrentValue();
        }
        return dicesValue;
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
    public void start() {
        this.printStartMessage();
        this.init();
        do {
            this.printUI();
            int option = this.scannerUtils.readOption();
            switch (option) {
                case 1:
                    this.diceRollCase();
                    break;
                case 2:
                    ANSIUtility.printcf("%s", ANSIUtility.BRIGHT_YELLOW, this.players[this.currentPlayer]);
                    this.scannerUtils.readKey("Press enter to continue...");
                    break;
                case 3:
                    this.isGameRunning = false;
                    break;
                default:
                    ANSIUtility.printcf("Invalid option, try again", ANSIUtility.RED);
                    break;
            }
            this.hasPlayerLost();
        } while (this.isGameRunning);
        this.printLeaderboard();
        this.scannerUtils.readKey("Game ended, press enter to exit...");
        this.quit();
    }

    /**
     * When the game is over, this method closes the scanner.
     */
    public void quit() {
        this.scannerUtils.closeScanner();
    }
}