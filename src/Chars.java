import java.awt.image.BufferedImage;

public class Chars {
    private String name;
    private BufferedImage sprite;
    public Chars(String name,BufferedImage sprite){
        this.name = name;
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
