package org.zoo.modelo.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zoo.modelo.EscenaZoo;
import org.zoo.modelo.Zoo;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.habitat.MeadowHabitat;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;

import java.util.ArrayList;

public class FoodAreaTest {
    private EscenaZoo escenaZoo;
    private Zoo zoo;
    private Habitat habitat;

    @BeforeEach
    void preTest() {
        escenaZoo = new EscenaZoo();
        zoo = escenaZoo.getZoo();
        habitat = new MeadowHabitat(zoo, new ZooPoint(500, 500));
    }

    @Test
    void searchFoodContainerTest() {
        /* Habitats vacios */
        Habitat habitat1 = new MeadowHabitat(zoo, new ZooPoint(500, 500));
        habitat1.getContainables().removeComponent(habitat1.getContainables().getDrawables().get(0));
        Habitat habitat2 = new MeadowHabitat(zoo, new ZooPoint(500, 500));
        habitat2.getContainables().removeComponent(habitat2.getContainables().getDrawables().get(0));

        FoodArea fd = new FoodArea(habitat1, 0, 0, 0, 0);
        habitat1.getContainables().addComponent(fd);

        assertEquals(fd, FoodArea.searchFoodContainer(habitat1));
        assertNull(FoodArea.searchFoodContainer(habitat2));
        assertThrows(Exception.class, () -> {FoodArea.searchFoodContainer(null);});
    }

    @Test
    void addAndRemoveFood() {
        int CANTIDAD_DE_TESTEOS = 100;
        for (int i = 0; i < CANTIDAD_DE_TESTEOS; i++) {

            FoodArea fd = new FoodArea(habitat, 0, 0, 0, 0);
            for (EnumFood ef: EnumFood.values() ) {

                int additions = (int) (Math.random() * 100);
                int deletions = (int) (Math.random() * 100);

                for (int j = 0; j < additions; j++) {
                    fd.add(ef);
                }
                for (int j = 0; j < deletions; j++) {
                    fd.remove(ef);
                }

                if (additions > deletions) {
                    assertNotNull(fd.find(new EnumFood[]{ef}));
                }
                else {
                    assertNull(fd.find(new EnumFood[]{ef}));
                }

            }

        }
    }

    @Test
    void getAbsX() {
        int CANTIDAD_DE_TESTEOS = 100;
        for (int i = 0; i < CANTIDAD_DE_TESTEOS; i++) {
            int x = (int) (Math.random() * habitat.getWidth());
            int y = (int) (Math.random() * habitat.getHeight());
            int width = (int) (Math.random() * (habitat.getWidth() - x));
            int height = (int) (Math.random() * (habitat.getHeight() - y));
            FoodArea fd = new FoodArea(habitat, x,y, width, height);

            assertEquals(x + habitat.x + escenaZoo.getZoo().getAbsX(), fd.getAbsX());
            assertEquals(x + habitat.getAbsX(), fd.getAbsX());
        }
    }

    @Test
    void getAbsY() {
        int CANTIDAD_DE_TESTEOS = 100;
        for (int i = 0; i < CANTIDAD_DE_TESTEOS; i++) {
            int x = (int) (Math.random() * habitat.getWidth());
            int y = (int) (Math.random() * habitat.getHeight());
            int width = (int) (Math.random() * (habitat.getWidth() - x));
            int height = (int) (Math.random() * (habitat.getHeight() - y));
            FoodArea fd = new FoodArea(habitat, x,y, width, height);

            assertEquals(y + habitat.y + escenaZoo.getZoo().getAbsY(), fd.getAbsY());
            assertEquals(y + habitat.getAbsY(), fd.getAbsY());
        }
    }

}
