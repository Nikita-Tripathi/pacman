import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

/**
This application is a simple demonstration of adding buttons to the BorderPane layout.
It allows the user to deposit or withdraw an appropriate amount from their balance
which is handled through the button click event.
The defualt Customer name is John Doe with an account balance of $0.0.
*/
public class BankApp extends Application
{
    /**
     * Initialize the BankAccount 
     */
    private BankAccount account = new BankAccount(new Customer("John Doe"), 0.0);
    private TextField txtName = new TextField();
    private Label balance = new Label("Balance: " + account.getBalance());
	
	
   public static void main(String[] args)
   {
        launch(args);
   }
   
	public void start(Stage primaryStage) throws Exception
   {    
	    BorderPane root = new BorderPane();
	    FlowPane centerPane = new FlowPane();

	    VBox topPane = new VBox();
	    FlowPane bottomPane = new FlowPane();


		/**
		Adds the button to deposit and withdraw to the bottom
		*/
        Button deposit = new Button("Deposit");
        Button withdraw = new Button("Withdraw");


		/**
		Adds label message at top that displays the name and balance
		*/
	    topPane.getChildren().add(new Label("Account Holder: " + account.getAccountHolder().getName()));
	    topPane.getChildren().add(balance);
		
		
		/**
		Puts the text box where you can enter the amonut in the middle
		*/
	    centerPane.getChildren().add(new Label("Enter amount:"));
	    txtName.setPrefWidth(200);
    
	    centerPane.getChildren().add(txtName);
	    centerPane.setAlignment(Pos.CENTER);
    
	    bottomPane.getChildren().add(deposit);
	    bottomPane.getChildren().add(withdraw);
	    bottomPane.setAlignment(Pos.CENTER);
    
	
	    topPane.setAlignment(Pos.CENTER);
	        root.setCenter(centerPane);
	        root.setBottom(bottomPane);
	        root.setTop(topPane);
    
	
		/**
		The following sets the event handler when button is clicked
		*/

		//Puts in an appropriate number to the account balance
        deposit.setOnAction(new EventHandler<ActionEvent>(){
        
            @Override
            public void handle(ActionEvent event) {
                double amount = Double.parseDouble(txtName.getText());
                account.deposit(amount);
                balance.setText("Balance: " + account.getBalance());
            }
        });
		

		//Takes out a valid amount from the account balance
        withdraw.setOnAction(new EventHandler<ActionEvent>(){
        
            @Override
            public void handle(ActionEvent event) {
                double amount = Double.parseDouble(txtName.getText());
                account.withdraw(amount);
                balance.setText("Balance: " + account.getBalance());
            }
        });
    
	
	    Scene scene = new Scene(root, 500, 200);
	    	primaryStage.setTitle("My Bank");
        primaryStage.setScene(scene);
        primaryStage.show();
   }    
}   