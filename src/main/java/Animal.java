import java.awt.*;
public class Animal implements Timeable {
    int x;
    int y;
    int speedX = 5; int speedY = 5;
    int width = 64;
    int height = 64;
    Habitat habitat;
    public Animal(Habitat habitat) {
        this.habitat = habitat;
    }
    public void draw(Graphics g, int absX, int absY) {
        int x = absX + this.x;
        int y = absY + this.y;

        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
    public void step() {
        x += speedX;
        if (x + width > habitat.width || x < 0) {speedX *= -1;}
        y += speedY;
        if (x + height > habitat.height || x < 0) {speedY *= -1;}
    }
}
