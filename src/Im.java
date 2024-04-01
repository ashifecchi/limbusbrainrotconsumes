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
    private JButton singlegacha;
    private JButton inventorybutton;
    private pull puller;
    private Graphics g;
    private Chars[] chars;
    private boolean drawchars = false;
    private double currentChar = 0;
    private double finish;
    private Timer time;
    private Inventory inv;
    private boolean Inv = false;
    public Im(){
        super();
        time = new Timer(100, this);
        time.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        initializeCharPool();
        super.paintComponent(g);
        this.g = g;

        //buttons
        inventorybutton = new JButton();
        inventorybutton.setSize(60,50);
        inventorybutton.setEnabled(true);
        inventorybutton.setOpaque(false); //https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable thank you random guy
        inventorybutton.setContentAreaFilled(false);
        inventorybutton.setBorderPainted(false);
        inventorybutton.setVisible(true);
        inventorybutton.setName("inventory");
        inventorybutton.setLocation(895,540);
        inventorybutton.addActionListener(this);

        singlegacha = new JButton();
        singlegacha.setSize(115,30);
        singlegacha.setEnabled(true);
        singlegacha.setOpaque(false); //https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable thank you random guy
        singlegacha.setContentAreaFilled(false);
        singlegacha.setBorderPainted(false);
        singlegacha.setVisible(true);
        singlegacha.setName("singlepull");
        singlegacha.setLocation(665,470);
        singlegacha.addActionListener(this);

        decagacha = new JButton();
        decagacha.setSize(125,30);
        decagacha.setVisible(true);
        decagacha.setEnabled(true);
        decagacha.setOpaque(false); //https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable thank you random guy
        decagacha.setContentAreaFilled(false);
        decagacha.setName("10pull");
        decagacha.setBorderPainted(false);
        decagacha.setLocation(800,470);
        decagacha.addActionListener(this);

        //bg
        BufferedImage bg;
        BufferedImage splash;
        BufferedImage splash2;
        try {
            if (Inv){
         //       bg = loadImg(); i add later
            }
            bg = loadImg("src/backgorudnsnstuff/the ;iombus company.png");
            splash = loadImg("src/CharacterImgsSrc/BOOM.png");
            splash2 = loadImg("src/CharacterImgsSrc/BANG.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //POOl !!!
        initializeCharPool();
        inv = new Inventory(pool);

        //draw
        g.drawImage( bg,0,0,this);
        g.setColor(Color.WHITE);
        if  (drawchars) {
            g.drawImage(splash, 240,100,null);
            Chars Currentchar = chars[(int)currentChar];
            inv.addChar(Currentchar.getName());
            if (Currentchar.getName().equals("goblin")){
                g.drawImage(Currentchar.getSprite(),270,110,null);
            } else{
                    g.drawImage(chars[(int) currentChar].getSprite(), 200, 100, null);
            }
            g.setFont(new Font("Impact",Font.PLAIN, 100));
            g.drawString("YOU GOT A "+chars[(int)currentChar].getName().toUpperCase(),100,100);
            g.setFont(new Font("Lobster",Font.PLAIN,30));
           // g.drawString("pull number "+(int)(currentChar+1),10,500);
            if ((int)currentChar + .2 > currentChar){
                g.drawImage(splash2,0,-100,null);
            }
            if (currentChar >= finish){
                currentChar = 0;
                drawchars = false;
            }
        }
        this.add(inventorybutton);
        this.add(singlegacha);
        this.add(decagacha);
    }
    private void initializeCharPool(){
        pool = new ArrayList<>();
        Scanner filescanner;
        try {
            filescanner = new Scanner(new File("src/CharacterNames"));
            Scanner file2scanner = new Scanner(new File("src/CharacterImgs"));
            while (filescanner.hasNext()){
                Chars c = new Chars(filescanner.nextLine(),loadImg(file2scanner.nextLine()));
                pool.add(c);
            }
            puller = new pull(pool);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void printPull(Chars[] chars){ //for 10 pulls
        this.chars = chars;
        finish = 9.5;
        drawchars = true;
    }
    private void printPull(Chars chars){ //for single pulls
        this.chars = new Chars[1];
        this.chars[0] = chars;
        finish = 0.9;
        drawchars = true;
    }
    public static BufferedImage loadImg(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            if (drawchars) {
                currentChar += 0.1;
            }
            repaint();
        }
        if (e.getSource() instanceof JButton){
            System.out.print("click ");
            if (((JButton) e.getSource()).getName().equals("10pull")) {
                printPull(puller.deca());
            }
            if (((JButton) e.getSource()).getName().equals("singlepull")){
                printPull(puller.single());
            }
            if (((JButton) e.getSource()).getName().equals("inventory")){
                Inv = true;
            }
        }
    }
}