import javax.swing.*;
import java.awt.*;

public class EscenaZoo extends JPanel implements Steps {
    private final VistaZoo zoo;
    private final HabitatPlacementManager habitatPlacementManager;
    public EscenaZoo() {
        setIgnoreRepaint(true);

        habitatPlacementManager = new HabitatPlacementManager();
        zoo = new VistaZoo(habitatPlacementManager);
        habitatPlacementManager.setVistaZoo(zoo);


        setLayout(new BorderLayout());


        add(zoo, BorderLayout.CENTER);

        // TODO: Cambiar nombres de PanelAnimal y PanelHabitat, ver si se generalizar a una clase.
        add(new PanelHabitat(habitatPlacementManager), BorderLayout.WEST);
        add(new PanelAnimal(), BorderLayout.EAST);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // TODO: Implementar un sistema de layers, posiblemente.
        // Esto para que se dibuje al final.
        // zoo.draw(g, 0, 0);
        habitatPlacementManager.draw(g, 0, 0);
    }

    public void step() {
        zoo.step();
    }

}
