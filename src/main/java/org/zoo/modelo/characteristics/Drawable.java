package org.zoo.modelo.characteristics;
import org.zoo.modelo.characteristics.Positionable;
import org.zoo.visitor.Visitable;

/**
 * Una interfaz implementada por clases del modelo que sean dibujables en el
 * lado gráfico.
 */
public interface Drawable extends Visitable, Positionable {
}
