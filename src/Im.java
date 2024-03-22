import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.Time;
import java.util.Objects;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Im extends JPanel implements ActionListener {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //buttons
        JButton gacha = new JButton("gacja");
        gacha.setSize(125,30);
        gacha.setVisible(false);
        gacha.setEnabled(true);
        gacha.setLocation(800,470);
        gacha.addActionListener(this);

        //bg
        BufferedImage bg;
        try {
            bg = loadImg("src/backgorudnsnstuff/the ;iombus company.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //draw
        g.drawImage( bg,0,0,this);
        this.add(gacha);
    }
    public static BufferedImage loadImg(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
        @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){

        }
    }
}

