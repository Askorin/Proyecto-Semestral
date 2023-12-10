package org.zoo.modelo;

/**
 * Almacena informacion sobre la animacion actual que cursa un objeto,
 * esta orientado a ser un componente solo de la parte logica del programa,
 * por lo tanto, no contiene ni carga graficos.
 */
public enum Sprite {
    CAT_IDLE("src/main/resources/CatIdle.png", 6, 150, 13*4, 9*4),
    CAT_WALK("src/main/resources/CatWalk.png", 2, 90, 15*4, 9*4),
    CAT_EAT("src/main/resources/CatEat.png", 4, 90, 16*4, 2*4),
    CAT_HUNGRY("src/main/resources/CatHungry.png", 6, 220, 13*4, 9*4),
    ELEPHANT_IDLE("src/main/resources/ElephantIdle.png", 2, 350, 11*4, 11*4),
    ELEPHANT_WALK("src/main/resources/ElephantWalk.png", 3, 130, 16*4, 9*4),
    ELEPHANT_EAT("src/main/resources/ElephantEat.png", 4, 100, 16*4, 10*4),
    ELEPHANT_HUNGRY("src/main/resources/ElephantHungry.png", 2, 500, 11*4, 11*4),
    LION_IDLE("src/main/resources/LionIdle.png", 2, 250, 8*4, 7*4),
    LION_WALK("src/main/resources/LionWalk.png", 4, 120, 8*4, 7*4),
    LION_HUNGRY("src/main/resources/LionIdle.png", 2, 350, 8*4, 7*4),
    LION_EAT("src/main/resources/LionEat.png", 2, 100, 8*4, 7*4),
    MONKEY_IDLE("src/main/resources/MonkeyIdle.png", 1, 1, 3*4, 5*4),
    MONKEY_WALK("src/main/resources/MonkeyWalk.png", 4, 100, 5*4, 5*4),
    MONKEY_HUNGRY("src/main/resources/MonkeyIdle.png", 1, 1, 3*4, 5*4),
    MONKEY_EAT("src/main/resources/MonkeyEat.png", 2, 120, 3*4, 5*4),
    PANDA_IDLE("src/main/resources/PandaIdle.png", 1, 1, 6*4, 6*4),
    PANDA_WALK("src/main/resources/PandaWalk.png", 4, 150, 6*4, 6*4),
    PANDA_HUNGRY("src/main/resources/PandaIdle.png", 1, 1, 6*4, 6*4),
    PANDA_EAT("src/main/resources/PandaEat.png", 4, 120, 5*4, 6*4),
    PENGUIN_IDLE("src/main/resources/PenguinIdle.png", 1, 1, 3*4, 7*4),
    PENGUIN_WALK("src/main/resources/PenguinWalk.png", 4, 90, 4*4, 7*4),
    PENGUIN_HUNGRY("src/main/resources/PenguinIdle.png", 1, 1, 3*4, 7*4),
    PENGUIN_EAT("src/main/resources/PenguinEat.png", 4, 130, 5*4, 7*4),
    ANIMAL_DEAD("src/main/resources/AnimalDead.png", 1, 1, 5*4, 11*4),
    ANIMAL_DEAD_ANIM("src/main/resources/AnimalDeadAnim.png", 6, 100, 5*4, 11*4),
    MEADOWHABITAT("src/main/resources/MeadowHabitat.png", 1, 1, 106/2 * 4, 112/2 * 4),
    FORESTHABITAT("src/main/resources/ForestHabitat.png", 1, 1, 106/2 * 4, 96/2 * 4),
    SAVANNAHABITAT("src/main/resources/SavannaHabitat.png", 1, 1, 90/2 * 4, 112/2 * 4),
    SNOWYHABITAT("src/main/resources/SnowyHabitat.png", 1, 1, 90/2 * 4, 96/2 * 4),
    FISH_INGAME("src/main/resources/FishIngame.png", 1, 1, 4*4, 4*4),
    LEAVES_INGAME("src/main/resources/LeavesIngame.png", 1, 1, 4*4, 3*4),
    MEAT_INGAME("src/main/resources/MeatIngame.png", 1, 1, 4*4, 3*4),
    BANANA_INGAME("src/main/resources/BananaIngame.png", 1, 1, 2*4, 4*4),
    HUNGER_BUBBLE("src/main/resources/HungerBubble.png", 1, 1, 8*4, 6*4),
    ZOO_BACKGROUND("src/main/resources/ZooBackground.png", 1, 1, 1024*4, 1024*4);
    /**
     * Ruta del archivo de la animacion
     */
    private final String path;
    /**
     * Cuantos cuadros tiene la animacion
     */
    private final int framesNumber;
    /**
     * Cuando tiempo (en ms) dura un cuadro de la animacion
     */
    private final int timePerFrame;
    /**
     * Centro de la animacion
     */
    private int centerX; //Centro de la animacion, es util para casos que el centro del sprite no es en la mitad
    /**
     * Centro de la animacion
     */
    private int centerY;

    Sprite(String path, int framesNumber, int timePerFrame) {
        this.path = path;
        this.framesNumber = framesNumber;
        this.timePerFrame = timePerFrame;

        centerX = 5;
        centerY = 5;
    }

    //Por defecto, el centro de un sprite es al medio, pero podemos entregarle un centro personalizado
    Sprite(String path, int framesNumber, int timePerFrame, int centerX, int centerY) {
        this(path, framesNumber, timePerFrame);
        this.centerX = centerX;
        this.centerY = centerY;
    }

    /**
     * Permite obtener el centro de la animacion
     * @return Coordenada x del centro de la animacion
     */
    public int getCenterX() {
        return centerX;
    }
    /**
     * Permite obtener el centro de la animacion
     * @return Coordenada y del centro de la animacion
     */
    public int getCenterY() {
        return centerY;
    }

    /**
     * Permite obtener la cantidad de cuadros que tiene la animacion
     * @return Cantidad de cuadros que tiene la animacion
     */
    public int getFramesNumber() {
        return framesNumber;
    }

    /**
     * Permite obtener la ruta de archivo de la animacion
     * @return String que contiene la ruta de archivo de la animacion
     */
    public String getPath() {
        return path;
    }

    /**
     * Permite obtener cuanto dura (en ms) un cuadro de la animacion
     * @return Tiempo (en ms) dura un cuadro de la animacion
     */
    public int getTimePerFrame() {
        return timePerFrame;
    }
}
