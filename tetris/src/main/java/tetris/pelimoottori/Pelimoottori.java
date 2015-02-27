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
    /**
     * Muuttaa ohjelman arvoja jottai jotain liikettä tapahtuisi eli kutsuu 
     * maailma-luokan liikuta metodia.
     */
    public void paivita() {
        maailma.liikuta();
    }
    /**
     * Piirtää graafisen puolen. Lähettää myös aina uuden listan pysähtynteistä
     * palikoista.
     */
    public void piirra() {
        kayttoliittyma.piirra(maailma.getPysahtyneetPalikat());
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
    /**
     * Hidastaa päivittämistä jotta peli olisi ihmiselle mahdollista käyttää.
     */
    public void odota() {
        try {
            Thread.sleep(1000 / 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}