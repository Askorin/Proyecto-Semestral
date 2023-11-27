package org.zoo.vista.sidepanels;

import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.utilities.Utilities;
import org.zoo.vista.RenderedSprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LabelFood extends JLabel {
    private EnumFood enumFood;
    public LabelFood(int width, int height, EnumFood enumFood) {
        super();
        this.enumFood = enumFood;
        setSize(width, height);
        setPreferredSize(new Dimension(getWidth(), getHeight()));

        Image imagen = RenderedSprite.getFrame(this.enumFood.getInGameSprite(), 0);
        // imagen = ImageIO.read(getClass().getResource(this.enumFood.getInGameSprite().getPath()));
        Image reImg = imagen.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(reImg));
    }

    public EnumFood getEnumFood() {
        return enumFood;
    }
}
