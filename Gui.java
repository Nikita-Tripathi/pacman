import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pacmanlogic.Brain;
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
import javafx.scene.*;
import javafx.scene.layout.*;

import pacmanlogic.*;



/**
 * gui
 */
public class Gui extends Application {
    
    private Brain gameBrain = new Brain();
    private BorderPane root = new BorderPane();
    private Image image = new Image("File:Ghost.png");
    private ImageView pic = new ImageView(image);
    private Image pacImage = new Image("File:pacman.png");
    private ImageView pacView = new ImageView(pacImage);
    private GridPane gameGridPane = new GridPane();
    private HBox var = new HBox(pic);
    private String keyPressed = "d";
    private Label scoreLabel = new Label("Score: " + gameBrain.getScore());
    
    //creating the visual board
    public void displayBoard() {
        pic.setFitHeight(30);
        pic.setFitWidth(30);
        pacView.setFitHeight(30);
        pacView.setFitWidth(30);
        gameGridPane.gridLinesVisibleProperty();
        for (int i = 0; i < 10; i++) {
            gameGridPane.getColumnConstraints().add(new ColumnConstraints(40));
            gameGridPane.getRowConstraints().add(new RowConstraints(40));
            
        }
            // updates the score and lives
            scoreLabel.setText("Score: " + gameBrain.getScore()+ "Lives: "+ gameBrain.getLives());
        
            gameGridPane.getChildren().clear();
            for (int i = 0; i < gameBrain.getDisplayArr().length; i++) {
                for (int j = 0; j < gameBrain.getDisplayArr()[0].length; j++) {
                    if(gameBrain.getDisplayArr()[i][j] == "P"){
                        gameGridPane.add(pacView, j, i);
                        if (keyPressed == "s") {
                            pacView.setRotate(90);
                        }
                        else if (keyPressed == "w") {
                            pacView.setRotate(270);
                        } 
                        else if (keyPressed == "d") {
                            pacView.setRotate(0);
                        }
                        else if ( keyPressed == "a") {
                            pacView.setRotate(180);
                        }
                    }
                    else if(gameBrain.getDisplayArr()[i][j] == "G"){
                        gameGridPane.add(pic, j,i);
                    }

                    else if(gameBrain.getDisplayArr()[i][j] == "C"){
                        gameGridPane.add(new Circle(10, Color.YELLOW),j,i);
                    }
                    else if(gameBrain.getDisplayArr()[i][j] == "W"){
                        gameGridPane.add(new Rectangle(5, 10,Color.GRAY),j,i);
                    }
                    else if(gameBrain.getDisplayArr()[i][j] == "O"){
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
            else if (key.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
                System.exit(0);
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
                    
                    gameBrain.validateMove(gameBrain.getPlayerPosition(), gameBrain.getPlayer().move(keyPressed));
                    gameBrain.validateMove(gameBrain.getGhostPosition(), gameBrain.getGhost1().move(keyPressed)); 
                    gameBrain.checkCoins();  
                    gameBrain.checkLives("player");
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