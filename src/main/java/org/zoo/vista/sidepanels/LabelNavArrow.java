package org.zoo.vista.sidepanels;

import org.zoo.vista.HoverVisuals;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;

public class LabelNavArrow extends JLabel implements HoverVisuals {
    private final NavArrowOrientation orientation;
    private final ImageIcon defaultIcon;
    private final ImageIcon hoverIcon;
    private boolean hoverState;
    public LabelNavArrow(int width, int height, NavArrowOrientation orientation) {
        super();
        this.orientation = orientation;
        this.hoverState = false;

        setSize(new Dimension(width, height));
        BufferedImage defaultBufferedImg = null;
        BufferedImage hoverBufferedImg = null;
        try {
            defaultBufferedImg = ImageIO.read(getClass().getResource(orientation.getPath()));
        } catch (IOException e) {
            System.err.println(e);
        }

        hoverBufferedImg = new BufferedImage(
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

    public enum NavArrowOrientation {
        RIGHT("/NavArrowR.png"),
        LEFT("/NavArrowL.png");
        private final String path;
        NavArrowOrientation(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    public NavArrowOrientation getOrientation() {
        return orientation;
    }
}
