package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Game;
import ch.supsi.game.monopoly.Bank;
import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.Player;

/**
 * <p>
 * This class represents the Propriety cell of the game "Monopoly".
 * </p>
 * <p>
 * The cell has a unique name and a color.
 * </p>
 * <p>
 * The names of the different cells are defined in the {@code nameBank}
 * field (in class {@link Game}), which contains {@link ProprietyName} objects.
 * </p>
 * <p>
 * Finally, every cell has a fee, assigned randomly
 * </p>
 * <pre>
 * {@code
 * Cell cell = new ProprietyCell(ProprietyCell.nameBank[i], 10);    // create a new ProprietyCell
 * cell.getTitle();                                                 // get the name of the cell
 * cell.getDetail();                                                // get the detail of the cell
 * cell.applyEffect(player);                                        // apply the effect of the cell
 * }
 * </pre>
 *
 * @author Luca Mazza
 * @author Ivo Herceg
 * @author Andrea Masciocchi
 * @version 1.3.0
 */
public class ProprietyCell extends Cell{

    /**
     * The name of the cell.
     */
    private final ProprietyName name;

    /**
     * The rent of the cell.
     */
    private int rent;

    /**
     * The purchase price of the propriety.
     */
    private final int purchasePrice;

    /**
     * The price to build a house.
     */
    private final int housePrice;

    /**
     * Tbe price to build a hotel.
     */
    private final int hotelPrice;

    /**
     * The number of houses on the propriety.
     */
    private int numberOfHouses = 0;

    /**
     * Whether the propriety has a hotel built upon it.
     */
    private boolean hotel = false;

    /**
     * <p>
     * Instantiates a new ProprietyCell with a name and a rent.
     * </p>
     *
     * @param title the name of the cell
     * @param rent  the rent of the cell
     */
    public ProprietyCell(
            final ProprietyName title,
            final int rent,
            final int purchasePrice,
            final int housePrice,
            final int hotelPrice) {
        super(title.getName());
        this.name = title;
        if (rent < 0) {
            throw new IllegalArgumentException("The rent must be positive.");
        }
        this.rent = rent;
        if (purchasePrice < 0) {
            throw new IllegalArgumentException("The purchase price must be positive.");
        }
        this.purchasePrice = purchasePrice;
        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
    }

    /**
     * <p>
     * Applies the effect of a specific cell on a player.
     * </p>
     * <p>
     * When a player lands on a cell, he must pay the rent to the bank.
     * </p>
     *
     * @param player the player to apply the effect on.
     * @param game   the game to apply the effect on.
     */
    @Override
    public void applyEffect(final Player player, final Game game) {
        if (getOwner() != null){
            player.pay(this.rent);
            getOwner().receive(this.rent);
        }
        player.pay(this.rent);
        Bank.getInstance().deposit(this.rent);
    }

    /**
     * <p>
     * Returns the name of the cell.
     * </p>
     * <p>
     * Used to display the name of the cell on the board.
     * </p>
     *
     * @return the name of the cell
     */
    @Override
    public String getTitle() {
        return name.getName();
    }

    /**
     * <p>
     * Returns the description of the cell.
     * </p>
     * <p>
     * Used to display the detail of the cell on the board.
     * </p>
     *
     * @return the description of the cell
     */
    @Override
    public String getDetail() {
        return "Pay " + this.rent + "$";
    }

    /**
     * <p>
     * Returns the purchase price of the propriety.
     * </p>
     *
     * @return the purchase price
     */
    public double getPurchasePrice() {
        return this.purchasePrice;
    }

    /**
     * <p>
     * Returns the building prices (houses and hotel) of the propriety.
     * </p>
     *
     * @return the building prices
     */
    public String getBuildingPrice() {
        return "Prices: " + "⇧ " + this.housePrice + "$" + " □ " + this.hotelPrice + "$";
    }

    /**
     * <p>
     * Returns the color of the cell.
     * </p>
     *
     * @return the color of the propriety.
     */
    public int getColor(){
        return name.getColor();
    }

    /**
     * <p>
     * Builds a building on the propriety cell.
     * </p>
     * <p>
     * If there is 1-3 houses builds another house;
     * when the houses are 4, builds an hotel.
     * </p>
     *
     * @param currentPlayer The player building
     */
    public void addBuilding(final Player currentPlayer) {
        if (this.hotel){
            System.out.println("You can't build anymore on this propriety");
        } else if (this.numberOfHouses == Constant.MAX_NUMBER_HOUSES) {
            this.numberOfHouses = 0;
            currentPlayer.pay(this.hotelPrice);
            this.hotel = true;
            this.rent += Constant.PROPRIETY_HOTEL_RENT_INCREASE;
        }else {
            currentPlayer.pay(this.housePrice);
            this.numberOfHouses++;
            this.rent += Constant.PROPRIETY_HOUSE_RENT_INCREASE;
        }
    }

    /**
     * <p>
     * Removes all the buildings on the propriety
     * </p>
     */
    public void removeBuildings(){
        if(this.hotel){
            this.rent -= Constant.PROPRIETY_HOTEL_RENT_INCREASE;
        }
        this.rent -= this.numberOfHouses * Constant.PROPRIETY_HOUSE_RENT_INCREASE;
        this.hotel = false;
        this.numberOfHouses = 0;
    }

    /**
     * <p>
     * Show the buildings on a cell, as a detail.
     * </p>
     *
     * @return A string containing little images of houses ⌂ and hotels ⎕.
     */
    public String showBuildings() {
        if (this.hotel){
            return "□";
        }else {
            return "⇧".repeat(Math.max(0, numberOfHouses));
        }
    }
}
