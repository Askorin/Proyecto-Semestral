package org.zoo.vista;

import org.zoo.modelo.Sprite;
import org.zoo.utilities.Utilities;

import java.awt.*;
import java.util.ArrayList;

//Clase "estatica" no instanciable
// TODO: Implementar esto para los labels y el resto del codigo (Habitats, por ej)
public class RenderedSprite {
    private static boolean loaded = false;
    public static final int SCALE_FACTOR = 4; // Util si queremos hacer pixelart
    private static ArrayList<ArrayList<Image>> frames; //Conjunto de las imagenes individuales de todos los Sprites
    private RenderedSprite() {}
    public static void loadSprites() {
        if (!loaded) {
            frames = new ArrayList<>();
            for (int i = 0; i < Sprite.values().length; i++) {
                frames.add(new ArrayList<>());

                Sprite spr = Sprite.values()[i];
                for (int j = 0; j < spr.getFramesNumber(); j++) {
                    for (String p : getPaths(spr.getPath(), spr.getFramesNumber())) {
                        frames.get(i).add(Utilities.loadImage(p, SCALE_FACTOR));
                    }
                }
            }
            loaded = true;
        }
    }

    //Devuelve el frame del sprite que le corresponde en un tiempo
    public static Image getFrame(Sprite spr, long time) {
        ArrayList<Image> spriteFrames = frames.get(spr.ordinal());
        return spriteFrames.get((int)((time / spr.getTimePerFrame()) % spr.getFramesNumber()));
    }

    public static void draw(Sprite sprite,
                            Graphics g,
                            int x, int y,
                            int hitboxWidth, int hitboxHeight,
                            long timeElapsed,
                            float opacidad) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidad));

        //Calculos para centrar el sprite, de forma que se imprima al centro de la hitbox del objeto que lo llama
        int hitboxCenterX = x + (hitboxWidth/2);
        int drawX = hitboxCenterX - sprite.getCenterX();
        int hitboxCenterY = y + (hitboxHeight/2);
        int drawY = hitboxCenterY - sprite.getCenterY();

        g2d.drawImage(getFrame(sprite, timeElapsed), drawX, drawY, null);
    }

    //Permite determinar la direccion de cada frame solo entregando la direccion de uno
    //Por ejemplo: entregar .../animacion.png y devolver .../animacion1.png, .../animacion2.png,... etc.
    //NOTA: Para que funcione la direccion debe contener exactamente un sólo "."
    private static ArrayList<String> getPaths(String path, int framesNumber) {
        ArrayList<String> paths = new ArrayList<>(framesNumber);
        if (framesNumber == 1) {
            paths.add(path);
            return paths;
        }
        String[] splitedpath = path.split("\\.");
        int n = splitedpath.length;
        for (int i = 0; i < framesNumber; i++) {
            paths.add(splitedpath[0]+ (i + 1) + "." + splitedpath[n-1]);
        }
        return paths;
    }
}
