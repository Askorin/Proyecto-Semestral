package org.zoo.modelo.habitat;
import org.zoo.Containables;
import org.zoo.modelo.Sprite;
import org.zoo.modelo.TextMessage;
import org.zoo.modelo.animal.EnumAnimal;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.animal.Animal;
import org.zoo.modelo.food.FoodArea;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;
import org.zoo.vista.Drawable;
import org.zoo.modelo.characteristics.Positionable;
import org.zoo.visitor.Visitor;

/**
 * Clase que modela un habitat a ser ocupado por un animal.
 * @see org.zoo.modelo.Zoo
 * @see Animal
 */
public abstract class Habitat implements Updatable, Drawable {
    /** El dueño del <code>Habitat</code> dentro del modelo. */
    private final Positionable owner;
    public int x;
    public int y;
    private int width;
    private int height;
    /** La temperatura del <code>Habitat</code>, de relevancia para los animales. */
    private float temperature; //en °C porque no somos quiche

    /** Tolerancia para detección de colisiones con otros <code>Habitat</code>. */
    public static final int PLACEMENT_TOLERANCE = 60;
    /** <code>Hitbox</code> en coordenadas absolutas del <code>Habitat</code> */
    protected Hitbox absHitbox;
    protected Sprite habitatSprite;
    /** Para representar elementos contenidos dentro del <code>Habitat</code> */
    private final Containables containables;

    /**
     * Constructor unico del <code>Habitat</code>
     * @param owner El dueño del <code>Habitat</code>, probablemente un <code>Zoo</code>.
     * @param p Su ubicación en el zoológico.
     */
    public Habitat(Positionable owner, ZooPoint p) {
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
    }
  
    public Containables getContainables() {
        return containables;
    }

    /**
     * Intenta agregar un <code>Animal</code> al <code>Habitat</code>.
     * @param enumAnimal El enumerador que identifica al animal.
     * @param p El punto del animal, relativo al <code>Habitat</code>:
     * @return <code>True</code> en caso de haberse podido agregar el animal con éxito, <code>False</code> en caso contrario.
     */
    public boolean addAnimal(EnumAnimal enumAnimal, ZooPoint p) {
        Animal a = enumAnimal.newInstance(this, p);
        //Revisamos si la temperatura falla
        if (a.getMinTemperature() > temperature || a.getMaxTempperature() < temperature) {
            String text = "La temperatura del hábitat no es óptima para el animal";
            new TextMessage(text);
            return false;
        }
        //Revisamos si hay un animal incompatible en el habitat
        for (Updatable a2: getContainables().getUpdatables()) {
            if (a2 instanceof Animal) {
                if (!Animal.doGetAlong(a, (Animal)a2)) {
                    String text = "El animal no es compatible con los animales del hábitat";
                    new TextMessage(text);
                    return false;
                }
            }
        }
        getContainables().addComponent(a);
        return true;
    }

    /**
     * Retorna el area de comida en algún punto del <code>Habitat</code>.
     * @param p El <code>ZooPoint</code> en cuestion.
     * @return Una <code>FoodArea</code> que contenga al punto.
     */
    public FoodArea getFoodAreaFromPoint(ZooPoint p) {
        for (Drawable d: getContainables().getDrawables()) {
            if (d instanceof FoodArea f)   {
                if (Hitbox.checkPointHitboxCollision(f.getAbsHitbox(), p)) {
                    return f;
                }
            }
        }
        return null;
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

    public Hitbox getAbsHitbox() {
        return absHitbox;
    }


    /**
     * Retorna la <code>Hitbox</code> usada para chequear colisiones entre
     * otros habitats, al momento de su posicionamiento.
     * @return Una <code>Hitbox</code>.
     */
    public Hitbox getAbsPlacementHitbox() {
        int newWidth = absHitbox.width + 2 * PLACEMENT_TOLERANCE;
        int newHeight = absHitbox.height + 2 * PLACEMENT_TOLERANCE;

        int newX = absHitbox.x - PLACEMENT_TOLERANCE;
        int newY = absHitbox.y - PLACEMENT_TOLERANCE;

        Hitbox placementHitbox = new Hitbox(
                newX,
                newY,
                newWidth,
                newHeight
        );
        return placementHitbox;
    }

}
