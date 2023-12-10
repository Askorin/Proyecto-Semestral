package org.zoo.modelo.characteristics;

import org.zoo.modelo.characteristics.Unblockable;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.characteristics.Drawable;

import java.util.ArrayList;

/**
 * Clase containables, para definir composicion entre objetos sobre los aspectos
 * de renderizacion, actualizacion y chequeo de colisiones.
 */
public class Containables {
    /*
     * Es importante seguir las siguientes reglas:
     * 1- No ocupar un iterador para pasar por elementos updatable.
     * 2- Ocupar un for loop simple que se recorra al revés.
     * 3- Ningún elemento puede eliminar a otro que no sea si mismo.
     * Esto para evitar problemas de modificación de arreglos concurrent.
     */
    private final ArrayList<Updatable> updatables;
    private final ArrayList<Drawable> drawables;
    private final ArrayList<Unblockable> unblockables;
    public Containables() {
        updatables = new ArrayList<>();
        drawables = new ArrayList<>();
        unblockables = new ArrayList<>();
    }
    public void addComponent(Object o) {
        //TODO: este typecasting es malo??
        //TODO: quizas devolver boolean, relacionado con el boolean que devuevle ArrayList.add
        //TODO: que ocurre si "Object o" no entra en ninguna condicional??
        if (o instanceof Drawable) {addDrawable((Drawable) o);}
        if (o instanceof Updatable) {addUpdatable((Updatable) o);}
        if (o instanceof Unblockable) {addUnblockable((Unblockable) o);}
    }
    public void removeComponent(Object o) {
        if (o instanceof Drawable) {removeDrawable((Drawable) o);}
        if (o instanceof Updatable) {removeUpdatable((Updatable) o);}
        if (o instanceof Unblockable) {removeUnblockable((Unblockable) o);}
    }
    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    /**
     * Añade una instancia de objeto <code>Drawable</code> a la instancia <code>Containable</code>.
     * @param d El objeto a añadir.
     */
    private void addDrawable(Drawable d) {
        drawables.add(d);
    }
    // lo mismo
    private void removeDrawable(Drawable d) {
        drawables.remove(d);
    }
    public ArrayList<Updatable> getUpdatables() {
        return updatables;
    }

    /**
     * Añade una instancia de objeto <code>Updatable</code> a la instancia <code>Containable</code>.
     * @param u El objeto a añadir.
     */
    private void addUpdatable(Updatable u) {
        updatables.add(u);
    }
    private void removeUpdatable(Updatable u) {
        updatables.remove(u);
    }
    public ArrayList<Unblockable> getUnblockables() {
        return unblockables;
    }

    /**
     * Añade una instancia de objeto <code>Unblockable</code> a la instancia <code>Containable</code>.
     * @param u El objeto a añadir.
     */
    private void addUnblockable(Unblockable u) {
        unblockables.add(u);
    }
    private void removeUnblockable(Unblockable u) {
        unblockables.remove(u);
    }
}
