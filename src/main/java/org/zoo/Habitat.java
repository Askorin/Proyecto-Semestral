package org.zoo;

import org.zoo.vista.Drawable;
import org.zoo.vista.Positionable;
import org.zoo.vista.Visitor;

import java.awt.*;

public abstract class Habitat implements Updatable, Drawable {
    private Positionable owner;
    public int x;
    public int y;
    public int width;
    public int height;
    protected Sprite habitatSprite;
    private Containables containables;
    public Habitat(Positionable owner) {
        this.owner = owner;
        containables = new Containables();
    }
    public void accept(Visitor v) {
        v.visitHabitat(this);
        // int x = absPoint.x + this.x;
        // int y = absPoint.y + this.y;

        // habitatSprite.drawSprite(g, x, y, getWidth(), getHeight(), 0, 1.0f);
        // for (Drawable d: getContainables().getDrawables()) {
        //     d.draw(g, x, y);
        // }
    }

    @Override
    public int getAbsX() {
        return x + owner.getAbsX();
    }
    public int getAbsY() {
        return y + owner.getAbsY();
    }

    public void update() {
        for (Updatable u: getContainables().getUpdatables()) {
            if (u != null) {u.update();}
        }
    }
  
    public Containables getContainables() {
        return containables;
    }

    public void addAnimal(Animal a) {
        getContainables().addComponent(a);
    }
  
    //Es necesario llamar a metodos setters en el constructor de los hijos, para sobreescribir atributos del padre
    public void setHabitatSprite(Sprite habitatSprite) {
        this.habitatSprite = habitatSprite;
    }
    public Sprite getHabitatSprite() {
        return habitatSprite;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}
