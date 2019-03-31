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

import java.io.*;
import java.util.Scanner;
import java.util.Random;

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
	 
    private static BankAccount account = new SavingsAccount();
    private TextField txtName = new TextField();
    private Label balance = new Label("Balance: " + account.getBalance());
	private Label accountHolder = new Label("Account Holder: " + account.getAccountHolder().getName());
	private Label centerText = new Label("Enter amount: ");
	
	private Label invalidMessage = new Label("Please enter a number");
	
   public static void main(String[] args)
   {
	   
	   try{
		   BufferedReader inputFile = new BufferedReader(new FileReader("account.txt"));
		   account = new SavingsAccount(inputFile);
	   }
	   catch (IOException e){	   
		   account = new SavingsAccount(new Customer(" ", 1000), 0.00);
	   }
	   
        launch(args);
   }
   
	public void start(Stage primaryStage) throws Exception
   {    
		Random rand = new Random();
			   
	    BorderPane root = new BorderPane();
	    FlowPane centerPane = new FlowPane();

	    VBox topPane = new VBox();
	    FlowPane bottomPane = new FlowPane();


		/**
		Adds the button to deposit and withdraw to the bottom
		*/
        Button deposit = new Button("Deposit");
        Button withdraw = new Button("Withdraw");
		Button confirm = new Button("Confirm name");


		/**
		Adds label message at top that displays the name and balance
		*/
	    topPane.getChildren().add(accountHolder);
	    topPane.getChildren().add(balance);
		
		
		/**
		Puts the text box where you can enter the amonut in the middle
		*/
		if (account.getAccountHolder().getName().equals(" ")){
			centerText.setText("Enter name: ");

			bottomPane.getChildren().add(confirm);
			bottomPane.setAlignment(Pos.CENTER);
		
		}
		else {
			centerText.setText("Enter amount: ");
		
			bottomPane.getChildren().add(deposit);
			bottomPane.getChildren().add(withdraw);
			bottomPane.setAlignment(Pos.CENTER);
		
		}
		
		centerPane.getChildren().add(centerText);
		txtName.setPrefWidth(200);
		
		centerPane.getChildren().add(txtName);
		centerPane.setAlignment(Pos.CENTER);
		
		topPane.setAlignment(Pos.CENTER);
			root.setCenter(centerPane);
			root.setBottom(bottomPane);
			root.setTop(topPane);
	
		/**
		The following sets the event handler when button is clicked
		*/
			
		//sets name of account
		confirm.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event){
				
				int randomID = rand.nextInt(9000) + 1000;
				String inputName = txtName.getText();
				account = new SavingsAccount(new Customer(inputName, randomID), 0.00);
				
				accountHolder.setText("Account Holder: " + account.getAccountHolder().getName());
				centerText.setText("Enter amount: ");
				
				bottomPane.getChildren().remove(confirm);
				bottomPane.getChildren().add(deposit);
				bottomPane.getChildren().add(withdraw);
				bottomPane.setAlignment(Pos.CENTER);
			}
		});
		

		//Puts in an appropriate number to the account balance
        deposit.setOnAction(new EventHandler<ActionEvent>(){
        
            @Override
            public void handle(ActionEvent event) {
				try{
					double amount = Double.parseDouble(txtName.getText());
					account.deposit(amount);
                    balance.setText("Balance: " + account.getBalance());
					topPane.getChildren().remove(invalidMessage);
					
					account.saveToTextFile("account.txt");

				}
				catch(IllegalArgumentException ex){
					topPane.getChildren().add(invalidMessage);
				}
				catch(IOException e){
					System.out.println("Couldn't open file");
				}
            }
        });
		

		//Takes out a valid amount from the account balance
        withdraw.setOnAction(new EventHandler<ActionEvent>(){
        
            @Override
            public void handle(ActionEvent event) {
				try{
					double amount = Double.parseDouble(txtName.getText());
					account.withdraw(amount);
                    balance.setText("Balance: " + account.getBalance());
					topPane.getChildren().remove(invalidMessage);

					account.saveToTextFile("account.txt");
				}
				catch(IllegalArgumentException ex){
					topPane.getChildren().add(invalidMessage);
				}
				catch(IOException e){
					System.out.println("Couldn't open file");
				}
            }
        });
	
	    Scene scene = new Scene(root, 500, 200);
	    	primaryStage.setTitle("My Bank");
        primaryStage.setScene(scene);
        primaryStage.show();
   }    
}   
