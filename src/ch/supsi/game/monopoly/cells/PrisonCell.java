package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.movable.Player;

public class PrisonCell extends Cell{

    public PrisonCell() {
        super("Prison");
    }

    /**
     * The prison has no effect on the player who lands.
     * @param player the player to apply the effect on.
     */
    @Override
    public void applyEffect(Player player) {

    }

    @Override
    public String getDetail() {
        return "";
    }
}
