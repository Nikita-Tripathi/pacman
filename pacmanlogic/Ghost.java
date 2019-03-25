package pacmanlogic;

import java.util.Random;
/**
 * ghost
 */
public class Ghost extends Entity{

    private static final Random RANDOM = new Random();

    public int[] move(String input) {
        int rand = RANDOM.nextInt(4);
        int[][] directions = {{0, -1}, {0, 1}, {1,-1}, {1, 1}};
        // Up, Down, Left, Right
        
        return directions[rand];
    }
}