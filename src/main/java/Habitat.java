import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public abstract class Habitat implements Drawable {
    int x;
    int y;
    int width;
    int height;
    ArrayList<Drawable> drawableComponents;
    Image habitatImage;
    public Habitat() {
        drawableComponents = new ArrayList<>();
    }
    public void draw(Graphics g, int absX, int absY) {
        int x = absX + this.x;
        int y = absY + this.y;

        g.drawImage(habitatImage, x, y, null);
        for (Drawable d: drawableComponents) {
            d.draw(g, x, y);
        }
    }

    public void addDrawable(Drawable d) {
        drawableComponents.add(d);
    }

    public void removeDrawable(Drawable d) {
        drawableComponents.remove(d);
    }

    protected Image loadImage(String path) {
        BufferedImage buffImg = null;
        try {
            buffImg = ImageIO.read(new File(path));
        }
        catch (Exception e) {
            System.err.println(e);
        }
        return buffImg;
    }
    public void setHabitatImage(Image habitatImage) {
        this.habitatImage = habitatImage;
        width = habitatImage.getWidth(null);
        height = habitatImage.getHeight(null);
    }
}
