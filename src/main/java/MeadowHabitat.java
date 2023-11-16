import java.awt.*;

public class MeadowHabitat extends Habitat {
    Image habitatImage = loadImage("src/main/resources/meadowHabitat.png");
    public MeadowHabitat() {
        super();
        setHabitatImage(habitatImage);
    }
}
