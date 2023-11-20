public class Gato extends Animal {
    public static int width = 80;
    public static int height = 80;
    public Gato(Habitat habitat) {
        super(habitat);
        //Hay que entregar las dimensiones del hijo para sobreescribir las dimensiones del padre
        setWidth(width);
        setHeight(height);
        //Importante que el estado se defina DESPUES de las dimensiones
        currentState =  new IdleState(this);
    }

    @Override
    public Sprite getIdleSprite() {return Sprite.CAT_IDLE;}
    public Sprite getWalkSprite() {return Sprite.CAT_WALK;}
}
