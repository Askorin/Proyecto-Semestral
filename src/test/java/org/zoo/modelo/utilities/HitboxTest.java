package org.zoo.modelo.utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zoo.utilities.Hitbox;

public class HitboxTest {
    Hitbox hitbox1;
    Hitbox hitbox2;

    void sameHitboxes() {
        int x = (int)(Math.random()*1000);
        int y = (int)(Math.random()*1000);
        int width = (int)(Math.random()*1000);
        int height = (int)(Math.random()*1000);
        hitbox1 = new Hitbox(x, y, width, height);
        hitbox2 = new Hitbox(x, y, width, height);
    }

    @Test
    void HitboxCollisionTest() {
        /* Casos positivos */
        hitbox1 = new Hitbox(500, 500, 100, 100);
        for (Hitbox h : new Hitbox[]{new Hitbox(0, 0, 1000, 1000),
                                     new Hitbox(525, 525, 50, 50),
                                     new Hitbox(590, 490, 50, 50),
                                     new Hitbox(490, 590, 50, 50 ),
                                     new Hitbox(401, 401, 100, 100)} ) {
            assertTrue(Hitbox.checkHitboxCollision(hitbox1, h));
            assertTrue(Hitbox.checkHitboxCollision(h, hitbox1));
        }

        /* Casos negativos */
        hitbox1 = new Hitbox(500, 500, 100, 100);
        for (Hitbox h : new Hitbox[]{new Hitbox(1000, 1000, 1000, 1000),
                new Hitbox(625, 625, 1000, 1000),
                new Hitbox(1590, 490, 50, 50),
                new Hitbox(490, 1590, 50, 50 ),
                new Hitbox(399, 399, 100, 100)} ) {
            assertFalse(Hitbox.checkHitboxCollision(hitbox1, h));
            assertFalse(Hitbox.checkHitboxCollision(h, hitbox1));
        }

        /* Casos limite */
        hitbox1 = new Hitbox(0, 0, 100, 100);
        hitbox2 = new Hitbox(100, 100, 100, 100);
        assertFalse(Hitbox.checkHitboxCollision(hitbox1, hitbox2)); //Ese es el comportamiento deseado

        hitbox1 = new Hitbox(50, 50, 0, 0);
        hitbox2 = new Hitbox(0, 0, 100, 100);
        assertTrue(Hitbox.checkHitboxCollision(hitbox1, hitbox2)); //Un punto tambien puede colisionar
    }

    @Test
    void HitboxContainedTest() {
        /* Casos positivos */
        hitbox1 = new Hitbox(500, 500, 100, 100);
        for (Hitbox h : new Hitbox[]{new Hitbox(525, 525, 50, 50),
                new Hitbox(500, 500, 50, 50),
                new Hitbox(550, 550, 50, 50),
                new Hitbox(600, 600, 0, 0 ),
                new Hitbox(500, 500, 100, 100)} ) {
            assertTrue(Hitbox.isHitboxContained(hitbox1, h));
        }

        /* Casos negativos */
        hitbox1 = new Hitbox(500, 500, 100, 100);
        for (Hitbox h : new Hitbox[]{new Hitbox(525, 525, 100, 100),
                new Hitbox(500, 499, 50, 50),
                new Hitbox(550, 550, 51, 50),
                new Hitbox(600, 600, 1, 0 ),
                new Hitbox(0, 0, 0, 0)} ) {
            assertFalse(Hitbox.isHitboxContained(hitbox1, h));
        }
    }

    @Test
    void checkHitboxEqualsTest() {
        sameHitboxes();
        assertEquals(hitbox1, hitbox2);
        assertEquals(hitbox2, hitbox1);
        sameHitboxes();
        hitbox1.x += 1;
        assertNotEquals(hitbox1, hitbox2);
        assertNotEquals(hitbox2, hitbox1);
        hitbox1.y += 1;
        assertNotEquals(hitbox1, hitbox2);
        assertNotEquals(hitbox2, hitbox1);
        hitbox1.width += 1;
        assertNotEquals(hitbox1, hitbox2);
        assertNotEquals(hitbox2, hitbox1);
        hitbox1.height += 1;
        assertNotEquals(hitbox1, hitbox2);
        assertNotEquals(hitbox2, hitbox1);
    }
}
