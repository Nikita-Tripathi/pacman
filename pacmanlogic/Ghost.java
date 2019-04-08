package pacmanlogic;

import java.util.Random;
/**
 * ghost
 */
public class Ghost extends Entity{

    private static final Random RANDOM = new Random();
    private boolean powerStatus = false; 
    private int powerPelletCount = 20;
    private int[] position;


    //Constructor
    public Ghost(int[] initialPosition){
        setPosition(initialPosition);
    }

    public int[] move(String input) {
        int rand = RANDOM.nextInt(4);
        int[][] directions = {{0, -1}, {0, 1}, {1,-1}, {1, 1}};
        // Up, Down, Left, Right
        
        return directions[rand];
    }

    public void setPosition(int[] position){
        this.position = position;
    }

    public int[] getPosition(){
        return position;
    }

    public boolean getPowerStatus(){
        return powerStatus;
    }
    public void setPowerStatus(boolean value){
        powerStatus = value;
    }

    public int getCounter(){
        return powerPelletCount;
    }
    public void decreaseCounter(){
        powerPelletCount--;
    }

    public void resetCounter(){
        if (powerPelletCount == 0){
            powerPelletCount = 50;
        }
    }
}
