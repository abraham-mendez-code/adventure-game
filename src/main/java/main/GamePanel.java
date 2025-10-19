package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel  extends JPanel {

    // SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    final int SCALE = 3; // multiples the originalTileSize by this amount

    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // scale the tile size ( (16x16) x 3 = 48x48 tile)
    final int MAX_SCREEN_COL = 16; // this stores columns (pixels)
    final int MAX_SCREEN_ROW = 12; // this stores rows (pixels)

    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // (48 x 16 = 768 pixels) multiply the columns by the scaled tile size to get the width
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // (48 x 12 = 576 pixels) multiply the rows by the scaled tile size to get the height

    public GamePanel () {

        // this sets the size of this class (JPANEL)
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        // this sets the color of the background for this class (JPANEL)
        this.setBackground(Color.black);

        // if set to true, all the drawing from this component will be done in an offscreen painting buffer
        // improves the game's rendering performance
        this.setDoubleBuffered(true);

    }

}
