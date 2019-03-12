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
    public boolean powerStatus = false;

    // Subject to change
    public int timer = 0;
    
    // when should the powerup stop
    public int stopPowerup;

    /**
     * @return the timer
     */
    public int getTimer() {
        return timer;
    }


    public void incrementTimer() {
        this.timer++;
    }

    public void handlePowerUp(boolean pickedUp) {
        if (pickedUp) {
            // powerup expires in 10 sec/moves
            this.stopPowerup = getTimer() + 10;
            this.powerStatus = true;
            System.out.println("Got pwerup!");
        } else {
            if (getTimer() > this.stopPowerup) this.powerStatus = false;
            System.out.println(this.stopPowerup - getTimer() + " seco   nds left");
        }
    }

    // check if ghost is exactly 0 moves away from pacman
    // true means you met a ghost false means not yet
    public boolean checkGhost() {
        int wDist = Math.abs(playerPosition[0] - ghostPosition[0]);
        int hDist = Math.abs(playerPosition[1] - ghostPosition[1]);

        return (wDist == 0 & hDist == 0);
    }


    public void handleGhostTouch() {
        if (checkGhost()) {
            if (this.powerStatus) {
                System.out.println("Gottem");
                this.score += 250;
                this.ghostPosition[0] = this.width-1;
                this.ghostPosition[1] = this.height-1;

            }
        }
    }
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
        handlePowerUp(false);
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
                handlePowerUp(true);
            }
            String[][] newCoins = gameWorld.copyArr(coins);
            newCoins[playerPosition[0]][playerPosition[1]] = " ";
            gameWorld.setCoinArr(newCoins);
            System.out.println("Collected!");
        }
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
        board[this.playerPosition[0]][this.playerPosition[1]] = "P";
        board[this.ghostPosition[0]][this.ghostPosition[1]] = "G";
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
