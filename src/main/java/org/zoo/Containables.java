package org.zoo;

import org.zoo.vista.Drawable;

import java.util.ArrayList;

public class Containables {
    private ArrayList<Updatable> updatables;
    private ArrayList<Drawable> drawables;
    private ArrayList<Unblockable> unblockables;
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
    private void addUpdatable(Updatable u) {
        updatables.add(u);
    }
    private void removeUpdatable(Updatable u) {
        updatables.remove(u);
    }
    public ArrayList<Unblockable> getUnblockables() {
        return unblockables;
    }
    private void addUnblockable(Unblockable u) {
        unblockables.add(u);
    }
    private void removeUnblockable(Unblockable u) {
        unblockables.remove(u);
    }
}
