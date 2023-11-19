public class Gato extends Animal {
    int width = 80;
    int height = 80;
    public Gato(Habitat habitat, int x, int y) {
        super(habitat, x, y);
        //Hay que entregar las dimensiones del hijo para sobreescribir las dimensiones del padre
        setWidth(width);
        setHeight(height);
        //Importante que el estado se defina DESPUES de las dimensiones
        currentState =  new GatheringState(this);
    }

    @Override
    public Sprite getIdleSprite() {return Sprite.CAT_IDLE;}
    @Override
    public Sprite getWalkSprite() {return Sprite.CAT_WALK;}
    @Override
    public Sprite getEatSprite() {return Sprite.CAT_EAT;}
}
