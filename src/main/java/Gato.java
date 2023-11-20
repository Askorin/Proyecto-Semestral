public class Gato extends Animal {
    public static int width = 80;
    public static int height = 80;
    private long HUNGER_LIMIT_MS = 5000;
    private long HUNGER_MAX_LIMIT_MS = 18000;
    public Gato(Habitat habitat, int x, int y) {
        super(habitat, x, y);
        //Hay que entregar las dimensiones del hijo para sobreescribir las dimensiones del padre
        setWidth(width);
        setHeight(height);
        setHungerLimitMs(HUNGER_LIMIT_MS);
        setHungerMaxLimitMs(HUNGER_MAX_LIMIT_MS);
        //Importante que el estado se defina DESPUES de las dimensiones
        currentState =  new WalkingState(this);
    }

    @Override
    public Sprite getIdleSprite() {return Sprite.CAT_IDLE;}
    @Override
    public Sprite getWalkSprite() {return Sprite.CAT_WALK;}
    @Override
    public Sprite getEatSprite() {return Sprite.CAT_EAT;}
    @Override
    public Sprite getHungrySprite() {return Sprite.CAT_HUNGRY;}
}
