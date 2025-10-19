package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    final int SCALE = 3; // multiples the originalTileSize by this amount

    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // scale the tile size ( (16x16) x 3 = 48x48 tile)
    final int MAX_SCREEN_COL = 16; // this stores columns (pixels)
    final int MAX_SCREEN_ROW = 12; // this stores rows (pixels)

    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // (48 x 16 = 768 pixels) multiply the columns by the scaled tile size to get the width
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // (48 x 12 = 576 pixels) multiply the rows by the scaled tile size to get the height

    Thread gameThread; // Thread allows for control over starting and stopping of a program, can be used for game FPS

    public GamePanel () {

        // this sets the size of this class (JPANEL)
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        // this sets the color of the background for this class (JPANEL)
        this.setBackground(Color.black);

        // if set to true, all the drawing from this component will be done in an offscreen painting buffer
        // improves the game's rendering performance
        this.setDoubleBuffered(true);

    }

    // this method instantiates the game thread  constructor with this class
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start(); // called the run method
    }


    // this method contains the game loop
    @Override
    public void run() {

        // this loop runs as long as the gameThread exists
        while (gameThread != null) {

            System.out.println("The game loop is running");

            // 1 UPDATE: update information such as character positions
            update();

            // 2 DRAW: draw the screen with the updated information
            repaint(); // this is calling the paintComponent method

        }

    }

    public void update() {

    }

    // this is a built-in method for JPanel to draw to the panel, passes the Graphics class which has functions to draw objects on the screen.
    public void paintComponent(Graphics g) {


        // this is needed whenever paintComponent is called (JPanel.paintComponent(g)
        super.paintComponent(g);

        // declare a 2D graphics object and, assign it Graphics cast as Graphics2D
        Graphics2D g2 =  (Graphics2D) g;

        // this sets a color to use for drawing objects
        g2.setColor(Color.white);

        // this draws a rectangle to the screen, this will be the player object drawn at position (100, 100) with a height and width of TILE_SIZE
        g2.fillRect(100, 100, TILE_SIZE, TILE_SIZE);

        g2.dispose(); // dispose of this graphics context and release any system resources that it is using.
    }
}
