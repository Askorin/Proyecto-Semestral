import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelHabitat extends JPanel {

    private ArrayList<LabelHabitat> labels;
    public PanelHabitat(HabitatPlacementManager habitatPlacementManager) {
        super();
        crearLabelsHabitat(habitatPlacementManager);
    }

    private void crearLabelsHabitat(HabitatPlacementManager habitatPlacementManager) {
        for (EnumHabitat enumHabitat : EnumHabitat.values()) {
            add(new LabelHabitat(100, 100, enumHabitat, habitatPlacementManager));
        }
    }
}
