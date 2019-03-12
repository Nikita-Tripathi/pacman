import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;


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
            gameGridPane.getColumnConstraints().add(new ColumnConstraints(60));
            gameGridPane.getRowConstraints().add(new RowConstraints(60));
        }

        String [][] boardTemp = gameBrain.getDisplayArr();
            gameGridPane.getChildren().clear();
            for (int i = 0; i < boardTemp.length; i++) {
                for (int j = 0; j < boardTemp[0].length; j++) {
                    gameGridPane.add(new Label(boardTemp[i][j]), j, i);
                }
            }
        
        root.setTop(new Label("Lives :"));
        root.setCenter(gameGridPane);

        Scene gameScreen = new Scene(root, 700, 700);

        gameScreen.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            gameBrain.incrementTimer();
            gameBrain.validateMove(gameBrain.playerPosition, gameBrain.player.move(key.getText()));
            gameBrain.validateMove(gameBrain.ghostPosition, gameBrain.ghost1.getRandomMove());
            gameBrain.checkCoins();
            
            System.out.println(gameBrain.score);
            System.out.println(gameBrain.timer + " " + gameBrain.stopPowerup);
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