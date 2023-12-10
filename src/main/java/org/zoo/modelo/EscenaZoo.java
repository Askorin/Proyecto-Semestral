package org.zoo.modelo;
import org.zoo.modelo.animal.EnumAnimal;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.placementmanager.FoodPlacementManager;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.modelo.placementmanager.PlacementManager;

public class EscenaZoo implements Updatable {
    private final Zoo zoo;

    // TODO: Esto no tiene tanto sentido si se puede hacer zoo.getPlacement..
    private final HabitatPlacementManager habitatPlacementManager;
    private final AnimalPlacementManager animalPlacementManager;
    private final TextMessageManager textMessageManager;
    private final FoodPlacementManager foodPlacementManager;
    public EscenaZoo() {
        /*
         * Resulta necesario entregarle habitatPlacementManager al constructor de org.zoo.VistaZoo.
         * Esto por dos razones:
         * 1- De no ser así, org.zoo.modelo.Zoo intercepta los eventos (de click y movimiento) que
         * queremos que le lleguen a habitatPlacementManager. Esto podría ser solucionado
         * utilizando keybindings o algún otro sistema de registro de eventos.
         * 2- Para dibujar encima del panel de vistazoo. Si llamaramos draw con los Graphics
         * de org.zoo.modelo.EscenaZoo, no se dibujaría correctamente encima de org.zoo.modelo.Zoo, por lo que
         * resulta necesario utilizar los Graphics de org.zoo.modelo.Zoo en su paintComponent.
         */
        habitatPlacementManager = new HabitatPlacementManager();
        animalPlacementManager = new AnimalPlacementManager();
        foodPlacementManager = new FoodPlacementManager();

        /* Creamos la logica de org.zoo.modelo.Zoo */
        zoo = new Zoo(habitatPlacementManager, animalPlacementManager, foodPlacementManager);

        habitatPlacementManager.setZoo(zoo);
        animalPlacementManager.setZoo(zoo);
        foodPlacementManager.setZoo(zoo);

        /* Creamos el manejador de textos */
        textMessageManager = new TextMessageManager();
    }

    public Zoo getZoo() {
        return zoo;
    }

    public PlacementManager getPlacementManager(MenuItem itemEnum) {
        if (itemEnum instanceof EnumHabitat) {
            return habitatPlacementManager;
        }
        if (itemEnum instanceof EnumAnimal) {
            return animalPlacementManager;
        }
        if (itemEnum instanceof EnumFood) {
            return foodPlacementManager;
        }
        return null;
    }

    public HabitatPlacementManager getHabitatPlacementManager() {
        return habitatPlacementManager;
    }

    public AnimalPlacementManager getAnimalPlacementManager() {
        return animalPlacementManager;
    }
    public FoodPlacementManager getFoodPlacementManager() {
        return foodPlacementManager;
    }

    public TextMessageManager getTextMessageManager() {
        return textMessageManager;
    }

    public void update() {
        zoo.update();
        textMessageManager.update();
    }
}
