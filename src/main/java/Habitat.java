import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Habitat {
    int x;
    int y;
    int width;
    int height;
    Animal animal;
    Image image;
    public Habitat() {
        image = loadImage("src/main/resources/grassHabitat.png"); // Temporal
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    public void draw(Graphics g, int absX, int absY) {
        int x = absX + this.x;
        int y = absY + this.y;

        g.drawImage(image, x, y, null);
        animal.draw(g, x, y);
    }

    private Image loadImage(String path) {
        BufferedImage buffImg = null;
        try {
            buffImg = ImageIO.read(new File(path));
        }
        catch (Exception e) {
            System.err.println(e);
        }
        return buffImg;
    }
}
