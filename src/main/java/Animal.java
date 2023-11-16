import java.awt.*;
public abstract class Animal implements Timeable, Drawable {
    public int x;
    public int y;
    protected int width;
    protected int height;
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

        g.drawImage(currentSprite.getFrame(timeElapsed), x, y, null);
    }
    public void step() {
        currentState.stateBehavior(this);
        timeElapsed += GlobalTimer.MS_PER_FRAME;
    }

    public abstract Sprite getIdleSprite();
    public abstract Sprite getWalkSprite();

    public void setSprite(Sprite sprite) {
        currentSprite = sprite;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
