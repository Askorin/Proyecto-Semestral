package org.zoo.modelo.habitat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zoo.modelo.EscenaZoo;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HabitatTest {

    private EscenaZoo escenaZoo;
    private ArrayList<Habitat> habitats;
    private ArrayList<ZooPoint> pointCases;

    void instanceHabitats(ZooPoint zooPoint) {
        habitats = new ArrayList<>();
        for (EnumHabitat enumHabitat : EnumHabitat.values()) {
            Habitat habitat = enumHabitat.newInstance(escenaZoo.getZoo(), zooPoint);
            habitats.add(habitat);
        }
    }

    @BeforeEach
    void beforeEach() {
        escenaZoo = new EscenaZoo();
        pointCases = new ArrayList<>();

        /* Casos de puntos */
        pointCases.add(new ZooPoint(0, 0));
        pointCases.add(new ZooPoint(-10000, 0));
        pointCases.add(new ZooPoint(0, -99));
        pointCases.add(new ZooPoint(-52, -72));
        pointCases.add(new ZooPoint(10, 0));
        pointCases.add(new ZooPoint(0, 3));
        pointCases.add(new ZooPoint(8, 302));
        pointCases.add(new ZooPoint(-5, 591));
        pointCases.add(new ZooPoint(24, -342));
    }

    @AfterEach
    void tearDown() {
        escenaZoo = null;
        habitats = null;
    }

    @Test
    void getAbsX() {
        for (ZooPoint p : pointCases) {
           instanceHabitats(p);
           for (Habitat h : habitats) {
               assertEquals(p.x + escenaZoo.getZoo().getAbsX(), h.getAbsX());
           }
        }
    }

    @Test
    void getAbsY() {
        for (ZooPoint p : pointCases) {
            instanceHabitats(p);
            for (Habitat h : habitats) {
                assertEquals(p.y + escenaZoo.getZoo().getAbsY(), h.getAbsY());
            }
        }
    }

    @Test
    void getAbsHitbox() {
        for (ZooPoint p : pointCases) {
            instanceHabitats(p);
            for (Habitat h : habitats) {
                Hitbox expected = new Hitbox(
                        p.x,
                        p.y,
                        h.getWidth(),
                        h.getHeight()
                );
                assertEquals(expected, h.getAbsHitbox());
            }
        }
    }

    @Test
    void getAbsPlacementHitbox() {
        for (ZooPoint p : pointCases) {
            instanceHabitats(p);
            for (Habitat h : habitats) {
                Hitbox expected = new Hitbox(
                        p.x - Habitat.PLACEMENT_TOLERANCE,
                        p.y - Habitat.PLACEMENT_TOLERANCE,
                        h.getWidth() + Habitat.PLACEMENT_TOLERANCE  * 2,
                        h.getHeight() + Habitat.PLACEMENT_TOLERANCE * 2
                );
                assertEquals(expected, h.getAbsPlacementHitbox());
            }
        }
    }
}