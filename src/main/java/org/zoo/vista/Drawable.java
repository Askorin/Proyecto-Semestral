package org.zoo.vista;
import org.zoo.modelo.characteristics.Positionable;
import org.zoo.vista.visitor.Visitable;

/**
 * Una interfaz implementada por clases del modelo que sean dibujables en el
 * lado gráfico.
 */
public interface Drawable extends Visitable, Positionable {
}
