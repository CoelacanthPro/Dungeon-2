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
        xChange = 0;
        yChange = 0;
    }
    public void animate(){
        move();
        checkCollide();
    }
    public void checkCollide(){
            
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
}
