package org.zoo.vista.sidepanels;

import org.zoo.modelo.MenuItem;
import org.zoo.vista.HoverVisuals;
import org.zoo.vista.RenderedSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class ItemLabel<T extends MenuItem> extends JLabel implements HoverVisuals {

    private T itemEnum;
    private final ImageIcon defaultIcon;
    private final ImageIcon hoverIcon;
    private boolean hoverState;

    ItemLabel(int width, int height, T itemEnum) {
        super();
        this.itemEnum = itemEnum;
        this.hoverState = false;

        setSize(width, height);
        setPreferredSize(new Dimension(getWidth(), getHeight()));

        BufferedImage defaultBufferedImg = RenderedSprite.getFrame(itemEnum.getInGameSprite(), 0);
        BufferedImage hoverBufferedImg = new BufferedImage(
                defaultBufferedImg.getWidth(),
                defaultBufferedImg.getHeight(),
                defaultBufferedImg.getType()
        );

        /* Oscurecemos la imagen de hover state. */
        RescaleOp rescaleOp = new RescaleOp(0.7f, 0, null);
        rescaleOp.filter(defaultBufferedImg, hoverBufferedImg);

        /* Conseguimos la imagenes escaladas. */
        Image defaultImg = defaultBufferedImg.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        Image hoverImg = hoverBufferedImg.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

        /* Instanciamos los iconos para el label. */
        defaultIcon = new ImageIcon(defaultImg);
        hoverIcon = new ImageIcon(hoverImg);


        setIcon(defaultIcon);

    }

    @Override
    public void setHoverState(boolean hoverState) {
        if (hoverState & !this.hoverState) {
            setIcon(hoverIcon);
        } else if (!hoverState & this.hoverState) {
            setIcon(defaultIcon);
        }
        this.hoverState = hoverState;
    }

    public T getEnum() {
        return itemEnum;
    }
}
