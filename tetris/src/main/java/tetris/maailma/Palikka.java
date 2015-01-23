package tetris.maailma;

import java.util.Random;
 
public class Palikka {
 
    private int x;
    private int y;
    private int halkaisija;
 
    public Palikka(int x, int y, int halkaisija) {
        this.x = x;
        this.y = y;
        this.halkaisija = halkaisija;
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
 
    public int getHalkaisija() {
        return halkaisija;
    }
 
    public void liiku() {
      
       
    }
 
}