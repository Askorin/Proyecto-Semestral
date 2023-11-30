package org.zoo.vista.visitor;

import org.zoo.App;
import org.zoo.modelo.*;
import org.zoo.modelo.states.DeadState;
import org.zoo.modelo.placementmanager.FoodPlacementManager;
import org.zoo.utilities.ZooPoint;
import org.zoo.modelo.animal.Animal;
import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.vista.Drawable;
import org.zoo.vista.RenderedSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.font.GlyphVector;

public class DrawVisitor extends JPanel implements Visitor {
    private final int width;
    private final int height;
    private Graphics g;
    private Layer currentLayer;
    private final Zoo zoo;
    // TODO: El paintComponent lo debería llevar ventana en verdad?
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.g = g;
        for (Layer lyr: Layer.values()) {
            currentLayer = lyr;
            zoo.accept(this);

            TextMessageManager.getInstance().accept(this);
        }
    }
    public DrawVisitor(Zoo zoo) {
        this.zoo = zoo;

        width = zoo.getWidth();
        height = zoo.getHeight();

        cameraHeight = 850;
        cameraWidth = 480;

        setPreferredSize(new Dimension(cameraWidth, cameraHeight));

        RenderedSprite.loadSprites(); //Importante
    }
    public void visitAnimal(Animal animal) {
        boolean cond = (animal.getCurrentState().getClass() == DeadState.class);
        if (    (currentLayer == Layer.MIDDLE        && !cond)
             || (currentLayer == Layer.MIDDLE_BACK   &&  cond) ) {
            int x = animal.getAbsX() - getCameraX();
            int y = animal.getAbsY() - getCameraY();

            //Dibujar org.zoo.modelo.utilities.Hitbox (Borrar luego)
            if (App.SEE_HITBOX) {
                g.setColor(Color.RED);
                g.drawRect(x, y, animal.getWidth(), animal.getHeight());
            }

            Sprite spr = animal.getCurrentSprite();
            RenderedSprite.draw(spr, g, x, y, animal.getWidth(), animal.getHeight(), animal.getSpriteTimeElapsed(), 1.0f);
        }
    }

    public void visitHabitat(Habitat habitat) {
        if (currentLayer == Layer.MIDDLE_BACK) {
            int x = habitat.getAbsX() - getCameraX();
            int y = habitat.getAbsY() - getCameraY();

            Sprite spr = habitat.getHabitatSprite();
            RenderedSprite.draw(spr, g, x, y, habitat.getWidth(), habitat.getHeight(), 0, 1.0f);
        }
        for (Drawable d: habitat.getContainables().getDrawables()) {
            d.accept(this);
        }

    }

    public void visitZoo(Zoo zoo) {
        if (currentLayer == Layer.values()[0]) {
            // Es importante que esto se ejecute solo UNA vez,
            // o si no aumenta "la sensibilidad" al mover la camara.
            updateCamera();
        }
        int cameraX = getCameraX();
        int cameraY = getCameraY();
        ZooPoint p = new ZooPoint(-cameraX, -cameraY);

        /* Dibujamos camara */
        if (currentLayer == Layer.BOTTOM) {
            RenderedSprite.draw(zoo.getBackgroundSprite(), g, p.x, p.y, width, height, 0, 1.0f);
        }

        for (Drawable d: zoo.getContainables().getDrawables()) {
            // Este check de null es medio quiche.
            if (d != null) {
                d.accept(this);
            }
        }
        // TODO: Sistema de layers para no tener que hacerlo manual, que es lo contrario a lo que queremos.
        zoo.getHabitatPlacementManager().accept(this);
        zoo.getAnimalPlacementManager().accept(this);
        zoo.getFoodPlacementManager().accept(this);
    }

    public void visitHabitatPlacementManager(HabitatPlacementManager hpm) {
        if (currentLayer == Layer.TOP) {
            if (hpm.isActivo()) {
                Sprite spr = hpm.getEnumHabitat().getSprite();
                RenderedSprite.draw(spr, g, hpm.getAbsX() - getCameraX(), hpm.getAbsY() - getCameraY(), 0, 0, 0, 0.45f);
            }
        }
    }

    public void visitAnimalPlacementManager(AnimalPlacementManager apm) {
        if (currentLayer == Layer.TOP) {
            if (apm.isActivo()) {
                Sprite spr = apm.getEnumAnimal().getSprite();
                RenderedSprite.draw(spr, g, apm.getAbsX() - getCameraX(), apm.getAbsY() - getCameraY(), 0, 0, 0, 0.7f);
            }
        }
    }

    public void visitFoodPlacementManager(FoodPlacementManager fpm) {
        if (currentLayer == Layer.TOP) {
            if (fpm.isActivo()) {
                Sprite spr = fpm.getEnumFood().getInGameSprite();
                RenderedSprite.draw(spr, g, fpm.getAbsX() - getCameraX(), fpm.getAbsY() - getCameraY(), 0, 0, 0, 0.7f);
            }
        }
    }

    public void visitFoodDisplay(FoodArea.FoodDisplay foodDisplay) {
        if (currentLayer == Layer.MIDDLE) {
            int x = foodDisplay.getAbsX() - getCameraX();
            int y = foodDisplay.getAbsY() - getCameraY();

            int width = foodDisplay.getWidth();
            int height = foodDisplay.getHeight();

            if (App.SEE_HITBOX) {//Borrar luego
                g.setColor(Color.CYAN);
                g.drawRect(x, y, width, height);
            }
            Sprite spr = foodDisplay.getFood().getInGameSprite();
            RenderedSprite.draw(spr, g, x, y, width, height, 0, 1.0f);
        }
    }

    public void visitFoodArea(FoodArea foodArea) {
        if (currentLayer == Layer.MIDDLE) {
            int x = foodArea.getAbsX() - getCameraX();
            int y = foodArea.getAbsY() - getCameraY();
            int width = foodArea.getWidth();
            int height = foodArea.getHeight();

            g.setColor(new Color(85, 28, 19));
            g.fillRect(x, y, width, height);
            g.setColor(new Color(137, 58, 27));
            g.fillRect(x, y, width, 8);
            g.fillRect(x, y + height - 8, width, 8);
            g.fillRect(x, y, 8, height);
            g.fillRect(x + width - 8, y, 8, height);

            for (FoodArea.FoodDisplay fd : foodArea.getAllFoodDisplays()) {
                fd.accept(this);
            }

            g.setColor(new Color(195, 95, 29));
            g.fillRect(x, y, width, 4);
            g.fillRect(x, y + height - 4, width, 4);
            g.fillRect(x, y, 4, height);
            g.fillRect(x + width - 4, y, 4, height);
        }
    }

    int textCounter;
    public void visitTextMessageManager(TextMessageManager manager) {
        if (currentLayer == Layer.TOP) {
            textCounter = 0;
            for (Drawable d : TextMessageManager.getAllTextMessages()) {
                // Este check de null es medio quiche.
                if (d != null) {
                    d.accept(this);
                    textCounter += 1;
                }
            }
        }
    }
    public void visitTextMessage(TextMessage text) {
        if (currentLayer == Layer.TOP) {
            int x = 10;
            int y = 25 + 12 * textCounter;

            int time = (int) (text.getTimeElapsed());
            float lifetimeRatio = ((float) time) / ((float) TextMessage.LIFETIME);

            float opacidad = 1.0f - (float) Math.pow(lifetimeRatio, 4.0);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 1)); //El tamaño no importa
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidad));

            GlyphVector glyphVector = getFont().createGlyphVector(g2d.getFontRenderContext(), text.getText());
            Shape textShape = glyphVector.getOutline();

            g2d.translate(x, y);
            g2d.scale(1.75, 1.75);

            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1.0f));
            g2d.draw(textShape);

            g2d.setColor(Color.WHITE);
            g2d.fill(textShape);

            textCounter += 1;
        }
    }


    ///// CAMERA //TODO: Mover a otra clase, ojala no anidada a esta?
    private int cameraX; private int cameraY;
    private int cameraWidth; private int cameraHeight;
    private final int cameraSpeed = 1;
    private int mouseX; private int mouseY; private boolean isDragging;
    private ZooPoint prevMousePos;
    private void updateCamera() {


        /*
         * cameraWidth es ancho de lo que se "muestra"
         * width es ancho de fondo
         */
        cameraHeight = getSize().height;
        cameraWidth = getSize().width;

        ZooPoint currentMousePos = new ZooPoint(mouseX, mouseY);
        if (prevMousePos != null && isDragging) {
            ZooPoint deltaMousePos = ZooPoint.getDifference(prevMousePos, currentMousePos);
            deltaMousePos.x *= cameraSpeed;
            deltaMousePos.y *= cameraSpeed;
            ZooPoint newCameraPos = new ZooPoint(cameraX + deltaMousePos.x, cameraY + deltaMousePos.y);

            /*
             * Si la posición de renderizado del fondo más su ancho es mayor
             * al espacio disponible en la vista, no queremos aplicar la transformación.
             */
            boolean changeX = true;
            boolean changeY = true;

            /*
             * Si estamos en el borde derecho del mapa y el usuario quiere
             * seguir moviendose a la derecha.
             */
            if (newCameraPos.x + cameraWidth > width && deltaMousePos.x > 0) {
                changeX = false;
            }

            /*
             * Si estamos en el borde izquierdo del mapa y el usuario quiere
             * seguir moviendose a la izquierda.
             */
            if (newCameraPos.x < 0 && deltaMousePos.x < 0) {
                changeX = false;
            }

            /*
             * Si estamos en el borde inferior del mapa y el usuario quiere
             * seguir moviendo hacia abajo.
             */
            if (newCameraPos.y + cameraHeight > height && deltaMousePos.y > 0) {
                changeY = false;
            }

            /*
             * Si estamos en el borde superior del mapa y el usuario quiere
             * seguir moviendose hacia arriba
             */
            if (newCameraPos.y < 0 && deltaMousePos.y < 0) {
                changeY = false;
            }

            if (changeX) cameraX = newCameraPos.x;
            if (changeY) cameraY = newCameraPos.y;

        }
        prevMousePos = currentMousePos;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }
    public void setDragging(boolean dragging) {
        this.isDragging = dragging;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public int getCameraX() {
        return cameraX;
    }
    public int getCameraY() {
        return cameraY;
    }

    /// Layers
    private enum Layer {
        BOTTOM,
        MIDDLE_BACK,
        MIDDLE,
        TOP
    }
}