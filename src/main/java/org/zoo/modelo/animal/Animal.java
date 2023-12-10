package org.zoo.modelo.animal;
import org.zoo.modelo.*;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.states.*;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;
import org.zoo.modelo.characteristics.Drawable;
import org.zoo.visitor.Visitor;

/**
 * Clase abstracta que corresponde al modelo logico
 * de un animal en el zoologico
 */
public abstract class Animal implements Updatable, Drawable {
    public int x;
    public int y;
    protected Hitbox hitbox;
    private int width;
    private int height;
    /**
     * Temperatura minima de <code>Habitat</code> que el <code>Animal</code> acepta
     */
    private float minTemperature;
    /**
     * Temperatura maxima de <code>Habitat</code> que el <code>Animal</code> acepta
     */
    private float maxTempperature;
    /**
     * Lista de comida que el <code>Animal</code> consume
     * El orden importa, las comidas preferidas deberian ir pimero.
     */
    private EnumFood[] prefferedFood;
    /**
     * Lista de animales con cuales el <code>Animal</code> no puede compartir habitat.
     * No es necesario ser simetrico: A no convive con B, B si convive con A.
     */
    private EnumAnimal[] invalidCompanion;
    /**
     * Informacion abstracta del estado visual del <code>Animal</code>.
     * Notar que no es parte de la vista, puesto que no hace referencia a una imagen como tal.
     */
    private Sprite currentSprite;
    /**
     * Informacion sobre si el animal esta moviendose a la izquierda (<code>true</code>)
     * o a la derecha (<code>false</code>).
     */
    private boolean isFlipped;
    /**
     * <code>AnimalState</code> actual del <code>Animal</code>
     */
    private AnimalState currentState;
    /**
     * Habitat en el cual esta contenido
     */
    public Habitat ownerHabitat;
    /**
     * Cronometrizador
     */
    private final long initMs;
    /**
     * Cronometrizador
     */
    private long currentMs;
    /**
     * Cronometrizador de hace cuanto tiempo se hizo
     * el ultimo cambio a la informacion grafica del <code>Animal</code>
     */
    private long spriteInitMs;
    /**
     * Cronometrizador de hace cuanto tiempo se hizo
     * el ultimo cambio a la informacion grafica del <code>Animal</code>
     */
    private long spriteCurrentMs;
    /**
     * Cronometrizador de cuanto tiempo lleva sin comer
     */
    private long hungerInitMs;
    /**
     * Cronometrizador de cuanto tiempo lleva sin comer
     */
    private long hungerCurrentMs;
    /**
     * Tiempo (en ms) maximo que pasa sin estar hambriento
     */
    private long HUNGER_LIMIT_MS;
    /**
     * Tiempo (en ms) que puede pasar sin comer, antes de morir
     */
    private long HUNGER_MAX_LIMIT_MS;

    public Animal(Habitat ownerHabitat, ZooPoint p) {
        this.ownerHabitat = ownerHabitat;
        this.x = p.x;
        this.y = p.y;

        initMs = System.currentTimeMillis();
        currentMs = initMs;
        spriteInitMs = initMs;
        spriteCurrentMs = initMs;
        hungerInitMs = initMs;
        hungerCurrentMs = initMs;
    }

    @Override
    public void accept(Visitor v) {
        v.visitAnimal(this);
    }

    @Override
    public int getAbsX() {
        return x + ownerHabitat.getAbsX();
    }

    @Override
    public int getAbsY() {
        return y + ownerHabitat.getAbsY();
    }

    @Override
    public void update() {
        currentState.stateUpdate();

        currentMs = System.currentTimeMillis();
        spriteCurrentMs = System.currentTimeMillis();
        hungerCurrentMs = System.currentTimeMillis();
    }

    /**
     * Se llama para cambiar el <code>State</code> del <code>Animal</code> a otro <code>State</code>
     * @param newState Nuevo <code>State</code> que va a tomar el <code>Animal</code>
     */
    public void changeState(AnimalState newState) {
        this.currentState = newState;
        currentState.stateInit();
    }

    /**
     * Funcion para revisar si dos animales son compatibles
     * Notar que es simetrica, no importa que animal se ingresa primero
     * @param animal1 Primer animal a revisar compatibilidad
     * @param animal2 Segundo animal a revisar compatibilidad
     * @return <code>true</code> en caso de ser compatibles, <code>false</code> en caso contrario.
     */
    public static boolean doGetAlong(Animal animal1, Animal animal2) {
        for (EnumAnimal a: animal1.getInvalidCompanion()) {
            if (a.getTipo() == animal2.getClass()) {
                if (animal2.getCurrentState().getClass() != DeadAnimalState.class) {
                    return false;
                }
            }
        }
        for (EnumAnimal a: animal2.getInvalidCompanion()) {
            if (a.getTipo() == animal1.getClass()) {
                if (animal1.getCurrentState().getClass() != DeadAnimalState.class) {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO: Actualmente es necesario que los hijos definan metodos para acceder a sus sprites

    /**
     * Funcion para recibir la informacion grafica del <code>Animal</code> si es que esta en <code>IdleAnimalState</code>
     * @return Sprite que identifica al <code>Animal</code> en el estado <code>IdleAnimalState</code>
     */
    public abstract Sprite getIdleSprite();
    /**
     * Funcion para recibir la informacion grafica del <code>Animal</code> si es que esta en movimiento
     * @return Sprite que identifica al <code>Animal</code> si esta en movimiento
     */
    public abstract Sprite getWalkSprite();
    /**
     * Funcion para recibir la informacion grafica del <code>Animal</code> si es que esta comiendo
     * @return Sprite que identifica al <code>Animal</code> si esta comiendo
     */
    public abstract Sprite getEatSprite();
    /**
     * Funcion para recibir la informacion grafica del <code>Animal</code> si es que esta en <code>StarvingAnimalState</code>
     * @return Sprite que identifica al <code>Animal</code> en el estado <code>StarvingAnimalState</code>
     */
    public abstract Sprite getHungrySprite();

    /**
     * Cronometrizador
     * @return Devuelve el tiempo que lleva el objeto instanciado
     */
    public long getTimeElapsed() {
        return currentMs - initMs;
    }
    /**
     * Cronometrizador de hace cuanto tiempo se hizo
     * el ultimo cambio a la informacion grafica del <code>Animal</code>
     * @return Devuelve el tiempo que lleva el objeto con el mismo <code>Sprite</code>
     */
    public long getSpriteTimeElapsed() {
        return spriteCurrentMs - spriteInitMs;
    }
    /**
     * Metodo a llamar en caso de cambiar el <code>Sprite</code>,
     * reinicia el cronometrizador de hace cuanto tiempo se hizo el ultimo de <code>Sprite</code>
     */
    public void restartSpriteTimeElapsed() {
        spriteInitMs = System.currentTimeMillis();
        spriteCurrentMs = spriteInitMs;
    }
    /**
     * Cronometrizador de cuanto tiempo lleva el <code>Animal</code> sin comer
     * @return Devuelve el tiempo que lleva el <code>Animal</code> sin comer
     */
    public long getHungerTimeElapsed() {
        return hungerCurrentMs - hungerInitMs;
    }
    /**
     * Metodo a llamarse inmediatamente de que el <code>Animal</code> consuma comida,
     * reinicia el cronometrizador de cuanto tiempo lleva el <code>Animal</code> sin comer
     */
    public void restartHungerTimeElapsed() {
        hungerInitMs = System.currentTimeMillis();
        hungerCurrentMs = hungerInitMs;
    }

    /**
     * Permite obtener el tiempo maximo que se puede pasar sin estar hambriento
     * @return Tiempo (en ms) maximo que pasa sin estar hambriento
     */
    public long getHungerLimitMs() {
        return HUNGER_LIMIT_MS;
    }

    protected void setHungerLimitMs(long timeMs) {
        this.HUNGER_LIMIT_MS = timeMs;
    }
    /**
     * Permite obtener el tiempo maximo que se puede pasar sin comer
     * @return Tiempo (en ms) que puede pasar sin comer, antes de morir
     */
    public long getHungerMaxLimitMs() {
        return HUNGER_MAX_LIMIT_MS;
    }
    protected void setHungerMaxLimitMs(long timeMs) {
        this.HUNGER_MAX_LIMIT_MS = timeMs;
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

    /**
     * Permite obtener la temperatura minima de <code>Habitat</code> que el <code>Animal</code> acepta
     * @return Temperatura minima
     */
    public float getMinTemperature() {
        return minTemperature;
    }
    protected void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }
    /**
     * Permite obtener la temperatura maxima de <code>Habitat</code> que el <code>Animal</code> acepta
     * @return Temperatura maxima
     */
    public float getMaxTempperature() {
        return maxTempperature;
    }
    protected void setMaxTempperature(float maxTempperature) {
        this.maxTempperature = maxTempperature;
    }

    /**
     * Permite obtener la dieta del <code>Animal</code>
     * @return Lista de comida que el <code>Animal</code> consume
     */
    public EnumFood[] getPrefferedFood() {
        return prefferedFood;
    }
    protected void setPrefferedFood(EnumFood[] prefferedFood) {
        this.prefferedFood = prefferedFood;
    }

    /**
     * Permite obtener los animales con que <code>Animal</code> es incompatible
     * @return Lista de animales con cuales el <code>Animal</code> no puede compartir habitat.
     */
    public EnumAnimal[] getInvalidCompanion() {
        return invalidCompanion;
    }
    protected void setInvalidCompanion(EnumAnimal[] invalidCompanion) {
        this.invalidCompanion = invalidCompanion;
    }

    public Sprite getSprite() {
        return currentSprite;
    }
    public void setSprite(Sprite sprite) {
        currentSprite = sprite;
    }

    public boolean isFlipped() {
        return isFlipped;
    }
    public void setFlipped(boolean isFlipped) {
        this.isFlipped = isFlipped;
    }

    /**
     * Permite revisar el estado del <code>Animal</code>
     * @return <code>AnimalState</code> actual del <code>Animal</code>
     */
    public AnimalState getCurrentState() {return currentState;}
    public Hitbox getHitbox() {
        return hitbox;
    }
}
