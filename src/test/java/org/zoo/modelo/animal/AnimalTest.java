package org.zoo.modelo.animal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zoo.modelo.EscenaZoo;
import org.zoo.modelo.Zoo;
import org.zoo.modelo.habitat.*;
import org.zoo.utilities.ZooPoint;

import java.util.ArrayList;

public class AnimalTest {
    private EscenaZoo escenaZoo;
    private Zoo zoo;
    private Habitat meadowHabitat;
    private Habitat forestHabitat;
    private Habitat savannaHabitat;
    private Habitat snowyHabitat;
    private ArrayList<ZooPoint> pointCases;

    void initHabitats() {
        meadowHabitat = new MeadowHabitat(zoo, new ZooPoint(500, 500));
        forestHabitat = new ForestHabitat(zoo, new ZooPoint(500, 500));
        savannaHabitat = new SavannaHabitat(zoo, new ZooPoint(500, 500));
        snowyHabitat = new SnowyHabitat(zoo, new ZooPoint(500, 500));
    }
    @BeforeEach
    void preTest() {
        escenaZoo = new EscenaZoo();
        zoo = escenaZoo.getZoo();
        initHabitats();
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

    @Test
    void animalCompanionsCompatibilityTest() {
        Habitat h = meadowHabitat;

        Animal cat = new Cat(meadowHabitat, new ZooPoint(0, 0));
        Animal elephant = new Elephant(meadowHabitat, new ZooPoint(0, 0));
        Animal lion = new Lion(meadowHabitat, new ZooPoint(0, 0));
        Animal panda = new Panda(meadowHabitat, new ZooPoint(0, 0));
        Animal monkey = new Monkey(meadowHabitat, new ZooPoint(0, 0));
        Animal penguin = new Penguin(snowyHabitat, new ZooPoint(0, 0));

        for (Animal a1 : new Animal[]{cat, elephant, panda, monkey, penguin}) {
            for (Animal a2 : new Animal[]{cat, elephant, panda, monkey, penguin}) {
                assertTrue(Animal.doGetAlong(a1, a2));
                assertTrue(Animal.doGetAlong(a2, a1));
            }
        }

        assertTrue(Animal.doGetAlong(lion, lion));
        assertTrue(Animal.doGetAlong(lion, elephant));
        assertTrue(Animal.doGetAlong(elephant, lion));
        for (Animal a1: new Animal[]{cat, panda, monkey, penguin}) {
            assertFalse(Animal.doGetAlong(lion, a1));
            assertFalse(Animal.doGetAlong(a1, lion));
        }

        assertThrows(Exception.class, () -> {Animal.doGetAlong(null, cat);});
        assertThrows(Exception.class, () -> {Animal.doGetAlong(cat, null);});
        assertThrows(Exception.class, () -> {Animal.doGetAlong(null, null);});
    }

    @Test
    void animalHabitatCompatibility() {
        initHabitats();
        {
            EnumAnimal ea = EnumAnimal.CAT;
            assertTrue(meadowHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(!forestHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(savannaHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(!snowyHabitat.addAnimal(ea, new ZooPoint(0, 0)));
        }
        initHabitats();
        {
            EnumAnimal ea = EnumAnimal.ELEPHANT;
            assertTrue(meadowHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(forestHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(savannaHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(!snowyHabitat.addAnimal(ea, new ZooPoint(0, 0)));
        }
        initHabitats();
        {
            EnumAnimal ea = EnumAnimal.LION;
            assertTrue(meadowHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(!forestHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(savannaHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(!snowyHabitat.addAnimal(ea, new ZooPoint(0, 0)));
        }
        initHabitats();
        {
            EnumAnimal ea = EnumAnimal.MONKEY;
            assertTrue(meadowHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(forestHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(savannaHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(!snowyHabitat.addAnimal(ea, new ZooPoint(0, 0)));
        }
        initHabitats();
        {
            EnumAnimal ea = EnumAnimal.PANDA;
            assertTrue(meadowHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(forestHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(!savannaHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(!snowyHabitat.addAnimal(ea, new ZooPoint(0, 0)));
        }
        initHabitats();
        {
            EnumAnimal ea = EnumAnimal.PENGUIN;
            assertTrue(!meadowHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(!forestHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(!savannaHabitat.addAnimal(ea, new ZooPoint(0, 0)));
            assertTrue(snowyHabitat.addAnimal(ea, new ZooPoint(0, 0)));
        }
    }

    @Test
    void getAbsX() {
        Habitat h = meadowHabitat;

        for (ZooPoint p : pointCases) {
            for (EnumAnimal ea: EnumAnimal.values()) {
                Animal a = ea.newInstance(h, p);
                assertEquals(p.x + h.x + escenaZoo.getZoo().getAbsX(), a.getAbsX());
                assertEquals(p.x + h.getAbsX(), a.getAbsX());
            }
        }
    }

    @Test
    void getAbsY() {
        Habitat h = meadowHabitat;

        for (ZooPoint p : pointCases) {
            for (EnumAnimal ea: EnumAnimal.values()) {
                Animal a = ea.newInstance(h, p);
                assertEquals(p.y + h.y + escenaZoo.getZoo().getAbsY(), a.getAbsY());
                assertEquals(p.y + h.getAbsY(), a.getAbsY());
            }
        }
    }
}
