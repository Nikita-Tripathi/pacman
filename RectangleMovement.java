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

public class RectangleMovement extends Application{
	 public static void main(String[] args)
   {
      launch(args);
   }
	Rectangle rectangle = new Rectangle(0,0,20,20);
	
  
   @Override
   public void start(Stage primaryStage) throws Exception
   {
	   primaryStage.setTitle("KeyEvent");
	   Pane root = new Pane();
	   
	   rectangle.setHeight(40);
	   rectangle.setWidth(40);
	   rectangle.setFill(Color.YELLOW);
	   root.getChildren().add(rectangle);
	   Scene scene = new Scene(root, 500, 400);
	   
	   
	   primaryStage.setScene(scene);
	   primaryStage.show();
	   scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
	    
		 /**
		  On pressing the up,down,left,right keys 
		  on the keyboard the rectangle moves by one pixel.
		 */
		@Override
		public void handle(KeyEvent event){
			switch (event.getCode()){
				case DOWN:   
				rectangle.relocate(rectangle.getLayoutX(), rectangle.getLayoutY()+1);
				break;
				
				case UP:
				rectangle.relocate(rectangle.getLayoutX(), rectangle.getLayoutY()-1);
				break;
				
				case LEFT:
				rectangle.relocate(rectangle.getLayoutX()-1, rectangle.getLayoutY());
				break;
				
				case RIGHT:
				rectangle.relocate(rectangle.getLayoutX()+1, rectangle.getLayoutY());
				break;
				
				
				
				
		
			// case UP:    rectangle.setY(+5); rectangle.setX(+0); break;
			// case DOWN:  rectangle.setY(-5); rectangle.setX(+0); break;
			// case LEFT:  rectangle.setX(-5); rectangle.setY(+0); break;
			// case RIGHT: rectangle.setX(+5); rectangle.set(+0); break;
			}
		}
   });
   
}
}
	   