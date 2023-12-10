package org.zoo.utilities;


/**
 * Clase que modela una HitBox en el zoologico.
 */
public class Hitbox {
    public int x;
    public int y;
    public int width;
    public int height;

    /**
     * Constructor unico para un HitBox.
     * @param x Su posicion en el eje x.
     * @param y Su posicion en el eje y.
     * @param width Su ancho.
     * @param height Su altura.
     */
    public Hitbox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Verifica que una hitbox se encuentre contenida dentro de otra.
     * @param hitbox1 Hitbox externa que contiene.
     * @param hitbox2 Hitbox interna que es contenida
     * @return true si es que está contenida, false si es que no.
     */
    public static boolean isHitboxContained(Hitbox hitbox1, Hitbox hitbox2) {
        /*
         * El rectángulo 2 se encuentra dentro del rectángulo 1 si es que dados:
         * - El ancho y altura del primer rectángulo: w1, h1.
         * - Las coordenadas del primer rectángulo: x1, y1.
         * - El ancho y altura del segundo rectángulo: w2, h2.
         * - Las coordenadas del segundo rectángulo: x2, y2.
         * Se cumple lo siguiente:
         * - x2 + w2 <= x1 + w1
         * - y2 + h2 <= y2 + h2
         * - x2 >= x1
         * - y2 >= y1
         *
         */

        System.out.println(hitbox1);
        System.out.println(hitbox2);
        boolean cond1 = hitbox2.x + hitbox2.width <= hitbox1.x + hitbox1.width;
        boolean cond2 = hitbox2.y + hitbox2.height <= hitbox1.y + hitbox1.height;
        boolean cond3 = hitbox2.x >= hitbox1.x;
        boolean cond4 = hitbox2.y >= hitbox1.y;
        return cond1 && cond2 && cond3 && cond4;
    }

    /**
     * Verifica que un punto este colisionando con una HitBox.
     * @param hitbox La <code>HitBox</code>a chequear.
     * @param p El <code>ZooPoint</code> a chequear.
     * @return True si existe colision, false en caso contrario.
     */
    public static boolean checkPointHitboxCollision(Hitbox hitbox, ZooPoint p) {
        /*
         * Podemos crear un hitbox de ancho y largo 0, y ocupar la función
         * checkHitboxCollision
         */
        return checkHitboxCollision(hitbox, new Hitbox(p.x, p.y, 0, 0));
    }

    /**
     * Verifica que dos Hitbox esten colisonando.
     * @param hitbox1 La primera <code>HitBox</code> a chequear.
     * @param hitbox2 La segunda <code>HitBox</code> a chequear.
     * @return True si existe colision, false en caso contrario.
     */
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

    /**
     * Devuelve el punto mas cercano al que se deberia mover movableHitbox para
     * estar adyacente a targetHitbox, rozandolo, sin quedar sobrepuesta.
     * Tener en cuenta que este metodo no funciona si las <code>HitBox</code>
     * Se encuentar sobrepuestas.
     * @param movableHitbox La <code>HitBox</code> que se mueve.
     * @param targetHitbox La <code>HitBox</code> que no se mueve.
     * @return El <code>ZooPoint</code> mas cercano al que se debe mover movableHitbox
     */
    static public ZooPoint getCloserPointToHitbox(Hitbox movableHitbox, Hitbox targetHitbox) {
        int targetX;
        if (movableHitbox.x + movableHitbox.width < targetHitbox.x) {
            targetX = targetHitbox.x - movableHitbox.width;
        }
        else if (movableHitbox.x > targetHitbox.x + targetHitbox.width) {
            targetX = targetHitbox.x + targetHitbox.width;
        }
        else {
            targetX = movableHitbox.x;
        }
        int targetY;
        if (movableHitbox.y + movableHitbox.height < targetHitbox.y) {
            targetY = targetHitbox.y - movableHitbox.height;
        }
        else if (movableHitbox.y > targetHitbox.y + targetHitbox.height) {
            targetY = targetHitbox.y + targetHitbox.height;
        }
        else {
            targetY = movableHitbox.y;
        }
        return new ZooPoint(targetX, targetY);
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Hitbox hb) {
            return hb.x == x && hb.y == y && hb.width == width && hb.height == height;
        } else {
            return false;
        }
    }
}
