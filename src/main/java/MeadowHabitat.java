import java.awt.*;

public class MeadowHabitat extends Habitat {
    public MeadowHabitat() {
        super();
        setHabitatSprite(Sprite.MEADOWHABITAT);
        setWidth(64*4); setHeight(64*4);
        FoodArea foodArea = new FoodArea(48 * 4, 0, 16 * 4, 64 * 4);
        getContainables().addComponent(foodArea);
        foodArea.add(Food.FISH);
        foodArea.add(Food.FISH);
    }
}
