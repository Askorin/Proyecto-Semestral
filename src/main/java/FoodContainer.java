import java.awt.*;

public class FoodContainer implements Drawable, Unblockable {
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

    @Override
    public Hitbox getHitbox() {
        return new Hitbox(x, y, width, height);
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