package org.zoo.vista;

import org.zoo.*;
import org.zoo.Point;

import java.awt.*;

public class DrawVisitor implements Visitor {
    private Graphics g;
    public DrawVisitor(Graphics g) {
        this.g = g;
    }
    public void visitAnimal(Animal animal, Point absPoint) {
        int x = absPoint.x + animal.x;
        int y = absPoint.y + animal.y;

        //Dibujar org.zoo.Hitbox (Borrar luego)
        if (App.SEE_HITBOX) {
            g.setColor(Color.RED);
            g.drawRect(x, y, animal.getWidth(), animal.getHeight());
        }
        animal.getCurrentSprite().drawSprite(g, x, y, animal.getWidth(), animal.getHeight(), animal.getTimeElapsed(), 1.0f);
    }

    public void visitHabitat(Habitat habitat, Point absPoint) {
        int x = absPoint.x + habitat.x;
        int y = absPoint.y + habitat.y;

        habitat.getHabitatSprite().drawSprite(g, x, y, habitat.getWidth(), habitat.getHeight(), 0, 1.0f);
        for (Drawable d: habitat.getContainables().getDrawables()) {
            d.draw(g, new Point(x, y), this);
        }

    }

    public void visitVistaZoo(VistaZoo zoo, Point absPoint) {
        int cameraX = zoo.getCameraX();
        int cameraY = zoo.getCameraY();
        Point p = new Point(-cameraX, -cameraY);

        /* Dibujamos camara */
        g.drawImage(zoo.getBackgroundImage(), p.x, p.y, null);

        for (Drawable d: zoo.getContainables().getDrawables()) {
            // Este check de null es medio quiche.
            if (d != null) {
                d.draw(g, p, this);
            }
        }
        // TODO: Sistema de layers para no tener que hacerlo manual, que es lo contrario a lo que queremos.
        zoo.getHabitatPlacementManager().draw(g, p, this);
        zoo.getAnimalPlacementManager().draw(g, p, this);
    }

    public void visitHabitatPlacementManager(HabitatPlacementManager hpm, Point absPoint) {
        if (hpm.isActivo()) {
            hpm.getEnumHabitat().getSprite().drawSprite(g, hpm.getMouseX(), hpm.getMouseY(), 0, 0, 0, 0.45f);
        }
    }

    public void visitAnimalPlacementManager(AnimalPlacementManager apm, Point absPoint) {
        if (apm.isActivo()) {
            apm.getEnumAnimal().getSprite().drawSprite(g, apm.getMouseX(), apm.getMouseY(), 0, 0, 0, 0.7f);
        }
    }

    public void visitFoodDisplay(FoodArea.FoodDisplay foodDisplay, Point absPoint) {
        int x = foodDisplay.getX() + absPoint.x;
        int y = foodDisplay.getY() + absPoint.y;

        int width = foodDisplay.getWidth();
        int height = foodDisplay.getHeight();

        if (App.SEE_HITBOX) {//Borrar luego
            g.setColor(Color.CYAN);
            g.drawRect(x, y, width, height);
        }
        foodDisplay.getFood().getInGameSprite().drawSprite(g, x, y, width, height, 0, 1.0f);
    }

    public void visitFoodArea(FoodArea foodArea, Point absPoint) {
        int x = foodArea.getX() + absPoint.x;
        int y = foodArea.getY() + absPoint.y;
        int width = foodArea.getWidth();
        int height = foodArea.getHeight();

        g.setColor(new Color(85, 28, 19));
        g.fillRect(x, y, width, height);
        g.setColor(new Color(137, 58, 27));
        g.fillRect(x, y, width, 8);
        g.fillRect(x, y + height - 8, width, 8);
        g.fillRect(x, y, 8, height);
        g.fillRect(x + width - 8, y, 8, height);

        for (FoodArea.FoodDisplay fd: foodArea.getAllFoodDisplays()) {
            fd.draw(g, new Point(x, y), this);
        }

        g.setColor(new Color(195, 95, 29));
        g.fillRect(x, y, width, 4);
        g.fillRect(x, y + height - 4, width, 4);
        g.fillRect(x, y, 4, height);
        g.fillRect(x + width - 4, y, 4, height);
    }
}