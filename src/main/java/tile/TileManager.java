package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    // CLASS ATTRIBUTES
    GamePanel gp;
    Tile[] tile;
    int mapTileNumber[] []; // this array holds the values for map tiles

    // CONSTRUCTOR
    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10]; // set size of tile array, equal to the amount of different tiles
        mapTileNumber = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW]; // max col by max row

        getTileImage();
        loadMap("maps/world01.txt");
    }

    // this method loads the tile images
    public void getTileImage() {

        System.out.println("Image loading started");
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/grass00.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/water01.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/road00.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Image loading completed");
    }

    // this method loads map tile data from a txt file
    public void loadMap(String filePath) {

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath); // input stream imports a text file
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); // buffered reader allows reading of text file

            int col = 0;
            int row = 0;

            // this loops for every col and row on the screen
            while (col < gp.MAX_WORLD_COL && row < gp.MAX_WORLD_ROW) {

                String line = bufferedReader.readLine(); // read a line from the file

                while (col < gp.MAX_WORLD_COL) { // for every col in current row

                    String[] numbers = line.split(" "); // split the current line into its numbers

                    int number = Integer.parseInt(numbers[col]); // get the number in the current col

                    mapTileNumber[col] [row] = number; // assign the tile number to the current col and row
                    col++; // move to the next col
                }

                if (col == gp.MAX_WORLD_COL) { // if max col is reached
                    col = 0; // reset it
                    row++; // go to next row
                }

            }
            bufferedReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    // this method draws tiles to the GamePanel (screen)
    public void draw(Graphics2D g2) {

        // declare and initialize draw variables
        int worldCol = 0;
        int worldRow = 0;

        // this loop draws
        while (worldCol < gp.MAX_WORLD_COL && worldRow < gp.MAX_WORLD_ROW) {

            int tileNumber = mapTileNumber[worldCol] [worldRow];

            // tile map position
            int worldX = worldCol * gp.TILE_SIZE; // first loop ( 0 * 48 = worldX)
            int worldY = worldRow * gp.TILE_SIZE; // first loop ( 0 * 48 = worldY )

            // tile screen position
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // offset by player world positon and player screen position (center of screen)
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // this if statement draws a tile within a boundary from the center of the screen minus player screenX plus player screenX
            // and minus player screenY plus player screenY.
            if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNumber].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }

            worldCol++;

            if (worldCol == gp.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow++;
            }
        }

    }

}
