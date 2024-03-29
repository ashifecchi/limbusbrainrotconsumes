import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class pull {
    private ArrayList<Chars> pool;
    private BufferedImage garbolin = Im.loadImg("src/CharacterImgsSrc/gobln.png");
    public pull(ArrayList<Chars> characterpool) throws IOException {
        pool = characterpool;
    }
    public Chars single() { //gets 1 random char
        double num = ((Math.random() * 10) + 1);
        if (num > 0) {
           return pool.get((int) (Math.random() * (pool.size())));
        }
            return new Chars("goblin", garbolin);
    }
    public Chars[] deca(){ //gets 10 random chars
        Chars[] tenpull = new Chars[10];
        for (int i = 0; i < 10; i++){
            tenpull[i] = single();
        }
        return tenpull;
    }
    public void drawPull(Graphics g,Chars[] pull){
        for (Chars cha : pull){
            g.drawImage(cha.getSprite(),400,300,null);
        }
    }
    public void drawPull(Graphics g,Chars pull){
        g.drawImage(pull.getSprite(),400,300,null);
    }
}