package entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EntityImage{

    // This class loads sprites. Call the loadSpriteArray in this format: EntityImage.loadSpriteArray("sprites file path", number of sprites ---> 8);

    // Method to load a single sprite
    public BufferedImage loadSprite(String path) throws IOException {
        return ImageIO.read(getClass().getResourceAsStream(path));
    }

    // Method to load an array of sprites
    public BufferedImage[] loadSpriteArray(String basePath, int count) throws IOException {
        BufferedImage[] sprites = new BufferedImage[count];
        for (int i = 0; i < count; i++) {
            sprites[i] = loadSprite(basePath + (i + 1) + ".png");
        }
        return sprites;
    }


}
