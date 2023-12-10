package org.zoo.modelo;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.vista.Drawable;
import org.zoo.visitor.Visitor;
import java.util.ArrayList;

/*
 * Es un singleton porque necesitamos que haya una sola instancia que sea accesible por todos sin
 * tener que traer la referencia constantemente. Ademas necesita ser instanciable y no basta con
 * solo tener metodos estaticos porque necesitamos que sea una instancia que se puede pasar a un
 * contenedor para que reciba un llamado de .update(), y luego llama al .update() de sus componentes.
 */
public class TextMessageManager implements Updatable, Drawable {
    private static final ArrayList<TextMessage> allTextMessages = new ArrayList<>();
    public TextMessageManager() {}
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

    public static void addTextMessage(TextMessage text) {
        allTextMessages.add(text);
    }
    public static void removeTextMessage(TextMessage text) {
        allTextMessages.remove(text);
    }
    public static ArrayList<TextMessage> getAllTextMessages() {
        return allTextMessages;
    }
    public int getAbsX() {
        return 0;
    }
    public int getAbsY() {
        return 0;
    }
}
