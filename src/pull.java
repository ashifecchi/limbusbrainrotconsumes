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
        double num = (Math.random() * 10)+1;
        // this was to check if the numbers worked.. in the correct range..
     //   System.out.println(num);
        if (num < 10.5) { //the chance of NOT winning. it goes from 1 to 11 pretty sure
            return new Chars("goblin", garbolin);
        }
        // this was to check that the pool actually worked
       // for (Chars cha : pool){
       //     System.out.println(cha.getName());
      //  }
        return pool.get((int) (Math.random() * (pool.size())));
    }
    public Chars[] deca(){ //gets 10 random chars
        Chars[] tenpull = new Chars[10];
        for (int i = 0; i < 10; i++){
            tenpull[i] = single();
        }
        return tenpull;
    }
}