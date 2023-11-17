import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public abstract class Habitat implements Drawable {
    public int x;
    public int y;
    protected int width;
    protected int height;
    protected ArrayList<Drawable> drawableComponents;
    protected Sprite habitatSprite;
    public Habitat() {
        drawableComponents = new ArrayList<>();
    }
    public void draw(Graphics g, int absX, int absY) {
        int x = absX + this.x;
        int y = absY + this.y;

        habitatSprite.drawSprite(g, x, y, getWidth(), getHeight(), 0);
        for (Drawable d: drawableComponents) {
            d.draw(g, x, y);
        }
    }

    public void step() {
        for (Drawable d: drawableComponents) {
            d.step();
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
    public void setHabitatSprite(Sprite habitatSprite) {
        this.habitatSprite = habitatSprite;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}
