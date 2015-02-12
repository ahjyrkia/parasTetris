package tetris.maailma;

import java.awt.Color;
import java.util.ArrayList;

/*
* Pelipalikan kertoo kaiken liikutettavasta palikasta. Koordinaatit ja muodon. 
* Täällä tapahtuu myös liikkumisen viimeinen vaihe.
*/
public class Pelipalikka {

    private int x;
    private int y;
    private int liikeX;
    private int liikeY;
    private String muoto;
    private Color vari;
    private ArrayList<Palikka> vartalo;

    public Pelipalikka(int x, int y, String muoto, Color vari) {
        this.x = x;
        this.y = y;
        liikeX = 0;
        liikeY = 0;
        this.muoto = muoto;
        this.vari = vari;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public void setVari(Color vari) {
        this.vari = vari;
    }

    public void liiku(boolean saakoVasen) {
        if (saakoVasen) {
            if (liikeX < 0) {
                liikeX = 0;
            }
        }
        x = x + liikeX;
        liikeX = 0;

        y = y + liikeY + 1;
        liikeY = 0;

        if (this.x < 0) {
            setX(0);
        }

    }

    public boolean osuukoLattiaan() {
        if (getMuoto().equals("L")) {
            if (y + 60 > 500) {
                return true;
            }
        }
        return false;
    }

    public int getAlinKohta() {
        if (muoto.equals("L")) {
            return y + 60;
        }
        return -1;
    }

    public void liikeKoordinaattienMuutos(String mihin) {
        if (mihin.equals("right")) {
            liikeX += 20;

        }
        if (mihin.equals("left")) {
            liikeX -= 20;

        }
        if (mihin.equals("up")) {
            liikeY -= 20;

        }
        if (mihin.equals("down")) {
            if (y < 420) { // tilapäinen ratkasu
                liikeY += 20;
            }
        }

    }



    public String getMuoto() {
        return muoto;
    }
}
