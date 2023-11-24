package org.zoo.modelo;

import org.zoo.Containables;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.utilities.Utilities;
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
    private Containables containables;
    private HabitatPlacementManager habitatPlacementManager;
    private AnimalPlacementManager animalPlacementManager;
      
    public Zoo(HabitatPlacementManager habitatPlacementManager, AnimalPlacementManager animalPlacementManager) {
        containables = new Containables();

        backgroundImage = Utilities.loadImage("src/main/resources/testimage.jpg"); // Temporal
        width = backgroundImage.getWidth(null);
        height = backgroundImage.getHeight(null);

        this.habitatPlacementManager = habitatPlacementManager;
        this.animalPlacementManager = animalPlacementManager;

        //TODO: Temp
        addHabitat(64, 128, EnumHabitat.MEADOW);
    }

    // TODO: Pasar enumHabitat o org.zoo.modelo.habitat.Habitat? Es este método una buena idea siquiera?
    public void addHabitat(int x, int y, EnumHabitat enumHabitat) {
        Habitat habitat = enumHabitat.newInstance(this);
        System.out.println(habitat);
        // TODO: Esto es pal meme.
        {
            habitat.getContainables().addComponent(new Gato(habitat, 0, 100));
        }
        habitat.x = x;//habitat.x = x + cameraX; //TODO:
        habitat.y = y;//habitat.y = y + cameraY;
        
        getContainables().addComponent(habitat);
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
        for (Updatable u: getContainables().getUpdatables()) {
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
