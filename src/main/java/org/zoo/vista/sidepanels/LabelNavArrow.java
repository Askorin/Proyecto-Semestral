package org.zoo.vista.sidepanels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LabelNavArrow extends JLabel {
    public LabelNavArrow(int width, int height) {
        super();
        setSize(new Dimension(width, height));
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(getClass().getResource("/NavArrow.png"));
        } catch (IOException e) {
            System.err.println(e);
        }
        Image reImg = imagen.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(reImg));
    }
}
