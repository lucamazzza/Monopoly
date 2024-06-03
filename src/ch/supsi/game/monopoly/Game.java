package ch.supsi.game.monopoly;

import ch.mazluc.util.ANSIUtility;
import ch.supsi.game.monopoly.cards.Card;
import ch.supsi.game.monopoly.cards.Deck;
import ch.supsi.game.monopoly.cells.Cell;
import ch.supsi.game.monopoly.cells.ProprietyCell;
import ch.supsi.game.monopoly.exception.EmptyDeckException;
import ch.supsi.game.monopoly.exception.IllegalCardException;
import ch.supsi.game.monopoly.exception.NoCellFoundException;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
 * @version 1.4.0
 */
public class Game implements PropertyChangeListener {

    /**
     * List of players in the game
     */
    private final Player[] players;

    /**
     * Bank instance of the game.
     */
    private final Bank bank;

    /**
     * The board of the game.
     */
    private final Board board;

    /**
     * The dice in the game.
     */
    private final Dice[] dices;

    /**
     * List of chance cards.
     */
    private final Deck chanceCards;

    /**
     * List of unexpected cards.
     */
    private final Deck unexpectedCards;

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
     * @param playersNumber the number of players
     */
    public Game(final int playersNumber) {
        this.board = new Board();
        this.players = new Player[Math.max(playersNumber, Constant.PLAYER_NUMBER)];
        this.dices = new Dice[Constant.NUMBER_OF_DICES];
        this.bank = Bank.getInstance();
        for (int i = 0; i < Constant.NUMBER_OF_DICES; i++) {
            this.dices[i] = new Dice(Constant.DICE_MIN_VALUE, Constant.DICE_MAX_VALUE);
        }
        this.scannerUtils = new ScannerUtils();
        this.chanceCards = new Deck("Probabilita.txt");
        this.unexpectedCards = new Deck("Imprevisti.txt");
    }

    /**
     * <p>
     * Sets the next player's index, based on the current player, in field
     * {@link Game#indexOfCurrentPlayer}.
     * </p>
     */
    private void getNextPlayer() {
        this.indexOfCurrentPlayer = (++this.indexOfCurrentPlayer) % this.players.length;
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
        int i = 0;
        while (i < this.players.length) {
            final Player tmp = new Player(
                    this.scannerUtils.readNonBlankString("Player #" + (i + 1) + " name: "),
                    this.scannerUtils.readNonBlankChar("Player #" + (i + 1) + " symbol: ")
            );
            if (i > 0 && this.isNotUniquePlayer(tmp, i)) {
                ANSIUtility.printbcf(Constant.PLAYER_ALREADY_EXISTING, ANSIUtility.RED, tmp.getName());
                continue;
            }
            this.players[i] = tmp;
            this.bank.withdraw(Constant.PLAYER_START_AMOUNT);
            this.players[i].receive(Constant.PLAYER_START_AMOUNT);
            ANSIUtility.printcf(
                    Constant.PLAYER_CREATED,
                    ANSIUtility.WHITE,
                    this.players[i].getName(),
                    this.players[i].getSymbol()
            );
            this.initPlayer(i);
            i++;
        }
        for (Player player : this.players) {
            player.addPropertyChangeListener(this);
        }

        try {
            this.chanceCards.shuffle();
            this.unexpectedCards.shuffle();
        } catch (EmptyDeckException e) {
            ANSIUtility.printbcf("%s", ANSIUtility.RED, e.getMessage());
        }
    }

    /**
     * <p>
     * Checks if the player name is not already taken.
     * </p>
     *
     * @param player the player to check
     * @return true if the name is not taken, false if is
     */
    private boolean isNotUniquePlayer(final Player player, final int j) {
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
     * <p>
     * Prints the start message on the console.
     * </p>
     */
    private void printStartMessage() {
        ANSIUtility.clearScreen();
        ANSIUtility.setBold();
        ANSIUtility.printcf("%s       ", ANSIUtility.RED, Constant.TITLE);
        ANSIUtility.printcf("%s%n       ", ANSIUtility.WHITE, Constant.COPYRIGHT);
        this.scannerUtils.readKey("Press enter to start...");
        ANSIUtility.resetf();
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
     * <p>
     * Prints the leaderboard on the console, with the players ordered by balance.
     * </p>
     */
    private void printLeaderboard() {
        ANSIUtility.clearScreen();
        ANSIUtility.setBold();
        ANSIUtility.printbcf("Leaderboard%n", ANSIUtility.RED);
        this.sortPlayersByBalance();
        for (Player player : this.players) {
            ANSIUtility.printcf("%-20s: %.2f%n", ANSIUtility.BRIGHT_WHITE, player.getName(), player.getBalance());
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
                Constant.TURN_INFORMATION,
                ANSIUtility.GREEN,
                this.players[this.indexOfCurrentPlayer].getName(),
                this.players[this.indexOfCurrentPlayer].getBalance()
        );
        ANSIUtility.printcf("%s%n", ANSIUtility.WHITE, this.bank.getBalance());
        System.out.println(this.board);
    }

    /**
     * <p>
     * Checks if any player has lost.
     * </p>
     */
    private boolean hasPlayerLost(final int index) {
        return this.players[index].getBalance() <= 0;
    }

    /**
     * <p>
     * Initializes the player's position on the board,
     * setting it to the start cell.
     * </p>
     *
     * @param i the index of the player.
     */
    private void initPlayer(final int i) {
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
        final Player currentPlayer = this.players[this.indexOfCurrentPlayer];
        this.rollDices();
        this.checkIfEvaderIsCaught(currentPlayer);
        if (currentPlayer.isInPrison()) this.playerInPrisonCase(currentPlayer);
        if (!currentPlayer.isInPrison())this.playerNotInPrisonCase(currentPlayer);
        this.board.getCell(currentPlayer.getPosition()).applyEffect(currentPlayer, this);
        this.playerBuildingCase(currentPlayer);
        this.scannerUtils.readKey(Constant.PRESS_ENTER_TO_CONTINUE);
        this.getNextPlayer();
    }

    /**
     * <p>
     * Rolls the dices and prints them on the console.
     * </p>
     */
    private void rollDices() {
        for (int i = 0; i < this.dices.length; i++) {
            this.dices[i].roll();
            ANSIUtility.printcf(Constant.DICE_ROLL, ANSIUtility.BRIGHT_YELLOW, (i + 1), this.dices[i]);
        }
    }

    /**
     * <p>
     * Check if the current player is a tax evader and
     * check their chance of getting caught.
     * </p>
     * <p>
     * If they are caught they are sent to prison and their debt is paid.
     * </p>
     *
     * @param currentPlayer the current player
     */
    private void checkIfEvaderIsCaught(Player currentPlayer) {
        if(currentPlayer.isEvader()) {
            if (this.getDicesValue() % 3 == 0) {
                currentPlayer.setInPrison(true);
                this.board.getCell(
                        Constant.GO_TO_PRISON_POSITION).applyEffect(currentPlayer, this);
                ANSIUtility.printcf(Constant.UNLUCKY_EVADER_MESSAGE, ANSIUtility.RED);
                this.scannerUtils.readKey(Constant.PRESS_ENTER_TO_CONTINUE);
                final double amount = currentPlayer.getAmountEvaded() * Constant.DEBT_INTEREST_RATE;
                currentPlayer.pay(amount);
                Bank.getInstance().deposit(amount);
                ANSIUtility.printcf("Paid %.2f to repair your debt%n", ANSIUtility.BRIGHT_YELLOW,amount);
                currentPlayer.setEvader(false);
                currentPlayer.setAmountEvaded(0);
            }
        }
    }

    /**
     * <p>
     * Game case, in which the player is in prison.
     * </p>
     * <p>
     * When the player is in prison, they have three turns to roll the dice
     * and get two identical values in order to escape.
     * </p>
     * <p>
     * If the player does not get two identical values in three turns,
     * he has to pay 50$.
     * </p>
     *
     * @param currentPlayer the player
     */
    private void playerInPrisonCase(final Player currentPlayer) {
        boolean willBeInPrison = false;
        if (currentPlayer.getTimesTriedEvading() == Constant.MAX_PRISON_EVASIONS) {
            ANSIUtility.printcf(Constant.PRISON_GETOUT_MESSAGE, ANSIUtility.BRIGHT_WHITE, Constant.PRISON_TAX);
            this.scannerUtils.readKey(Constant.PRESS_ENTER_TO_CONTINUE);
            currentPlayer.pay(Constant.PRISON_TAX);
            this.bank.deposit(Constant.PRISON_TAX);
        } else {
            for (int i = 0; i < this.dices.length - 1; i++) {
                if (this.dices[i].getCurrentValue() != this.dices[i + 1].getCurrentValue()) {
                    willBeInPrison = true;
                    break;
                }
            }
            currentPlayer.setTimesTriedEvading(currentPlayer.getTimesTriedEvading() + 1);
        }
        currentPlayer.setInPrison(willBeInPrison);
    }

    /**
     * <p>
     * Game case, in which the player is not in prison.
     * </p>
     * <p>
     * When the player is not in prison, he can move around the board freely
     * and the game develops normally.
     * </p>
     *
     * @param currentPlayer the player
     */
    private void playerNotInPrisonCase(final Player currentPlayer) {
        currentPlayer.move(this.getDicesValue());
        if (this.hasPlayerPassedStart())
            this.board.getCell(Constant.START_POSITION).applyEffect(currentPlayer, this);
        System.out.println(this.board);
        if (this.board.getCell(currentPlayer.getPosition()) instanceof ProprietyCell pc &&
                pc.getOwner() == null && currentPlayer.getBalance() > pc.getPurchasePrice()) {
            System.out.printf("Buy %s for %.2f$%n",
                    this.board.getCell(currentPlayer.getPosition()).getTitle(),
                    pc.getPurchasePrice());
            if (this.scannerUtils.readBoolean()) {
                currentPlayer.pay(pc.getPurchasePrice());
                this.bank.deposit(pc.getPurchasePrice());
                pc.setOwner(currentPlayer);
                currentPlayer.addColor(pc);
                ANSIUtility.printcf("You have bought %s%n", ANSIUtility.GREEN, pc.getTitle());
            }
        }
    }

    /**
     * <p>
     * Game case, in which the player builds a building on a cell.
     * </p>
     * <p>
     * If the player can build, a prompt is shown asking if he wants to do so.
     * The prompt shows all proprieties the player can build upon.
     * </p>
     *
     * @param currentPlayer the player
     */
    private void playerBuildingCase(final Player currentPlayer) {
        if (currentPlayer.canBuild()) {
            System.out.println("Would you want to build ?");
            if (scannerUtils.readBoolean()) {
                currentPlayer.showBuildOptions(currentPlayer.getBuildOptions(board));
                int choice = scannerUtils.readIntInRange(
                        1, currentPlayer.getBuildOptions(board).length,
                        "Insert number between 1-" + currentPlayer.getBuildOptions(board).length + ": "
                );
                if (currentPlayer.getBuildOptions(board)[choice - 1] instanceof ProprietyCell pc) {
                    pc.addBuilding(currentPlayer);
                }
            }
        }
    }

    /**
     * <p>
     * Checks if the player has passed the start cell.
     * </p>
     *
     * @return true if the player has passed the start cell, false otherwise.
     */
    public boolean hasPlayerPassedStart() {
        return this.players[this.indexOfCurrentPlayer].getPosition() - getDicesValue() < 0;
    }

    /**
     * <p>
     * Checks if the player has passed the start as an effect of a card.
     * </p>
     *
     * @param previousPosition the previous position of the player
     * @return true if the player has passed the start with cards, false otherwise
     */
    public boolean hasPlayerPassedStartWithCards(final int previousPosition) {
        return this.players[this.indexOfCurrentPlayer].getPosition() - previousPosition > 0;
    }

    /**
     * <p>
     * Returns the sum of the dices.
     * </p>
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
     * Checks if the game is over.
     * When so sets the {@link Game#isGameRunning} to false.
     * </p>
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
                    this.scannerUtils.readKey(Constant.PRESS_ENTER_TO_CONTINUE);
                    break;
                case 3:
                    System.out.println("\n" + getProprietiesOfPlayer(this.players[this.indexOfCurrentPlayer]));
                    this.scannerUtils.readKey(Constant.PRESS_ENTER_TO_CONTINUE);
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
     * <p>
     * Resets all the propriety of the player that lost.
     * </p>
     * @param playerIndex index of the player that lost
     */
    private void playerGameOver(final int playerIndex) {
        if (playerIndex >= this.players.length) return;
        final Player player = this.players[playerIndex];
        this.board.stripAllProprietiesOfPlayer(player);
        this.printUI();
    }

    /**
     * <p>
     * Returns, as a String, all proprieties owned by the player.
     * </p>
     *
     * @param player the player
     * @return the list of the propriety owned by the player
     */
    private String getProprietiesOfPlayer(final Player player) {
        final StringBuilder sb = new StringBuilder();
        final Cell[] cells = this.board.getCells();
        int tmp = 0;
        for (Cell cell : cells) {
            if (cell instanceof ProprietyCell pc && (pc.getOwner() != null && pc.getOwner().equals(player))) {
                sb.append((++tmp)).append(" ").append(pc.getTitle()).append("\n");

            }
        }
        return sb.toString();
    }

    /**
     * <p>
     * Gets the propriety index on the table given the name of the propriety.
     * </p>
     * @param name the name of the cell
     */
    public int getCellIndexByName(final String name) throws NoCellFoundException {
        Cell c;
        for (int i = 0; i < this.board.getCells().length; i++) {
            c = this.board.getCells()[i];
            if (ANSIUtility.decolorize(c.getTitle()).toLowerCase().equals(name)) {
                return i;
            }
        }
        throw new NoCellFoundException("Cell " + name + " not found");
    }

    /**
     * <p>
     * Picks a card from the chance deck.
     * </p>
     * <p>
     * The deck is shuffled before picking the card.
     * </p>
     *
     * @return the card
     */
    public Card pickCardFromChanceDeck() {
        try {
            this.chanceCards.shuffle();
            final Card card = this.chanceCards.pick();
            this.chanceCards.putBack(card);
            return card;
        } catch (EmptyDeckException | IllegalCardException e) { return null; }
    }

    /**
     * <p>
     * Picks a card from the unexpected deck.
     * </p>
     * <p>
     * The deck is shuffled before picking the card.
     * </p>
     *
     * @return the card
     */
    public Card pickCardFromUnexpectedDeck() {
        try {
            this.unexpectedCards.shuffle();
            final Card card = this.unexpectedCards.pick();
            this.unexpectedCards.putBack(card);
            return card;
        } catch (EmptyDeckException | IllegalCardException e) { return null; }
    }

    /**
     * <p>
     * When the game is over, this method removes all {@link PropertyChangeListener}s
     * and closes the scanner.
     * </p>
     */
    public void quit() {
        for (Player player : this.players) player.removePropertyChangeListener(this);
        this.scannerUtils.closeScanner();
    }

    /**
     * <p>
     * The behaviour of this method is triggered when a {@link PropertyChangeEvent}
     * with the name `"position"` is fired.
     * </p>
     * <p>
     * Checks if the {@link PropertyChangeEvent} matches the name
     * and moves the player in the board using {@code oldValue} and
     * {@code newValue}.
     * </p>
     *
     * @param evt a PropertyChangeEvent object describing the event source
     *            and the property that has changed
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (!evt.getPropertyName().equals("position")) {
            return;
        }
        if (evt.getOldValue() instanceof Integer && evt.getNewValue() instanceof Integer) {
            final int oldPosition = (int) evt.getOldValue();
            final int newPosition = (int) evt.getNewValue();
            this.board.getCell(oldPosition).removePlayer(this.players[this.indexOfCurrentPlayer]);
            this.board.getCell(newPosition).setPlayer(this.players[this.indexOfCurrentPlayer]);
        }
    }
}