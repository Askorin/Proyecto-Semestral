package org.zoo.modelo;
import org.zoo.modelo.animal.EnumAnimal;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.placementmanager.FoodPlacementManager;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.modelo.placementmanager.PlacementManager;

/**
 * Clase que contiene la parte logica de la escena principal (y unica actualmente)
 * de la aplicación. Contiene el modelo del zoologico y los eventos que ocurren en el
 * como lo son manejadores de Habitats, Animales, etc.
 */
public class EscenaZoo implements Updatable {
    /**
     * <code>Zoo</code>> contenido en la escena
     */
    private final Zoo zoo;

    // TODO: Esto no tiene tanto sentido si se puede hacer zoo.getPlacement..
    /**
     * Manejador de <code>Habitat</code> de la escena
     * Es el intermediario que permite colocar habitats en la escena
     */
    private final HabitatPlacementManager habitatPlacementManager;
    /**
     * Manejador de <code>Animal</code> de la escena
     * Es el intermediario que permite colocar animales en la escena
     */
    private final AnimalPlacementManager animalPlacementManager;
    /**
     * Manejador de <code>TextMessage</code> de la escana
     */
    private final TextMessageManager textMessageManager;
    /**
     * Manejador de tipos de comida de la escena
     * Es el intermediario que permite colocar comida en la escena
     */
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

    /**
     * Permite obtener el <code>Zoo</code> contenido en la escena
     * @return <code>Zoo</code> contenido en la escena
     */
    public Zoo getZoo() {
        return zoo;
    }

    /**
     * Permite obtener los manejadores contenidos en la escena
     * @param itemEnum Qué tipo de item maneja el manejador deseado
     * @return Devuelve el manejador deseado
     */
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

    /**
     * Permite obtener el majeador de <code>Habitat</code> de la escena
     * @return Devuelve el <code>HabitatPlacementManager</code> que usa la escena
     */
    public HabitatPlacementManager getHabitatPlacementManager() {
        return habitatPlacementManager;
    }

    /**
     * Permite obtener el majeador de <code>Animal</code> de la escena
     * @return Devuelve el <code>AnimalPlacementManager</code> que usa la escena
     */
    public AnimalPlacementManager getAnimalPlacementManager() {
        return animalPlacementManager;
    }
    /**
     * Permite obtener el majeador de tipos de comida de la escena
     * @return Devuelve el <code>FoodPlacementManager</code> que usa la escena
     */
    public FoodPlacementManager getFoodPlacementManager() {
        return foodPlacementManager;
    }
    /**
     * Permite obtener el majeador de <code>TextMessage</code> de la escena
     * @return Devuelve el <code>TextMessageManager</code> que usa la escena
     */
    public TextMessageManager getTextMessageManager() {
        return textMessageManager;
    }

    @Override
    public void update() {
        zoo.update();
        textMessageManager.update();
    }
}
