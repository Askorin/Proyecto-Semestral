package org.zoo.utilities;

public class Point {
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static Point getDifference(Point minuend, Point subtrahend) {
        return new Point(minuend.x - subtrahend.x, minuend.y - subtrahend.y);
    }

    @Override
    public String toString() {
        return "Punto(x: " + x + ", y: " + y + ")";
    }
}
