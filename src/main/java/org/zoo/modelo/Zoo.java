package org.zoo.modelo;

import org.zoo.Containables;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.placementmanager.FoodPlacementManager;
import org.zoo.utilities.ZooPoint;
import org.zoo.utilities.Hitbox;
import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.vista.Drawable;
import org.zoo.vista.visitor.Visitable;
import org.zoo.vista.visitor.Visitor;

public class Zoo
        implements Updatable, Drawable, Visitable {

    private final int width = 2048;
    private final int height = 2048;
    private final Sprite backgroundSprite = Sprite.ZOO_BACKGROUND;
    // TODO: Se podría tener una arraylist de habitats? // y los habitats podrian tener un arrayList de animales?
    private final Containables containables;
    private final HabitatPlacementManager habitatPlacementManager;
    private final AnimalPlacementManager animalPlacementManager;
    private final FoodPlacementManager foodPlacementManager;
      
    public Zoo(HabitatPlacementManager habitatPlacementManager, AnimalPlacementManager animalPlacementManager, FoodPlacementManager foodPlacementManager) {
        containables = new Containables();

        this.habitatPlacementManager = habitatPlacementManager;
        this.animalPlacementManager = animalPlacementManager;
        this.foodPlacementManager = foodPlacementManager;
    }

    // TODO: Chequear si habitat esa fuera de zoologico, imprimir texto warning en casos fallidos.
    public boolean addHabitat(int x, int y, EnumHabitat enumHabitat) {
        Habitat habitat = enumHabitat.newInstance(this, new ZooPoint(x, y));
        habitat.x = x;
        habitat.y = y;

        /* Primero revisamos que el habitat se encuentra dentro del Zoo. */
        Hitbox zooHitbox = new Hitbox(getAbsX(), getAbsY(), getWidth(), getHeight());

        if (!Hitbox.isHitboxContained(zooHitbox, habitat.getAbsPlacementHitbox())) {
            String text = "El espacio se encuentra fuera del límite del zoológico.";
            new TextMessage(text);
            return false;
        }


        for (Drawable d: getContainables().getDrawables()) {
            if (d instanceof Habitat h) {
                if (Hitbox.checkHitboxCollision(h.getAbsPlacementHitbox(), habitat.getAbsPlacementHitbox())) {
                    String text = "El espacio se encuentra ocupado.";
                    new TextMessage(text);
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
    public Habitat getHabitatFromPoint(ZooPoint p) {
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

    public Sprite getBackgroundSprite() {
        return backgroundSprite;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
