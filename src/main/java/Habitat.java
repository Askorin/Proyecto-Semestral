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
    //TODO: quizas seria mejor dejar este tipo de metodos en una clase Utilities
    //Este metodo ya no se usa, no lo borro porque me gustaria dejar el to-do (se usa en sprite por ej)
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
