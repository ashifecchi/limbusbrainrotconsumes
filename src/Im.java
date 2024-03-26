import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Im extends JPanel implements ActionListener {
    private ArrayList<Chars> pool;
    private JButton decagacha;
    private pull puller;
    private Graphics g;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //buttons
        decagacha = new JButton();
        decagacha.setSize(125,30);
        decagacha.setEnabled(true);
        decagacha.setOpaque(false); //https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable thank you random guy
        decagacha.setContentAreaFilled(false);
        decagacha.setBorderPainted(false);
        decagacha.setLocation(800,470);
        decagacha.addActionListener(this);

        //bg
        BufferedImage bg;
        try {
            bg = loadImg("src/backgorudnsnstuff/the ;iombus company.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //POOl !!!
        ArrayList<Chars> pool = new ArrayList<>();
        try {
            initializeCharPool();
            puller = new pull(pool);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //draw
        g.drawImage( bg,0,0,this);
        this.add(decagacha);
    }
    private void initializeCharPool() throws IOException {
        pool = new ArrayList<>();
        Scanner filescanner = new Scanner(new File("src/CharacterNames"));
        Scanner file2scanner = new Scanner(new File("src/CharacterImgs"));
        while (filescanner.hasNext()){
            Chars c = new Chars(filescanner.nextLine(),loadImg(file2scanner.nextLine()));
            pool.add(c);
        }
    }
    private void printPull(Chars[] chars){
        for (Chars guy : chars){
            g.drawImage(guy.getSprite(),300,300,null);
        }
    }
    public static BufferedImage loadImg(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
        @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
            if (e.getSource() == decagacha){
                System.out.print("yippee");
                printPull(puller.deca());
            }
        }
    }
}

