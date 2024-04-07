import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Chars> listofChars;
    public Inventory(ArrayList<Chars> c) throws IOException {
        listofChars = c;
        listofChars.add(new Chars("goblin",Im.loadImg("src/CharacterImgsSrc/gobln.png")));
    }
    public void addChar(String name){
        for (int i = 0 ; i < listofChars.size(); i ++){
            if (listofChars.get(i).getName().equalsIgnoreCase(name)){
                listofChars.get(i).increment();
            }
        }
    }
    public int getGuyCopies(String name){
        for (Chars guy : listofChars) {
            if (guy.getName().equalsIgnoreCase(name)) {
                return guy.getCopies();
            }
        }
        return 0;
    }
    public ArrayList<Chars> getInv(){
        return listofChars;
    }
}