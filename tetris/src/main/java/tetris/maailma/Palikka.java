package tetris.maailma;

import java.util.HashMap;
import java.util.Random;
 
public class Palikka {
 
    private int x;
    private int y;
    
    private HashMap<Integer, Integer> neliot = new HashMap<Integer, Integer>();
    private String muoto;
 
    public Palikka(int x, int y, String muoto) {
        this.x = x;
        this.y = y;
        this.muoto = muoto;
        
    }
    public String getMuoto() {
        return muoto;
    }
 
    public void setX(int x) {
        this.x = x;
    }
 
    public int getX() {
        return x;
    }
 
    public int getY() {
        return y;
    }
 
 
    public void liiku() {
      
       
    }
 
}