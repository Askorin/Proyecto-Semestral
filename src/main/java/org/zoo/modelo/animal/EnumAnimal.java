package org.zoo.modelo.animal;

import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.Sprite;
import org.zoo.utilities.Point;

public enum EnumAnimal {
    GATO("org.zoo.modelo.animal.Gato", Gato.class, Sprite.CAT_IDLE, "/CatIdle1.png"),
    ELEPHANT("org.zoo.modelo.animal.Elephant", Elephant.class, Sprite.ELEPHANT_IDLE, "/ElephantIdle1.png"),
    PERRO("org.zoo.modelo.animal.Gato", Gato.class, Sprite.CAT_HUNGRY, "/CatHungry1.png");
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

    public Sprite getSprite() {
        return sprite;
    }
    public String getLabelPath() {
        return labelPath;
    }

    public Animal newInstance(Habitat habitat, Point p) {
        Animal animal = null;
        try {
            animal = (Animal) tipo.getDeclaredConstructor(Habitat.class, Point.class).newInstance(habitat, p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animal;
    }

}


