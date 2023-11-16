import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum Sprite {
    CAT_IDLE("src/main/resources/catIdle.png"),
    CAT_WALK("src/main/resources/catWalk.png");
    public static final int scaleFactor = 4;
    private Image image;
    private int width;
    private int height;
    Sprite(String path) {
        BufferedImage buffImage = null;
        try {
            buffImage = ImageIO.read(new File(path));
        }
        catch (IOException e) {
            System.out.println(e);
        }

        width = buffImage.getWidth() * scaleFactor;
        height = buffImage.getHeight() * scaleFactor;
        image = buffImage.getScaledInstance(width, height, Image.SCALE_FAST);
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
