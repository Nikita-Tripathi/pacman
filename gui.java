import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

/**
 * gui
 */
public class gui extends Application {

    @Override
    public void start(Stage primaryStage) {

        Brain gameBrain = new Brain();
        
        BorderPane root = new BorderPane();
        

        GridPane gameGridPane = new GridPane();
        
        for (int i = 0; i < 10; i++) {
            gameGridPane.getColumnConstraints().add(new ColumnConstraints(40));
            gameGridPane.getRowConstraints().add(new RowConstraints(40));
        }
		
		//creating the visual board
        String [][] boardTemp = gameBrain.getDisplayArr();
            gameGridPane.getChildren().clear();
            for (int i = 0; i < boardTemp.length; i++) {
                for (int j = 0; j < boardTemp[0].length; j++) {
                    gameGridPane.add(new Label(boardTemp[i][j]), j, i);
                }
            }
			
		//setting the texts
        Label scoreLabel = new Label("Score: " + gameBrain.score);
 
        Label youLost = new Label(" You Lost!!");
        root.setTop(scoreLabel);
        root.setCenter(gameGridPane);
        gameGridPane.setGridLinesVisible(true);

        Scene gameScreen = new Scene(root, 700, 700);

		//Event handler
        gameScreen.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			
			//normal pacman
			if(gameBrain.powerStatus == false) {
				gameBrain.validateMove(gameBrain.playerPosition, gameBrain.player.move(key.getText()));
				gameBrain.checkLives("player");
				gameBrain.validateMove(gameBrain.ghostPosition, gameBrain.ghost1.getRandomMove());
				gameBrain.checkLives("player");
				
				gameBrain.checkCoins();
			}
			
			//pacman with power up
			if(gameBrain.powerStatus == true) {
				int counter = 50;
				System.out.println(counter);

				
				if(counter > 0 && gameBrain.score < 850){ //score is also for testing
					gameBrain.gameWorld.setPowerUpArr();
					
					gameBrain.checkLives("ghost");
					gameBrain.validateMove(gameBrain.playerPosition, gameBrain.player.move(key.getText()));
					gameBrain.checkLives("ghost");
					gameBrain.validateMove(gameBrain.ghostPosition, gameBrain.ghost1.getRandomMove());
					gameBrain.checkLives("ghost");
					
					gameBrain.checkCoins();
					System.out.println(counter);
					
					counter--;
				}
				gameBrain.gameWorld.resetFromPowerUpArr();
				
				gameBrain.powerStatus = false;
			}
            
			//checks the game status
            if(gameBrain.checkGameOver()) {
                root.getChildren().remove(gameGridPane);
                root.setCenter(youLost);
                youLost.setFont(Font.font("Verdana" , 76));
                youLost.setTextFill(Color.RED);
                
                }
				
            else if( gameBrain.score >= 850) {
                root.getChildren().remove(gameGridPane);
                Label label1 = new Label("You Won!");
                label1.setFont(Font.font("Verdana" , 76));
                label1.setTextFill(Color.DARKGREEN);
                root.setCenter(label1);
                
                }
                
            System.out.println(gameBrain.score);
            scoreLabel.setText("Score: " + gameBrain.score+ "Lives: "+ gameBrain.lives);
            if (gameBrain.score > 850) {
                System.out.println("You Won!");
                }
            
            // check for win

            String [][] board = gameBrain.getDisplayArr();
            gameGridPane.getChildren().clear();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    gameGridPane.add(new Label(board[i][j]), j, i);
                }
            }
            

            
        });

        primaryStage.setTitle("PACMAN Beta");
        primaryStage.setScene(gameScreen);
        primaryStage.show();

    }

}
