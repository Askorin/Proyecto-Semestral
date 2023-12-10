package org.zoo.modelo.animal;

import org.zoo.modelo.MenuItem;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.Sprite;
import org.zoo.utilities.ZooPoint;

/**
 * Enumeracion que contiene informacion de los animales que contiene el programa,
 * cada elemento de la enumeración hace referencia a un <code>Animal</code> en especifico.
 */
public enum EnumAnimal implements MenuItem {
    GATO("Gato", Cat.class, Sprite.CAT_IDLE, "/CatIdle1.png"),
    ELEPHANT("Elefante", Elephant.class, Sprite.ELEPHANT_IDLE, "/ElephantIdle1.png"),
    LION("León", Lion.class, Sprite.LION_IDLE, "/LionIdle1.png"),
    MONKEY("Mono", Monkey.class, Sprite.MONKEY_IDLE, "/MonkeyIdle.png"),
    PANDA("Panda", Panda.class, Sprite.PANDA_IDLE, "/PandaIdle.png"),
    PENGUIN("Pingüino", Penguin.class, Sprite.PENGUIN_IDLE, "/PenguinIdle.png");
    /**
     * Nombre del <code>Animal</code>, util para imprimirlo al usuario
     */
    private final String nombre;
    /**
     * Clase que modela al <code>Animal</code>
     */
    private final Class<?> tipo;
    /**
     * Sprite representativo del <code>Animal</code>
     */
    private final Sprite sprite;
    /**
     * Ruta del archivo que contiene una imagen portada del <code>Animal</code>
     */
    private final String labelPath;
    EnumAnimal(String nombre, Class<?> tipo, Sprite sprite, String labelPath) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.sprite = sprite;
        this.labelPath = labelPath;
    }

    /**
     * Permite obtener la clase que modela al <code>Animal</code>
     * @return Clase que modela al <code>Animal</code>
     */
    public Class<?> getTipo() {
        return tipo;
    }

    /**
     * Permite obtener el nombre del <code>Animal</code>
     * @return String del nombre del <code>Animal</code>
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite obtener el sprite representativo del <code>Animal</code> en el juego
     * @return Sprite representativo del <code>Animal</code>
     */
    @Override
    public Sprite getInGameSprite() {
        return sprite;
    }

    /**
     * Permite obtener la ruta del archivo que contiene una imagen portada del <code>Animal</code>
     * @return String que contiene una ruta de archivo
     */
    public String getLabelPath() {
        return labelPath;
    }

    /**
     * Permite crear una instancia del <code>Animal</code> referenciado en el elemento de la enumaración
     * @param habitat Habitat que va a contener al <code>Animal</code>
     * @param point Punto del habitat donde se va a crear el <code>Animal</code>
     * @return Instancia de <code>Animal</code> creada
     */
    public Animal newInstance(Habitat habitat, ZooPoint point) {
        Animal animal = null;
        try {
            animal = (Animal) tipo.getDeclaredConstructor(Habitat.class, ZooPoint.class).newInstance(habitat, point);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animal;
    }

}


