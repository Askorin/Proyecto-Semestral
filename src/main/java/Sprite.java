import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//Sprite almacena una animacion, es decir, una coleccion de imagenes
public enum Sprite {
    CAT_IDLE("src/main/resources/CatIdle.png", 6, 150, 13*4, 9*4),
    CAT_WALK("src/main/resources/CatWalk.png", 2, 90),
    CAT_EAT("src/main/resources/CatEat.png", 4, 90, 16*4, 2*4),
    MEADOWHABITAT("src/main/resources/meadowHabitat.png", 1, 1);
    public static final int scaleFactor = 4; //Util si queremos hacer pixelart
    private ArrayList<Image> frames; //Conjunto de las imagenes individuales de la animacion
    private int framesNumber; //Numero de frames
    private int timePerFrame; //Velocidad (mas bien el reciproco) de la animacion
    private int centerX; //Centro de la animacion, es util para casos que el centro del sprite no es en la mitad
    private int centerY;

    /*Con las dimensiones ocurre algo extraño, no sé si tiene sentido que existan:
    Las imagenes de un mismo sprite podrian tener dimensiones distintas,
    pero en practica, todas tienen las mismas dimensiones, y de hecho, cosas como el centro tienen solo sentido
    si la dimension a traves de las imagenes es constante.
    */
    private int width;
    private int height;
    Sprite(String path, int framesNumber, int timePerFrame) {
        this.framesNumber = framesNumber;
        this.timePerFrame = timePerFrame;

        frames = new ArrayList<>(framesNumber);
        for (String p: getPaths(path, framesNumber)) {
            BufferedImage buffImage = null;
            try {
                buffImage = ImageIO.read(new File(p));
            }
            catch (IOException e) {
                System.out.println(e);
            }
            int width = buffImage.getWidth() * scaleFactor;
            int height = buffImage.getHeight() * scaleFactor;
            Image image = buffImage.getScaledInstance(width, height, Image.SCALE_FAST);
            frames.add(image);
        }

        //Necesitamos un width y height para fijar un centro por defecto
        width = frames.get(0).getWidth(null);
        height = frames.get(0).getHeight(null);
        centerX = width/2;
        centerY = height/2;
    }

    //Por defecto, el centro de un sprite es al medio, pero podemos entregarle un centro personalizado
    Sprite(String path, int framesNumber, int timePerFrame, int centerX, int centerY) {
        this(path, framesNumber, timePerFrame);
        this.centerX = centerX;
        this.centerY = centerY;
    }

    //Permite determinar la direccion de cada frame solo entregando la direccion de uno
    //Por ejemplo: entregar .../animacion.png y devolver .../animacion1.png, .../animacion2.png,... etc.
    //NOTA: Para que funcione la direccion debe contener exactamente un sólo "."
    private ArrayList<String> getPaths(String path, int framesNumber) {
        ArrayList<String> paths = new ArrayList<>(framesNumber);
        if (framesNumber == 1) {
            paths.add(path);
            return paths;
        }
        String[] splitedpath = path.split("\\.");
        int n = splitedpath.length;
        for (int i = 0; i < framesNumber; i++) {
            paths.add(splitedpath[0]+ Integer.toString(i+1) + "." + splitedpath[n-1]);
        }
        return paths;
    }

    //Devuelve el frame del sprite que le corresponde en un tiempo
    public Image getFrame(long time) {
        return frames.get((int)((time / timePerFrame) % framesNumber));
    }

    public void drawSprite(Graphics g, int x, int y, int hitboxWidth, int hitboxHeight, long timeElapsed, float opacidad) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidad));

        //Calculos para centrar el sprite, de forma que se imprima al centro de la hitbox del objeto que lo llama
        int hitboxCenterX = x + (hitboxWidth/2);
        int drawX = hitboxCenterX - getCenterX();
        int hitboxCenterY = y + (hitboxHeight/2);
        int drawY = hitboxCenterY - getCenterY();

        g2d.drawImage(getFrame(timeElapsed), drawX, drawY, null);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getCenterX() {
        return centerX;
    }
    public int getCenterY() {
        return centerY;
    }
}
