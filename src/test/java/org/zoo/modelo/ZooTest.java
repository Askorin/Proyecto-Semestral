package org.zoo.modelo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.utilities.ZooPoint;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {
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
    void setUp() {
        escenaZoo = new EscenaZoo();
    }

    @AfterEach
    void tearDown() {

    }

    ArrayList<ZooPoint> generateEdgePoints() {
        ArrayList<ZooPoint> zooPoints = new ArrayList<>();
        zooPoints.add(new ZooPoint(Habitat.PLACEMENT_TOLERANCE - 1, Habitat.PLACEMENT_TOLERANCE));
        zooPoints.add(new ZooPoint(Habitat.PLACEMENT_TOLERANCE , Habitat.PLACEMENT_TOLERANCE - 1));
        zooPoints.add(new ZooPoint(Habitat.PLACEMENT_TOLERANCE - 1, Habitat.PLACEMENT_TOLERANCE - 1));
        zooPoints.add(new ZooPoint(escenaZoo.getZoo().getWidth() - Habitat.PLACEMENT_TOLERANCE + 1, Habitat.PLACEMENT_TOLERANCE));
        zooPoints.add(new ZooPoint(Habitat.PLACEMENT_TOLERANCE, escenaZoo.getZoo().getHeight() - Habitat.PLACEMENT_TOLERANCE + 1));
        zooPoints.add(new ZooPoint(escenaZoo.getZoo().getWidth() - Habitat.PLACEMENT_TOLERANCE + 1, escenaZoo.getZoo().getHeight() - Habitat.PLACEMENT_TOLERANCE + 1));
        return zooPoints;
    }

    @Test
    void addHabitat() {

        /* Chequeamos colisiones entre habitats */
        ZooPoint p = new ZooPoint(Habitat.PLACEMENT_TOLERANCE,  Habitat.PLACEMENT_TOLERANCE);
        for (EnumHabitat enumHabitat1 : EnumHabitat.values()) {
            boolean addHab1 = escenaZoo.getZoo().addHabitat(p.x, p.y, enumHabitat1);
            assertTrue(addHab1);
            for (EnumHabitat enumHabitat2 : EnumHabitat.values()) {
                boolean addHab2 = escenaZoo.getZoo().addHabitat(p.x, p.y, enumHabitat2);
                assertFalse(addHab2);
            }
            escenaZoo = new EscenaZoo();
        }

        /* Chequeamos posicionamiento de habitats fuera de zoo */
        ArrayList<ZooPoint> zooPoints = generateEdgePoints();
        for (ZooPoint pt: zooPoints) {
            for (EnumHabitat enumHabitat : EnumHabitat.values()) {
                boolean addHab = escenaZoo.getZoo().addHabitat(pt.x, pt.y, enumHabitat);

                assertFalse(addHab);
            }
        }
    }



    @Test
    void getHabitatFromPoint() {
        ZooPoint p = new ZooPoint(Habitat.PLACEMENT_TOLERANCE, Habitat.PLACEMENT_TOLERANCE);
        for (EnumHabitat enumHabitat : EnumHabitat.values()) {
            escenaZoo.getZoo().addHabitat(p.x, p.y, enumHabitat);
            Habitat h = escenaZoo.getZoo().getHabitatFromPoint(p);
            assertEquals(h.getClass(), enumHabitat.getTipo());

            ZooPoint bp1 = new ZooPoint(h.getAbsX(), h.getAbsY());
            ZooPoint bp2 = new ZooPoint(h.getAbsX() + h.getWidth() - 1, h.getAbsY() + h.getHeight() - 1);

            Habitat h2 = escenaZoo.getZoo().getHabitatFromPoint(bp1);
            Habitat h3 = escenaZoo.getZoo().getHabitatFromPoint(bp2);
            assertEquals(h, h2);
            assertEquals(h, h3);

            escenaZoo = new EscenaZoo();
        }
    }
}