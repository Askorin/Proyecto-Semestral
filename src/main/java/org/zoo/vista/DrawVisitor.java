package org.zoo.vista;

import org.zoo.*;
import org.zoo.Point;

import javax.swing.*;
import java.awt.*;

public class DrawVisitor extends JPanel implements Visitor {
    //Temp
    int width = 1000;
    int height = 1000;
    private Graphics g;
    private Zoo zoo;
    // TODO: El paintComponent lo debería llevar ventana en verdad?
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.g = g;
        zoo.accept(this);
    }
    public DrawVisitor(Zoo zoo) {
        this.zoo = zoo;

        width = zoo.getWidth();
        height = zoo.getHeight();

        cameraHeight = 850;
        cameraWidth = 480;

        RenderedSprite.loadSprites(); //Importante
    }
    public void visitAnimal(Animal animal) {
        int x = animal.getAbsX() - getCameraX();
        int y = animal.getAbsY() - getCameraY();

        //Dibujar org.zoo.Hitbox (Borrar luego)
        if (App.SEE_HITBOX) {
            g.setColor(Color.RED);
            g.drawRect(x, y, animal.getWidth(), animal.getHeight());
        }

        Sprite spr = animal.getCurrentSprite();
        RenderedSprite.draw(spr, g, x, y, animal.getWidth(), animal.getHeight(), animal.getTimeElapsed(), 1.0f);
    }

    public void visitHabitat(Habitat habitat) {
        int x = habitat.getAbsX() - getCameraX();
        int y = habitat.getAbsY() - getCameraY();

        Sprite spr = habitat.getHabitatSprite();
        RenderedSprite.draw(spr, g, x, y, habitat.getWidth(), habitat.getHeight(), 0, 1.0f );
        for (Drawable d: habitat.getContainables().getDrawables()) {
            d.accept(this);
        }

    }

    public void visitZoo(Zoo zoo) {
        updateCamera();
        int cameraX = getCameraX();
        int cameraY = getCameraY();
        Point p = new Point(-cameraX, -cameraY);

        /* Dibujamos camara */
        g.drawImage(zoo.getBackgroundImage(), p.x, p.y, null);

        for (Drawable d: zoo.getContainables().getDrawables()) {
            // Este check de null es medio quiche.
            if (d != null) {
                d.accept(this);
            }
        }
        // TODO: Sistema de layers para no tener que hacerlo manual, que es lo contrario a lo que queremos.
        zoo.getHabitatPlacementManager().accept(this);
        zoo.getAnimalPlacementManager().accept(this);
    }

    public void visitHabitatPlacementManager(HabitatPlacementManager hpm) {
        if (hpm.isActivo()) {
            Sprite spr = hpm.getEnumHabitat().getSprite();
            RenderedSprite.draw(spr, g, hpm.getMouseX(), hpm.getMouseY(), 0, 0, 0, 0.45f);
        }
    }

    public void visitAnimalPlacementManager(AnimalPlacementManager apm) {
        if (apm.isActivo()) {
            Sprite spr = apm.getEnumAnimal().getSprite();
            RenderedSprite.draw(spr, g, apm.getMouseX(), apm.getMouseY(), 0, 0, 0, 0.7f );
        }
    }

    public void visitFoodDisplay(FoodArea.FoodDisplay foodDisplay) {
        int x = foodDisplay.getAbsX() - getCameraX();
        int y = foodDisplay.getAbsY() - getCameraY();

        int width = foodDisplay.getWidth();
        int height = foodDisplay.getHeight();

        if (App.SEE_HITBOX) {//Borrar luego
            g.setColor(Color.CYAN);
            g.drawRect(x, y, width, height);
        }
        Sprite spr = foodDisplay.getFood().getInGameSprite();
        RenderedSprite.draw(spr, g, x, y, width, height, 0, 1.0f);
    }

    public void visitFoodArea(FoodArea foodArea) {
        int x = foodArea.getAbsX() - getCameraX();
        int y = foodArea.getAbsY() - getCameraY();
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
            fd.accept(this);
        }

        g.setColor(new Color(195, 95, 29));
        g.fillRect(x, y, width, 4);
        g.fillRect(x, y + height - 4, width, 4);
        g.fillRect(x, y, 4, height);
        g.fillRect(x + width - 4, y, 4, height);
    }


    ///// CAMERA //TODO: Mover a otra clase, ojala no anidada a esta?

    private int cameraX; private int cameraY;
    private int cameraWidth; private int cameraHeight;
    private final int cameraTol = 24; private final int cameraSpeed = 5;
    private int mouseX; private int mouseY; private boolean mouseIn;
    private void updateCamera() {

        cameraHeight = getSize().height;
        cameraWidth = getSize().width;

        if (mouseIn) {
            if (mouseX < cameraTol && cameraX - cameraSpeed >= 0) {
                cameraX += -cameraSpeed;
            }
            else if (mouseX > (cameraWidth - cameraTol)
                    && cameraX + cameraWidth + cameraSpeed <= width) {
                cameraX += cameraSpeed;
            }
            if (mouseY < cameraTol && cameraY - cameraSpeed >= 0) {
                cameraY += -cameraSpeed;
            }
            else if (mouseY > (cameraHeight - cameraTol)
                    && cameraY + cameraHeight + cameraSpeed <= height) {
                cameraY += cameraSpeed;
            }
        }
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }
    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }
    public void setMouseIn(boolean mouseIn) {
        this.mouseIn = mouseIn;
    }

    public int getCameraX() {
        return cameraX;
    }
    public int getCameraY() {
        return cameraY;
    }
}