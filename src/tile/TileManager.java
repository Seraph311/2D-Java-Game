package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;
    Tile[][] tileMap2DArray;
    int[][] mapTileNumerator;
    int[][] mapTileDenominator;

    public TileManager(GamePanel gamePanel){

        this.gamePanel = gamePanel;

        tiles = new Tile[10];
        tileMap2DArray = new Tile[50][50];
        mapTileNumerator = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        mapTileDenominator = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];

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

            // The column in tileMap2DArray[col][row] is the type of tile e.g. first_grass_, plant_ .  Row in tileMap2DArray[col][row] is tile's variation or parts since tiles are chopped into 16x16 e.g. first_grass_1, first_grass_2, plant_1, plant_2.

            // tileMap2DArray[0][x] is first_grass_1, 2 3, 4... 9.png
            BufferedImage[] tileImages = loadTileArray("/tiles/summer_tiles/ground_grass_tiles/first_grass_", 9);
            for (int i = 0; i < tileImages.length; i++) {

                tileMap2DArray[0][i] = new Tile();
                tileMap2DArray[0][i].image = tileImages[i];

            }







        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public BufferedImage[] loadTileArray(String basePath, int count) throws IOException{

        BufferedImage[] tile = new BufferedImage[count];
        for(int i = 0; i < count; i++){

            tile[i] = ImageIO.read(getClass().getResourceAsStream(basePath + (i + 1) + ".png"));

        }

        return tile;

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
                    String[] numbers = line.split(" ");

                    String[] numeratorString = new String[line.length()];
                    String[] denominatorString = new String[line.length()];

                    for(int i = 0; i < numbers.length; i++){

                        String[] fraction = numbers[i].split("/");
                        numeratorString[i] = fraction[0];
                        denominatorString[i] = fraction[1];

                    }

                    // Converts the elements inside the numbers[] array string into integer
                    int numeratorInt = Integer.parseInt(numeratorString[col]);
                    int denominatorInt = Integer.parseInt(denominatorString[col]);


                    // Put the converted integer into mapTileNum[][] 2D array. Basically, this will look like the MapData.txt but, instead of String, it's 2D array, so every numbers inside MapData.txt can be read and access the same way it was arranged.
                    mapTileNumerator[col][row] = numeratorInt;
                    mapTileDenominator[col][row] = denominatorInt;


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

            int tileNumerator = mapTileNumerator[col][row];
            int tileDenominator = mapTileDenominator[col][row];

            graphics2D.drawImage(tileMap2DArray[0][4].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            // Since the grass plant is foreground, the size is 16 x 16 instead of 48 x 48
            graphics2D.drawImage(tileMap2DArray[tileNumerator][tileDenominator].image, x, y, 16, 16, null);

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
