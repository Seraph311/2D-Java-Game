package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "right";

    }

    public void getPlayerImage() {
        try {
            // Load idle sprites
            idleLeft = loadSpriteArray("/player/idle_left_",2);
            idleRight = loadSpriteArray("/player/idle_right_",2);

            // Load walk sprites
            leftSprites = loadSpriteArray("/player/walk_left_", 8);
            rightSprites = loadSpriteArray("/player/walk_right_", 8);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to load a single sprite
    private BufferedImage loadSprite(String path) throws IOException {
        return ImageIO.read(getClass().getResourceAsStream(path));
    }

    // Helper method to load an array of sprites
    private BufferedImage[] loadSpriteArray(String basePath, int count) throws IOException {
        BufferedImage[] sprites = new BufferedImage[count];
        for (int i = 0; i < count; i++) {
            sprites[i] = loadSprite(basePath + (i + 1) + ".png");
        }
        return sprites;
    }


    public void update() {

        // Make the character run if shift is pressed
        if(keyHandler.shiftPressed){
            speed = 8;
        }
        else{
            speed = 4;
        }

        // Make the character move if W, A, S, and D is pressed
        if(keyHandler.upPressed) {
            // When walking up it will have a walking animation instead of idle animation
            if(direction.equals("idle_right")){
                direction = "right";
            }
            else if(direction.equals("idle_left")){
                direction = "left";
            }

            y -= speed;
        }
        else if(keyHandler.downPressed) {
            // When walking down it will have a walking animation instead of idle animation
            if(direction.equals("idle_right")){
                direction = "right";
            }
            else if(direction.equals("idle_left")){
                direction = "left";
            }

            y += speed;
        }
        // Separate if statement so the character can move diagonally
        if(keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
        }
        else if(keyHandler.rightPressed) {
            direction = "right";
            x += speed;
        }

        if(!keyHandler.checkIfKeyPressed()){
            if(direction.equals("left")){
                direction = "idle_left";
            }
            else if(direction.equals("right")){
                direction = "idle_right";
            }
        }

        spriteCounter++;
        if(spriteCounter > 5) {
            if(spriteNum < 8){
                spriteNum ++;
            }
            else if(spriteNum == 8){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        // Every 12th frame it will update the animation. This is to make the animation slower since it only has 2 sprites
        spriteCounter2Sprites++;
        if(spriteCounter2Sprites > 12){
            if(spriteNum2Sprites == 1){
                spriteNum2Sprites = 2;
            }
            else if(spriteNum2Sprites == 2){
                spriteNum2Sprites = 1;
            }
            spriteCounter2Sprites = 0;
        }

    }

    public void draw(Graphics graphics) {

        // Temp player character
        /*graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);*/

       BufferedImage image = null;

       switch (direction){
           case "left":
               image =  leftSprites[spriteNum - 1];
               break;
           case "right":
               image =  rightSprites[spriteNum - 1];
               break;
           case "idle_left":
               image = idleLeft[spriteNum2Sprites - 1];
               break;
           case "idle_right":
               image = idleRight[spriteNum2Sprites - 1];
               break;

       }

       graphics.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);


    }

}
