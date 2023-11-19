import javax.swing.*;

public class PanelAnimal extends JPanel {
    PanelAnimal(AnimalPlacementManager animalPlacementManager) {
        super();
        crearLabelsAnimal(animalPlacementManager);
    }
    private void crearLabelsAnimal(AnimalPlacementManager animalPlacementManager) {
        for (EnumAnimal enumAnimal : EnumAnimal.values()) {
            add(new LabelAnimal(100, 100, enumAnimal, animalPlacementManager));
        }
    }
}
