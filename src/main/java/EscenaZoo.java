import javax.swing.*;
import java.awt.*;

public class EscenaZoo extends JPanel implements Updatable {
    private final VistaZoo zoo;
    private final HabitatPlacementManager habitatPlacementManager;
    private final AnimalPlacementManager animalPlacementManager;
    public EscenaZoo() {
        setIgnoreRepaint(true);

        MeadowHabitat test = new MeadowHabitat();

        /*
         * Resulta necesario entregarle habitatPlacementManager al constructor de VistaZoo.
         * Esto por dos razones:
         * 1- De no ser así, VistaZoo intercepta los eventos (de click y movimiento) que
         * queremos que le lleguen a habitatPlacementManager. Esto podría ser solucionado
         * utilizando keybindings o algún otro sistema de registro de eventos.
         * 2- Para dibujar encima del panel de vistazoo. Si llamaramos draw con los Graphics
         * de EscenaZoo, no se dibujaría correctamente encima de VistaZoo, por lo que
         * resulta necesario utilizar los Graphics de VistaZoo en su paintComponent.
         */

        habitatPlacementManager = new HabitatPlacementManager();
        animalPlacementManager = new AnimalPlacementManager();

        zoo = new VistaZoo(habitatPlacementManager, animalPlacementManager);

        habitatPlacementManager.setVistaZoo(zoo);
        animalPlacementManager.setVistaZoo(zoo);


        setLayout(new BorderLayout());


        add(zoo, BorderLayout.CENTER);

        // TODO: Cambiar nombres de PanelAnimal y PanelHabitat, ver si se generalizar a una clase.
        add(new PanelHabitat(habitatPlacementManager), BorderLayout.WEST);
        add(new PanelAnimal(animalPlacementManager), BorderLayout.EAST);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void update() {
        zoo.update();
    }

}
