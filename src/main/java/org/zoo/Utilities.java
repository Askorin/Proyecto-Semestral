package org.zoo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utilities {
    public static Point getNormalizedVector(Point vector) {
        return getNormalizedVector(vector, 1);
    }
    public static Point getNormalizedVector(Point vector, int norm) {
        double vectorXnotNorm = (double)(vector.x);
        double vectorYnotNorm = (double)(vector.y);
        double vectorNorm = Math.sqrt(vectorXnotNorm * vectorXnotNorm
                +  vectorYnotNorm * vectorYnotNorm);
        int vectorX = (int)(norm * (vectorXnotNorm) / vectorNorm);
        int vectorY = (int)(norm * (vectorYnotNorm) / vectorNorm);
        return  new Point(vectorX, vectorY);
    }
    public static Image loadImage(String path) {
        return loadImage(path, 1);
    }
    protected static Image loadImage(String path, int scaleFactor) {
        BufferedImage buffImage = null;
        try {
            buffImage = ImageIO.read(new File(path));
        }
        catch (IOException e) {
            System.out.println(e);
        }
        int width = buffImage.getWidth() * scaleFactor;
        int height = buffImage.getHeight() * scaleFactor;
        return buffImage.getScaledInstance(width, height, Image.SCALE_FAST);
    }
}
