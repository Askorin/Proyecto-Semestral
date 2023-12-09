package org.zoo.vista.sidepanels;
import org.zoo.App;
import org.zoo.modelo.MenuItem;
import org.zoo.vista.VistaEscenaZoo;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Clase <code>JPanel</code> generica que representa un panel de items.
 * @param <T> Un <code>Enum</code> que extienda <code>MenuItem</code> y catalogue los elementos a ser mostrados.
 */
public class ItemPanel<T extends Enum<T> & MenuItem> extends JPanel {
    private final Class<T> clazz;
    private BufferedImage background;
    private Dimension bgDim;
    /* Margen entre borde de panel y elemento más externo (flechas de nav) */
    private final int BORDER_MARGIN = 35;
    private final int ITEM_SPACING = 35;
    private JPanel labelPanel;
    public ItemPanel(VistaEscenaZoo.PanelListener panelListener, Class<T> clazz) {
        super();
        this.clazz = clazz;
        setLayout(new BorderLayout());

        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
        labelPanel.setOpaque(false);
        labelPanel.add(Box.createHorizontalGlue());
        createLabels((int) (60 * App.SCALE_FACTOR), panelListener);
        labelPanel.add(Box.createHorizontalGlue());

        if (App.SEE_HITBOX) {
            labelPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        try {
            background = ImageIO.read(getClass().getResource("/MenuPanel.png"));
            bgDim = new Dimension(background.getWidth(), background.getHeight());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


        addNavArrowL((int) (100 * App.SCALE_FACTOR), panelListener);
        add(labelPanel);
        addNavArrowR((int) (100 * App.SCALE_FACTOR), panelListener);

    }

    private T[] values() {
        return clazz.getEnumConstants();
    }

    /**
     * Crea los labels a mostrar en el panel.
     * @param height La altura de los labels.
     * @param panelListener El listener de eventos para panel.
     */
    private void createLabels(int height, VistaEscenaZoo.PanelListener panelListener) {
        for (T itemEnum : values())  {
            ItemLabel<T> itemLabel = new ItemLabel<>(height, itemEnum);

            if (App.SEE_HITBOX) {
                itemLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
            }

            itemLabel.addMouseListener(panelListener);
            labelPanel.add(itemLabel);
            if (itemEnum.ordinal() < values().length - 1) {
                labelPanel.add(Box.createRigidArea(new Dimension(ITEM_SPACING, 0)));
            }
        }
    }

    private void addNavArrowR(int height, VistaEscenaZoo.PanelListener panelListener) {
        LabelNavArrow labelNavArrow = new LabelNavArrow(height, LabelNavArrow.NavArrowOrientation.RIGHT);

        labelNavArrow.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, BORDER_MARGIN));

        labelNavArrow.addMouseListener(panelListener);
        add(labelNavArrow, BorderLayout.EAST);
    }

    private void addNavArrowL(int height, VistaEscenaZoo.PanelListener panelListener) {
        LabelNavArrow labelNavArrow = new LabelNavArrow(height, LabelNavArrow.NavArrowOrientation.LEFT);

        labelNavArrow.setBorder(BorderFactory.createEmptyBorder(0, BORDER_MARGIN, 0,0));

        labelNavArrow.addMouseListener(panelListener);
        add(labelNavArrow, BorderLayout.WEST);
    }

    @Override
    public Dimension getPreferredSize() {
        float ratio = getWidth() / (float) bgDim.width;
        int panelWidth = (int) (bgDim.width * ratio);
        // TODO: tuve que multiplicar por 0.6 :(
        int panelHeight = (int) (bgDim.height * ratio * 0.6);
        return new Dimension(panelWidth, panelHeight);
    }


    @Override
    protected void paintComponent(Graphics g) {
        revalidate();
        super.paintComponent(g);

        BufferedImage bg = new BufferedImage(
                getWidth(),
                getHeight(),
                background.getType()
        );
        Graphics2D g2d = bg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2d.dispose();

        g.drawImage(bg, 0, 0, null);
    }
}
