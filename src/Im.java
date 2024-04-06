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
    private JButton decagacha = new JButton();
    private JButton singlegacha = new JButton();;
    private JButton inventorybutton = new JButton();;
    private JButton invRight = new JButton();;
    private JButton invLeft = new JButton();
    private BufferedImage bg;
    private BufferedImage splash = loadImg("src/NonCharacterResources/BOOM.png");
    private BufferedImage splash2 = loadImg("src/NonCharacterResources/BANG.png");
    private BufferedImage arrowLeft = loadImg("src/NonCharacterResources/arrowleft.png");
    private BufferedImage arrowright = loadImg("src/NonCharacterResources/arrowright.png");
    private pull puller;
    private Graphics g;
    private Chars[] chars;
    private boolean drawchars = false;
    private double currentChar = 0;
    private int currentCharInv = 0;
    private double finish;
    private JButton extractionbutton = new JButton();;
    private Timer time;
    private Inventory inv;
    private boolean Inv = false;
    public Im() throws IOException {
        super();
        time = new Timer(100, this);
        time.start();
        initializeCharPool();
    }
    @Override
    public void paintComponent(Graphics g) {
        initializeCharPool();
        super.paintComponent(g);
        this.g = g;

        //buttons

        inventorybutton.setSize(60,50);
        inventorybutton.setEnabled(true);
        inventorybutton.setOpaque(false); //https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable thank you random guy
        inventorybutton.setContentAreaFilled(false);
        inventorybutton.setBorderPainted(false);
        inventorybutton.setVisible(true);
        inventorybutton.setName("inventory");
        inventorybutton.setLocation(895,540);
        inventorybutton.addActionListener(this);

        invRight.setName("right");
        invRight.setBorderPainted(false);
        invRight.setContentAreaFilled(false);
        invRight.setSize(36,53);
        invRight.setLocation(600,300);
        invRight.setEnabled(true);
        invRight.addActionListener(this);

        invLeft.setName("left");
        invLeft.setIcon(new ImageIcon("src/NonCharacterResources/arrowleft.png"));
        invLeft.setBorderPainted(false);
        invLeft.setContentAreaFilled(false);
        invLeft.setSize(36,53);
        invLeft.setLocation(250,300);
        invLeft.setEnabled(true);
        invLeft.addActionListener(this);

        extractionbutton.setSize(60,50);
        extractionbutton.setEnabled(true);
        extractionbutton.setOpaque(false); //https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable thank you random guy
        extractionbutton.setContentAreaFilled(false);
        extractionbutton.setBorderPainted(false);
        extractionbutton.setVisible(true);
        extractionbutton.setName("extract");
        extractionbutton.setLocation(815,540);
        extractionbutton.addActionListener(this);


        singlegacha.setSize(115,30);
        singlegacha.setEnabled(true);
        singlegacha.setOpaque(false); //https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable thank you random guy
        singlegacha.setContentAreaFilled(false);
        singlegacha.setBorderPainted(false);
        singlegacha.setVisible(true);
        singlegacha.setName("singlepull");
        singlegacha.setLocation(665,470);
        singlegacha.addActionListener(this);

        decagacha.setSize(125,30);
        decagacha.setVisible(true);
        decagacha.setEnabled(true);
        decagacha.setOpaque(false); //https://stackoverflow.com/questions/5654208/making-a-jbutton-invisible-but-clickable thank you random guy
        decagacha.setContentAreaFilled(false);
        decagacha.setName("10pull");
        decagacha.setBorderPainted(false);
        decagacha.setLocation(800,470);
        decagacha.addActionListener(this);

        if (Inv){
            decagacha.setEnabled(false);
            singlegacha.setEnabled(false);
        } else{
            decagacha.setEnabled(true);
            singlegacha.setEnabled(true);
        }

        //bg
        try {
            if (Inv){
                bg = loadImg("src/backgorudnsnstuff/the limbus company.png");
            } else {
                bg = loadImg("src/backgorudnsnstuff/the ;iombus company.png");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //POOl !!!
        try {
            inv = new Inventory(pool);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g.drawImage( bg,0,0,this);

        //draw inv
        if (Inv){
            g.drawImage(arrowLeft,250,300,null);
            g.drawImage(arrowright,600,300,null);
            invRight.setEnabled(true);
            invLeft.setEnabled(true);
            Chars current = inv.getInv().get(currentCharInv);
            g.setFont(new Font("Comic Sans MS",Font.PLAIN, 50));
            g.setColor(Color.RED);
            decagacha.setVisible(false);
            singlegacha.setVisible(false);
            g.drawImage(current.getSprite().getScaledInstance(800,600,0),60,0,null);
            g.drawString(current.getName(),400,100);
            g.setFont(new Font("Comic Sans MS",Font.PLAIN, 20));
            g.drawString(""+inv.getInv().get(currentCharInv).getCopies(),775,260);
        } else {
            invRight.setEnabled(false);
            invLeft.setEnabled(false);
        }

        //draw pulls
        g.setColor(Color.WHITE);
        if  (drawchars) {
            decagacha.setVisible(false);
            singlegacha.setVisible(false);
            inventorybutton.setVisible(false);
            extractionbutton.setVisible(false);
            g.drawImage(splash, 240,100,null);
            Chars Currentchar = chars[(int)currentChar];
            for (Chars cha : chars){
                inv.addChar(cha.getName());
            }
            if (Currentchar.getName().equals("goblin")){
                g.drawImage(Currentchar.getSprite(),270,110,null);
            } else{
                g.drawImage(chars[(int) currentChar].getSprite(), 0, 0, null);
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
                decagacha.setVisible(true);
                singlegacha.setVisible(true);
                inventorybutton.setVisible(true);
                extractionbutton.setVisible(true);
            }
        }
        this.add(invRight);
        this.add(invLeft);
        this.add(extractionbutton);
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
            //System.out.print("click ");
            // was used to check if the listener worked
            if (((JButton) e.getSource()).getName().equals("10pull")) {
                printPull(puller.deca());
            }
            if (((JButton) e.getSource()).getName().equals("singlepull")){
                printPull(puller.single());
            }
            if (((JButton) e.getSource()).getName().equals("inventory")){
                Inv = true;
            }
            if (((JButton) e.getSource()).getName().equals("extract")){
                Inv = false;
            }
            if (((JButton) e.getSource()).getName().equals("right")){
                if (currentCharInv < inv.getInv().size()-1) {
                    currentCharInv++;
                } else{
                    System.out.println("NUH UH");
                }
            }
            if (((JButton) e.getSource()).getName().equals("left")){
                if (currentCharInv > 0) {
                    currentCharInv--;
                } else{
                    System.out.println("NUH UH");
                }
            }
        }
    }
}