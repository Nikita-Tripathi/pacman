import java.util.Arrays;

/**
 * brain
 */
public class Brain {
    public static String[][] initialArray = {
        {" ", " ", "W", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", "W", " ", " ", " ", " "},

        {" ", " ", "W", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", "W", " ", " ", " ", " "},

        {" ", " ", "W", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", "W", " ", " ", " ", " "},

        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},

        {" ", " ", "W", "W", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", "W", "W", " ", " ", " "},

        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},

        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},

        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},

        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},

        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},

        {" ", " ", "W", "W", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", "W", "W", " ", " ", " "},

        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},

        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},

        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},

        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "}

    };
	
    public static int width = initialArray.length;
    public static int height = initialArray[0].length;
    public static int[] playerPosition = {0, 0};
    public static int[] ghostPosition = {width-1, height-1};
    public static Pacman player = new Pacman();
    public static Ghost ghost1 = new Ghost();
    public static World gameWorld = new World(initialArray, playerPosition, ghostPosition);
	
	public static int lives = 3;
	public static boolean powerStatus = false; //in attemps to fix the bug specified in comment found in method move
	
    
    // score
    public static int score = 0;

    // checks for valid moves 
    public static void validateMove(int[] position, int[] move) {
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
    public static void move(int[] position, int[] move) {
		//Resets the position if they intersect, depeding on the Pacmans status (powered up or not)
		//Current parameters: Pacman gets sent to top left, ghost gets sent to bottom right
		//BUG: down by the lower corner where ghost respawns, if Pacman encounters the ghost in that area,
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

    // check if pacman got coin/powerup
    public static void checkCoins() {
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
 
	//checks lives of player or ghost and resets the position if necessary
	public static void checkLives(String character){
		
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
	
	//allows pacman to eat the ghosts
	public static void activatePowerUp(){
		int counter = 50; //number of moves before power up runs out--> set to 50 for testing
		powerStatus = true; //experimental
		
		while (counter > 0 && score < 1000){ //score is also for testing
			gameWorld.setPowerUpArr();
			
            displayBoard();
			checkLives("ghost");
            validateMove(playerPosition, player.move());
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
	
	//resets pacman to the first block if lives stil remain, and ghost to the diagonal if consumed
	public static void resetPosition(String character) {
		
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
				
				ghostPosition[0] = width-1;
				ghostPosition[1] = height-1;

				newMoveArr[ghostPosition[0]][ghostPosition[1]] = "g";
			}
		}

        gameWorld.setMovingArr(newMoveArr);
	}
	
 
 
    //CHANGED: games over when lives have run out
    public static boolean checkGameOver() {
        
        return lives == 0;
    }

    //For displaying arrays in  matrix form (good for debugging)
	public static void display(String display[][]){
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
    public static void displayBoard() {
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

    public static void main(String[] args) {
        // Gameloop
        while (score < 1000 && !checkGameOver()) {
			checkLives("player");
            displayBoard();
            validateMove(playerPosition, player.move());
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
