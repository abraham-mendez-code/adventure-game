package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    final int SCALE = 3; // multiples the originalTileSize by this amount

    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // scale the tile size ( (16x16) x 3 = 48x48 tile)
    public final int MAX_SCREEN_COL = 16; // this stores columns (pixels)
    public final int MAX_SCREEN_ROW = 12; // this stores rows (pixels)

    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // (48 x 16 = 768 pixels) multiply the columns by the scaled tile size to get the width
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // (48 x 12 = 576 pixels) multiply the rows by the scaled tile size to get the height

    // WORLD SETTINGS
    public final int MAX_WORLD_COL = 50;
    public final int MAX_WORLD_ROW = 50;
    public final int MAX_WORLD_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public final int MAX_WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROW;

    // FPS
    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler(); // KeyHandler allows receipt of user keyboard input
    Thread gameThread; // Thread allows for control over starting and stopping of a program, can be used for game FPS
    public Player player = new Player(this, keyHandler); // Instantiate a player class with this game panel and the keyHandler


    public GamePanel () {

        // this sets the size of this class (JPANEL)
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        // this sets the color of the background for this class (JPANEL)
        this.setBackground(Color.black);

        // if set to true, all the drawing from this component will be done in an offscreen painting buffer
        // improves the game's rendering performance
        this.setDoubleBuffered(true);

        // this lets the game panel handle key input
        this.addKeyListener(keyHandler);

        // setFocusable lets the GamePanel be focused for key input
        this.setFocusable(true);

    }

    // this method instantiates the game thread  constructor with this class
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start(); // called the run method
    }


    /*
    //  This method uses the "Sleep" method for game loop
    @Override
    public void run() {

        // System.nanoTime() returns the current value of the running JVM time source in nanoseconds
        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval; // this makes the nextDrawTime .01666 seconds from now

        // this loop runs as long as the gameThread exists
        while (gameThread != null) {

            // 1 UPDATE: update information such as character positions
            update();

            // 2 DRAW: draw the screen with the updated information
            repaint(); // this is calling the paintComponent method

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // subtract the current time from nextDrawTime
                remainingTime = remainingTime/1000000; // convert the remaining time from nanoseconds to milliseconds

                if (remainingTime < 0) {
                    remainingTime = 0; // if the update and draw time took longer than the drawInterval, no time is left no need to sleep
                }

                Thread.sleep((long) remainingTime); // sleep the thread for the remaining time (pause the game)

                nextDrawTime += drawInterval; // nextDrawTime will be 0.01666 seconds later
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    */

    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        // check FPS
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval; // add the passed time divided by draw interval to delta
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }


    public void update() {

        player.update();
    }

    // this is a built-in method for JPanel to draw to the panel, passes the Graphics class which has functions to draw objects on the screen.
    public void paintComponent(Graphics g) {


        // this is needed whenever paintComponent is called (JPanel.paintComponent(g)
        super.paintComponent(g);

        // declare a 2D graphics object and, assign it Graphics cast as Graphics2D
        Graphics2D g2 =  (Graphics2D) g;

        // draw in layers, last draw is at the top, first draw is at the bottom
        tileManager.draw(g2);
        player.draw(g2);

        g2.dispose(); // dispose of this graphics context and release any system resources that it is using.
    }
}
