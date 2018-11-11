/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPG;
import java.awt.Color;
import java.awt.Graphics2D;

public class Board {
    static final int numRows = 10;
    static final int numColumns = 10;
//Possible values for the board.
    static final int PATH = 0;
    static final int WALL = 1;

    static int board[][] = {
   {WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL},     
   {WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL},
   {WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL},  
   {WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL},  
   {WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL},   
   {WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL},
   {WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL},  
   {WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL},  
   {WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL},   
   {WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL}
   }; 
} 

