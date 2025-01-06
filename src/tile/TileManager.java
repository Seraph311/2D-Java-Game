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

                String line = bufferedReader.readLine();

                while(col < gamePanel.maxScreenCol){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

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
            graphics2D.drawImage(tiles[tileNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);

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
