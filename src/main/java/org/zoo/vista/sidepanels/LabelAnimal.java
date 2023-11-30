package org.zoo.vista.sidepanels;

import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.animal.EnumAnimal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LabelAnimal extends JLabel {

    private final EnumAnimal enumAnimal;
    LabelAnimal(int width, int height, EnumAnimal enumAnimal) {
        super();
        this.enumAnimal = enumAnimal;
        setSize(width, height);
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(getClass().getResource(this.enumAnimal.getLabelPath()));
            // imagen = ImageIO.read(getClass().getResource("/habitat.png"));
        } catch (IOException e) {
            System.err.println(e);
        }
        Image reImg = imagen.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(reImg));
    }

    public EnumAnimal getEnumAnimal() {
        return enumAnimal;
    }
}
