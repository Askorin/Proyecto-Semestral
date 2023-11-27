package org.zoo.vista.sidepanels;

import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LabelHabitat extends JLabel {

    private EnumHabitat enumHabitat;
    LabelHabitat(int width, int height, EnumHabitat enumHabitat) {
        super();
        this.enumHabitat = enumHabitat;
        setSize(width, height);
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(getClass().getResource(this.enumHabitat.getPath()));
            // imagen = ImageIO.read(getClass().getResource("/habitat.png"));
        } catch (IOException e) {
            System.err.println(e);
        }
        Image reImg = imagen.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(reImg));
    }

    public EnumHabitat getEnumHabitat() {
        return enumHabitat;
    }
}
