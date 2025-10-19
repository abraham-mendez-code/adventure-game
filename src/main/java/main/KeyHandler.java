package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    // declare direction booleans for each movement direction
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    // this method is not used
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // this method checks for user key press and updates movement direction
    @Override
    public void keyPressed(KeyEvent e) {

        // geyKeyCode returns the integer keyCode associated with the key in this event
        int code = e.getKeyCode();

        // these statements check if the user pressed one of the WASD keys and updates direction booleans based on input
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }

    }

    // this method checks for user key relead and updated movement direction
    @Override
    public void keyReleased(KeyEvent e) {

        // geyKeyCode returns the integer keyCode associated with the key in this event
        int code = e.getKeyCode();

        // these statements check if the user released one of the WASD keys and updates direction booleans based on input
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

    }
}
