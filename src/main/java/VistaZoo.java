import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class VistaZoo extends JPanel
        implements MouseMotionListener, MouseListener, Updatable, Drawable {
    protected int width; protected int height;
    private int cameraX; private int cameraY;
    private int cameraWidth; private int cameraHeight;
    private final int cameraTol = 24; private final int cameraSpeed = 5;
    private int mouseX; private int mouseY; private boolean mouseIn;
    private Image backgroundImage;
    private ArrayList<Drawable> drawableComponents;
    private ArrayList<Updatable> updatableComponents;
    private HabitatPlacementManager habitatPlacementManager;
    public VistaZoo(HabitatPlacementManager habitatPlacementManager) {
        drawableComponents = new ArrayList<>();
        updatableComponents = new ArrayList<>();

        backgroundImage = loadImage("src/main/resources/testimage.jpg"); // Temporal
        width = backgroundImage.getWidth(null);
        height = backgroundImage.getHeight(null);

        cameraHeight = getSize().height;
        cameraWidth = getSize().width;

        addMouseMotionListener(this);
        addMouseListener(this);

        this.habitatPlacementManager = habitatPlacementManager;
        addMouseMotionListener(this.habitatPlacementManager);
        addMouseListener(this.habitatPlacementManager);

        //TODO: Temp
        addHabitat(64, 128, EnumHabitat.MEADOW);
    }

    // TODO: Pasar enumHabitat o Habitat? Es este método una buena idea siquiera?
    public void addHabitat(int x, int y, EnumHabitat enumHabitat) {
        Habitat habitat = enumHabitat.newInstance();

        // TODO: Esto es pal meme.
        Animal testAnimal = new Gato(habitat);
        habitat.addDrawable(testAnimal);
        habitat.addUpdatable(testAnimal);
        habitat.x = x + cameraX; habitat.y = y + cameraY;
        addDrawable(habitat);
        addUpdatable(habitat);
    }
    // TODO: El paintComponent lo debería llevar ventana en verdad?
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g, 0, 0);
    }
    public void draw(Graphics g, int absX, int absY) {
        int x = -cameraX;
        int y = -cameraY;
        drawCamera(g);
        for (Drawable d: drawableComponents) {
            // Este check de null es medio quiche.
            if (d != null) {d.draw(g, x, y);}
        }
        // TODO: Sistema de layers para no tener que hacerlo manual, que es lo contrario a lo que queremos.
        habitatPlacementManager.draw(g, 0, 0);
    }

    public void update() {
        updateCamera();
        for (Updatable u: updatableComponents) {
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

    public void addDrawable(Drawable d) {
        drawableComponents.add(d);
    }

    public void removeDrawable(Drawable d) {
        drawableComponents.remove(d);
    }
    public void addUpdatable(Updatable u) {
        updatableComponents.add(u);
    }

    public void removeUpdatable(Updatable u) {
        updatableComponents.remove(u);
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
        // System.out.println("(" + mouseX + ", " + mouseY + ")");
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
    public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
