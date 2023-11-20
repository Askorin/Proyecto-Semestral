import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class Habitat implements Updatable, Drawable {
    public int x;
    public int y;
    protected int width;
    protected int height;
    protected Sprite habitatSprite;
    private Containables containables;
    public Habitat() {
        containables = new Containables();
    }
    public void draw(Graphics g, int absX, int absY) {
        int x = absX + this.x;
        int y = absY + this.y;

        habitatSprite.drawSprite(g, x, y, getWidth(), getHeight(), 0, 1.0f);
        for (Drawable d: getContainables().getDrawables()) {
            d.draw(g, x, y);
        }
    }

    public void update() {
        for (Updatable u: getContainables().getUpdatables()) {
            if (u != null) {u.update();}
        }
    }
    public Containables getContainables() {
        return containables;
    }
    //Es necesario llamar a este metodo en el constructor de los hijos, para sobreescribir el sprite del padre
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
