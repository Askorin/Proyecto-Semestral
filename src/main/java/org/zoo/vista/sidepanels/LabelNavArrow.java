package org.zoo.vista.sidepanels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LabelNavArrow extends JLabel {
    private final NavArrowOrientation orientation;
    public LabelNavArrow(int width, int height, NavArrowOrientation orientation) {
        super();
        this.orientation = orientation;
        setSize(new Dimension(width, height));
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(getClass().getResource(orientation.getPath()));
        } catch (IOException e) {
            System.err.println(e);
        }
        Image reImg = imagen.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(reImg));
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
