package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;
    int mapTileNum[][];

    public TileManager(GamePanel gamePanel){

        this.gamePanel = gamePanel;

        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];

        getTileImage();
        loadMap();

    }

    // Load Tile Images
    public void getTileImage(){

        try{

            tiles [0] = new Tile();
            tiles [1] = new Tile();

            tiles [0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/summer_tiles/ground_grass_tiles/first_grass_5.png"));

            tiles [1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/summer_tiles/plant_tiles/plant_1.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void loadMap(){

        try{
            // Import text file
            InputStream inputStream = getClass().getResourceAsStream("/maps/MapData.txt");
            // Read the text file
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){

                // This will put read text file into string
                String line = bufferedReader.readLine();
                // Test String line
                // System.out.println(line);

                while(col < gamePanel.maxScreenCol){

                    // Splits the numbers with space
                    String numbers[] = line.split(" ");

                    // Converts the elements inside the numbers[] array string into integer
                    int num = Integer.parseInt(numbers[col]);

                    // Put the converted integer into the mapTileNum[][] 2D array. Basically, this will look like the MapData.txt but, instead of String, it's 2D array, so every numbers inside MapData.txt can be read and access the same way it was arranged.
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxScreenCol){

                    col = 0;
                    row++;

                }

            }

            bufferedReader.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // Draw the tiles into screen
    public void draw(Graphics2D graphics2D) {

        /*graphics2D.drawImage(tiles[0].image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);*/

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            graphics2D.drawImage(tiles[0].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            // Since the grass plant is foreground, the size is 16 x 16 instead of 48 x 48
            graphics2D.drawImage(tiles[tileNum].image, x, y, 16, 16, null);

            col++;
            x += gamePanel.tileSize;

            if(col == gamePanel.maxScreenCol){

                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;

            }

        }


    }

}
