package org.zoo.modelo;

import org.zoo.modelo.characteristics.Containables;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.placementmanager.FoodPlacementManager;
import org.zoo.utilities.ZooPoint;
import org.zoo.utilities.Hitbox;
import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.modelo.characteristics.Drawable;
import org.zoo.visitor.Visitor;

/**
 * Clase que contiene toda la logica del zoologico,
 * contiene a todos los elementos que estan en el zoologico (Habitats, animales, etc.)
 */
public class Zoo
        implements Updatable, Drawable {

    private final int width = 2048;
    private final int height = 2048;
    private final Sprite backgroundSprite = Sprite.ZOO_BACKGROUND;
    /**
     * <code>Zoo</code> contiene una instancia de <code>Containables</code>,
     * es donde se almacenaran los elementos contenidos en el <code>Zoo</code>
     */
    private final Containables containables;
    /**
     * Manejador de <code>Habitat</code> del zoo.
     * Es el intermediario que permite colocar habitats en el <code>Zoo</code>
     */
    private final HabitatPlacementManager habitatPlacementManager;
    /**
     * Manejador de <code>Animal</code> del zoo
     * Es el intermediario que permite colocar animales en el<code>Zoo</code>
     */
    private final AnimalPlacementManager animalPlacementManager;
    /**
     * Manejador de tipos de comida del zoo.
     * Es el intermediario que permite colocar comida en el <code>Zoo</code>
     */
    private final FoodPlacementManager foodPlacementManager;
      
    public Zoo(HabitatPlacementManager habitatPlacementManager, AnimalPlacementManager animalPlacementManager, FoodPlacementManager foodPlacementManager) {
        containables = new Containables();

        this.habitatPlacementManager = habitatPlacementManager;
        this.animalPlacementManager = animalPlacementManager;
        this.foodPlacementManager = foodPlacementManager;
    }

    /***
     * Metodo que permite agregar un <code>Habitat</code> al <code>Zoo</code>
     * @param x Coordenada x del <code>Zoo</code> donde colocar el <code>Habitat</code> deseado
     * @param y Coordenada y del <code>Zoo</code> donde colocar el <code>Habitat</code> deseado
     * @param enumHabitat Corresponde al elemento de <code>EnumHabitat</code> que referencia
     *                    a la subclase de <code>Habitat</code> que queremos agregar al <code>Zoo</code>
     * @return Devuelve <code>true</code> si el <code>Habitat</code> se coloco exitosamente,
     *         devuelve <code>false</code> en caso contrario
     */
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
     * Metodo que permite conocer si existe un <code>Habitat</code> en un determinado punto del <code>Zoo</code>
     * @param p Punto del <code> Zoo</code>donde queremos revisar si hay un <code>Habitat</code>
     * @return Retorna el <code>Habitat</code> contenido en el punto, retorna <code>null</code> si no existe.
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

    @Override
    public void accept(Visitor v) {
        v.visitZoo(this);
    }

    @Override
    public int getAbsX() {
        return 0;
    }
    @Override
    public int getAbsY() {
        return 0;
    }
    @Override
    public void update() {
        for (int i = getContainables().getUpdatables().size() - 1; i >= 0; --i) {
            Updatable u = getContainables().getUpdatables().get(i);
            if (u != null) {u.update();}
        }
    }

    /**
     * Permite obtener la coleccion de todos los elementos contenibles contenidos en el <code>Zoo</code>
     * @return Instancia de <code>Containables</code> que contiene los elementos contenidos
     */
    public Containables getContainables() {
        return containables;
    }

    /**
     * Permite obtener el majeador de <code>Habitat</code> del <code>Zoo</code>
     * @return Devuelve el <code>HabitatPlacementManager</code> que usa el <code>Zoo</code>
     */
    public HabitatPlacementManager getHabitatPlacementManager() {
        return habitatPlacementManager;
    }
    /**
     * Permite obtener el majeador de <code>Animal</code> del <code>Zoo</code>
     * @return Devuelve el <code>AnimalPlacementManager</code> que usa el <code>Zoo</code>
     */
    public AnimalPlacementManager getAnimalPlacementManager() {
        return animalPlacementManager;
    }
    /**
     * Permite obtener el majeador de tipos de comida del <code>Zoo</code>
     * @return Devuelve el <code>FoodPlacementManager</code> que usa el <code>Zoo</code>
     */
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
