package ch.supsi.game.monopoly;

import ch.mazluc.util.ANSIUtility;
import ch.supsi.game.monopoly.cells.Cell;
import ch.supsi.game.monopoly.cells.ProprietyCell;
import ch.supsi.game.monopoly.movable.Player;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static ch.supsi.game.monopoly.Bank.deposit;

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
 * @version 1.3.0
 */
public class Game implements PropertyChangeListener {

    /**
     * List of players in the game
     */
    private final Player[] players;

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
    private int indexOfCurrentPlayer = 0;

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
        this.board = new Board();
        this.players = new Player[playersNumber];
        this.dices = new Dice[Constant.NUMBER_OF_DICES];
        for (int i = 0; i < Constant.NUMBER_OF_DICES; i++) {
            this.dices[i] = new Dice(Constant.DICE_MIN_VALUE, Constant.DICE_MAX_VALUE);
        }
        this.scannerUtils = new ScannerUtils();
    }

    /**
     * Sets the next player's index, based on the current player, in field
     * {@link Game#indexOfCurrentPlayer}.
     */
    private void getNextPlayer() {
        this.indexOfCurrentPlayer = (this.indexOfCurrentPlayer + 1) % this.players.length;
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
            Bank.withdraw(Constant.PLAYER_START_AMOUNT);
            this.players[i].receive(Constant.PLAYER_START_AMOUNT);
            ANSIUtility.printcf(
                    "Player %s (%c) created%n%n",
                    ANSIUtility.GREEN,
                    this.players[i].getName(),
                    this.players[i].getSymbol()
            );
            this.initPlayer(i);
        }
        for (Player player : this.players) {
            player.addPropertyChangeListener(this);
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
            System.out.printf("%-20s: %.2f%n", player.getName(), player.getBalance());
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
                "%s's Turn [Balance: %.2f]%n",
                ANSIUtility.GREEN,
                this.players[this.indexOfCurrentPlayer].getName(),
                this.players[this.indexOfCurrentPlayer].getBalance()
        );
        ANSIUtility.printcf("%s%n", ANSIUtility.WHITE, Bank.getBalance());
        System.out.println(this.board);
    }

    /**
     * Checks if any player has lost.
     */
    private boolean hasPlayerLost(int index) {
        return this.players[index].getBalance() <= 0;
    }

    /**
     * Initializes the player's position on the board,
     * setting it to the start cell.
     *
     * @param i the index of the player.
     */
    private void initPlayer(int i) {
        this.board.getCells()[this.players[i].getPosition()].removePlayer(this.players[i]);
        this.players[i].move(0);
        this.board.getCells()[this.players[i].getPosition()].setPlayer(this.players[i]);
    }

    /**
     * <p>
     * Manages the behaviour of the dice roll case.
     * </p>
     * <p>
     * When called the dice is rolled and the player's position is moved.
     * Once the player is moved the cell he's onto gets checked and the relative fee
     * is applied to the player.
     * </p>
     */
    private void diceRollCase() {
        Player currentPlayer = this.players[indexOfCurrentPlayer];
        for (int i = 0; i < dices.length; i++){
            this.dices[i].roll();
            ANSIUtility.printcf("Dice " + (i + 1) + " rolled: %s%n", ANSIUtility.BRIGHT_YELLOW, this.dices[i]);
        }
        if(currentPlayer.isInPrison()){
            boolean willBeInPrison = false;
            if(currentPlayer.getTimesTriedEvading()==3){
                System.out.println("To get out of prison you need to pay " + Constant.PRISON_TAX + "CHF");
                this.scannerUtils.readKey("Press enter to continue...");
                currentPlayer.pay(Constant.PRISON_TAX);
                deposit(Constant.PRISON_TAX);
                willBeInPrison = false;
            }else{
                for(int i = 0; i < this.dices.length-1; i++){
                    if(this.dices[i] != this.dices[i+1]){
                        willBeInPrison = true;
                        break;
                    }
                }
                currentPlayer.setTimesTriedEvading(currentPlayer.getTimesTriedEvading() + 1);
            }
            currentPlayer.setInPrison(willBeInPrison);
        }
        if(!currentPlayer.isInPrison()){
            currentPlayer.move(this.getDicesValue());
            if (this.hasPlayerPassedStart()){
                this.board.getCell(Constant.START_POSITION).applyEffect(currentPlayer);
            }
            System.out.println(board);

            // Land on a propriety owned by the bank
            if (this.board.getCell(currentPlayer.getPosition()) instanceof ProprietyCell pc &&
                    this.board.getCell(currentPlayer.getPosition()).getOwner() == null){
                System.out.printf("Buy %s for %.2f$%n",
                        this.board.getCell(currentPlayer.getPosition()).getTitle(),
                        pc.getPurchasePrice());
                if (this.scannerUtils.readBoolean()){
                    currentPlayer.pay(pc.getPurchasePrice());
                    Bank.deposit(pc.getPurchasePrice());
                    pc.setOwner(currentPlayer);
                    currentPlayer.addColor(pc);
                }
            }
        }

        // Player does not pay itsself
        if (!(currentPlayer.equals(board.getCell(currentPlayer.getPosition()).getOwner()))) {
            this.board.getCell(currentPlayer.getPosition()).applyEffect(currentPlayer);
        }

        //per costruire
        if (currentPlayer.canBuild()){
            System.out.println("Would you want to build ?");
            if (scannerUtils.readBoolean()){
                currentPlayer.showBuildOptions(currentPlayer.getBuildOptions(board));
                int choice = scannerUtils.readIntInRange(
                        1,currentPlayer.getBuildOptions(board).length,
                        "Insert number between 1-" + currentPlayer.getBuildOptions(board).length + ": "
                );
                if(currentPlayer.getBuildOptions(board)[choice-1] instanceof ProprietyCell pc) {
                    pc.addBuilding(currentPlayer);
                }
            }
        }
        this.scannerUtils.readKey("Press enter to continue...");
        this.getNextPlayer();
    }

    /**
     * Checks if the player has passed the start cell.
     *
     * @return true if the player has passed the start cell, false otherwise.
     */
    private boolean hasPlayerPassedStart() {
        int previousPosition = this.players[this.indexOfCurrentPlayer].getPosition() - getDicesValue();
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
     * Checks if the game is over.
     * When so sets the {@link Game#isGameRunning} to false.
     */
    private void isGameOver() {
        int counter = 0;
        for (Player player : players) {
            if (player.getBalance() < 0) {
                counter++;
            }
        }
        if (counter == players.length - 1) {
            this.isGameRunning = false;
        }
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
            while (this.hasPlayerLost(this.indexOfCurrentPlayer)) {
                this.playerGameOver(this.indexOfCurrentPlayer);
                this.getNextPlayer();
            }
            this.printUI();
            int option = this.scannerUtils.readOption();
            switch (option) {
                case 1:
                    this.diceRollCase();
                    break;
                case 2:
                    ANSIUtility.printcf("%s", ANSIUtility.BRIGHT_YELLOW, this.players[this.indexOfCurrentPlayer]);
                    this.scannerUtils.readKey("Press enter to continue...");
                    break;
                case 3:
                    System.out.println("\n"+getProprietiesOfPlayer(this.players[this.indexOfCurrentPlayer]));
                    this.scannerUtils.readKey("Press enter to continue...");
                    break;
                case 4:
                    this.isGameRunning = false;
                    break;
                default:
                    ANSIUtility.printcf("Invalid option, try again", ANSIUtility.RED);
                    break;
            }
            this.isGameOver();
        } while (this.isGameRunning);
        this.printLeaderboard();
        this.scannerUtils.readKey("Game ended, press enter to exit...");
        this.quit();
    }

    /**
     * Resets all the propriety of the player that lost.
     * @param playerIndex index of the player that lost
     */
    private void playerGameOver(int playerIndex) {
        Player player = this.players[playerIndex];
        Cell[] cells = this.board.getCells();
        for(int i = 0; i < cells.length; i++){
            if(cells[i] instanceof ProprietyCell pc){
                pc.removeBuildings();
                pc.setOwner(null);
            }
        }
    }

    /**
     *
     * @param player
     * @return the list of the propriety owned by the player
     */
    private String getProprietiesOfPlayer(Player player){
        StringBuilder sb = new StringBuilder();
        Cell[] cells = this.board.getCells();
        int tmp = 0;
        for(int i = 0; i < cells.length; i++){
            if(cells[i] instanceof ProprietyCell pc){
                if(pc.getOwner()!=null && pc.getOwner().equals(player)){
                    sb.append((++tmp) + " " + pc.getTitle() + "\n");
                }
            }
        }
        return sb.toString();
    }

    /**
     * When the game is over, this method closes the scanner.
     */
    public void quit() {
        this.scannerUtils.closeScanner();
    }

    /**
     * The behaviour of this method is triggered when a {@link PropertyChangeEvent}
     * with the name `"position"` is fired.
     *
     * <p>
     * Checks if the {@link PropertyChangeEvent} matches the name
     * and moves the player in the board using {@code oldValue} and
     * {@code newValue}.
     * </p>
     *
     * @param evt a PropertyChangeEvent object describing the event source
     *          and the property that has changed
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!evt.getPropertyName().equals("position")) {
            return;
        }
        if (evt.getOldValue() instanceof Integer && evt.getNewValue() instanceof Integer) {
            int oldPosition = (int) evt.getOldValue();
            int newPosition = (int) evt.getNewValue();
            this.board.getCell(oldPosition).removePlayer(this.players[this.indexOfCurrentPlayer]);
            this.board.getCell(newPosition).setPlayer(this.players[this.indexOfCurrentPlayer]);
        }
    }
}