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

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    // this method sets the player image for each direction
    public void getPlayerImage() {

        try {

            // set the image for each direction
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("/player/player_up_2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("/player/player_down_2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("/player/player_left_2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("/player/player_right_2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // this methods updates player positon and direction faced based on key input
    public void update() {

        // this will update player positon and direction based on key input
        if(keyHandler.upPressed == true) {
            direction = "up";
            y -= speed;
        }
        else if (keyHandler.downPressed == true) {
            direction = "down";
            y += speed;
        }
        else if (keyHandler.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
        else if (keyHandler.rightPressed == true) {
            direction = "right";
            x += speed;
        }

    }

    public void draw(Graphics2D  g2) {
        BufferedImage image = null;

        // this switch sets the image based on the direction
        switch(direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        // Draw the image based on direction
        g2.drawImage(image, x, y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);

    }
}
