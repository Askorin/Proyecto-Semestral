package org.zoo.utilities;

public class ZooPoint {
    public int x;
    public int y;
    public ZooPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static ZooPoint getDifference(ZooPoint minuend, ZooPoint subtrahend) {
        return new ZooPoint(minuend.x - subtrahend.x, minuend.y - subtrahend.y);
    }

    @Override
    public String toString() {
        return "Punto(x: " + x + ", y: " + y + ")";
    }
}
