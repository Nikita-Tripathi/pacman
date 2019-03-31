import java.io.*;
import java.io.BufferedReader;

public class Customer{
  private String name;
  private int customerID;

  public Customer(BufferedReader br) throws IOException{
    //try{
      String line;
      line = br.readLine();
      
        //if(line == ""){
        //  this.name = "Linda";
        //  this.customerID = 0;
        //}
        if(line.equals("null")){
            throw new IOException("Customer is null in file");
          }
        else{
          this.name = line;
        }
        String secondLine;
        secondLine = br.readLine();
        if(secondLine == null){
          throw new IOException("No customer ID found in file");
        }
        else{
          this.customerID = Integer.parseInt(secondLine);
        }
    //}
    // catch(EOFException e){
    //       this.name = "Linda";
    //       this.customerID = 0;

    //}
    
        

  }

  public Customer(){
    this.name = " ";
    this.customerID = 0;
  }

  public Customer(String customer_name){
    this.name = customer_name;
    this.customerID = 0;
  }

  public Customer(String customer_name, int person_ID){
     this.name = customer_name;
    this.customerID =  person_ID;
  }

  public Customer(Customer c)
  {
      this.name=c.name;
      this.customerID=c.customerID;
  }



// Getter (accessor) for customer's name
  public String getName(){
    return this.name;
  }
// Getter (accessor) for customer's ID
  public int getID(){

    return this.customerID;
  }
// The format for representing the name and customer ID together as a string
  public String toString(){
    return(this.name + " " +this.customerID);
  }

  public void setName(String givenName){
    this.name=givenName;
    }
  // Setter(mutator)
  public void setID(int givenID){
    this.customerID = givenID;
  }

  public void save(PrintWriter pw) throws IOException{
    pw.println(this.name);
    pw.println(this.customerID);
    pw.close();



  }
}
