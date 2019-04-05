import java.util.Arrays;


/**
 * brain
 */
public class Brain {
    public String[][] initialArray = {
        {" ", " ", " ", " ", "W", "W", "W", " ", "W", " "},

        {" ", "W", " ", " ", " ", " ", " ", " ", "W", " "},

        {" ", "W", " ", "W", " ", "W", " ", " ", "W", " "},

        {" ", "W", " ", "W", "W", "W", " ", " ", " ", " "},

        {" ", "W", " ", " ", " ", " ", " ", "W", "W", " "},

        {" ", " ", " ", " ", " ", " ", " ", "W", " ", " "},

     };
	
    public int width = initialArray.length;
    public int height = initialArray[0].length;
    public int[] playerPosition = {0, 0};
    public int[] ghostPosition = {2, 4}; //Inside the open ended box
    public Pacman player = new Pacman();
    public Ghost ghost1 = new Ghost();
    public World gameWorld = new World(initialArray, playerPosition, ghostPosition);
	
	public int lives = 3;
	
	public boolean powerStatus = false; //in attemps to fix the bug specified in comment found in method move
	
    
    // score
    public int score = 0;

   /**
   	checks for valid moves of movable objects, moving them accordingly
   */ 
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
    public void move(int[] position, int[] move) {
		//Resets the position if they intersect, depending on the Pacmans status (powered up or not)
		//Current parameters: Pacman gets sent to top left, ghost gets sent to inside the box
		//BUG: can not get the GUI version to take any keyboard input until counter runs out
		//     the game halts until the counter runs out for the power pellet
		
		if ((playerPosition[0] == ghostPosition[0])&&(playerPosition[1] == ghostPosition[1])) {
			if(powerStatus == false){
				resetPosition("player");
			}
			else if (powerStatus == true){
				resetPosition("ghost");
			}
		}
		else {
			String[][] newArr = gameWorld.copyArr(gameWorld.getMovingArr());
			String character = newArr[position[0]][position[1]];

			newArr[position[0]][position[1]] = " ";
			position[move[0]] = position[move[0]] + move[1];

			newArr[position[0]][position[1]] = character;

			gameWorld.setMovingArr(newArr);
		}
    }

    /**
    	Checks if pacman obtained a power pellet or coin	
    */
    public void checkCoins() {
        String[][] coins = gameWorld.getCoinArr();
		String[][] newCoins = gameWorld.copyArr(coins);
						
        if (coins[playerPosition[0]][playerPosition[1]] != " "){
            if (coins[playerPosition[0]][playerPosition[1]] == "C") {
                score += 100;
            } else if (coins[playerPosition[0]][playerPosition[1]] == "O"){
                score += 250;
				newCoins[playerPosition[0]][playerPosition[1]] = " ";
				gameWorld.setCoinArr(newCoins);
				System.out.println("Collected PowerPellet!");
				activatePowerUp();
            }
            newCoins[playerPosition[0]][playerPosition[1]] = " ";
            gameWorld.setCoinArr(newCoins);
            System.out.println("Collected!");
        }
    }
 
	/**
	    Checks lives of player or ghost and resets the position if necessary
	*/
	public void checkLives(String character){
		
		boolean intersect = (playerPosition[0] == ghostPosition[0])&&(playerPosition[1] == ghostPosition[1]);

		if (intersect && lives > 0 && character.equals("player")) {
			--lives;
			resetPosition("player");
			System.out.println("You lost a life!");
		}
		else if(intersect && character.equals("ghost")){
			resetPosition("ghost");
		}
	}
	
	/**
		returns the game board
	*/
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
	
	/**
		Allows pacman to eat the ghosts after obtaining a power pellet (only functional in the Text base version)
	*/
	public void activatePowerUp(){
		int counter = 50; //number of moves before power up runs out--> set to 50 for testing
		powerStatus = true; //experimental
		
		while (counter > 0 && score < 1000){ //score is also for testing
			gameWorld.setPowerUpArr();
			
            displayBoard();
			checkLives("ghost");
            validateMove(playerPosition, player.move("s"));
			checkLives("ghost");
            validateMove(ghostPosition, ghost1.getRandomMove());
			checkLives("ghost");
            
            checkCoins();
            System.out.println("Curent Score: " + score);
			System.out.println("Lives Left: " + lives);
			System.out.println("Moves left: " + counter);
            System.out.println();
			
			counter--; 
		}
		gameWorld.resetFromPowerUpArr();
		
		powerStatus = false;
	}
	
	
		//resets pacman to the first block if lives stil remain, and ghost to the inside box if consumed
	
	public void resetPosition(String character) {
		
		String[][] newMoveArr = gameWorld.copyArr(gameWorld.getMovingArr());
		
		if (character.equals("player")){

			newMoveArr[playerPosition[0]][playerPosition[1]] = "G";
			
			playerPosition[0] = 0;
			playerPosition[1] = 0;

			newMoveArr[playerPosition[0]][playerPosition[1]] = "P";
		}
		else if(character == "ghost"){
			if((ghostPosition[0] != width-1) && (ghostPosition[1] != height-1)){
			
				newMoveArr[ghostPosition[0]][ghostPosition[1]] = "P";
				
				ghostPosition[0] = 2;
				ghostPosition[1] = 4;

				newMoveArr[ghostPosition[0]][ghostPosition[1]] = "g";
			}
		}

        gameWorld.setMovingArr(newMoveArr);
	}
	
 
	/**
		games over when lives have run out
	*/
    public boolean checkGameOver() {
        
        return lives == 0;
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
        String[][] moving = gameWorld.getMovingArr();
        String[][] coins = gameWorld.getCoinArr();

        for(int i =0; i< moving.length;i++){
            System.out.print("|");
				for (int j =0;j < moving[0].length; j++){
                    if (moving[i][j] != " ") {
                        System.out.print(moving[i][j] + " ");
                    } else if (coins[i][j] != " "){
                        System.out.print(coins[i][j] + " ");
                    } else {
                        System.out.print("  ");
                    }
                }
            System.out.print("|");
            System.out.println();
        }
		System.out.println();

    }

    public void main(String[] args) {
        // Gameloop
        while (score < 850 && !checkGameOver()) {
			checkLives("player");
            displayBoard();
            validateMove(playerPosition, player.move("s"));
			checkLives("player");
            validateMove(ghostPosition, ghost1.getRandomMove());
			checkLives("player");
            
            checkCoins();
            System.out.println("Curent Score: " + score);
			System.out.println("Lives Left: " + lives);
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
    }
}
