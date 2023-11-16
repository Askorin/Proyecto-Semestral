import java.awt.*;
public class Animal implements Timeable, Drawable {
    int x;
    int y;
    int speedX = 5; int speedY = 5;
    int width = 64;
    int height = 64;
    Habitat ownerHabitat;
    public Animal(Habitat habitat) {
        ownerHabitat = habitat;
    }
    public void draw(Graphics g, int absX, int absY) {
        int x = absX + this.x;
        int y = absY + this.y;

        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
    public void step() {
        x += speedX;
        if (x + width > ownerHabitat.width || x < 0) {speedX *= -1;}
        y += speedY;
        if (x + height > ownerHabitat.height || x < 0) {speedY *= -1;}
    }
}
