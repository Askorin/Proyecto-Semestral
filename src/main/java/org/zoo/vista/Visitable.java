package org.zoo.vista;

import org.zoo.Point;

import java.awt.*;

public interface Visitable {
    public void accept(Visitor v);
}
