package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 16; // 16 x 16 tile size
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // Scaled up of orginalTileSile. 16 x 3 = 48 thus 48 x 48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize; // Screen width. 768 pixels
    public final int screenHeight = maxScreenRow * tileSize; // Screen Height. 576 pixels

    // Key inputs
    KeyHandler keyHandler = new KeyHandler();

    Thread gameThread; // Updates the frame every second so the game keeps running

    Player player = new Player(this, keyHandler);

    // Tiles
    TileManager tileManager = new TileManager(this);

    // Set FPS
    int FPS = 60;


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set Window Size
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // if set to true, all the drawing from this component will be done in an offscreen painting buffer.
        this.addKeyListener(keyHandler); // So the GamePanel recognizes the key input
        this.setFocusable(true); // With this, the GamePanel can be "focused" to receive key input


    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    // Uses Thread.sleep for FPS
    /*@Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;


        // Game will loop continuously as long as gameThread is not null.
        // This will update information such as character position and draw the screen with the updated information.
        // Basically, this is the frames that will be updated every second.
        while(gameThread != null) {

            // Just to check that game is indeed looping and frame is updating
            // System.out.println("Game is looping and frame is updating");

            // Update the character position or other things
            update();
            // Draw the screen with updated position
            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0) {

                    remainingTime = 0;

                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {

                throw new RuntimeException(e);

            }

        }

    }*/

    // Uses delta time for FPS
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // Displaying FPS
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime; // For displaying FPS
            lastTime = currentTime;

            if (delta >= 1) {

                update();
                repaint();
                delta--;
                drawCount++; // For displaying FPS

            }

            // Display FPS
            /*if(timer >= 1000000000) {

                System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;

            }*/


        }


    }


    public void update() {

        player.update();

    }

    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        Graphics2D g2D = (Graphics2D) graphics;

        // Draw Tiles put first before player so the player is visible
        tileManager.draw(g2D);

        player.draw(graphics);

        // Closes the window without terminating to release resources and hide the frame
        g2D.dispose();

    }


}
