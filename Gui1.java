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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.shape.*;
import javafx.animation.AnimationTimer;
import javafx.scene.image.*;




/**
 * gui
 */
public class Gui1 extends Application {
    Brain gameBrain = new Brain();
        
    BorderPane root = new BorderPane();

    GridPane gameGridPane = new GridPane();

    String keyPressed = "d";
    Label scoreLabel = new Label("Score: " + gameBrain.score);
    public void displayBoard() {
        for (int i = 0; i < 10; i++) {
            gameGridPane.getColumnConstraints().add(new ColumnConstraints(40));
            gameGridPane.getRowConstraints().add(new RowConstraints(40));
        }
        //creating the visual board
            scoreLabel.setText("Score: " + gameBrain.score+ "Lives: "+ gameBrain.lives);
        String [][] boardTemp = gameBrain.getDisplayArr();
        String [][] board = gameBrain.getDisplayArr();
            gameGridPane.getChildren().clear();
            for (int i = 0; i < boardTemp.length; i++) {
                for (int j = 0; j < boardTemp[0].length; j++) {
                    if(boardTemp[i][j] == "P"){
                        gameGridPane.add(new Rectangle(20, 20, Color.YELLOWGREEN),j,i);
                        
                    }
                    else if(boardTemp[i][j] == "G"){
                        gameGridPane.add(new Rectangle(20, 20, Color.PINK),j,i);
                    }

                    else if(boardTemp[i][j] == "C"){
                        gameGridPane.add(new Circle(10, Color.YELLOW),j,i);
                    }
                    else if(boardTemp[i][j] == "W"){
                        gameGridPane.add(new Rectangle(5, 10,Color.GRAY),j,i);
                    }
                    else if(boardTemp[i][j] == "O"){
                        gameGridPane.add(new Circle (10,Color.BLUE),j,i);
                    }
                    
                }
            }
    }

    @Override
    public void start(Stage primaryStage) {

        
        //displayBoard();
        
        
            
        


			
		//setting the texts
        gameGridPane.setStyle("-fx-background-color: #C0C0C0;");
 
        Label youLost = new Label(" You Lost!!");
        root.setTop(scoreLabel);
        root.setCenter(gameGridPane);
        gameGridPane.setGridLinesVisible(true);

        Scene gameScreen = new Scene(root, 700, 700);

		//Event handler
        gameScreen.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(key.getCode() == javafx.scene.input.KeyCode.A ) {
                System.out.println("A is pressed");
                // var.setCenterX(var.getCenterX() + 10);
                keyPressed = "a";
            }
            else if (key.getCode() == javafx.scene.input.KeyCode.W) {
                System.out.println("W is pressed");
                keyPressed = "w";
                
            }
            else if (key.getCode() == javafx.scene.input.KeyCode.D) {
                System.out.println("d is pressed ");
                keyPressed = "d";
                
            }
            else if (key.getCode() == javafx.scene.input.KeyCode.S) {
                System.out.println("s is pressed");
                keyPressed = "s";
            }
			
			
        });
        long lastUpdate = System.nanoTime();
        
        new AnimationTimer() {
            long lastUpdate = System.nanoTime();
            @Override
            public void handle(long now) {
                displayBoard(); 
                // System.out.println("in animation timer;");
                //from stackoverflow https://stackoverflow.com/questions/30146560/how-to-change-animationtimer-speed
                
                
                    if (now - lastUpdate >= 500000000l ) {
                    
                    gameBrain.validateMove(gameBrain.playerPosition, gameBrain.player.move(keyPressed));
                    gameBrain.validateMove(gameBrain.ghostPosition, gameBrain.ghost1.getRandomMove()); 
                    gameBrain.checkCoins();  
                    System.out.println(keyPressed);
                    lastUpdate = now;
                }
                
              
                
                
            }
        }.start();

        primaryStage.setTitle("PACMAN Beta");
        primaryStage.setScene(gameScreen);
        primaryStage.show();

    }

}