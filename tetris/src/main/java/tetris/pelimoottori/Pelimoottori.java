package tetris.pelimoottori;
import tetris.kayttoliittyma.Kayttoliittyma;

import tetris.maailma.Maailma;

public class Pelimoottori extends Thread {
 
    private Kayttoliittyma kayttoliittyma;
    private Maailma maailma;
    private boolean kaynnissa;
 
    public Pelimoottori(Kayttoliittyma kayttoliittyma, Maailma maailma) {
        this.kayttoliittyma = kayttoliittyma;
        this.maailma = maailma;
        this.kaynnissa = true;
    }
 
    public void paivita() {
        maailma.liikuta();
        
    }
 
    public void piirra() {
        kayttoliittyma.piirra(maailma.getPelipalikat());
    }
 
    @Override
    public void run() {
        while (kaynnissa) {
            paivita();
            piirra();
            odota();
        }
    }
 
    public void sammuta() {
        kaynnissa = false;
    }
 
    public void odota() {
        try {
            Thread.sleep(1000 / 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}