import static org.junit.Assert.*;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;


public class GhostTest{
    public static final String CLASSNAME = "Ghost";
    public static final String FILENAME = "src/"+CLASSNAME+".java";

    @Test
    public void testmove_pos2(){
        Ghost ghost = new Ghost();
        ghost.move("2");
       // int expected[][] = new int[][] {{0, -1}, {0, 1}, {1,-1}, {1, 1}};
        assertEquals("Not in the array",ghost.move("0"), "0");
    }

    @Test
    public void testmove_neg3(){
        Ghost ghost = new Ghost();
        ghost.move("-3");
       // int expected[][] = new int[][] {{0, -1}, {0, 1}, {1,-1}, {1, 1}};
        assertEquals("Not in the array",ghost.move("-1"), "-1");     
    }

    @Test
    public void testmove_pos5() {
        Ghost ghost = new Ghost();
        ghost.move("5");
      //  int expected[][] = new int[][] {{0, -1}, {0, 1}, {1, -1}, {1, 1}};
        assertEquals("Not in the array", ghost.move("1"), "1");
        
    }
}