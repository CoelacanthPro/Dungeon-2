
package eels.and.escalators;

import java.awt.Color;

public class Eel {
    private int startCol;
    private int startRow;
    private int endCol;
    private int endRow;
    Eel(int _startCol, int _startRow, int _endCol, int _endRow){
        startCol = _startCol;
        startRow = _startRow;
        endCol = _endCol;
        endRow = _endRow;
    }
    public void draw(){
        int ydelta = Window.getHeight2()/Board.numRows;
        int xdelta = Window.getWidth2()/Board.numColumns;
        Drawing.drawCircle(Window.getX(startCol*xdelta),Window.getY(startRow*ydelta) ,0,2.5,2.5, Color.green);
        Drawing.drawCircle(Window.getX(endCol*xdelta),Window.getY(endRow*ydelta) ,0,2.5,2.5, Color.green);
    }
    public boolean checkTouched(int characterRow, int characterCol){
        if(characterRow == startRow && characterCol == startCol){
            return(true);
        }
        return(false);
    }
    public int getStartRow(){
        return(startRow);
    }
    public int getStartCol(){
        return(startCol);
    }
    public int getEndRow(){
        return(endRow);
    }
    public int getEndCol(){
        return(endCol);
    }
}
