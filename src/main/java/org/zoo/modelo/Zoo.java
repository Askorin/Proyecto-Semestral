package org.zoo.modelo;

import org.zoo.Containables;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.placementmanager.FoodPlacementManager;
import org.zoo.utilities.Point;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.Utilities;
import org.zoo.modelo.animal.Gato;
import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.vista.Drawable;
import org.zoo.vista.visitor.Visitable;
import org.zoo.vista.visitor.Visitor;

import java.awt.*;

public class Zoo
        implements Updatable, Drawable, Visitable {

    private int width; private int height;
    private Image backgroundImage;
    // TODO: Se podría tener una arraylist de habitats?
    private Containables containables;
    private HabitatPlacementManager habitatPlacementManager;
    private AnimalPlacementManager animalPlacementManager;
    private FoodPlacementManager foodPlacementManager;
      
    public Zoo(HabitatPlacementManager habitatPlacementManager, AnimalPlacementManager animalPlacementManager, FoodPlacementManager foodPlacementManager) {
        containables = new Containables();

        backgroundImage = Utilities.loadImage("src/main/resources/testimage.jpg"); // Temporal
        width = backgroundImage.getWidth(null);
        height = backgroundImage.getHeight(null);

        this.habitatPlacementManager = habitatPlacementManager;
        this.animalPlacementManager = animalPlacementManager;
        this.foodPlacementManager = foodPlacementManager;

        //TODO: Temp
        addHabitat(64, 128, EnumHabitat.MEADOW);
    }

    public boolean addHabitat(int x, int y, EnumHabitat enumHabitat) {
        Habitat habitat = enumHabitat.newInstance(this, new Point(x, y));
        // TODO: Esto es pal meme.
        {
            habitat.getContainables().addComponent(new TextMessage(habitat, "class Cat implements Moonwalk"));
            habitat.getContainables().addComponent(new Gato(habitat, new Point(0, 100)));
        }
        habitat.x = x;
        habitat.y = y;

        for (Drawable d: getContainables().getDrawables()) {
            if (d instanceof Habitat h) {
                if (Hitbox.checkHitboxCollision(h.getAbsHitbox(), habitat.getAbsHitbox())) {
                    System.out.println("Uh oooh");
                    return false;
                }
            }
        }
        getContainables().addComponent(habitat);
        return true;
    }

    /**
     * Retorna el habitat que contiene un punto específico, retorna null si no existe.
     */
    public Habitat getHabitatFromPoint(Point p) {
        for (Drawable d: getContainables().getDrawables()) {
            if (d instanceof Habitat h)   {
                if (Hitbox.checkPointHitboxCollision(h.getAbsHitbox(), p)) {
                    return h;
                }
            }
        }
        return null;
    }

    public void accept(Visitor v) {
        v.visitZoo(this);

        // int x = -cameraX;
        // int y = -cameraY;
        // drawCamera(g);
        // for (Drawable d: getContainables().getDrawables()) {
        //     // Este check de null es medio quiche.
        //     if (d != null) {d.draw(g, x, y);}
        // }
        // // TODO: Sistema de layers para no tener que hacerlo manual, que es lo contrario a lo que queremos.
        // habitatPlacementManager.draw(g, 0, 0);
        // animalPlacementManager.draw(g, 0, 0);
    }

    public int getAbsX() {
        return 0;
    }
    public int getAbsY() {
        return 0;
    }

    public void update() {
        for (int i = getContainables().getUpdatables().size() - 1; i >= 0; --i) {
            Updatable u = getContainables().getUpdatables().get(i);
            if (u != null) {u.update();}
        }
    }

    public Containables getContainables() {
        return containables;
    }

    public HabitatPlacementManager getHabitatPlacementManager() {
        return habitatPlacementManager;
    }
    public AnimalPlacementManager getAnimalPlacementManager() {
        return animalPlacementManager;
    }

    public FoodPlacementManager getFoodPlacementManager() {
        return foodPlacementManager;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
