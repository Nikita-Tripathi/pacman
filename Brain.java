import java.util.Arrays;

/**
 * brain
 */
public class Brain {
    public String[][] initialArray = {
        {" ", " ", "W", " ", " "},
        {" ", " ", "W", " ", " "},
        {" ", " ", "W", " ", " "},
        {" ", " ", "W", " ", " "},
        {" ", " ", " ", " ", " "}
    };
    public int width = initialArray.length;
    public int height = initialArray[0].length;
    public int[] playerPosition = {0, 0};
    public int[] ghostPosition = {width-1, height-1};
    public Pacman player = new Pacman();
    public Ghost ghost1 = new Ghost();
    public World gameWorld = new World(initialArray, playerPosition, ghostPosition);
    public int lives = 3;
    
    // score
    public int score = 0;
    

    // checks for valid moves 
    public void validateMove(int[] position, int[] move) {
        int toCheck = position[move[0]] + move[1];
        int ceiling;
        int w = position[0];
        int h = position[1];
        if (move[0] == 0){
            ceiling = width;
            w += move[1];
        } else {
            ceiling = height;
            h += move[1];
        }

        if (toCheck < 0 || toCheck >= ceiling || gameWorld.getCoinArr()[w][h] == "W"){
            // do nothing
            System.out.println("Couldn't move, Did nothing");
        } else {
            move(position, move);
        }
        
    }
    
    // updates position of movable object
    public  void move(int[] position, int[] move) {
        String[][] newArr = gameWorld.copyArr(gameWorld.getMovingArr());
        String character = newArr[position[0]][position[1]];
        
        newArr[position[0]][position[1]] = " ";
        position[move[0]] = position[move[0]] + move[1];
        
        newArr[position[0]][position[1]] = character;
        
        gameWorld.setMovingArr(newArr);
        // displayBoard();
    }


    // check if pacman got coin/powerup
    public void checkCoins() {
        String[][] coins = gameWorld.getCoinArr();
        if (coins[playerPosition[0]][playerPosition[1]] != " "){
            if (coins[playerPosition[0]][playerPosition[1]] == "C") {
                score += 100;
            } else if (coins[playerPosition[0]][playerPosition[1]] == "O"){
                score += 250;
            }
            String[][] newCoins = gameWorld.copyArr(coins);
            newCoins[playerPosition[0]][playerPosition[1]] = " ";
            gameWorld.setCoinArr(newCoins);
            System.out.println("Collected!");
        }
    }
 
    // check if ghost is exactly 1 or 0 moves away from pacman
    // if the vertical distance between ghost an player is 1 and horisontal
    // is 0 (or vice-versa) that means they are exactly 1 move apart, so game over
    // true means you lost false means still going
    public boolean checkGameOver() {
        int wDist = Math.abs(playerPosition[0] - ghostPosition[0]);
        int hDist = Math.abs(playerPosition[1] - ghostPosition[1]);

        return (wDist == 1 & hDist == 0) || (wDist == 0 & hDist == 1) || (wDist == 0 & hDist == 0);
    }
    
    //returns the thing (very specific i know)
    public String[][] getDisplayArr() {
        String[][] moving = gameWorld.getMovingArr();
        String[][] coins = gameWorld.getCoinArr();
        String[][] board = new String[moving.length][moving[0].length];
        
        for(int i =0; i< moving.length;i++){
            for (int j =0;j < moving[0].length; j++) {
                if (moving[i][j] != " ") {
                    board[i][j] = moving[i][j];
                } else if (coins[i][j] != " ") {
                    board[i][j] = coins[i][j];
                } else {
                    board[i][j] = " ";
                }
            }
        }
        return board;
    }    

    //For displaying arrays in  matrix form (good for debugging)
	public void display(String display[][]){
		for(int i =0; i< display.length;i++){
            System.out.print("| ");
				for (int j = 0; j < display[0].length; j++){
					System.out.print(display[i][j]+" ");
                }
            System.out.print("|");
            System.out.println();
        }
		System.out.println();        
    }
    
    // for displaying the actual game board
    public void displayBoard() {
        String[][] toDisplay = getDisplayArr();

        for(int i =0; i< toDisplay.length;i++){
            System.out.print("|");
				for (int j =0;j < toDisplay[0].length; j++){
                    if (toDisplay[i][j] != " ") {
                        System.out.print(toDisplay[i][j] + " ");
                    } else {
                        System.out.print("  ");
                    }
                }
            System.out.print("|");
            System.out.println();
        }
		System.out.println();

    }


    /* public void main(String[] args) {
        // Gameloop
        while (score < 250 && !checkGameOver()) {
            displayBoard();
            validateMove(playerPosition, player.move("s"));
            validateMove(ghostPosition, ghost1.getRandomMove());
            
            checkCoins();
            System.out.println("Curent Score: " + score);
            System.out.println();
        }
        
        System.out.println("Curent Score: " + score);
        displayBoard();

        // Display Win/Lose message
    	if (checkGameOver()) {
            System.out.println("You Lost!");
        } else {
	        System.out.println("You Won!");
        }
    } */
}
