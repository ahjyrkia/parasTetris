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

    /**
     * Luodaan ensimmäinen palikka, asetetaan pausen arvoksi false ja luodaan
     * värit.
     */
    public Maailma() {
        seuraavaMuoto();
        this.pelipalikka = new Pelipalikka(200, -50, seuraavaMuoto, Color.GREEN);
        this.pysahtyneetPalikat = new ArrayList<Palikka>();
        varit = new ArrayList<Color>();
        luoVarit();
        seuraavaMuoto();
        seuraavaVari();
        pelipalikka.setSeuraavaVari(seuraavaVari);
        pelipalikka.setSeuraavaMuoto(seuraavaMuoto);
        pause = false;
    }

    /**
     * Liikuttaa palikoita mikäli ne eivät osu lattiaan tai muihin palikoihin
     * (miinus palikannitkutus-aika). Toteuttaa myös toiminnalisuuden sen
     * jälkeen kun palikka pysähtyy.
     */
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

    /**
     * Lisää pysähtyneen pelipalikan palikka-olioina pysähtyneiden palikoiden
     * listaan.
     *
     * @param klooni Pelipalikan kopio mistä otetaan tiedot, joilla luodaan
     * pysähtyneet palikat.
     */
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
        if (pelipalikka.getMuoto().equals("KOLMIO")) {
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 40, pelipalikka.getY(), pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 20, pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("KOLMIO2")) {
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 20, pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 20, pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 40, pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("KOLMIO3")) {
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 20, pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 20, pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 40, pelipalikka.getY() - 20, pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("KOLMIO4")) {
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 20, pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 40, pelipalikka.getVari()));
            pysahtyneetPalikat.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 20, pelipalikka.getVari()));
        }
    }

    /**
     * Kun pysähtynyt palikka menee ylälaidan yli muutetaan gameOver-muuttujan
     * arvoa.
     */
    public void tuleekoGameOver() {
        for (Palikka kuollu : pysahtyneetPalikat) {
            if (kuollu.getY() < 0) {
                pelipalikka.setGameOver(true);
            }
        }

    }

    /**
     * Tarkistetaan saako vielä antaa palikalle vauhtia (speed up). Mikäli liian
     * lähellä pysähtynyttä palikkaa palauttaa arvon true.
     *
     * @return Palauttaa false jos saa keulia ja true jos ei.
     */
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

    /**
     * Tarkistaa osuuko alaspäin mentäessä lattiaan tai palikkaan.
     *
     * @return Palauttaa true jos osuu ja false jos ei osu.
     */
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

    /**
     * Tarkistaa osuuko palikkaan jos liikutaan vasemmalle.
     *
     * @return Palauttaa true jos osuu.
     */
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

    /**
     * Tarkistaa osuuko palikkaan jos liikutaan oikealle.
     *
     * @return Palauttaa true jos osuu.
     */
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

    /**
     * Luo värit ja lisää ne värilistaan.
     */
    public void luoVarit() {
        varit.add(Color.red);
        varit.add(Color.BLUE);
        varit.add(Color.CYAN);
        varit.add(Color.GREEN);
        varit.add(Color.YELLOW);
        varit.add(Color.lightGray);
        varit.add(Color.ORANGE);
        varit.add(Color.PINK);
        varit.add(Color.magenta);
    }

    /**
     * Palauttaa listan palikoista, jotka muodostavat pelipalikan sen hetkisen
     * alueen. Käytetään pysähtyneiden palikoiden luomiseen.
     *
     * @return
     */
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
        if (pelipalikka.getMuoto().equals("KOLMIO")) {
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 40, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 20, pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("KOLMIO2")) {
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 20, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 20, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 40, pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("KOLMIO3")) {
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 20, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 20, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 40, pelipalikka.getY() - 20, pelipalikka.getVari()));
        }
        if (pelipalikka.getMuoto().equals("KOLMIO4")) {
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 20, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() - 40, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY() - 20, pelipalikka.getVari()));
        }

        return lista;
    }

    /**
     * Poistaa täydet rivit ruudulta ja antaa pisteytä sen mukaan.
     */
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
        if (pisteytys > 3) {
            score += 400 * pisteytys;
        }
        if (pisteytys == 3) {
            score += 300 * pisteytys;
        }
        if (pisteytys == 2) {
            score += 200 * pisteytys;
        }
        if (pisteytys == 1) {
            score += 100 * pisteytys;
        }

        pelipalikka.setScore(score);
    }

    /**
     * Siirtää tiettyjä pysähtyneitä(kuolleita) palikoita alaspäin palikoiden
     * poistuessa niiden alta.
     *
     * @param mistaYlospain Poistettavan rivin rivinumero minkä yläpuolelta
     * palikoita liikutetaan.
     */
    public void siirraKuolleitaPalikoita(int mistaYlospain) {
        for (Palikka palikka : pysahtyneetPalikat) {
            if (palikka.getY() <= mistaYlospain) {
                palikka.setY(palikka.getY() + 20);
            }
        }
    }

    /**
     * Arpoo seuraavan värin.
     */
    public void seuraavaVari() {
        Random r = new Random();
        seuraavaVari = varit.get(r.nextInt(9));
    }

    /**
     * Arpoo seuraavan muodon.
     */
    public void seuraavaMuoto() {
        Random r = new Random();
        int luku = r.nextInt(4);
        if (luku == 2) {
            seuraavaMuoto = "NELIO";
        }
        if (luku == 1) {
            seuraavaMuoto = "L";
        }
        if (luku == 0) {
            seuraavaMuoto = "SUORA";
        }
        if (luku == 3) {
            seuraavaMuoto = "KOLMIO";
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
