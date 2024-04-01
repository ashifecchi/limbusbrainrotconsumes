import java.awt.image.BufferedImage;

public class Chars {
    private String name;
    private BufferedImage sprite;
    private int copies;
    public Chars(String name,BufferedImage sprite){
        this.name = name;
        this.sprite = sprite;
        copies = 0;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
    public void increment(){
        copies++;
    }
    public int getCopies(){
        return copies;
    }
}
