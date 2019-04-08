import pacmanlogic.*;
public class PacmanText{
    public static void main(String[] args) {
        Brain gameBrain = new Brain();
        // Gameloop
        while (gameBrain.score < 850 && ! gameBrain.checkGameOver()) {
			gameBrain.checkLives("player");
            gameBrain.displayBoard();
            gameBrain.validateMove(gameBrain.playerPosition, gameBrain.player.move("s"));
			gameBrain.checkLives("player");
            gameBrain.validateMove(gameBrain.ghostPosition, gameBrain.ghost1.move(""));
			gameBrain.checkLives("player");
            
            gameBrain.checkCoins();
            System.out.println("Curent Score: " + gameBrain.score);
			System.out.println("Lives Left: " + gameBrain.lives);
            System.out.println();
			
        }
        
        System.out.println("Curent Score: " + gameBrain.score);
        gameBrain.displayBoard();

        // Display Win/Lose message
    	if (gameBrain.checkGameOver()) {
            System.out.println("You Lost!");
        } else {
	        System.out.println("You Won!");
        }
    }
    
}