public class Gato extends Animal {
    public Gato(Habitat habitat) {
        super(habitat);
        width = 64;
        height = 64;
        //Importante que el estado se defina DESPUES de las dimensiones
        currentState =  new IdleState(this);
    }

    @Override
    public Sprite getIdleSprite() {return Sprite.CAT_IDLE;}
    public Sprite getWalkSprite() {return Sprite.CAT_WALK;}
}
