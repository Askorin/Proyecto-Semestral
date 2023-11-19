import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class VistaZoo extends JPanel
        implements MouseMotionListener, MouseListener, Updatable, Drawable {
    protected int width; protected int height;
    private int cameraX; private int cameraY;
    private int cameraWidth; private int cameraHeight;
    private final int cameraTol = 24; private final int cameraSpeed = 5;
    private int mouseX; private int mouseY; private boolean mouseIn;
    private Image backgroundImage;
    private Containables containables;
    private HabitatPlacementManager habitatPlacementManager;
    public VistaZoo() {
        containables = new Containables();

        backgroundImage = loadImage("src/main/resources/testimage.jpg"); // Temporal
        width = backgroundImage.getWidth(null);
        height = backgroundImage.getHeight(null);

        cameraHeight = getSize().height;
        cameraWidth = getSize().width;

        addMouseMotionListener(this);
        addMouseListener(this);

        //TODO: Temp
        addHabitat(64, 128, EnumHabitat.MEADOW);

        // Esto para el posicionamiento de habitats.
        this.habitatPlacementManager = new HabitatPlacementManager(this);
        addMouseMotionListener(habitatPlacementManager);
        addMouseListener(habitatPlacementManager);
    }

    // TODO: Pasar enumHabitat o Habitat? Es este método una buena idea siquiera?
    public void addHabitat(int x, int y, EnumHabitat enumHabitat) {
        Habitat habitat = enumHabitat.newInstance();

        // TODO: Esto es pal meme.
        {
            habitat.getContainables().addComponent(new FoodContainer());
            habitat.getContainables().addComponent(new Gato(habitat, 0, 100));
        }

        habitat.x = x; habitat.y = y;
        getContainables().addComponent(habitat);
    }
    // TODO: El paintComponent lo debería llevar ventana en verdad?
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g, 0, 0);
    }

    @Override
    public void draw(Graphics g, int absX, int absY) {
        int x = -cameraX;
        int y = -cameraY;
        drawCamera(g);
        for (Drawable d: getContainables().getDrawables()) {
            // Este check de null es medio quiche.
            if (d != null) {d.draw(g, x, y);}
        }
        // TODO: Implementar un sistema de layers, posiblemente.
        // Esto para que se dibuje al final.
        habitatPlacementManager.draw(g, x, y);
    }

    public void update() {
        updateCamera();
        for (Updatable u: getContainables().getUpdatables()) {
            if (u != null) {u.update();}
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

    public Containables getContainables() {
        return containables;
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
