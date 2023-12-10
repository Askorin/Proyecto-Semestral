package org.zoo.utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utilities {

    // TODO: en verdad esto debería estar en clase ZooPoint...
    public static ZooPoint getNormalizedVector(ZooPoint vector) {
        return getNormalizedVector(vector, 1);
    }

    /**
     * Metodo estatico que normaliza un vector.
     * @param vector El vector a normalizar.
     * @param norm La norma del vector deseado.
     * @return El vector, <code>ZooPoint</code> con norma <code>norm</code>.
     */
    public static ZooPoint getNormalizedVector(ZooPoint vector, int norm) {
        double vectorXnotNorm = vector.x;
        double vectorYnotNorm = vector.y;
        double vectorNorm = Math.sqrt(vectorXnotNorm * vectorXnotNorm
                +  vectorYnotNorm * vectorYnotNorm);
        int vectorX = (int)(norm * (vectorXnotNorm) / vectorNorm);
        int vectorY = (int)(norm * (vectorYnotNorm) / vectorNorm);
        return  new ZooPoint(vectorX, vectorY);
    }
    public static Image loadImage(String path) {
        return loadImage(path, 1);
    }
    /*
     * TODO: Con mas tiempo se podria haber hecho para retoranr BufferedImage,
     * tenemos codigo que hace la conversion por temas de necesidad.
     */
    public static Image loadImage(String path, int scaleFactor) {
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
