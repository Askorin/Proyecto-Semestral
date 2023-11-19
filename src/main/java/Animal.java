import java.awt.*;
public abstract class Animal implements Updatable, Drawable {
    public int x;
    public int y;
    private int width;
    private int height;
    protected Sprite currentSprite;
    protected State currentState;
    protected Habitat ownerHabitat;
    private final long initMs;
    private long currentMs;
    public Animal(Habitat habitat) {
        ownerHabitat = habitat;
        initMs = System.currentTimeMillis();
        currentMs = initMs;
    }
    public void draw(Graphics g, int absX, int absY) {
        int x = absX + this.x;
        int y = absY + this.y;

        //Dibujar Hitbox (Borrar luego)
        if (true) {
            g.setColor(Color.RED);
            g.drawRect(x, y, width, height);
        }
        long timeElapsed = currentMs - initMs;
        currentSprite.drawSprite(g, x, y, getWidth(), getHeight(), timeElapsed, 1.0f);
    }
    public void update() {
        currentState.stateBehavior(this);
        currentMs = System.currentTimeMillis();
    }
    //Administrador de estados, corresponde al grafo de estados en una maquina de estados finitos (No sé de que hablo)
    public void changeState(State currentState) {
        if (currentState.getClass() == IdleState.class) {
            this.currentState = new WalkingState(this);
            return;
        }
        if (currentState.getClass() == WalkingState.class) {
            this.currentState = new IdleState(this);
            return;
        }
    }

    //TODO: Actualmente es necesario que los hijos definan metodos para acceder a sus sprites
    public abstract Sprite getIdleSprite();
    public abstract Sprite getWalkSprite();

    public void setSprite(Sprite sprite) {
        currentSprite = sprite;
    }
    public int getWidth() {
        return width;
    }
    protected void setWidth(int width) {this.width = width;}
    public int getHeight() {
        return height;
    }
    protected void setHeight(int height) {this.height = height;}
}
