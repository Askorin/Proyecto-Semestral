import java.awt.*;

public class MeadowHabitat extends Habitat {
    private Image habitatImage = loadImage("src/main/resources/meadowHabitat.png");
    public MeadowHabitat() {
        super();
        setHabitatImage(habitatImage);
    }
}
