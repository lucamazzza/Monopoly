package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.movable.Player;

public class GoToPrisonCell extends Cell{

    public GoToPrisonCell() {
        super("Go To Prison");
    }

    /**
     * Move the player to the prison.
     * @param player the player to apply the effect on.
     */
    @Override
    public void applyEffect(Player player) {
        player.setPosition(Constant.PRISON_POSITION);
        player.setInPrison(true);
        System.out.println("You are now in prison, to evade (like the Daltons) you need to roll for both dices the same number");
    }

    @Override
    public String getDetail() {
        return "";
    }
}
