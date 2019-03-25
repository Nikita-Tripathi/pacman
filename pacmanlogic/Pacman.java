
package pacmanlogic;

/**
 * pacman
 */
public class Pacman extends Entity{
	
	public int[] prevMove = new int[] {0, -1};
	
	/**
	This method takes in user input and converts it into the correct format to be used in Brain.java
	*/
    public int[] move(String input){
        System.out.println(input);
		int[] direction;
			
		System.out.println(prevMove[0] + "  " + prevMove[1]);
		switch(input) { 
			case "w":
				direction = new int[] {0, -1};
				break;

			case "s":
				direction = new int[] {0, 1};
				break;

			case "a":
				direction = new int[] {1, -1};
				break;

			case "d":
				direction = new int[] {1, 1};
				break;

            default:
				// previous move
                return prevMove;
		}
		this.prevMove = direction;

        return direction;
		
	}
	
}
