package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {

        // this will update player positon based on key input
        if(keyHandler.upPressed == true) {
            y -= speed;
        }
        else if (keyHandler.downPressed == true) {
            y += speed;
        }
        else if (keyHandler.leftPressed == true) {
            x -= speed;
        }
        else if (keyHandler.rightPressed == true) {
            x += speed;
        }

    }

    public void draw(Graphics2D  g2) {
        // this sets a color to use for drawing objects
        g2.setColor(Color.white);

        // this draws a rectangle to the screen, this will be the player object drawn at (playerX, playerY) with a height and width of TILE_SIZE
        g2.fillRect(x, y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }
}
