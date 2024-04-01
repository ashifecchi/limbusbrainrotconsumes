import java.lang.reflect.Array;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Chars> listofChars;
    public Inventory(ArrayList<Chars> c){
        listofChars = c;
    }
    public void addChar(String name){
        for (Chars guy : listofChars){
            if (guy.getName().equals(name)){
                guy.increment();
            }
        }
    }
    public ArrayList<Chars> getInv(){
        return listofChars;
    }
}