import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public enum Sprite {
    CAT_IDLE("src/main/resources/CatIdle", 6, 150),
    CAT_WALK("src/main/resources/CatWalk", 2, 50);
    public static final int scaleFactor = 4;
    private ArrayList<Image> frames;
    private int framesNumber;
    private int timePerFrame;
    private int width;
    private int height;
    Sprite(String path, int framesNumber, int timePerFrame) {
        this.framesNumber = framesNumber;
        this.timePerFrame = timePerFrame;

        frames = new ArrayList<>(framesNumber);
        for (String p: getPaths(path, framesNumber)) {
            System.out.println(p);
            BufferedImage buffImage = null;
            try {
                buffImage = ImageIO.read(new File(p));
            }
            catch (IOException e) {
                System.out.println(e);
            }
            width = buffImage.getWidth() * scaleFactor;
            height = buffImage.getHeight() * scaleFactor;
            Image image = buffImage.getScaledInstance(width, height, Image.SCALE_FAST);
            frames.add(image);
        }

    }

    private ArrayList<String> getPaths(String path, int framesNumber) {
        ArrayList<String> paths = new ArrayList<>(framesNumber);
        for (int i = 0; i < framesNumber; i++) {
            paths.add(path + Integer.toString(i+1) + ".png");
        }
        return paths;
    }

    public Image getFrame(long time) {
        return frames.get((int)((time / timePerFrame) % framesNumber));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
