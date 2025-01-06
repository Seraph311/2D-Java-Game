package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;

    public TileManager(GamePanel gamePanel){

        this.gamePanel = gamePanel;

        tiles = new Tile[10];

        getTileImage();

    }

    public void getTileImage(){

        try{

            tiles [0] = new Tile();

            tiles [0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/summer_tiles/ground_grass_tiles/first_grass_5.png"));

        }catch(IOException e){
            e.printStackTrace();
        }


    }

    public void draw(Graphics2D graphics2D) {

        /*graphics2D.drawImage(tiles[0].image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);*/

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){

            graphics2D.drawImage(tiles[0].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);

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
