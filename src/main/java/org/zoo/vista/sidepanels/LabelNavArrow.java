package org.zoo.vista.sidepanels;

import org.zoo.vista.HoverVisuals;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;


/**
 * Clase que se encarga de mostrar flecha de navegación de paneles.
 */
public class LabelNavArrow extends JLabel implements HoverVisuals {

    /** La orientación de la flecha. */
    private final NavArrowOrientation orientation;
    private final ImageIcon defaultIcon;
    private final ImageIcon hoverIcon;
    private boolean hoverState;
    private Dimension imgDim;

    /**
     * El constructor único de la clase.
     * @param height La altura que ocupa el label.
     * @param orientation La orientacion de la flecha.
     */
    public LabelNavArrow(int height, NavArrowOrientation orientation) {
        super();
        this.orientation = orientation;
        this.hoverState = false;

        BufferedImage defaultBufferedImg = null;
        BufferedImage hoverBufferedImg = null;
        try {
            defaultBufferedImg = ImageIO.read(getClass().getResource(orientation.getPath()));
            imgDim = new Dimension(defaultBufferedImg.getWidth(), defaultBufferedImg.getHeight());
        } catch (IOException e) {
            System.err.println(e);
        }

        /* El ratio entre la altura dada y la altura de la imagen. */
        float ratio = height / (float) imgDim.height;
        Dimension size = new Dimension(
                (int) (imgDim.width  * ratio),
                (int) (imgDim.height * ratio)
        );
        setSize(size);

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

    /**
     * Enumerador interno que espeicifica las orientaciones que puede tener una
     * flecha de navegacion.
     */
    public enum NavArrowOrientation {
        RIGHT("/ArrowRight.png"),
        LEFT("/ArrowLeft.png");
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
