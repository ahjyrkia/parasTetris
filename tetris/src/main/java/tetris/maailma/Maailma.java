package tetris.maailma;


import java.util.ArrayList;
import tetris.maailma.Palikka;


public class Maailma {
 
    private int nopeus;
    private ArrayList<Palikka> palikat;
    private Pelipalikka pelipalikka;
 
    public Maailma() {
        this.nopeus = 1;
        this.pelipalikka = new Pelipalikka(50, 150);
        this.palikat = new ArrayList<Palikka>();
        int kierros = 0;
        for (int i = 0; i < 200; i++) {
            kierros++;
        }
    }
 
    public void nopeuta() {
        nopeus++;
    }
    
    public int getNopeus() {
        return nopeus;
    }
 
    public void liikuta() {
        pelipalikka.liiku();
    }
 
    public Pelipalikka getPelipalikka() {
        return pelipalikka;
    }
 
    public ArrayList<Palikka> getPalikat() {
        return palikat;
    }

}