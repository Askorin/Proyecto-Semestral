package org.zoo.utilities;


/**
 * Clase ZooPoint para realizar operaciones comunes entre vectores / puntos en un
 * plano cartesiano.
 */
public class ZooPoint {
    public int x;
    public int y;
    public ZooPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calcula el <code>ZooPoint</code> de diferencia entre dos instancias de <code>ZooPoint</code>.
     * @param minuend El minuendo.
     * @param subtrahend El sustraendo.
     * @return El <code>ZooPoint</code> de diferencia.
     */
    public static ZooPoint getDifference(ZooPoint minuend, ZooPoint subtrahend) {
        return new ZooPoint(minuend.x - subtrahend.x, minuend.y - subtrahend.y);
    }

    @Override
    public String toString() {
        return "Punto(x: " + x + ", y: " + y + ")";
    }
}
