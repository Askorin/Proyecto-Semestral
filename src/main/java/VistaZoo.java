import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

class VistaZoo extends JPanel
        implements ActionListener, MouseMotionListener, MouseListener {
    public int width; public int height;
    public int cameraX; public int cameraY;
    public int cameraWidth; public int cameraHeight;
    public final int cameraTol = 24; public final int cameraSpeed = 5;
    public int mouseX; public int mouseY; public boolean mouseIn;
    public Timer t;
    public Image image;
    public VistaZoo() {
        image = loadImage("src/main/rsc/testimage.jpg"); // Temporal
        width = image.getWidth(null);
        height = image.getHeight(null);

        cameraHeight = getSize().height;
        cameraWidth = getSize().width;

        // TODO: Esto en App o en un loop principal, no se.
        t = new Timer(1000/60, null);
        t.addActionListener(this);
        t.start();

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCamera(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        step();
    }

    public void step() {
        updateCamera();
        repaint();
    }

    public void updateCamera() {
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

    public void drawCamera(Graphics g) {
        g.drawImage(image, -cameraX, -cameraY, null);
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
