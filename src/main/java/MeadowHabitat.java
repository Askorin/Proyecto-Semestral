import java.awt.*;

public class MeadowHabitat extends Habitat {
    public static int width = 64*4;
    public static int height = 64*4;
    public MeadowHabitat() {
        super();
        // Esto para coordinar ancho y alto estático con el provisto por el padre.
        setWidth(width);
        setHeight(height);
        // Y esto es para coordinar el sprite
        setHabitatSprite(Sprite.MEADOWHABITAT);

        FoodArea foodArea = new FoodArea(48 * 4, 0, 16 * 4, 64 * 4);
        getContainables().addComponent(foodArea);
        foodArea.add(Food.FISH);
        foodArea.add(Food.FISH);
    }
}
