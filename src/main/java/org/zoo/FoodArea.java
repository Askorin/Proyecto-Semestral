package org.zoo;

import org.zoo.vista.Drawable;
import org.zoo.vista.Positionable;
import org.zoo.vista.Visitor;

import java.awt.*;
import java.util.ArrayList;

public class FoodArea implements Drawable, Unblockable {
    public int x;
    public int y;
    protected int width;
    protected int height;
    private int [] foodQuantity;
    private ArrayList<FoodDisplay> allFoodDisplays;
    private Positionable owner;
    public FoodArea(Positionable owner, int x, int y, int width, int height) {
        this.owner = owner;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        foodQuantity = new int[Food.values().length];
        allFoodDisplays = new ArrayList<>();
    }
    public void add(Food food) {
        foodQuantity[food.ordinal()] += 1;
        allFoodDisplays.add(new FoodDisplay(food));
    }
    public boolean remove(Food food) {
        if (foodQuantity[food.ordinal()] > 0) {
            foodQuantity[food.ordinal()] += -1;
            for (FoodDisplay fd: allFoodDisplays) {
                if (fd.food == food) {
                    allFoodDisplays.remove(fd);
                    return true;
                }
            }

        }
        return false;
    }
    public boolean find(Food food) {
        return foodQuantity[food.ordinal()] > 0;
    }
    //Detectar el FoodContainer de un habitat
    static public FoodArea searchFoodContainer(Habitat habitat) {
        //TODO: No es bueno usar typecasting, quizas org.zoo.Habitat podria tener como variable el FoodContainer
        FoodArea targetFood = null;
        for (Unblockable u: habitat.getContainables().getUnblockables()) {
            if (u.getClass() == FoodArea.class) {
                targetFood = (FoodArea) u;
            }
        }
        return targetFood;
    }
    @Override
    public void accept(Visitor v) {
        v.visitFoodArea(this);
        // int x = this.x + absX;
        // int y = this.y + absY;
        // g.setColor(new Color(85, 28, 19));
        // g.fillRect(x, y, width, height);
        // g.setColor(new Color(137, 58, 27));
        // g.fillRect(x, y, width, 8);
        // g.fillRect(x, y + height - 8, width, 8);
        // g.fillRect(x, y, 8, height);
        // g.fillRect(x + width - 8, y, 8, height);

        // for (FoodDisplay fd: allFoodDisplays) {
        //     fd.draw(g, x, y);
        // }

        // g.setColor(new Color(195, 95, 29));
        // g.fillRect(x, y, width, 4);
        // g.fillRect(x, y + height - 4, width, 4);
        // g.fillRect(x, y, 4, height);
        // g.fillRect(x + width - 4, y, 4, height);
    }

    @Override
    public int getAbsX() {
        return x + owner.getAbsX();
    }

    @Override
    public int getAbsY() {
        return y + owner.getAbsY();
    }

    @Override
    public Hitbox getHitbox() {
        return new Hitbox(x, y, width, height);
    }

    public ArrayList<FoodDisplay> getAllFoodDisplays() {
        return allFoodDisplays;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getFoodQuantity() {
        return foodQuantity;
    }

    public class FoodDisplay implements Drawable {
        private Food food;
        private int x;
        private int y;
        private int width = 32;
        private int height = 32;
        private int rotation;
        public FoodDisplay(Food food) {
            //es importante que la comida se imprima dentro de org.zoo.FoodArea
            x = (int) (Math.random() * (FoodArea.this.width - width));
            y = (int) (Math.random() * (FoodArea.this.height - height));

            rotation = (int) (Math.random() * 360); //TODO: Implementar rotación

            this.food = food;
        }
        @Override
        public void accept(Visitor v) {
            v.visitFoodDisplay(this);

            // int x = this.x + absX;
            // int y = this.y + absY;
            // if (true) {//Borrar luego
            //     g.setColor(Color.CYAN);
            //     g.drawRect(x, y, width, height);
            // }
            // food.getInGameSprite().drawSprite(g, x, y, width, height, 0, 1.0f);
        }
        @Override
        public int getAbsX() {
            return x + FoodArea.this.x + owner.getAbsX();
        }
        public int getAbsY() {
            return y + FoodArea.this.y + owner.getAbsY();
        }

        public Food getFood() {
            return food;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}