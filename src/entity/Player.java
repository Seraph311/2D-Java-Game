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

            // Idle sprite
            // Left
            idleLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/idle_left_1.png"));
            idleLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/idle_left_2.png"));
            // Right
            idleRight1 = ImageIO.read(getClass().getResourceAsStream("/player/idle_right_1.png"));
            idleRight2 = ImageIO.read(getClass().getResourceAsStream("/player/idle_right_2.png"));

            // Walk sprite
            // Left
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/walk_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/walk_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/walk_left_3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/walk_left_4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/player/walk_left_5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/player/walk_left_6.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/player/walk_left_7.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/player/walk_left_8.png"));
            // Right
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/walk_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/walk_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/walk_right_3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/walk_right_4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/player/walk_right_5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/player/walk_right_6.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/player/walk_right_7.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/player/walk_right_8.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
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

       Image[] leftWalkingSprites = {left1, left2, left3, left4, left5, left6, left7, left8};
       Image[] rightWalkingSprites = {right1, right2, right3, right4, right5, right6, right7, right8};
       Image[] leftIdleSprites = {idleLeft1, idleLeft2};
       Image[] rightIdleSprites = {idleRight1, idleRight2};

       switch (direction){
           case "left":
               image = (BufferedImage) leftWalkingSprites[spriteNum - 1];
               break;
           case "right":
               image = (BufferedImage) rightWalkingSprites[spriteNum - 1];
               break;
           case "idle_left":
               image = (BufferedImage) leftIdleSprites[spriteNum2Sprites - 1];
               break;
           case "idle_right":
               image = (BufferedImage) rightIdleSprites[spriteNum2Sprites - 1];
               break;

       }

       graphics.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);


    }

}
