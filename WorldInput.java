import java.io.*;
import java.util.Arrays;
public class WorldInput {
	
      FileReader in = null;
	  String[][] map = new String[10][10];
	  
	  public String[][] getWorldArray(String level){
		  
			 in = new FileReader(level);
			 
			 int i = 0;
			 int j = 0;
			 int c;
			 
			 while ((c = in.read()) != -1) {
				 char y = (char)c;
				  
				String n = String.valueOf(y);
				
				if(n.equals("|") && i < 10){
					i++;
					j = 0;
				}else{
					map[i][j] = n;
					if(j < 10){
						j++;
					}
				}

			 }

			 if (in != null) {
				in.close();
			 }
		  
		 return map;
	  }
	  /*
	  for(int i = 0; i < 9; i++){
		  for(int j = 0; j < 9; j++){
			  System.out.print(map[i][j]);
		  }
		 System.out.println();
	  }
	  */

}