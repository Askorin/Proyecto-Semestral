package org.zoo.utilities;

public class Hitbox {
    public int x;
    public int y;
    public int width;
    public int height;
    public Hitbox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static boolean checkPointHitboxCollision(Hitbox hitbox, Point p) {
        /*
         * Podemos crear un hitbox de ancho y largo 0, y ocupar la función
         * checkHitboxCollision
         */
        return checkHitboxCollision(hitbox, new Hitbox(p.x, p.y, 0, 0));
    }

    public static boolean checkHitboxCollision(Hitbox hitbox1, Hitbox hitbox2) {
        boolean cond1 = false;
        boolean cond2 = false;

        /*
         * Que dos rectángulos estén en contacto implica que:
         * Sea el primer rectángulo de origen (xa , ya) y dimensiones (ha, wa)
         * y el segundo rectángulo de origen (xb, yb) y dimensiones (hb, wb)
         * entonces, que estén colisionando es que se cumplan dos condiciones:
         * cond 1: en el caso de xa < xb se debe cumplir xa + ha >= xb
         * en el caso de xb < xa se debe cumplir xb + hb >= xa
         * o bien que sea el caso de que xa == xb
         * cond 2: análogo para y.
         */

        if (hitbox1.x < hitbox2.x) {
            if (hitbox1.x + hitbox1.width > hitbox2.x) {cond1 = true;}
        }
        else if (hitbox2.x < hitbox1.x) {
            if (hitbox2.x + hitbox2.width > hitbox1.x) {cond1 = true;}
        }
        else {cond1 = true;}

        if (hitbox1.y < hitbox2.y) {
            if (hitbox1.y + hitbox1.height > hitbox2.y) {cond2 = true;}
        }
        else if (hitbox2.y < hitbox1.y) {
            if (hitbox2.y + hitbox2.height > hitbox1.y) {cond2 = true;}
        }
        else {cond2 = true;}

        return (cond1 && cond2);
    }

    /*Tenemos dos rectangulos: movableHitbox y targetHitbox
      este metodo devuelve al punto más cercano que se deberia mover movableHitbox para
      estar adyacente a targetHitbox (rozandolo, pero sin estar uno sobre el otro)
      Importante: No funciona si los objetos estan uno sobre el otro
     */
    static public Point getCloserPointToHitbox(Hitbox movableHitbox, Hitbox targetHitbox) {
        int targetX;
        if (movableHitbox.x < targetHitbox.x) {
            targetX = targetHitbox.x - movableHitbox.width;
        }
        else if (movableHitbox.x > targetHitbox.x + targetHitbox.width) {
            targetX = targetHitbox.x + targetHitbox.width;
        }
        else {
            targetX = movableHitbox.x;
        }
        int targetY;
        if (movableHitbox.y < targetHitbox.y) {
            targetY = targetHitbox.y - movableHitbox.height;
        }
        else if (movableHitbox.y > targetHitbox.y + targetHitbox.height) {
            targetY = targetHitbox.y + targetHitbox.height;
        }
        else {
            targetY = movableHitbox.y;
        }
        return new Point(targetX, targetY);
    }

    @Override
    public String toString() {
        return "Hitbox{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
