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
    public boolean checkHitboxCollision(int x, int y, int width, int height) {
        boolean cond1 = false;
        boolean cond2 = false;

        /* Que dos rectangulos esten en contacto implica que:
           Sea el primer rectangulo de origen (xa , ya) y dimensiones (ha, wa)
           y el segundo rectangulo de origen (xb, yb) y dimensiones (hb, wb)
           entonces, que esten colisionando es que se cumplan dos condiciones:
           cond 1: en el caso de xa < xb se debe cumplir xa + ha >= xb
           en el caso de xb < xa se debe cumplir xb + hb >= xa
           o bien que sea el caso de que xa == xb
           cond 2: analogo para y */

        if (this.x < x) {
            if (this.x + this.width > x) {cond1 = true;}
        }
        else if (x < this.x) {
            if (x + width > this.x) {cond1 = true;}
        }
        else {cond1 = true;}

        if (this.y < y) {
            if (this.y + this.height > y) {cond2 = true;}
        }
        else if (y < this.y) {
            if (y + height > this.y) {cond2 = true;}
        }
        else {cond2 = true;}

        return (cond1 && cond2);
    }

    //Detectar el FoodContainer de un habitat
    static public FoodContainer searchFoodContainer(Habitat habitat) {
        //TODO: No es bueno usar typecasting, quizas Habitat podria tener como variable el FoodContainer
        FoodContainer container = null;
        for (Drawable d: habitat.drawableComponents) {
            if (d.getClass() == FoodContainer.class) {
                container = (FoodContainer) d;
            }
        }
        return container;
    }
}