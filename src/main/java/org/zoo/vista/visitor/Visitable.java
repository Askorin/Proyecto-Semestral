package org.zoo.vista.visitor;

/**
 * Interfaz que describe una clase visitable por un Visitor.
 */
public interface Visitable {
    void accept(Visitor v);
}
