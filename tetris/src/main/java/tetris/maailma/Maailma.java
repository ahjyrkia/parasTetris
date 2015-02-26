package tetris.maailma;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
/* 
 * Paljo pelin toiminnallisuutta on ängetty tänne.
 * 
 */

public class Maailma {

    private ArrayList<Palikka> pysahtyneetPalikat;
    ArrayList<Palikka> liikkuvatPalikat = new ArrayList<Palikka>();
    private Pelipalikka pelipalikka;
    private ArrayList<Color> varit;
    private int palikanNitkutus = 100;
    private int score = 0;
    private String seuraavaMuoto = "";
    private Color seuraavaVari;
    private boolean pause;

    public Maailma() {
        this.pelipalikka = new Pelipalikka(200, -50, "L", Color.GREEN);
        this.pysahtyneetPalikat = new ArrayList<Palikka>();
        varit = new ArrayList<Color>();
        luoVarit();
        seuraavaMuoto();
        seuraavaVari();
        pelipalikka.setSeuraavaVari(seuraavaVari);
        pelipalikka.setSeuraavaMuoto(seuraavaMuoto);
        pause = false;
    }

    public void liikuta() {
        liikkuvatPalikat = new ArrayList<Palikka>(kutsuPelipalikat());
        if (osuukoKuolleenPaalleTaiLattiaan()) {
            palikanNitkutus--;
            if (palikanNitkutus == 0) {
                tuleekoGameOver();
                Pelipalikka klooni = new Pelipalikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getMuoto(), pelipalikka.getVari());
                lisaaPelipalikkaPalikoihin(klooni);
                poistaTaydetRivit();
                setMuoto();
                pelipalikka.setY(-50);
                pelipalikka.setX(200);
                pelipalikka.setVari(seuraavaVari);
                palikanNitkutus = 100;
                seuraavaMuoto();
                seuraavaVari();
                pelipalikka.setSeuraavaMuoto(seuraavaMuoto);
                pelipalikka.setSeuraavaVari(seuraavaVari);
            }
        }
        pelipalikka.liiku(osuukoJosLiikutaanVasemmalle(), osuukoJosLiikutaanOikealle(), osuukoKuolleenPaalleTaiLattiaan(), saakoKeuliaViela());
    }

    public void lisaaPelipalikkaPalikoihin(Pelipalikka klooni) {
        String muoto = klooni.getMuoto();
        if (muoto.equals("L")) {
            pysahtyneetPalikat.add(new Palikka(klooni.getX(), klooni.getY(), klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX() + 20, klooni.getY(), klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX(), klooni.getY() + 20, klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX(), klooni.getY() + 40, klooni.getVari()));
        }
        if (muoto.equals("L2")) {
            pysahtyneetPalikat.add(new Palikka(klooni.getX(), klooni.getY(), klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX() + 20, klooni.getY(), klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX() + 40, klooni.getY(), klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX(), klooni.getY() - 20, klooni.getVari()));
        }
        if (muoto.equals("L3")) {
            pysahtyneetPalikat.add(new Palikka(klooni.getX() + 20, klooni.getY(), klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX() + 20, klooni.getY() - 20, klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX() + 20, klooni.getY() - 40, klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX(), klooni.getY(), klooni.getVari()));
        }
        if (muoto.equals("L4")) {
            pysahtyneetPalikat.add(new Palikka(klooni.getX() + 40, klooni.getY(), klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX() + 40, klooni.getY() - 20, klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX() + 20, klooni.getY() - 20, klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX(), klooni.getY() - 20, klooni.getVari()));
        }
        if (muoto.equals("NELIO")) {
            pysahtyneetPalikat.add(new Palikka(klooni.getX(), klooni.getY(), klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX() + 20, klooni.getY(), klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX(), klooni.getY() + 20, klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX() + 20, klooni.getY() + 20, klooni.getVari()));
        }
        if (pelipalikka.getMuoto().equals("SUORA")) {
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 40, pelipalikka.getY(), pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 60, pelipalikka.getY(), pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("SUORA2")) {
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 20, pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 40, pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 60, pelipalikka.getVari()));
        }
    }

    public void tuleekoGameOver() {
        for (Palikka kuollu : pysahtyneetPalikat) {
            if (kuollu.getY() < 0) {
                pelipalikka.setGameOver(true);
            }
        }

    }

    public boolean saakoKeuliaViela() {
        for (Palikka liikkuva : liikkuvatPalikat) {
            if (liikkuva.getY() + 40 >= 500) {
                return true;
            }
            for (Palikka kuollu : pysahtyneetPalikat) {
                if (liikkuva.getX() + 20 > kuollu.getX() && liikkuva.getX() < kuollu.getX() + 20) {
                    if (liikkuva.getY() + 40 >= kuollu.getY()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean osuukoKuolleenPaalleTaiLattiaan() {
        for (Palikka liikkuva : liikkuvatPalikat) {
            if (liikkuva.getY() + 20 >= 500) {
                return true;
            }
            for (Palikka kuollu : pysahtyneetPalikat) {
                if (liikkuva.getX() + 20 > kuollu.getX() && liikkuva.getX() < kuollu.getX() + 20) {
                    if (liikkuva.getY() + 20 == kuollu.getY()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean osuukoJosLiikutaanVasemmalle() {
        for (Palikka kuollu : pysahtyneetPalikat) {
            for (Palikka liikkuva : liikkuvatPalikat) {
                if (liikkuva.getY() + 20 > kuollu.getY() && liikkuva.getY() < kuollu.getY() + 20) {
                    if (liikkuva.getX() <= kuollu.getX() + 20 && liikkuva.getX() + 20 >= kuollu.getX() && liikkuva.getX() > kuollu.getX()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean osuukoJosLiikutaanOikealle() {
        for (Palikka kuollu : pysahtyneetPalikat) {
            for (Palikka liikkuva : liikkuvatPalikat) {

                if (liikkuva.getY() + 20 > kuollu.getY() && liikkuva.getY() < kuollu.getY() + 20) {
                    if (liikkuva.getX() + 20 >= kuollu.getX() && liikkuva.getX() <= kuollu.getX() + 20 && liikkuva.getX() < kuollu.getX()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void luoVarit() {
        varit.add(Color.red);
        varit.add(Color.BLUE);
        varit.add(Color.CYAN);
        varit.add(Color.GREEN);
        varit.add(Color.YELLOW);
        varit.add(Color.WHITE);
        varit.add(Color.ORANGE);
        varit.add(Color.PINK);
        varit.add(Color.magenta);
    }

    public ArrayList kutsuPelipalikat() {
        ArrayList<Palikka> lista = new ArrayList<Palikka>();
        if (pelipalikka.getMuoto().equals("L")) {
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() + 20, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() + 40, pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("L2")) {
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 40, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 20, pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("L3")) {
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 20, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 40, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("L4")) {
            lista.add(new Palikka(pelipalikka.getX() + 40, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 40, pelipalikka.getY() - 20, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 20, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 20, pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("NELIO")) {
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() + 20, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() + 20, pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("SUORA")) {
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 40, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 60, pelipalikka.getY(), pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("SUORA2")) {
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 20, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 40, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 60, pelipalikka.getVari()));
        }

        return lista;
    }

    public void poistaTaydetRivit() {
        int pisteytys = 0;
        for (int i = 480; i >= 0; i -= 20) {
            int rivi = 0;
            ArrayList<Palikka> poistettavat = new ArrayList<Palikka>();
            for (Palikka kuollu : pysahtyneetPalikat) {
                if (kuollu.getY() == i) {
                    rivi++;
                    poistettavat.add(kuollu);
                }
            }
            if (rivi >= 20) {
                pysahtyneetPalikat.removeAll(poistettavat);
                pisteytys += 1;
                siirraKuolleitaPalikoita(i);
                i = 500;
            }
        }
        score += 200 * pisteytys;
        pelipalikka.setScore(score);
    }

    public void siirraKuolleitaPalikoita(int mistaYlospain) {
        for (Palikka palikka : pysahtyneetPalikat) {
            if (palikka.getY() <= mistaYlospain) {
                palikka.setY(palikka.getY() + 20);
            }
        }
    }

    public void seuraavaVari() {
        Random r = new Random();
        seuraavaVari = varit.get(r.nextInt(9));
    }

    public void seuraavaMuoto() {
        Random r = new Random();
        int luku = r.nextInt(3);
        if (luku == 2) {
            seuraavaMuoto = "NELIO";
        }
        if (luku == 1) {
            seuraavaMuoto = "L";
        }
        if (luku == 0) {
            seuraavaMuoto = "SUORA";
        }
    }

    public void setMuoto() {
        pelipalikka.setMuoto(seuraavaMuoto);
    }

    public Pelipalikka getPelipalikka() {
        return pelipalikka;
    }

    public ArrayList<Palikka> getPysahtyneetPalikat() {
        return pysahtyneetPalikat;
    }

}
