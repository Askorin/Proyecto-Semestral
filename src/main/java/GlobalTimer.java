import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//TODO: Eliminar
public class GlobalTimer implements ActionListener {
    private static ArrayList<Timeable> timeables;
    private static Timer timer;
    public static final int MS_PER_FRAME = 1000/60;
    public GlobalTimer() {
        timeables = new ArrayList<>();

        timer = new Timer(MS_PER_FRAME, null);
        timer.addActionListener(this);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Timeable t: timeables) {
            t.step();
        }
    }
    public static void addTimeable(Timeable t) {
        timeables.add(t);
    }

    public static void removeTimeable(Timeable t) {
        timeables.remove(t);
    }
}

