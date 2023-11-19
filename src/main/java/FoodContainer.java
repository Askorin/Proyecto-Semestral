import java.awt.*;

public class FoodContainer implements Drawable {
    public int x = 48 * 4;
    public int y = 0;
    protected int width = 16 * 4;
    protected int height = 64 * 4;

    @Override
    public void draw(Graphics g, int absX, int absY) {
        int x = this.x + absX;
        int y = this.y + absY;
        g.setColor(new Color(150, 75, 0));
        g.fillRect(x, y, width, height);
    }

    public int getCloserPointToHitbox_coordX(int x, int y, int width, int height) {
        int targetX;
        if (x < this.x) {
            targetX = this.x - width;
        }
        else if (x > this.x + this.width) {
            targetX = this.x + this.width;
        }
        else {
            targetX = x;
        }
        int targetY;
        if (y < this.y) {
            targetY = this.y - height;
        }
        else if (y > this.y + this.height) {
            targetY = this.y + this.height;
        }
        else {
            targetY = y;
        }
        return targetX;
    }
    public int getCloserPointToHitbox_coordY(int x, int y, int width, int height) {
        int targetX;
        if (x < this.x) {
            targetX = this.x - width;
        }
        else if (x > this.x + this.width) {
            targetX = this.x + this.width;
        }
        else {
            targetX = x;
        }
        int targetY;
        if (y < this.y) {
            targetY = this.y - height;
        }
        else if (y > this.y + this.height) {
            targetY = this.y + this.height;
        }
        else {
            targetY = y;
        }
        return targetY;
    }
}