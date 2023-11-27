package org.zoo.vista.sidepanels;

import org.zoo.modelo.placementmanager.PlacementManager;
import org.zoo.vista.VistaEscenaZoo;

import javax.swing.*;
import java.awt.*;

public class ItemPanel extends JPanel {
    public ItemPanel(VistaEscenaZoo.PanelListener panelListener) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void addNavArrowR(int width, int height, VistaEscenaZoo.PanelListener panelListener) {
        LabelNavArrow labelNavArrow = new LabelNavArrow(width, height, LabelNavArrow.NavArrowOrientation.RIGHT);
        labelNavArrow.addMouseListener(panelListener);
        add(Box.createHorizontalGlue());
        add(labelNavArrow);
    }

    public void addNavArrowL(int width, int height, VistaEscenaZoo.PanelListener panelListener) {
        LabelNavArrow labelNavArrow = new LabelNavArrow(width, height, LabelNavArrow.NavArrowOrientation.LEFT);
        labelNavArrow.addMouseListener(panelListener);
        add(labelNavArrow);
        add(Box.createHorizontalGlue());
    }
}
