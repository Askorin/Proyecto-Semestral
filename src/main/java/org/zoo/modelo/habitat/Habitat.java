package org.zoo.modelo.habitat;

import org.zoo.Containables;
import org.zoo.modelo.Sprite;
import org.zoo.modelo.TextMessage;
import org.zoo.modelo.animal.EnumAnimal;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.animal.Animal;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.Point;
import org.zoo.vista.Drawable;
import org.zoo.modelo.characteristics.Positionable;
import org.zoo.vista.visitor.Visitor;

public abstract class Habitat implements Updatable, Drawable {
    private Positionable owner;
    public int x;
    public int y;
    private int width;
    private int height;
    private float temperature; //en °C porque no somos quiche
    protected Hitbox hitbox;
    protected Sprite habitatSprite;
    private Containables containables;
    public Habitat(Positionable owner, Point p) {
        this.owner = owner;
        this.x = p.x;
        this.y = p.y;
        containables = new Containables();
    }
    public void accept(Visitor v) {
        v.visitHabitat(this);
    }

    @Override
    public int getAbsX() {
        return x + owner.getAbsX();
    }
    @Override
    public int getAbsY() {
        return y + owner.getAbsY();
    }

    public void update() {
        for (int i = getContainables().getUpdatables().size() - 1; i >= 0; --i) {
            Updatable u = getContainables().getUpdatables().get(i);
            if (u != null) {u.update();}
        }
        // for (Updatable u: getContainables().getUpdatables()) {
        //     System.out.println(u);
        //     if (u != null) {u.update();}
        // }
    }
  
    public Containables getContainables() {
        return containables;
    }

    public boolean addAnimal(EnumAnimal enumAnimal, Point p) {
        Animal a = enumAnimal.newInstance(this, p);
        //Revisamos si la temperatura falla
        if (a.getMinTemperature() > temperature || a.getMaxTempperature() < temperature) {
            String text = "La temperatura del hábitat no es óptima para el animal";
            getContainables().addComponent(new TextMessage(this, text));
            return false;
        }
        getContainables().addComponent(a);
        return true;
    }
  
    //Es necesario llamar a metodos setters en el constructor de los hijos, para sobreescribir atributos del padre
    protected void setTemperature(float temperature) {
        this.temperature = temperature;
    }
    public float getTemperature() {
        return temperature;
    }
    protected void setHabitatSprite(Sprite habitatSprite) {
        this.habitatSprite = habitatSprite;
    }
    public Sprite getHabitatSprite() {
        return habitatSprite;
    }
    public int getWidth() {
        return width;
    }
    protected void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    protected void setHeight(int height) {
        this.height = height;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }
}
