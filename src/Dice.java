import java.util.Random;

public class Dice {

    private final Random random = new Random();

    private int loBound;

    private int hiBound;

    public Dice(int loBound, int hiBound){
        this.loBound = loBound;
        this.hiBound = hiBound;
    }
}
