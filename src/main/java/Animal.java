import java.awt.*;
public abstract class Animal implements Timeable, Drawable {
    int x;
    int y;
    int width;
    int height;
    private Sprite currentSprite;
    State currentState;
    Habitat ownerHabitat;
    public Animal(Habitat habitat) {
        ownerHabitat = habitat;
    }
    public void draw(Graphics g, int absX, int absY) {
        int x = absX + this.x;
        int y = absY + this.y;

        g.drawImage(currentSprite.getImage(), x, y, null);
    }
    public void step() {
        currentState.stateBehavior(this);
    }

    public abstract Sprite getIdleSprite();
    public abstract Sprite getWalkSprite();

    public void setSprite(Sprite sprite) {
        currentSprite = sprite;
    }
}
