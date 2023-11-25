package org.zoo.vista.sidepanels;

import org.zoo.modelo.placementmanager.PlacementManager;
import org.zoo.vista.VistaEscenaZoo;

import javax.swing.*;

public class ItemPanel extends JPanel {
    public ItemPanel(VistaEscenaZoo.PanelListener panelListener) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(50));
    }

    public void addNavArrow(int width, int height, VistaEscenaZoo.PanelListener panelListener) {
        /* Esto es para la flecha de navegación panel */
        LabelNavArrow labelNavArrow = new LabelNavArrow(width, height);
        labelNavArrow.addMouseListener(panelListener);
        add(Box.createGlue());
        add(labelNavArrow);
        add(Box.createHorizontalStrut(50));
    }
}
