package org.zoo.vista;

import java.awt.*;
import org.zoo.Point;

// TODO: cambiar nombre de interfaz a Acceptor o algo así.
public interface Drawable {
    public void draw(Graphics g, Point absPoint, Visitor v);
}
