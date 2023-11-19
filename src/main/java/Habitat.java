import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public abstract class Habitat implements Updatable, Drawable {
    public int x;
    public int y;
    public int width;
    public int height;
    protected ArrayList<Drawable> drawableComponents; //Habitat es un contenedor de elementos dibujables
    protected ArrayList<Updatable> updatableComponents; //Habitat.update() llama al update de sus componentes
    protected Sprite habitatSprite;
    public Habitat() {
        drawableComponents = new ArrayList<>();
        updatableComponents = new ArrayList<>();
    }
    public void draw(Graphics g, int absX, int absY) {
        int x = absX + this.x;
        int y = absY + this.y;

        habitatSprite.drawSprite(g, x, y, getWidth(), getHeight(), 0, 1.0f);
        for (Drawable d: drawableComponents) {
            d.draw(g, x, y);
        }
    }

    public void update() {
        for (Updatable u: updatableComponents) {
            if (u != null) {u.update();}
        }
    }

    public void addAnimal(Animal a) {
        addUpdatable(a);
        addDrawable(a);
    }

    // Es publico por ahora, pero supongo que eventualmente deberia ser privado
    // y por ejemplo, un metodo publico addAnimal, deberia llamar a este metodo
    public void addDrawable(Drawable d) {
        drawableComponents.add(d);
    }
    // lo mismo
    public void removeDrawable(Drawable d) {
        drawableComponents.remove(d);
    }
    public void addUpdatable(Updatable u) {
        updatableComponents.add(u);
    }

    public void removeUpdatable(Updatable u) {
        updatableComponents.remove(u);
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
