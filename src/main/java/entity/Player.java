package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.SCREEN_WIDTH / 2 - (gamePanel.TILE_SIZE / 2); // center of the screen offset by center of player sprite size
        screenY = gamePanel.SCREEN_HEIGHT / 2 - (gamePanel.TILE_SIZE / 2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gamePanel.TILE_SIZE * 23;
        worldY = gamePanel.TILE_SIZE * 21;
        speed = 4;
        direction = "down";
    }

    // this method sets the player image for each direction
    public void getPlayerImage() {

        try {

            // set the image for each direction
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_up_2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_down_2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_left_2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_right_2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // this methods updates player positon and direction faced based on key input 60/second
    public void update() {

        // this will update player positon and direction based on key input ONLY IF A KEY IS BEING PRESSED
        if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true) {
            // check which key is being pressed
            if (keyHandler.upPressed == true) {
                direction = "up";
                worldY -= speed;
            } else if (keyHandler.downPressed == true) {
                direction = "down";
                worldY += speed;
            } else if (keyHandler.leftPressed == true) {
                direction = "left";
                worldX -= speed;
            } else if (keyHandler.rightPressed == true) {
                direction = "right";
                worldX += speed;
            }

            spriteCounter++; // increase sprite counter every frame
            if (spriteCounter > 12) { // after 12 frames
                if (spriteNumber == 1) { // if the sprite number is 1
                    spriteNumber = 2; // change it to 2
                } else if (spriteNumber == 2) { // otherwise if the sprite number is 2
                    spriteNumber = 1; // change it to 1
                }
                spriteCounter = 0; // reset sprite counter every 10 frames
            }
        }
    }

    public void draw(Graphics2D  g2) {
        BufferedImage image = null;

        // this switch sets the image based on the direction
        switch(direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber ==2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber ==2) {
                    image = right2;
                }
                break;
        }

        // Draw the image based on direction
        g2.drawImage(image, screenX, screenY, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);

    }
}
