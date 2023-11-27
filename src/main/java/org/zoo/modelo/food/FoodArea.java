package org.zoo.modelo.food;

import org.zoo.modelo.habitat.Habitat;
import org.zoo.utilities.Hitbox;
import org.zoo.modelo.characteristics.Unblockable;
import org.zoo.vista.Drawable;
import org.zoo.modelo.characteristics.Positionable;
import org.zoo.vista.visitor.Visitor;

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
        foodQuantity = new int[EnumFood.values().length];
        allFoodDisplays = new ArrayList<>();
    }
    public void add(EnumFood enumFood) {
        foodQuantity[enumFood.ordinal()] += 1;
        allFoodDisplays.add(new FoodDisplay(enumFood));
    }
    public boolean remove(EnumFood enumFood) {
        if (foodQuantity[enumFood.ordinal()] > 0) {
            foodQuantity[enumFood.ordinal()] += -1;
            for (FoodDisplay fd: allFoodDisplays) {
                if (fd.enumFood == enumFood) {
                    allFoodDisplays.remove(fd);
                    return true;
                }
            }

        }
        return false;
    }
    public EnumFood find(EnumFood[] prefferedFood) {
        for (EnumFood f: prefferedFood) {
            if (foodQuantity[f.ordinal()] > 0) {
                return f;
            }
        }
        return null;
    }
    //Detectar el FoodContainer de un habitat
    static public FoodArea searchFoodContainer(Habitat habitat) {
        //TODO: No es bueno usar typecasting, quizas org.zoo.modelo.habitat.Habitat podria tener como variable el FoodContainer
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

    @Override
    public Hitbox getAbsHitbox() {
        return new Hitbox(getAbsX(), getAbsY(), width, height);
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
        private EnumFood enumFood;
        private int x;
        private int y;
        private int width = 32;
        private int height = 32;
        private int rotation;
        public FoodDisplay(EnumFood enumFood) {
            //es importante que la comida se imprima dentro de org.zoo.modelo.food.FoodArea
            x = (int) (Math.random() * (FoodArea.this.width - width));
            y = (int) (Math.random() * (FoodArea.this.height - height));

            rotation = (int) (Math.random() * 360); //TODO: Implementar rotación

            this.enumFood = enumFood;
        }
        @Override
        public void accept(Visitor v) {
            v.visitFoodDisplay(this);
        }
        @Override
        public int getAbsX() {
            return x + FoodArea.this.x + owner.getAbsX();
        }
        public int getAbsY() {
            return y + FoodArea.this.y + owner.getAbsY();
        }

        public EnumFood getFood() {
            return enumFood;
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