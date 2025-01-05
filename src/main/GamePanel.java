package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 16; // 16 x 16 tile size
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // Scaled up of orginalTileSile. 16 x 3 = 48 thus 48 x 48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize; // Screen width. 768 pixels
    final int screenHeight = maxScreenRow * tileSize; // Screen Height. 576 pixels

    Thread gameThread; // Updates the frame every second so the game keeps running

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set Window Size
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // if set to true, all the drawing from this component will be done in an offscreen painting buffer.

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {



    }
}
