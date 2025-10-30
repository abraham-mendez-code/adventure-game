package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    // CLASS ATTRIBUTES
    GamePanel gp;
    KeyHandler keyHandler;

    // PLAYER POSITION ON SCREEN
    public final int screenX;
    public final int screenY;

    // CONSTRUCTOR
    public Player(GamePanel gp, KeyHandler keyHandler) {

        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.SCREEN_WIDTH / 2 - (gp.TILE_SIZE / 2); // center of the screen offset by center of player sprite size
        screenY = gp.SCREEN_HEIGHT / 2 - (gp.TILE_SIZE / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    // this method sets the default player positon to the center of the screen
    public void setDefaultValues() {

        worldX = gp.TILE_SIZE * 23; // (48 * 23)
        worldY = gp.TILE_SIZE * 21; // (48 * 21)
        speed = 4;
        direction = "down";
    }

    // this method sets the player image for each movement direction
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

    // this method updates player positon and direction faced based on key input 60/second, also checks for collision
    public void update() {

        // this will update player positon and direction based on key input ONLY IF A KEY IS BEING PRESSED
        if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true) {
            // check which key is being pressed
            if (keyHandler.upPressed == true) {
                direction = "up";
            } else if (keyHandler.downPressed == true) {
                direction = "down";
            } else if (keyHandler.leftPressed == true) {
                direction = "left";
            } else if (keyHandler.rightPressed == true) {
                direction = "right";
            }

            // check tile collision;
            collisionOn = false;
            gp.collisionManager.checkTile(this); // check tile for collision

            // if collision is false, player can move
            if (!collisionOn) {

                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }

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

    // this method draws the player movement image based on direction
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
        g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);

    }
}
