import java.util.Random;
/**
 * ghost
 */
public class Ghost {

    private static final Random RANDOM = new Random();

    public int[] getRandomMove() {
        int rand = RANDOM.nextInt(4);
        int[][] directions = {{0, -1}, {0, 1}, {1,-1}, {1, 1}};
        // Up, Down, Left, Right
        
        return directions[rand];
    }
}
