package org.zoo.vista;
import org.zoo.modelo.Sprite;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.Utilities;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Clase no instanciable que proporciona funcionalidades de renderizado de Sprites.
 * @see Sprite
 */
public class RenderedSprite {
    private static boolean loaded = false;
    public static final int SCALE_FACTOR = 4; // Util si queremos hacer pixelart
    private static ArrayList<ArrayList<BufferedImage>> frames; //Conjunto de las imagenes individuales de todos los Sprites
    private RenderedSprite() {}

    /**
     * Metodo que carga los frames de los sprites como BufferedImage.
     */
    public static void loadSprites() {
        if (!loaded) {
            frames = new ArrayList<>();
            for (int i = 0; i < Sprite.values().length; i++) {
                frames.add(new ArrayList<>());

                Sprite spr = Sprite.values()[i];
                for (int j = 0; j < spr.getFramesNumber(); j++) {
                    for (String p : getPaths(spr.getPath(), spr.getFramesNumber())) {
                        Image img = Utilities.loadImage(p, SCALE_FACTOR);
                        frames.get(i).add(toBufferedImage(img));
                    }
                }
            }
            loaded = true;
        }
    }

    /**
     * Método que convierte una instancia de <code>Image</code> a <code>BufferedImage</code>
     * @param image La imagen a ser convertida.
     * @return Una <code>BufferedImage</code> equivalente.
     */
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bufferedImage;
    }

    //Devuelve el frame del sprite que le corresponde en un tiempo

    /**
     * Devuelve la imagen del frame que le corresponde a un <code>Sprite</code> en un instante dado.
     * @param spr El <code>Sprite</code> del cual queremos el frame.
     * @param time El tiempo a revisar.
     * @return Una <code>BufferedImage</code> correspondiente al frame solicitado.
     */
    public static BufferedImage getFrame(Sprite spr, long time) {
        ArrayList<BufferedImage> spriteFrames = frames.get(spr.ordinal());
        return spriteFrames.get((int)((time / spr.getTimePerFrame()) % spr.getFramesNumber()));
    }

    /**
     * Metodo que contiene los detalles de como dibujar un <code>Sprite</code>
     * @param sprite El <code>Sprite</code> a dibujar.
     * @param g Las graficas a utilizar.
     * @param hitbox El <code>Hitbox</code> correspondiente.
     * @param timeElapsed El tiempo transcurrido.
     * @param opacidad La opacidad que se desea.
     * @param isFlipped True si es que la imagen debe estar dada vuelta con respecto al eje y.
     */
    public static void draw(Sprite sprite,
                            Graphics g,
                            Hitbox hitbox,
                            long timeElapsed,
                            float opacidad,
                            boolean isFlipped) {
        int x = hitbox.x; int y = hitbox.y;
        int hitboxWidth = hitbox.width; int hitboxHeight = hitbox.height;

        Graphics2D g2d = (Graphics2D) g;

        /* Cambiamos la transparencia */
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidad));

        //Calculos para centrar el sprite, de forma que se imprima al centro de la hitbox del objeto que lo llama
        int hitboxCenterX = x + (hitboxWidth/2);
        int drawX = hitboxCenterX - sprite.getCenterX();
        int hitboxCenterY = y + (hitboxHeight/2);
        int drawY = hitboxCenterY - sprite.getCenterY();

        /* Dibujamos la imagen */
        BufferedImage image = getFrame(sprite, timeElapsed);
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        if (!isFlipped) {
            /* En caso de no tener que dar vuelta la imagen */
            g2d.drawImage(image, drawX, drawY, imageWidth, imageHeight,null);
        }
        else {
            /* En caso de tener que dar vuelta la imagen */
            int newSpriteCenterX = imageWidth - sprite.getCenterX();
            drawX = hitboxCenterX - newSpriteCenterX;

            g2d.drawImage(image, drawX + imageWidth, drawY, -imageWidth, imageHeight,null);
        }

        /* Volvemos a un estado limpio de la transparencia */
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
    public static void draw(Sprite sprite,
                            Graphics g,
                            Hitbox hitbox,
                            long timeElapsed) {
        draw(sprite, g, hitbox, timeElapsed, 1.0f, false);
    }
    public static void draw(Sprite sprite,
                            Graphics g,
                            int x, int y) {
        draw(sprite, g, new Hitbox(x, y, 0, 0), 0, 1.0f, false);
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
