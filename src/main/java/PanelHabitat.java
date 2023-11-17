import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelHabitat extends JPanel {

    private ArrayList<LabelHabitat> labels;
    PanelHabitat() {
        super();
        crearLabelsHabitat();
    }

    private void crearLabelsHabitat() {
        for (EnumHabitat enumHabitat : EnumHabitat.values()) {
            add(new LabelHabitat(100, 100, enumHabitat));
        }
    }
}
