import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class VistaZoo extends JPanel
        implements Updatable, Drawable {
    protected int width; protected int height;
    private int cameraX; private int cameraY;
    private int cameraWidth; private int cameraHeight;
    private final int cameraTol = 24; private final int cameraSpeed = 5;
    private int mouseX; private int mouseY; private boolean mouseIn;
    private Image backgroundImage;
    private Containables containables;
    private HabitatPlacementManager habitatPlacementManager;
    private AnimalPlacementManager animalPlacementManager;
      
    public VistaZoo(HabitatPlacementManager habitatPlacementManager, AnimalPlacementManager animalPlacementManager) {
        containables = new Containables();

        backgroundImage = Utilities.loadImage("src/main/resources/testimage.jpg"); // Temporal
        width = backgroundImage.getWidth(null);
        height = backgroundImage.getHeight(null);

        cameraHeight = getSize().height;
        cameraWidth = getSize().width;

        this.habitatPlacementManager = habitatPlacementManager;
        this.animalPlacementManager = animalPlacementManager;

        //TODO: Temp
        addHabitat(64, 128, EnumHabitat.MEADOW);
    }

    // TODO: Pasar enumHabitat o Habitat? Es este método una buena idea siquiera?
    public void addHabitat(int x, int y, EnumHabitat enumHabitat) {
    
        Habitat habitat = enumHabitat.newInstance();
        // TODO: Esto es pal meme.
        {
            habitat.getContainables().addComponent(new Gato(habitat, 0, 100));
        }
        habitat.x = x + cameraX; habitat.y = y + cameraY;
        
        getContainables().addComponent(habitat);
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
        for (Drawable d: getContainables().getDrawables()) {
            // Este check de null es medio quiche.
            if (d != null) {d.draw(g, x, y);}
        }
        // TODO: Sistema de layers para no tener que hacerlo manual, que es lo contrario a lo que queremos.
        habitatPlacementManager.draw(g, 0, 0);
        animalPlacementManager.draw(g, 0, 0);
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

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public void setMouseIn(boolean mouseIn) {
        this.mouseIn = mouseIn;
    }
}
