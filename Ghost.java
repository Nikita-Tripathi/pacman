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
	
	public int[] getSpawnPosition(String[][] movingArr){
		int[] ghostPosition = new int[2];
		ghostPosition[0] = 9;
		ghostPosition[1] = 9;
		
	  for(int i = 0; i < 9; i++){
		  for(int j = 0; j < 9; j++){
			  if (movingArr[i][j] == "s"){
				  ghostPosition[0] = i;
				  ghostPosition[1] = j;
				  break;
			  }
		  }
	  }
	  
	  return ghostPosition;
	}
				  
}
