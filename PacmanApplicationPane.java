import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import java.util.Random;
import javafx.scene.layout.GridPane;

public class PacmanApplicationPane extends Application{
	 public static void main(String[] args)
   {
      launch(args);
   }
	private Rectangle rectangle = new Rectangle(0,0,20,20);
	private Rectangle ghost = new Rectangle(0,0,30,30);
	private int max = 0;
	private int min = 0;
	
	/**
	 *Random number generator for the ghost.
	 */
	
	public int randomNumber(int max, int min){
		Random RANDOM = new Random();
		max = RANDOM.nextInt(8);
		min = RANDOM.nextInt(8);
		return(max - min);
	}
	/**
	 *Method to obtain random ghost movement along the X axis.
	 */
	public int ghostMovementX(){
		return ((int)ghost.getLayoutX()+randomNumber(max,min));
	}
	
	/**
	 *Method to obtain random ghost movement along the Y axis.
	 */
	public int ghostMovementY(){
		return ((int)ghost.getLayoutY()+randomNumber(max,min));
	}
		
	
  
   @Override
   public void start(Stage primaryStage) throws Exception
   {
	   primaryStage.setTitle("KeyEvent");
	   Pane root = new Pane();
	   
	   rectangle.setHeight(30);
	   rectangle.setWidth(30);
	   rectangle.setFill(Color.YELLOW);
	   root.getChildren().add(rectangle);
	   root.getChildren().add(ghost);
	   
	   ghost.setHeight(40);
	   ghost.setWidth(40);
	   ghost.setFill(Color.RED);
	   
	   Scene scene = new Scene(root, 200, 200);
	   
	   
	   primaryStage.setScene(scene);
	   primaryStage.show();
	   scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
	    
		 /**
		  On pressing the up,down,left,right keys 
		  on the keyboard the rectangle moves by one pixel.
		  The ghost moves randomly.
		 */
		@Override
		public void handle(KeyEvent event){
			switch (event.getCode()){
				case DOWN:   
				rectangle.relocate(rectangle.getLayoutX(), rectangle.getLayoutY()+1);
				ghost.relocate(ghostMovementX(),ghostMovementY());
				break;
				
				case UP:
				rectangle.relocate(rectangle.getLayoutX(), rectangle.getLayoutY()-1);
				ghost.relocate(ghost.getLayoutX()+randomNumber(max,min), ghost.getLayoutY()+randomNumber(max,min));
				break;
				
				case LEFT:
				rectangle.relocate(rectangle.getLayoutX()-1, rectangle.getLayoutY());
				ghost.relocate(ghost.getLayoutX()+randomNumber(max,min), ghost.getLayoutY()+randomNumber(max,min));
				break;
				
				case RIGHT:
				rectangle.relocate(rectangle.getLayoutX()+1, rectangle.getLayoutY());
				ghost.relocate(ghost.getLayoutX()+randomNumber(max,min), ghost.getLayoutY()+randomNumber(max,min));
				break;
			}
		}
   });
   
}
}
	   