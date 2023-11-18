import java.awt.*;

public interface Drawable {
    public void draw(Graphics g, int absX, int absY);
    //¿Por qué step? No cualquier objeto dibujable debe actualizar algo cada frame cierto?
    public void step();
}
