import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Field;


public class HabitatPlacementManager extends PlacementManager<EnumHabitat> implements Drawable {
    private EnumHabitat enumHabitat;
    public HabitatPlacementManager() {
        super();
    }

    @Override
    public void enablePlacement(EnumHabitat enumHabitat) {
        this.enumHabitat = enumHabitat;
        setActivo(true);
    }

    @Override
    public void disablePlacement() {
        enumHabitat = null;
        setActivo(false);
    }

    @Override
    public void place() {
        Field fieldWidth = null;
        Field fieldHeight = null;
        int width = 0, height = 0;
        try {
            fieldWidth = enumHabitat.getTipo().getField("width");
            fieldHeight = enumHabitat.getTipo().getField("height");
        } catch (NoSuchFieldException e) {}
        try {
            width = fieldWidth.getInt(null);
            height = fieldHeight.getInt(null);
        } catch (IllegalAccessException e) {}

        int posX = getMouseX() - width / 2;
        int posY = getMouseY() - height / 2;
        getVistaZoo().addHabitat(posX, posY, enumHabitat);
    }


    @Override
    public void draw(Graphics g, int absX, int absY) {
        if (isActivo()) {
            enumHabitat.getSprite().drawSprite(g, getMouseX(), getMouseY(), 0, 0, 0, 0.45f);
        }
    }
}
