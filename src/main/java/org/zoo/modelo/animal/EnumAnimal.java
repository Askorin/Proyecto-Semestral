package org.zoo.modelo.animal;

import org.zoo.modelo.MenuItem;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.Sprite;
import org.zoo.utilities.ZooPoint;

public enum EnumAnimal implements MenuItem {
    GATO("org.zoo.modelo.animal.Gato", Gato.class, Sprite.CAT_IDLE, "/CatIdle1.png"),
    ELEPHANT("org.zoo.modelo.animal.Elephant", Elephant.class, Sprite.ELEPHANT_IDLE, "/ElephantIdle1.png"),
    LION("org.zoo.modelo.animal.Lion", Lion.class, Sprite.LION_IDLE, "/LionIdle1.png"),
    MONKEY("org.zoo.modelo.animal.Monkey", Monkey.class, Sprite.MONKEY_IDLE, "/MonkeyIdle.png");
    private final String nombre;
    private final Class<?> tipo;
    private final Sprite sprite;
    private final String labelPath;
    EnumAnimal(String nombre, Class<?> tipo, Sprite sprite, String labelPath) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.sprite = sprite;
        this.labelPath = labelPath;
    }

    public Class<?> getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public Sprite getInGameSprite() {
        return sprite;
    }


    public String getLabelPath() {
        return labelPath;
    }

    public Animal newInstance(Habitat habitat, ZooPoint p) {
        Animal animal = null;
        try {
            animal = (Animal) tipo.getDeclaredConstructor(Habitat.class, ZooPoint.class).newInstance(habitat, p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animal;
    }

}


