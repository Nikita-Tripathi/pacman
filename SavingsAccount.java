import java.io.*;
public class SavingsAccount extends BankAccount {
	
	/** 
	    Class represents a Saving account
	    is a branch of BankAccount
	*/


	/**
		*Instance Variables
	*/
	
	private double annualInterestRate = 0.05;
	private double minimumBalance;
	
	
	
	//Constructors
	public SavingsAccount(BufferedReader br)throws IOException{
		super(br);
		String first = br.readLine();
		this.annualInterestRate = Double.parseDouble(first);
		String second = br.readLine();
		this.minimumBalance = Double.parseDouble(second);

	}
	public SavingsAccount(double balance, double annualInterestRate ){
		super(balance);
		setAnnualInterestRate(annualInterestRate); 


	}
	public SavingsAccount(){
		super();
		annualInterestRate = 0.05;
	}
	
	public SavingsAccount(double aRate){
		super();
		setAnnualInterestRate(aRate); 
		
	}

	// public SavingsAccount (double aRate, double minBalance) {
	// 	super();
	// 	annualInterestRate = 0.05;
	// 	setAnnualInterestRate(aRate); 
	// 	setMinimumBalance(minBalance);
	// }
	
	public SavingsAccount(Customer accountHolder, double balance, double annualInterestRate) {
	
		super(accountHolder, balance);
		setAnnualInterestRate(annualInterestRate); 
		
	}

	// public SavingsAccount(Customer accountHolder, double annualInterestRate) {
	// 	super.getAccountHolder();
	// 	setAnnualInterestRate(annualInterestRate); 

	// }
	public SavingsAccount(Customer accountHolder, double balance){
		super(accountHolder, balance);
	}




	
	
	//Setter Methods
	
	public void setAnnualInterestRate(double aRate){
		if ((aRate >= 0) && (aRate <=1)) {
			annualInterestRate = aRate;
		}
	}
	
	public void setMinimumBalance(double aBalance) {
		minimumBalance = aBalance;
	}
	
	
	
	//Getter Methods

	
	public double getAnnualInterestRate(){
		return this.annualInterestRate;
	}
	
	public double getMinimumBalance(){
		return this.minimumBalance;
	}
	
	
	//Methods

	
		/** 
			This method calculates the monthly interest rate and adds the amonut to the balance
		*/
		
	public double getMonthlyFeesAndInterest() {
		double amountToDeposit = annualInterestRate/12*super.getBalance();
		//deposit(amountToDeposit);
		return amountToDeposit;
	}
	
		/**
			This method withdraws the indicated amount only if amount left over is equal or 
			greater than the minimum balance
		*/
		
	public void withdraw(double amount){
		if (super.getBalance() - amount >= minimumBalance) {
			super.withdraw(amount);
		}		
	}

	public void saveToTextFile(String filename)throws IOException{
		super.saveToTextFile(filename);
		 FileOutputStream fw = new FileOutputStream(filename,true);
		 PrintWriter pw = new PrintWriter(fw);
		 pw.println(this.annualInterestRate);
    	 pw.println(this.minimumBalance);
		 pw.close();

	}
}
