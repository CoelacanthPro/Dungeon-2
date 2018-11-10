/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.io.File;

public class Character {
    private Image image;    
    public int currentRow;
    public int currentColumn;
    private int rowDir;
    private int columnDir;
    
    Character(Image _image)
    {
        image = _image;
        rowDir = 0;
        columnDir = -1;
        boolean keepLooping = true;
        while (keepLooping)
        {
            currentRow = (int)(Math.random()*Board.numRows);
            currentColumn = (int)(Math.random()*Board.numColumns);
            if (Board.board[currentRow][currentColumn] == Board.STAR)
                keepLooping = false;
        }  
    }
    public void reset(){
        boolean keepLooping = true;
        while (keepLooping)
        {
            currentRow = (int)(Math.random()*Board.numRows);
            currentColumn = (int)(Math.random()*Board.numColumns);
            if (Board.board[currentRow][currentColumn] == Board.STAR)
                keepLooping = false;
        }  
    }
    public void Draw(Graphics2D g) {
        int ydelta = Window.getHeight2()/Board.numRows;
        int xdelta = Window.getWidth2()/Board.numColumns;
        Drawing.drawImage(image, Window.getX(currentColumn * xdelta-xdelta/2), Window.getY(currentRow*ydelta-ydelta/2), xdelta, ydelta);
    }
    public void animate()
    {
        if (Board.board[currentRow+rowDir][currentColumn+columnDir] != Board.WALL)
        {
            currentColumn+=columnDir;
            currentRow+=rowDir;
        }
    }
    public void move(int reps)
    {
        for(int i=0;i<reps;i++){
            if (Board.board[currentRow+rowDir][currentColumn+columnDir] == Board.WALL)
            {
                if(columnDir == -1){
                    rowDir =-1;
                    columnDir = 0;
                    
                    if (Board.board[currentRow+rowDir][currentColumn+columnDir] == Board.WALL)
                    {
                        rowDir = 1;
                    }
                }
                else if(columnDir == 1){
                    rowDir =-1;
                    columnDir = 0;
                    if (Board.board[currentRow+rowDir][currentColumn+columnDir] == Board.WALL)
                    {
                        rowDir = 1;
                    }
                }
                else if(rowDir == 1){
                    rowDir =0;
                    columnDir = 1;
                    if (Board.board[currentRow+rowDir][currentColumn+columnDir] == Board.WALL)
                    {
                        columnDir = -1;
                    }
                }
                else if(rowDir == -1){
                    rowDir = 0;
                    columnDir = 1;
                    if (Board.board[currentRow+rowDir][currentColumn+columnDir] == Board.WALL)
                    {
                        columnDir = -1;
                    }
                }
            }
            animate();
        }
    }   
}
