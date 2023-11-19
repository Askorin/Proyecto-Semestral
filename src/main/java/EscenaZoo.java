import javax.swing.*;
import java.awt.*;

public class EscenaZoo extends JPanel implements Steps {
    private final VistaZoo vistaZoo;
    private final PanelHabitat panelHabitat;
    private final PanelAnimal panelAnimal;
    private final HabitatPlacementManager habitatPlacementManager;
    public EscenaZoo() {
        setIgnoreRepaint(true);
        setLayout(new BorderLayout());

        this.vistaZoo = new VistaZoo();

        this.habitatPlacementManager = new HabitatPlacementManager(vistaZoo);
        addMouseMotionListener(habitatPlacementManager);
        addMouseListener(habitatPlacementManager);

        this.panelHabitat = new PanelHabitat();

        this.panelAnimal = new PanelAnimal();

        add(this.vistaZoo, BorderLayout.CENTER);
        add(this.panelHabitat, BorderLayout.WEST);
        add(this.panelAnimal, BorderLayout.EAST);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        vistaZoo.draw(g, 0, 0);

        // TODO: Implementar un sistema de layers, posiblemente.
        // Esto para que se dibuje al final.
        habitatPlacementManager.draw(g, 0, 0);
    }

    public void step() {
        vistaZoo.step();
    }

}
