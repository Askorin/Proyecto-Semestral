package org.zoo.modelo.food;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.utilities.Hitbox;
import org.zoo.modelo.characteristics.Unblockable;
import org.zoo.modelo.characteristics.Drawable;
import org.zoo.modelo.characteristics.Positionable;
import org.zoo.visitor.Visitor;

import java.util.ArrayList;

/**
 * Clase logica que modela un compartimento de comida dentro del zoologico,
 * al cual se dirigen a buscar a comida los animales cuando tienen hambre.
 */
public class FoodArea implements Drawable, Unblockable {
    public int x;
    public int y;
    protected int width;
    protected int height;
    /**
     * Array de n enteros, donde n es la cantidad de tipos de comidas que hay en el programa
     * el i-esimo entero es de valor igual a la cantidad de comidas
     * que hay del i-esimo tipo de comida de <code>EnumFood</code>
     * @see EnumFood
     */
    private final int [] foodQuantity;
    /**
     * Lista de todos los <code>FoodDisplay</code> que contiene el compartimento
     */
    private final ArrayList<FoodDisplay> allFoodDisplays;
    /**
     * Contenedor (generalmente un <code>Habitat</code>) donde esta contenido el compartimento
     */
    private final Positionable owner;
    public FoodArea(Positionable owner, int x, int y, int width, int height) {
        this.owner = owner;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        foodQuantity = new int[EnumFood.values().length];
        allFoodDisplays = new ArrayList<>();
    }

    /**
     * Metodo que permite agregar comida al compartimento
     * @param enumFood Agrega una unidad de la comida especificada por el elemento de la enumeracion
     */
    public void add(EnumFood enumFood) {
        foodQuantity[enumFood.ordinal()] += 1;
        allFoodDisplays.add(new FoodDisplay(enumFood));
    }

    /**
     * Metodo que permite eliminar comida al compartimento (Generalmente porque esta fue consumida)
     * @param enumFood Elimina una unidad de la comida especificada por el elemento de la enumeracion
     * @return Devuelve <code>true</code> si habia de la comida que se solicito eliminar, <code>false</code> en caso contrario.
     */
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

    /**
     * Metodo que permite saber si existe una unidad de comida en el compartimento de una lista de tipos de comida
     * @param prefferedFood Lista de tipos de comida de la cual se desea buscar una unidad
     * @return Devuelve el tipo de comida si se encuentra una comida de la lista. Devuelve <code>null</code> en caso contrario.
     */
    public EnumFood find(EnumFood[] prefferedFood) {
        for (EnumFood f: prefferedFood) {
            if (foodQuantity[f.ordinal()] > 0) {
                return f;
            }
        }
        return null;
    }

    /**
     * Metodo estatico que permite saber si hay un compartimento de comida en un cierto <code>Habitat</code> dado
     * @param habitat <code>Habitat</code> en el cual queremos revisar
     * @return Devuelve un <code>FoodArea</code> encontrado en el <code>Habitat</code> si es que existe.
     * Devuelve <code>null</code> en caso de que no exista.
     */
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

    /**
     * Permite ver todos los <code>FoodDisplay</code> del compartimento
     * @return Lista de <code>FoodDisplay</code> de <code>FoodArea</code>
     */
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

    /**
     * Clase que representa una unidad de un cierto tipo de comida en el zoo
     * Es una clase anidada dentro de <code>FoodArea</code>
     * @see FoodArea
     */
    public class FoodDisplay implements Drawable {
        /**
         * Tipo de comida de la instancia
         */
        private final EnumFood enumFood;
        private final int x;
        private final int y;
        private final int width = 32;
        private final int height = 32;
        public FoodDisplay(EnumFood enumFood) {
            //es importante que la comida se imprima dentro de org.zoo.modelo.food.FoodArea
            x = (int) (Math.random() * (FoodArea.this.width - width));
            y = (int) (Math.random() * (FoodArea.this.height - height));

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

        /**
         * Permite saber el tipo de comida que es la comida
         * @return Devuelve el elemento de <code>EnumFood</code> que corresponde al tipo de comida
         */
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