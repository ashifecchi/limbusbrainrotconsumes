import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Win extends JFrame {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Button butt;
    private static JFrame window;

    public static void run() throws IOException {
        window = new JFrame();
        Im pa = new Im();
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setLocation((int)screenSize.getWidth()/4,(int)screenSize.getHeight()/8);
        window.setSize(1000,750);
        window.add(pa);
        window.setVisible(true);
        window.setResizable(false);
        window.add(pa);
    }
}