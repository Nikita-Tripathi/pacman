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
public class Gui1 extends Application {

    Brain gameBrain = new Brain();

    BorderPane root = new BorderPane();

    GridPane gameGridPane = new GridPane();
    Scene gameScreen = new Scene(root, 700, 700);

    //setting the texts
    Label scoreLabel = new Label("Score: " + gameBrain.score);
    Label youLost = new Label(" You Lost!!");

    @Override
    public void start(Stage primaryStage) {

        
        
        
        
        for (int i = 0; i < 10; i++) {
            gameGridPane.getColumnConstraints().add(new ColumnConstraints(40));
            gameGridPane.getRowConstraints().add(new RowConstraints(40));
        }
		
	 	//creating the visual board
        
            gameGridPane.getChildren().clear();
            for (int i = 0; i < gameBrain.getDisplayArr().length; i++) {
                for (int j = 0; j < gameBrain.getDisplayArr().length; j++) {
                    gameGridPane.add(new Label(gameBrain.getDisplayArr()[i][j]), j, i);
                }
            } 
			
		
 
        
        root.setTop(scoreLabel);
        root.setCenter(gameGridPane);
        gameGridPane.setGridLinesVisible(true);
        

        

            
   

        
		//Event handler
        gameScreen.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            
            
            gameBrain.validateMove(gameBrain.playerPosition, gameBrain.player.move(key.getText()));
            gameBrain.checkLives("player");
            gameBrain.validateMove(gameBrain.ghostPosition, gameBrain.ghost1.getRandomMove());
            gameBrain.checkLives("player");
            gameBrain.displayBoard();
            gameBrain.checkCoins();
            
            gameGridPane.getChildren().clear();
            for (int i = 0; i < gameBrain.getDisplayArr().length; i++) {
                for (int j = 0; j < gameBrain.getDisplayArr()[0].length; j++) {
                    gameGridPane.add(new Label(gameBrain.getDisplayArr()[i][j]), j, i);
                }
            } 
                
                //creating the visual board
			
        });


        

        primaryStage.setTitle("PACMAN Beta");
        primaryStage.setScene(gameScreen);
        primaryStage.show();

    }

    /* public void visualBoard() {  
        String [][] board = gameBrain.getDisplayArr();
            gameGridPane.getChildren().clear();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    gameGridPane.add(new Label(board[i][j]), j, i);
                }
            }
        }
 */
}
