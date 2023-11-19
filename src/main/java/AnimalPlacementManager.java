import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Field;

public class AnimalPlacementManager extends PlacementManager<EnumAnimal> implements Drawable, MouseMotionListener, MouseListener {

    private EnumAnimal enumAnimal;
    public AnimalPlacementManager() {
        super();
    }

    @Override
    public void enablePlacement(EnumAnimal enumAnimal) {
        this.enumAnimal = enumAnimal;
        setActivo(true);
    }

    @Override
    public void disablePlacement() {
        enumAnimal = null;
        setActivo(false);
    }

    @Override
    public void place() {
        Field fieldWidth = null;
        Field fieldHeight = null;
        int width = 0, height = 0;
        try {
            fieldWidth = enumAnimal.getTipo().getField("width");
            fieldHeight = enumAnimal.getTipo().getField("height");
        } catch (NoSuchFieldException e) {}
        try {
            width = fieldWidth.getInt(null);
            height = fieldHeight.getInt(null);
        } catch (IllegalAccessException e) {}
    }
    @Override
    public void draw(Graphics g, int absX, int absY) {
        if (isActivo()) {
            enumAnimal.getSprite().drawSprite(g, getMouseX(), getMouseY(), 0, 0, 0, 0.45f);
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        setMouseX(mouseEvent.getX());
        setMouseY(mouseEvent.getY());
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (isActivo()) {
            if (mouseEvent.getButton() == MouseEvent.BUTTON1) {place();}
            disablePlacement();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
