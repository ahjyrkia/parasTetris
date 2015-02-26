package tetris.maailma;

import java.awt.Color;
import java.util.HashMap;
import java.util.Random;
/*
* Turha luokka, varmaan menee roskikseen.
*/
public class Palikka {
 
    private int x;
    private int y;
    private Color vari;
    
    public Palikka(int x, int y, Color vari) {
        this.x = x;
        this.y = y;
        this.vari = vari;
        
    }
    public void setY(int y) {
        this.y = y;
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
    public Color getVari() {
        return vari;
    }
 
 
 
}