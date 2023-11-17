import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

class VistaZoo extends JPanel
        implements MouseMotionListener, MouseListener, Timeable {
    protected int width; protected int height;
    private int cameraX; private int cameraY;
    private int cameraWidth; private int cameraHeight;
    private final int cameraTol = 24; private final int cameraSpeed = 5;
    private int mouseX; private int mouseY; private boolean mouseIn;
    private Image backgroundImage;
    private ArrayList<Drawable> drawableComponents;
    public VistaZoo() {
        drawableComponents = new ArrayList<>();

        backgroundImage = loadImage("src/main/resources/testimage.jpg"); // Temporal
        width = backgroundImage.getWidth(null);
        height = backgroundImage.getHeight(null);

        cameraHeight = getSize().height;
        cameraWidth = getSize().width;

        addMouseMotionListener(this);
        addMouseListener(this);

        //Temp
        Habitat testHabitat; Animal testAnimal;

        testHabitat = new MeadowHabitat();
        testHabitat.x = 64; testHabitat.y = 128;
        testAnimal = new Gato(testHabitat);
        testHabitat.addDrawable(testAnimal);
        // GlobalTimer.addTimeable(testAnimal);
        addDrawable(testHabitat);
    }

    // TODO: El paintComponent lo debería llevar ventana en verdad?
    @Override
    protected void paintComponent(Graphics g) {
        int x = -cameraX;
        int y = -cameraY;
        super.paintComponent(g);

        drawCamera(g);
        for (Drawable d: drawableComponents) {
            d.draw(g, x, y);
        }
    }

    public void step() {
        updateCamera();
        for (Drawable d: drawableComponents) {
            d.step();
        }
    }

    private void updateCamera() {
        cameraHeight = getSize().height;
        cameraWidth = getSize().width;

        if (mouseIn) {
            if (mouseX < cameraTol && cameraX - cameraSpeed >= 0) {
                cameraX += -cameraSpeed;
            }
            else if (mouseX > (cameraWidth - cameraTol)
                    && cameraX + cameraWidth + cameraSpeed <= width) {
                cameraX += cameraSpeed;
            }
            if (mouseY < cameraTol && cameraY - cameraSpeed >= 0) {
                cameraY += -cameraSpeed;
            }
            else if (mouseY > (cameraHeight - cameraTol)
                    && cameraY + cameraHeight + cameraSpeed <= height) {
                cameraY += cameraSpeed;
            }
        }
    }

    private void drawCamera(Graphics g) {
        g.drawImage(backgroundImage, -cameraX, -cameraY, null);
    }

    public void addDrawable(Drawable d) {
        drawableComponents.add(d);
    }

    public void removeDrawable(Drawable d) {
        drawableComponents.remove(d);
    }
    private Image loadImage(String path) {
        BufferedImage buffImg = null;
        try {
            buffImg = ImageIO.read(new File(path));
        }
        catch (Exception e) {
            System.err.println(e);
        }
        return buffImg;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseIn = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseIn = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
