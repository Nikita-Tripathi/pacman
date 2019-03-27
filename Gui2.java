import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.shape.*;

import javafx.scene.image.*;

//import pacmanlogic.*;


/**
 * gui
 */
public class Gui2 extends Application {
    Brain gameBrain = new Brain();


    String nKey = "s";


    BorderPane root = new BorderPane();

    GridPane gameGridPane = new GridPane();

    public void displayBoard() {
        for (int i = 0; i < 10; i++) {
            gameGridPane.getColumnConstraints().add(new ColumnConstraints(40));
            gameGridPane.getRowConstraints().add(new RowConstraints(40));
        }
        //creating the visual board
      
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

        
        displayBoard();
        
        
            
        


			
		//setting the texts
        Label scoreLabel = new Label("Score: " + gameBrain.score);
 
        Label youLost = new Label(" You Lost!!");
        root.setTop(scoreLabel);
        root.setCenter(gameGridPane);
        gameGridPane.setGridLinesVisible(true);

        Scene gameScreen = new Scene(root, 700, 700);

		//Event handler
        gameScreen.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode()== KeyCode.D) {
            
            nKey = "d";
            System.out.println(nKey);
            }
            else if (key.getCode() == KeyCode.A) {
            nKey = "a";
            System.out.println(nKey);
                
            } 
                
            
        });

        primaryStage.setTitle("PACMAN Beta");
        primaryStage.setScene(gameScreen);
        primaryStage.show();

    }

}