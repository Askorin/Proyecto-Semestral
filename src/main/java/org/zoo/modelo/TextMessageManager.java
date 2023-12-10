package org.zoo.modelo;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.characteristics.Drawable;
import org.zoo.visitor.Visitor;
import java.util.ArrayList;

/**
 * Clase que permite manejar todos los mensajes de texto que estan en circulacion
 * @see TextMessage
 */
public class TextMessageManager implements Updatable, Drawable {
    /**
     * Lista de todos los mensajes de texto que hay en ciruculación
     * @see TextMessage
     */
    private static final ArrayList<TextMessage> allTextMessages = new ArrayList<>();
    public TextMessageManager() {}
    @Override
    public void update() {
        for (int i = getAllTextMessages().size() - 1; i >= 0; --i) {
            TextMessage t = getAllTextMessages().get(i);
            if (t != null) {t.update();}
        }
    }
    @Override
    public void accept(Visitor v) {
        v.visitTextMessageManager(this);
    }

    /**
     * Metodo que se debe usar para agregar mensajes en circulacion al <code>TextMessageManager</code>
     * @param text <code>TextMessage</code> que se debe agregar al <code>TextMessageManager</code>
     */
    public static void addTextMessage(TextMessage text) {
        allTextMessages.add(text);
    }
    /**
     * Metodo que se debe usar inmediatamente despues de que un mensaje
     * termine de estar en circulacion para eliminarlo de <code>TextMessageManager</code>
     * @param text <code>TextMessage</code> que se debe eliminar de <code>TextMessageManager</code>
     */
    public static void removeTextMessage(TextMessage text) {
        allTextMessages.remove(text);
    }

    /**
     * Metodo que permite ver la lista de todos los mensajes de texto que estan en circulacion
     * @return Lista de todos los <code>TextMessage</code> actuales
     */
    public static ArrayList<TextMessage> getAllTextMessages() {
        return allTextMessages;
    }
    @Override
    public int getAbsX() {
        return 0;
    }
    @Override
    public int getAbsY() {
        return 0;
    }
}
