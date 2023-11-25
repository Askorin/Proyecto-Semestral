package org.zoo.vista.sidepanels;
import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.vista.VistaEscenaZoo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PanelHabitat extends ItemPanel {

    public PanelHabitat(HabitatPlacementManager habitatPlacementManager, VistaEscenaZoo.PanelListener panelListener) {
        super(panelListener);
        crearLabelsHabitat(habitatPlacementManager, panelListener);
        addNavArrow(128, 128, panelListener);
    }

    private void crearLabelsHabitat(HabitatPlacementManager habitatPlacementManager, VistaEscenaZoo.PanelListener panelListener) {
        for (EnumHabitat enumHabitat : EnumHabitat.values()) {
            LabelHabitat labelHabitat = new LabelHabitat(100, 100, enumHabitat, habitatPlacementManager);
            labelHabitat.addMouseListener(panelListener);
            add(labelHabitat);
        }
    }
}
