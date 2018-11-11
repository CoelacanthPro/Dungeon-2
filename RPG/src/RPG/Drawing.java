/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPG;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Drawing {
    private static Graphics2D g;
    private static RPG mainClassInst;

    public static void setDrawingInfo(Graphics2D _g,RPG _mainClassInst) {
        g = _g;
        mainClassInst = _mainClassInst;
    }
////////////////////////////////////////////////////////////////////////////
    public static void drawCircle(int xpos,int ypos,double rot,double xscale,double yscale,Color color)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor(color);
        g.fillOval(0,0,20,20);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
////////////////////////////////////////////////////////////////////////////
    public static void drawImage(Image image,int xpos,int ypos, int _width, int _height) {
        int ydelta = Window.getHeight2()/Board.numRows;
        int xdelta = Window.getWidth2()/Board.numColumns;
        int width = _width;
        int height = _height;
        g.translate(xpos,ypos);

        g.drawImage(image,width/2,height/2,
        width,height,mainClassInst);

        g.translate(-xpos,-ypos);
    }
    ////////////////////////////////////////////////////////////////////////////
    public static void drawRectangle(int xpos,int ypos,double rot,double xscale,double yscale,Color color)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor(color);
        g.fillRect(0,0,20,20);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    public static int getImageWidth(Image image){
        return(image.getWidth(mainClassInst));
    }
    public static int getImageHeight(Image image){
        return(image.getHeight(mainClassInst));
    }
}
