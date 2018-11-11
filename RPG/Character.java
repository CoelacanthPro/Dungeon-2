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

public class Character {
    protected int xPos;
    protected int yPos;
    protected Image image;
    
    Character()
    {
    }
    public void Draw(){
        Drawing.drawImage(image, xPos,  yPos, 50, 50);
    }
    public void move(int x, int y){
        xPos += x;
        yPos += y;
    }
}
