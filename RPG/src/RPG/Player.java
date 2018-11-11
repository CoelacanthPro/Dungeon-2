/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPG;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.io.File;
public class Player extends Character {
    private int xChange;
    private int yChange;
    Player(Image _image){
        xPos = Window.getWidth2()/2;
        yPos = Window.getHeight2()/2;
        image = _image;
        xChange =  0;
        yChange = 0;
    }
    public void move(){
        xPos += xChange;
        yPos += yChange;
        
    }
    public void animate(){
        checkCollide();
        move();
        xChange = 0;
        yChange = 0;
    }
    public void checkCollide(){
        int returnVal = Board.Collide(xPos,xPos+Drawing.getImageWidth(image),yPos,yPos+Drawing.getImageHeight(image),xChange,yChange);
        if(returnVal == 0){
            return;
        }else if(returnVal == 1){
            
        }
    }
    public void moveUp(){
        yChange = -5;       
    }
    public void moveDown(){
        yChange = 5;
    }
    public void moveRight(){
        xChange = 5;
    }
    public void moveLeft(){
        xChange = -5;
    }
    public int getXChange(){
        return xChange;
    }
    public int getYChange(){
        return yChange;
    }
}
