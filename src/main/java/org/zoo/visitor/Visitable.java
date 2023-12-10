package org.zoo.visitor;

/**
 * Interfaz que describe una clase visitable por un Visitor.
 */
public interface Visitable {
    void accept(Visitor v);
}
