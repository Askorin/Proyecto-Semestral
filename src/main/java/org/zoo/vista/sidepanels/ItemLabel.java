package org.zoo.vista.sidepanels;

import org.zoo.modelo.MenuItem;
import org.zoo.vista.RenderedSprite;

import javax.swing.*;
import java.awt.*;

public class ItemLabel<T extends MenuItem> extends JLabel {

    private T itemEnum;
    private final ImageIcon defaultIcon;

    ItemLabel(int width, int height, T itemEnum) {
        super();
        this.itemEnum = itemEnum;
        setSize(width, height);
        setPreferredSize(new Dimension(getWidth(), getHeight()));

        Image imagen = RenderedSprite.getFrame(itemEnum.getInGameSprite(), 0);
        // imagen = ImageIO.read(getClass().getResource(this.enumFood.getInGameSprite().getPath()));
        Image reImg = imagen.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        defaultIcon = new ImageIcon(reImg);

        setIcon(defaultIcon);

    }

    public T getEnum() {
        return itemEnum;
    }
}
