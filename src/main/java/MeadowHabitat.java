import java.awt.*;

public class MeadowHabitat extends Habitat {
    public static int width = 64*4;
    public static int height = 64*4;
    public MeadowHabitat() {
        super();
        setHabitatSprite(Sprite.MEADOWHABITAT);
        // Esto para coordinar ancho y alto estático con el provisto por el padre.
        setWidth(width);
        setHeight(height);
    }
}
