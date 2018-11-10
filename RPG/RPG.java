/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPG;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

public class RPG extends JFrame implements Runnable {
    static final int numRows = 23;
    static final int numColumns = 27;
    static final int XBORDER = 20;
    static final int YBORDER = 20;
    static final int YTITLE = 30;
    static final int WINDOW_BORDER = 8;
    static final int WINDOW_WIDTH = 2*(WINDOW_BORDER + XBORDER) + numColumns*30;
    static final int WINDOW_HEIGHT = YTITLE + WINDOW_BORDER + 2 * YBORDER + numRows*30;
    
    boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    Image image;
    Graphics2D g;
    
//Possible values for the board.
    final int EMPTY = 0;
    final int SNAKE = 1;
    final int BAD_BOX = 2;
    int board[][];

//Current location of the head of the snake.    
    int currentRow;
    int currentColumn;
//Direction of the snake.    
    int rowDir;
    int columnDir;
//Speed of the snake.    
    int snakeSpeed;
    
    int Portalrow;
    int Portalcolumn;
    int Portalrow2;
    int Portalcolumn2;    
    
    boolean gameOver;
    int score;
    int timeCount; 
    int addNumBadBoxes;

    static RPG frame;
    public static void RPG(String[] args) {
        frame = new RPG();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public RPG() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button

// location of the cursor.
                    int xpos = e.getX();
                    int ypos = e.getY();

                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {

        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
//Change the direction of the snake with the arrow keys.
                if (e.VK_UP == e.getKeyCode()) {
                    rowDir = -1;
                    columnDir = 0;
                } else if (e.VK_DOWN == e.getKeyCode()) {
                    rowDir = 1;
                    columnDir = 0;
                } else if (e.VK_LEFT == e.getKeyCode()) {
                    rowDir = 0;
                    columnDir = -1;
                } else if (e.VK_RIGHT == e.getKeyCode()) {
                    rowDir = 0;
                    columnDir = 1;
                }else if (e.VK_SPACE == e.getKeyCode()) {
                if (rowDir == 1)
                {
                    int val = currentRow + 5;
                    if (val > numRows - 1)
                        val = val - numRows;
                    for (int i=currentRow+1;i  < val;i++)
                        board[i][currentColumn] = EMPTY;
                }
                else if (rowDir == -1)
                {
                    int val = currentRow - 5;
                    if (val > numRows - 1)
                        val = numRows;
                    for (int i=currentRow-1;i>currentRow-5;i--)
                        board[i][currentColumn] = EMPTY;
                }
                 if (rowDir == 1)
                {
                    int val = currentRow + 5;
                    if (val > numRows)
                        val = val - numRows;
                    for (int i=currentRow+1;i<currentRow+5;i++)
                        board[i][currentColumn] = EMPTY;
                }
                }                
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }



////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || xsize != getSize().width || ysize != getSize().height) {
            xsize = getSize().width;
            ysize = getSize().height;
            image = createImage(xsize, ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background
        g.setColor(Color.cyan);
        g.fillRect(0, 0, xsize, ysize);

        int x[] = {getX(0), getX(getWidth2()), getX(getWidth2()), getX(0), getX(0)};
        int y[] = {getY(0), getY(0), getY(getHeight2()), getY(getHeight2()), getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.red);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        
        g.setColor(Color.red);
//horizontal lines
        for (int zi=1;zi<numRows;zi++)
        {
            g.drawLine(getX(0) ,getY(0)+zi*getHeight2()/numRows ,
            getX(getWidth2()) ,getY(0)+zi*getHeight2()/numRows );
        }
//vertical lines
        for (int zi=1;zi<numColumns;zi++)
        {
            g.drawLine(getX(0)+zi*getWidth2()/numColumns ,getY(0) ,
            getX(0)+zi*getWidth2()/numColumns,getY(getHeight2())  );
        }
        
//Display the objects of the board
        for (int zrow=0;zrow<numRows;zrow++)
        {
            for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
            {
                if (board[zrow][zcolumn] == SNAKE)
                {
                    g.setColor(Color.gray);
                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
                else if (board[zrow][zcolumn] == BAD_BOX)
                {
                    g.setColor(Color.red);
                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
            }
        }
        
                {
                    g.setColor(Color.pink);
                    g.fillRect(getX(0)+Portalcolumn*getWidth2()/numColumns,
                    getY(0)+Portalrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
                {
                    g.setColor(Color.orange);
                    g.fillRect(getX(0)+Portalcolumn2*getWidth2()/numColumns,
                    getY(0)+Portalrow2*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }                
                   
        g.setColor(Color.black);
        g.setFont(new Font("Arial",Font.PLAIN,16));
        g.drawString("Score = " + score,40,46);                       

        if (gameOver)
        {
            g.setColor(Color.black);
            g.setFont(new Font("Arial",Font.PLAIN,50));
            g.drawString("GAME OVER",100,200);                       
        }
        gOld.drawImage(image, 0, 0, null);
    }

////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = .1;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {
//Allocate memory for the 2D array that represents the board.
        board = new int[numRows][numColumns];
//Initialize the board to be empty.
        for (int zrow = 0;zrow < numRows;zrow++)
        {
            for (int zcolumn = 0;zcolumn < numColumns;zcolumn++)
                board[zrow][zcolumn] = EMPTY;
        }
//Start the snake in the middle of the board.
        currentRow = numRows/2;
        currentColumn = numColumns/2;
        board[currentRow][currentColumn] = SNAKE;
        score = 1;
        rowDir = 0;
        columnDir = 0;
        gameOver = false;
        timeCount = 0;
        addNumBadBoxes = 1;
        snakeSpeed = 3;
        Portalrow = (int)(Math.random()*numRows);
        Portalcolumn = (int)(Math.random()*numColumns);
        Portalrow2 = (int)(Math.random()*numRows);
        Portalcolumn2 = (int)(Math.random()*numColumns);        
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height) {
                xsize = getSize().width;
                ysize = getSize().height;
            }
            reset();
        }
                 
        if (gameOver)
            return;

//Return from animate if the snake is not moving.
        if (columnDir == 0 && rowDir == 0)
            return;

//Determine what happens when the snake moves.
        if (timeCount % snakeSpeed == snakeSpeed-1)
        {
//Game over if the snake hits one of the borders.
            if (currentColumn+columnDir < 0)
            {
                gameOver = true;
            }
            else if (currentColumn+columnDir >= numColumns)
            {
                gameOver = true;
            }
            else if (currentRow+rowDir < 0)
            {
                gameOver = true;
            }
            else if (currentRow+rowDir >= numRows)
            {
                gameOver = true;
            }
//Game over if the snake runs into itself.
            else if (board[currentRow+rowDir][currentColumn+columnDir] == SNAKE)
            {
                gameOver = true;
            }
//Game over if the snake runs into a bad box.
            else if (board[currentRow+rowDir][currentColumn+columnDir] == BAD_BOX)
            {
                gameOver = true;
            }
            else if (currentRow == Portalrow && currentColumn == Portalcolumn)
            {
                currentRow = Portalrow2;
                currentColumn = Portalcolumn2;
                Portalrow = (int)(Math.random()*numRows);
                Portalcolumn = (int)(Math.random()*numColumns);
                Portalrow2 = (int)(Math.random()*numRows);
                Portalcolumn2 = (int)(Math.random()*numColumns);                
            }            
            else
            {
//Move the head of the snake to its next location.
                currentColumn+=columnDir;
                currentRow+=rowDir;
                board[currentRow][currentColumn] = SNAKE;
                score++;
            }          
        }
        
        if (timeCount % 50 == 49)
        {
            Portalrow = (int)(Math.random()*numRows);
            Portalcolumn = (int)(Math.random()*numColumns);
            Portalrow2 = (int)(Math.random()*numRows);
            Portalcolumn2 = (int)(Math.random()*numColumns); 
        }
        
//Add more bad boxes to the board.
        if (timeCount % 20 == 19)
        {
            for (int count=0;count<addNumBadBoxes;count++)
            {
                int badBoxRow = (int)(Math.random()*numRows);
                int badBoxColumn = (int)(Math.random()*numColumns);
                board[badBoxRow][badBoxColumn] = BAD_BOX;  
            }
            addNumBadBoxes++;
        }
        
        timeCount++;
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }


/////////////////////////////////////////////////////////////////////////
    public int getX(int x) {
        return (x + XBORDER + WINDOW_BORDER);
    }

    public int getY(int y) {
        return (y + YBORDER + YTITLE );
    }

    public int getYNormal(int y) {
        return (-y + YBORDER + YTITLE + getHeight2());
    }
    
    public int getWidth2() {
        return (xsize - 2 * (XBORDER + WINDOW_BORDER));
    }

    public int getHeight2() {
        return (ysize - 2 * YBORDER - WINDOW_BORDER - YTITLE);
    }
}