import java.awt.*;
public abstract class Animal implements Timeable, Drawable {
    public int x;
    public int y;
    private int width;
    private int height;
    protected Sprite currentSprite;
    protected State currentState;
    protected Habitat ownerHabitat;
    protected int timeElapsed = 0;
    public Animal(Habitat habitat) {
        ownerHabitat = habitat;
    }
    public void draw(Graphics g, int absX, int absY) {
        int x = absX + this.x;
        int y = absY + this.y;

        //Dibujar Hitbox (Borrar luego)
        if (true) {
            g.setColor(Color.RED);
            g.drawRect(x, y, width, height);
        }

        currentSprite.drawSprite(g, x, y, getWidth(), getHeight(), timeElapsed);
    }
    public void step() {
        currentState.stateBehavior(this);
        timeElapsed += GlobalTimer.MS_PER_FRAME;
    }
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
