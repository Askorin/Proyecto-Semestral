import javax.swing.*;

public final class App {
    private App() {
        VentanaApp frame = new VentanaApp();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}
