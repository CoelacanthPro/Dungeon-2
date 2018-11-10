
package eels.and.escalators;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import java.io.File;
import java.util.ArrayList;

public class EelsAndEscalators extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
    
    Image[] dice = new Image[6];
   
    Character SPONGEBOB = new Character(Toolkit.getDefaultToolkit().getImage("./spongebob.png"));
    Character PATRICK = new Character(Toolkit.getDefaultToolkit().getImage("./patrick.jpg"));
    Character SQUIDWARD = new Character(Toolkit.getDefaultToolkit().getImage("./squidward.jpg"));
    Character MR_KRABS = new Character(Toolkit.getDefaultToolkit().getImage("./mr krabs.jpg"));
    
    ArrayList<Eel> eels = new ArrayList<Eel>();
    ArrayList<Escalator> escalators = new ArrayList<Escalator>();
    
    int diceNum;
    int currentDie;
    boolean stopDiceRoll;
    
    int timeCount; 

    static EelsAndEscalators frame;
    public static void main(String[] args) {
        frame = new EelsAndEscalators();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public EelsAndEscalators() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button

// location of the cursor.
                    int xpos = e.getX();
                    int ypos = e.getY();
                    stopDiceRoll = true;
                    

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

                if (e.VK_UP == e.getKeyCode()) {
                } else if (e.VK_DOWN == e.getKeyCode()) {
                } else if (e.VK_LEFT == e.getKeyCode()) {
                } else if (e.VK_RIGHT == e.getKeyCode()) {
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
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            Drawing.setDrawingInfo(g,this);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background
        g.setColor(Color.cyan);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.red);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.cyan);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        
        g.setColor(Color.cyan);
//horizontal lines
        for (int zi=1;zi<Board.numRows;zi++)
        {
            g.drawLine(Window.getX(0) ,Window.getY(0)+zi*Window.getHeight2()/Board.numRows ,
            Window.getX(Window.getWidth2()) ,Window.getY(0)+zi*Window.getHeight2()/Board.numRows );
        }
//vertical lines
        for (int zi=1;zi<Board.numColumns;zi++)
        {
            g.drawLine(Window.getX(0)+zi*Window.getWidth2()/Board.numColumns ,Window.getY(0) ,
            Window.getX(0)+zi*Window.getWidth2()/Board.numColumns,Window.getY(Window.getHeight2())  );
        }
        
//Display the objects of the board
        for (int zrow=0;zrow<Board.numRows;zrow++)
        {
            for (int zcolumn=0;zcolumn<Board.numColumns;zcolumn++)
            {
                if (Board.board[zrow][zcolumn] == Board.WALL)
                {
                    g.setColor(Color.cyan);
                    g.fillRect(Window.getX(0)+zcolumn*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+zrow*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows);
                }
                else if (Board.board[zrow][zcolumn] == Board.STAR)
                {
                    g.setColor(Color.green);
                    g.fillRect(Window.getX(0)+zcolumn*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+zrow*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows);
                }
                else if (Board.board[zrow][zcolumn] == Board._END)
                {
                    g.setColor(Color.black);
                    g.fillRect(Window.getX(0)+zcolumn*Window.getWidth2()/Board.numColumns,
                    Window.getY(0)+zrow*Window.getHeight2()/Board.numRows,
                    Window.getWidth2()/Board.numColumns,
                    Window.getHeight2()/Board.numRows);
                }
            }
        }
        for(int i=0; i<eels.size();i++){
            eels.get(i).draw();
        }
        for(int i=0; i<escalators.size();i++){
            escalators.get(i).draw();
        }
        PATRICK.Draw(g);
        SPONGEBOB.Draw(g);
        SQUIDWARD.Draw(g);
        MR_KRABS.Draw(g);    
             
            Drawing.drawImage(dice[diceNum], Window.getWidth2()-(Window.getWidth2()/Board.numRows), Window.getY(0),Window.getWidth2()/Board.numColumns,Window.getHeight2()/Board.numRows);
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
        timeCount = 0;
        diceNum = 0;
        currentDie = 0;
        stopDiceRoll = false;
        dice[0] = Toolkit.getDefaultToolkit().getImage("./Die1.png");
        dice[1] = Toolkit.getDefaultToolkit().getImage("./Die2.png");
        dice[2] = Toolkit.getDefaultToolkit().getImage("./Die3.png");
        dice[3] = Toolkit.getDefaultToolkit().getImage("./Die4.png");
        dice[4] = Toolkit.getDefaultToolkit().getImage("./Die5.png");
        dice[5] = Toolkit.getDefaultToolkit().getImage("./Die6.png");
        eels.add(new Eel(8,1,8,15));
        eels.add(new Eel(9,7,9,9));
        eels.add(new Eel(6,5,4,13));
        escalators.add(new Escalator(6,15,8,13));
        escalators.add(new Escalator(6,11,2,5));
        escalators.add(new Escalator(8,3,3,1));
        
        Player.reset();
        SPONGEBOB.reset();
        PATRICK.reset();
        SQUIDWARD.reset();
        MR_KRABS.reset();

    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            reset();
        }
        
        timeCount++;       
        if(!stopDiceRoll){
            if(currentDie == 0){
                diceNum++;
                if(diceNum>=6){
                    diceNum = 1;
                }
            }
        }else if(Player.currentPlayer == Player.getPlayerIndex(0)){
            currentDie=diceNum+1;
            SPONGEBOB.move(currentDie);
            for(int i=0; i<eels.size();i++){
                if(eels.get(i).checkTouched(SPONGEBOB.currentRow,SPONGEBOB.currentColumn )){
                    SPONGEBOB.currentColumn = eels.get(i).getEndCol();
                    SPONGEBOB.currentRow = eels.get(i).getEndRow();
                }
            }
            for(int i=0; i<escalators.size();i++){
                if(escalators.get(i).checkTouched(SPONGEBOB.currentRow,SPONGEBOB.currentColumn )){
                    SPONGEBOB.currentColumn = escalators.get(i).getEndCol();
                    SPONGEBOB.currentRow = escalators.get(i).getEndRow();
                }
            }
            stopDiceRoll = false;
            currentDie = 0;
            Player.switchTurn();    
            System.out.println("spongeBob");
        }else if(Player.currentPlayer == Player.getPlayerIndex(1)){
            currentDie=diceNum+1;
            PATRICK.move(currentDie);
            for(int i=0; i<escalators.size();i++){
                if(eels.get(i).checkTouched(PATRICK.currentRow,PATRICK.currentColumn )){
                    PATRICK.currentColumn = eels.get(i).getEndCol();
                    PATRICK.currentRow = eels.get(i).getEndRow();
                }
            }
            for(int i=0; i<escalators.size();i++){
                if(escalators.get(i).checkTouched(PATRICK.currentRow,PATRICK.currentColumn )){
                    PATRICK.currentColumn = escalators.get(i).getEndCol();
                    PATRICK.currentRow = escalators.get(i).getEndRow();
                }
            }
            stopDiceRoll = false;
            currentDie = 0;
            Player.switchTurn();      
            System.out.println("patrick");
        }else if(Player.currentPlayer == Player.getPlayerIndex(2)){
            currentDie=diceNum+1;
            SQUIDWARD.move(currentDie);
            for(int i=0; i<eels.size();i++){
                if(eels.get(i).checkTouched(SQUIDWARD.currentRow,SQUIDWARD.currentColumn )){
                    SQUIDWARD.currentColumn = eels.get(i).getEndCol();
                    SQUIDWARD.currentRow = eels.get(i).getEndRow();
                }
            }
            for(int i=0; i<escalators.size();i++){
                if(escalators.get(i).checkTouched(SQUIDWARD.currentRow,SQUIDWARD.currentColumn )){
                    SQUIDWARD.currentColumn = escalators.get(i).getEndCol();
                    SQUIDWARD.currentRow = escalators.get(i).getEndRow();
                }
            }
            stopDiceRoll = false;
            currentDie = 0;
            Player.switchTurn();      
            System.out.println("squidward");
        }else if(Player.currentPlayer == Player.getPlayerIndex(3)){
            
            currentDie=diceNum+1;
            MR_KRABS.move(currentDie);
            for(int i=0; i<eels.size();i++){
                if(eels.get(i).checkTouched(MR_KRABS.currentRow,MR_KRABS.currentColumn )){
                    MR_KRABS.currentColumn = eels.get(i).getEndCol();
                    MR_KRABS.currentRow = eels.get(i).getEndRow();
                }
            }
            for(int i=0; i<escalators.size();i++){
                if(escalators.get(i).checkTouched(MR_KRABS.currentRow,MR_KRABS.currentColumn )){
                    MR_KRABS.currentColumn = eels.get(i).getEndCol();
                    MR_KRABS.currentRow = eels.get(i).getEndRow();
                }
            }
            stopDiceRoll = false;
            currentDie = 0;
            Player.switchTurn();      
            System.out.println("mr krabs");
        }                       
        
        
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
}
