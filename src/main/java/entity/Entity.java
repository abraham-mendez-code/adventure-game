package entity;

import java.awt.image.BufferedImage;

// This stores variables that will be used in player, monster and NPC classes.
public class Entity {

    public int x,y; //position
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // movement animation images
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;
}
