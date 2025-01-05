package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage[] leftSprites, rightSprites, idleLeft, idleRight;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    // For 2 sprite animation
    public int spriteCounter2Sprites = 1;
    public int spriteNum2Sprites = 1;

}
