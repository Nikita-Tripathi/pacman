package pacmangui;
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
import javafx.geometry.Insets;
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
    Brain gameBrain = new Brain();
        
    BorderPane root = new BorderPane();
    Image image = new Image("Resources/Ghost.gif");

    GridPane gameGridPane = new GridPane();

    Image image1 = new Image("Resources/Gif.gif");
    ImageView picPacman = new ImageView(image1);
   
    HBox varPacman = new HBox(picPacman);
    String keyPressed = "d";
    Label scoreLabel = new Label("Score: " + gameBrain.getPlayer().getScore());
    
    //creating the visual board
    public void displayBoard() {

        picPacman.setFitHeight(25);
        picPacman.setFitWidth(25);
        
        gameGridPane.setGridLinesVisible(true);
        for (int i = 0; i < 10; i++) {
            gameGridPane.getColumnConstraints().add(new ColumnConstraints(30));
            gameGridPane.getRowConstraints().add(new RowConstraints(30));
            
        }
            // updates the score and lives
            scoreLabel.setText("Score: " + gameBrain.getPlayer().getScore()+ "Lives: "+ gameBrain.getPlayer().getLives());
        
            gameGridPane.getChildren().clear();

            int[] pacmanPos = gameBrain.getPlayer().getPosition();

            gameGridPane.add(picPacman,pacmanPos[1], pacmanPos[0]);

            if(keyPressed =="a"){
                picPacman.setRotate(180.00);
            }
            else if(keyPressed == "s"){
                picPacman.setRotate(90.00);
            }
            else if(keyPressed == "w"){
                picPacman.setRotate(270.00);
            }
            else if(keyPressed == "d"){
                picPacman.setRotate(0.00);
            }

            for (Ghost ghost : gameBrain.getGhostArray()) {
                int[] pos = ghost.getPosition();
                if (! ghost.getPowerStatus()) {
                    ImageView picGhost = new ImageView(image);
                    picGhost.setFitHeight(40);
                    picGhost.setFitWidth(40);

                    gameGridPane.add(picGhost,pos[1],pos[0]);
                } else {
                    gameGridPane.add(new Rectangle(5, 10,Color.BLACK),pos[1],pos[0]);
                }
            }



            for (int i = 0; i < gameBrain.getDisplayArr().length; i++) {
                for (int j = 0; j < gameBrain.getDisplayArr()[0].length; j++) {

                    if(gameBrain.getDisplayArr()[i][j] == "C"){
                        gameGridPane.add(new Circle(7, Color.ORANGE),j,i);
                    }
                    else if(gameBrain.getDisplayArr()[i][j] == "W"){
                        gameGridPane.add(new Rectangle(30, 30,Color.GRAY),j,i);
                    }
                    else if(gameBrain.getDisplayArr()[i][j] == "O"){
                        gameGridPane.add(new Circle (7,Color.BLUE),j,i);
                    }
                    
                }
            }
    }

    @Override
    public void start(Stage primaryStage) {
        Pacman player = gameBrain.getPlayer();

        gameGridPane.setGridLinesVisible(true);
        Label youLost = new Label(" You Lost!!");
        root.setTop(scoreLabel);
        root.setCenter(gameGridPane);
        
        gameGridPane.setAlignment(Pos.CENTER);
       
        gameGridPane.setHgap(0);
	    gameGridPane.setVgap(0); 
        
        Scene gameScreen = new Scene(root, 650, 650);

		//Event handler
        gameScreen.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(key.getCode() == javafx.scene.input.KeyCode.A ) {
  
                keyPressed = "a";
            }
            else if (key.getCode() == javafx.scene.input.KeyCode.W) {
                keyPressed = "w";
                
            }
            else if (key.getCode() == javafx.scene.input.KeyCode.D) {
                keyPressed = "d";
                
            }
            else if (key.getCode() == javafx.scene.input.KeyCode.S) {
                keyPressed = "s";
            }
            else if (key.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
                System.exit(0);
            }

            if(gameBrain.checkGameOver()) {
                root.getChildren().remove(gameGridPane);
                root.setCenter(youLost);
                youLost.setFont(Font.font("Verdana" , 50));
                youLost.setTextFill(Color.RED);
                
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

                        gameBrain.validateMove(player, player.move(keyPressed));

                        gameBrain.checkLives();
                        
                        gameBrain.checkCoins();
                        
                        int number = 1;
                        for (Ghost ghost : gameBrain.getGhostArray()) {
                            gameBrain.validateMove(ghost, ghost.move("s")); 
                            
                            
                            if(ghost.getPowerStatus() && ghost.getCounter() > 0){
                                ghost.decreaseCounter();
                            }
                            else{
                                gameBrain.deactivatePowerUp(ghost);
                                gameBrain.getGhost(number).resetCounter();
                            }
                            number++;
                        }

                        gameBrain.checkLives();

                        lastUpdate = now;
                        if(gameBrain.checkWin()== true) {
                            root.getChildren().remove(gameGridPane);
                            root.setCenter(new Label("YOU WON"));
                        }
                } 
                
            }
        }.start();

        if(gameBrain.checkWin()== true) {
            root.getChildren().remove(gameGridPane);
            root.setCenter(youLost);
        }
        
        System.out.println(player.getScore());
        scoreLabel.setText("Score: " + player.getScore()+ "Lives: "+ player.getLives());
        primaryStage.setTitle("PACMAN Beta");
        primaryStage.setScene(gameScreen);
        primaryStage.show();

    }

}
