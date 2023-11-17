import java.awt.*;

public class MeadowHabitat extends Habitat {
    private Image habitatImage = loadImage("src/main/resources/meadowHabitat.png");
    public MeadowHabitat() {
        super();
        setHabitatSprite(Sprite.MEADOWHABITAT);
        setWidth(64*4); setHeight(64*4);
    }
}
