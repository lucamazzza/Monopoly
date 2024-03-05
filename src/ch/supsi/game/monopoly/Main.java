package ch.supsi.game.monopoly;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(Constant.PLAYER_NUMBER);
        game.start();
    }
}
