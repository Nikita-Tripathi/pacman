import java.io.*;

public abstract class BankAccount{
  private double balance;
  private  String accountNumber= "1000";

   private Customer accountHolder;

  // Getters
  public double getBalance(){
    return this.balance;

  }
  public String getAccountNumber(){
    return this.accountNumber;
  }

public Customer getAccountHolder(){
  return this.accountHolder;
}

//Methods
public String toString(){

  return ("("+this.accountHolder +") " + this.accountNumber +": "+ this.balance);
}

public void transfer(int amount, BankAccount b){
  if(this.balance >= amount){
    this.balance = this.balance-amount;
    b.balance= b.balance+amount;


  }

  // else{
  //   this.balance = this.balance;
  //   b.balance = b.balance;
  // }

}

protected abstract double getMonthlyFeesAndInterest();



public void monthEndUpdate(){
	getMonthlyFeesAndInterest();
	this.balance = this.balance+getMonthlyFeesAndInterest();
	
}


public void saveToTextFile(String filename) throws IOException{
  PrintWriter pw = new PrintWriter(filename);
  pw.println(this.balance);
  pw.println(this.accountNumber);
  if(this.accountHolder == null){
    
    pw.println("null");
    
    
  }
  else{
    accountHolder.save(pw);
  }
  pw.close();




}
// Setters
public void deposit(double deposit){

  if (deposit < 0.0){
    this.balance = this.balance;
  }

  else {
    this.balance = this.balance + deposit;
  }

}



public void withdraw(double withdraw){


  if( withdraw < 0.0){
    this.balance = this.balance;
  }

    else if ( withdraw > balance){
      this.balance = this.balance;
    }

    else{
      this.balance = this.balance -withdraw;
    }

}

public void setAccountHolder(Customer bank_accountHolder){
  this.accountHolder= bank_accountHolder;
}
// Constructors


public BankAccount(){
  this.balance = 0.0;
  this.accountNumber = "1000";
  this.accountHolder = accountHolder;
}

public BankAccount(BufferedReader br)throws IOException{
  String first = br.readLine();
  this.balance = Double.parseDouble(first);
  String second = br.readLine();
  this.accountNumber = second;
  try{
  this.accountHolder = new Customer(br);
  }
   catch(IOException e){
     this.accountHolder = null;
   }



}





public BankAccount(double start_balance){
  this.balance = start_balance;
}

public BankAccount(double start_balance, String accountNumber){
  this.balance = start_balance;
  this.accountNumber = accountNumber;
}

public BankAccount(Customer accountHolder, double start_balance){
  this.accountHolder = accountHolder;
  this.balance = start_balance;





}

public BankAccount(double start_balance, String the_accountNumber, Customer accountHolder){

  this.balance = start_balance;
  this.accountNumber = the_accountNumber;
  this.accountHolder= accountHolder;
  //this.accountHolder =  Customer accountHolder;

}

public BankAccount(BankAccount b){
  this.balance = b.balance;
  this.accountNumber = b.accountNumber;
  this.accountHolder = b.accountHolder;
}





}
