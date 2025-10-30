package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    // class attributes
    GamePanel gp;
    Tile[] tile;
    int mapTileNumber[] []; // this array holds the values for map tiles

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10]; // set size of tile array, equal to the amount of different tiles
        mapTileNumber = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW]; // max col by max row

        getTileImage();
        loadMap("maps/maps01.txt");
    }

    // this method loads the tile images
    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/grass00.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/water01.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/road00.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath); // input stream imports a text file
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); // buffered reader allows reading of text file

            int col = 0;
            int row = 0;

            // this loops for every col and row on the screen
            while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {

                String line = bufferedReader.readLine(); // read a line from the file

                while (col < gp.MAX_SCREEN_COL) { // for every col in current row

                    String[] numbers = line.split(" "); // split the current line into its numbers

                    int number = Integer.parseInt(numbers[col]); // get the number in the current col

                    mapTileNumber[col] [row] = number; // assign the tile number to the current col and row
                    col++; // move to the next col
                }

                if (col == gp.MAX_SCREEN_COL) { // if max col is reached
                    col = 0; // reset it
                    row++; // go to next row
                }

            }
            bufferedReader.close();
        }
        catch (Exception e) {

        }


    }

    // draws tiles to the GamePanel
    public void draw(Graphics2D g2) {

        // declare and initialize draw variables
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        // this loop draws
        while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {

            int tileNumber = mapTileNumber[col] [row];

            g2.drawImage(tile[tileNumber].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
            col++;
            x += gp.TILE_SIZE;

            if (col == gp.MAX_SCREEN_COL) {
                col = 0;
                x = 0;
                row++;
                y += gp.TILE_SIZE;
            }
        }

    }

}
