
import java.io.FileInputStream;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;


public class GridPaneExperiments extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("GridPane Experiment");

        //Image image1 = new Image(new FileInputStream("C:/Users/turec/Desktop/Blue_Circle_o.jpg"));

        //ImageView imageView = new ImageView(image1);

        Label l1 = new Label("*");
        Label l2 = new Label(" ");
        Label l3 = new Label(" ");
        Label l4 = new Label("*");
        l1.setFont(Font.font("Cambria", 32));
        GridPane gridPane = new GridPane();
        

        gridPane.add(l1, 0, 0);
        gridPane.add(l2, 0, 1);
        gridPane.add(image1,2,2);
        //gridPane.add(l4, 0, 3);


        

        Scene scene = new Scene(gridPane, 100, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> { 
            
            
            if (key.getCode() == KeyCode.ESCAPE) {
                System.out.println("hello world");
                gridPane.add(l4, 0, 3);
                
            }
            
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}