package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

// This stores variables that will be used in player, monster and NPC classes.
public class Entity {

    // CLASS ATTRIBUTES

    // POSITION AND MOVEMENT
    public int worldX, worldY; //position
    public int speed;

    // DIRECTION AND ANIMATION IMAGES
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // movement animation images
    public String direction;

    // ANIMATION FRAME COUNTER
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    // COLLISION SETTINGS
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
