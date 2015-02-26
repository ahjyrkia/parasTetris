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
    private int score;
    private String seuraavaMuoto;
    private Color seuraavaVari;
    private boolean gameOver;
    private boolean pause;
    private int kyltinVilkkumisMuuttuja;

    public Pelipalikka(int x, int y, String muoto, Color vari) {
        this.x = x;
        this.y = y;
        liikeX = 0;
        liikeY = 0;
        this.muoto = muoto;
        this.vari = vari;
        score = 0;
        gameOver = false;
        pause = false;
        kyltinVilkkumisMuuttuja = 0;
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

    public void liiku(boolean saakoVasen, boolean saakoOikea, boolean saakoAlas, boolean loppuukoKeuliminen) {
        if (saakoVasen) {
            if (liikeX < 0) {
                liikeX = 0;
            }
        }
        if (saakoOikea) {
            if (liikeX > 0) {
                liikeX = 0;
            }
        }
        x = x + liikeX;
        liikeX = 0;
        if (!saakoAlas && !pause) {
            y = y + 1;
        }
        if (loppuukoKeuliminen) {
            liikeY = 0;
        }
        y = y + liikeY;
        liikeY = 0;
        hoidaReunanYlitykset();
        
    }

    public void liikeKoordinaattienMuutos(String mihin) {
        if (mihin.equals("right")) {
            liikeX += 20;

        }
        if (mihin.equals("left")) {
            liikeX -= 20;

        }
        if (mihin.equals("down")) {
            liikeY += 20;
        }

    }

    public void kaannaPalikkaa() {
        while (true) {
            if (muoto.equals("L")) {
                muoto = "L2";
                break;
            }
            if (muoto.equals("L2")) {
                muoto = "L3";
                break;
            }
            if (muoto.equals("L3")) {
                muoto = "L4";
                break;
            }
            if (muoto.equals("L4")) {
                muoto = "L";
                break;
            }
            if (muoto.equals("SUORA")) {
                muoto = "SUORA2";
                break;
            }
            if (muoto.equals("SUORA2")) {
                muoto = "SUORA";
                break;
            }
            if (muoto.equals("NELIO")) {
                muoto = "NELIO";
                break;
            }
        }
    }

    public void hoidaReunanYlitykset() {
        if (muoto.equals("L") || muoto.equals("NELIO") || muoto.equals("L3")) {
            if (x + 40 > 400) {
                setX(360);
            }
        }
        if (muoto.equals("SUORA2")) {
            if (x + 20 > 400) {
                setX(380);
            }
        }
        if (muoto.equals("L2") || muoto.equals("L4")) {
            if (x + 60 > 400) {
                setX(340);
            }
        }
        if (muoto.equals("SUORA")) {
            if (x + 80 > 400) {
                setX(320);
            }
        }
        if (this.x < 0) {
            setX(0);
        }
    }

    public void setMuoto(String muoto) {
        this.muoto = muoto;
    }

    public String getMuoto() {
        return muoto;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setSeuraavaMuoto(String seuraava) {
        seuraavaMuoto = seuraava;
    }

    public String getSeuraavaMuoto() {
        return seuraavaMuoto;
    }

    public void setSeuraavaVari(Color vari) {
        seuraavaVari = vari;
    }
    public Color getSeuraavaVari() {
        return seuraavaVari;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    public boolean getGameover() {
        return gameOver;
    }
    public void pause() {
        if (pause) {
            pause = false;
        } else {
            pause = true;
        }
    }
    public boolean getPause() {
        return pause;
    }
    public void kyltinVilkkumisMuuttuja() {
        if (kyltinVilkkumisMuuttuja == 0) {
            kyltinVilkkumisMuuttuja = 1;
        } else {
            kyltinVilkkumisMuuttuja = 0;
        }
    }
    public int kyltinVari() {
        return kyltinVilkkumisMuuttuja;
    }
    
}
