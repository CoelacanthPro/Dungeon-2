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
    public static int Collide(int xmin,int xmax,int ymin, int ymax, int xChange, int yChange){
        int currentMinColumn = 0;
        int currentMaxColumn = 0;
        int currentMinRow = 0;
        int currentMaxRow = 0;
        int ydelta = Window.getHeight2()/numRows;
        int xdelta = Window.getWidth2()/numColumns;
        {
            int currentXVal=currentMinColumn;
            while(currentXVal < xmin){
                currentMinColumn ++;
                currentXVal += xdelta;
            }
            int currentYVal=currentMinRow;
            while(currentYVal < ymin){
                currentMinRow ++;
                currentYVal += ydelta;
            }
        }
        {
            int currentXVal=currentMaxColumn;
            while(currentXVal < xmax){
                currentMaxColumn ++;
                currentXVal += xdelta;
            }
            int currentYVal=currentMaxRow;
            while(currentYVal < ymax){
                currentMaxRow ++;
                currentYVal += ydelta;
            }
        }
        if(board[currentMinRow-1][0] == WALL){
            if(board[currentMinRow][currentMinColumn-1] == WALL){
                
            }
        }
    }
}


