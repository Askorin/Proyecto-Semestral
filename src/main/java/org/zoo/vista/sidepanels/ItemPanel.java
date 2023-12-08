package org.zoo.vista.sidepanels;

import org.zoo.modelo.MenuItem;
import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.placementmanager.PlacementManager;
import org.zoo.vista.VistaEscenaZoo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ItemPanel<T extends Enum<T> & MenuItem> extends JPanel {
    private Class<T> clazz;
    Image background;
    public ItemPanel(VistaEscenaZoo.PanelListener panelListener, Class<T> clazz) {
        super();
        this.clazz = clazz;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        try {
            background = ImageIO.read(getClass().getResource("/menu.png"));
        } catch (IOException e) {

        }
        addNavArrowL(100, 100, panelListener);
        createLabels(panelListener);
        addNavArrowR(100, 100, panelListener);
    }

    private T[] values() {
        return clazz.getEnumConstants();
    }
    private void createLabels(VistaEscenaZoo.PanelListener panelListener) {
        for (T itemEnum : values())  {
            ItemLabel<T> itemLabel = new ItemLabel<>(100, 100, itemEnum);
            itemLabel.addMouseListener(panelListener);
            add(itemLabel);
        }
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // background = background.getScaledInstance(getWidth(), getHeight(), Image.SCALE_FAST);
        // g.drawImage(background, 0, 0, null);

    }
}
