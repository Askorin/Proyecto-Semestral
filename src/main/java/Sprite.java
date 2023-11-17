import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public enum Sprite {
    CAT_IDLE("src/main/resources/CatIdle.png", 6, 150, 13*4, 9*4),
    CAT_WALK("src/main/resources/CatWalk.png", 2, 90),
    MEADOWHABITAT("src/main/resources/meadowHabitat.png", 1, 1);
    public static final int scaleFactor = 4;
    private ArrayList<Image> frames;
    private int framesNumber;
    private int timePerFrame;
    private int centerX;
    private int centerY;
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

        width = frames.get(0).getWidth(null);
        height = frames.get(0).getHeight(null);
        centerX = width/2;
        centerY = height/2;
    }

    Sprite(String path, int framesNumber, int timePerFrame, int centerX, int centerY) {
        this(path, framesNumber, timePerFrame);
        this.centerX = centerX;
        this.centerY = centerY;
    }

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

    public Image getFrame(long time) {
        return frames.get((int)((time / timePerFrame) % framesNumber));
    }

    public void drawSprite(Graphics g, int x, int y, int hitboxWidth, int hitboxHeight, int timeElapsed) {
        int hitboxCenterX = x + (hitboxWidth/2);
        int drawX = hitboxCenterX - getCenterX();
        int hitboxCenterY = y + (hitboxHeight/2);
        int drawY = hitboxCenterY - getCenterY();

        g.drawImage(getFrame(timeElapsed), drawX, drawY, null);
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
