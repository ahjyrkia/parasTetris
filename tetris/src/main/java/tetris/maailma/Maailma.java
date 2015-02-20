package tetris.maailma;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
/* 
 * Paljo pelin toiminnallisuutta on ängetty tänne (turhankin paljon).
 * 
 */

public class Maailma {

    private ArrayList<Palikka> pysahtyneetPalikat;
    ArrayList<Palikka> liikkuvatPalikat = new ArrayList<Palikka>();
    private Pelipalikka pelipalikka;
    private ArrayList<Color> varit;
    private int palikanNitkutus = 100;

    public Maailma() {
        this.pelipalikka = new Pelipalikka(200, -50, "L", Color.GREEN);
        this.pysahtyneetPalikat = new ArrayList<Palikka>();
        varit = new ArrayList<Color>();
        luoVarit();

        int kierros = 0;

    }

    public void liikuta() {
        liikkuvatPalikat = new ArrayList<Palikka>(kutsuPelipalikat());
        if (osuukoKuolleenPaalleTaiLattiaan()) {
            palikanNitkutus--;
            if (palikanNitkutus == 0) {
                Pelipalikka klooni = new Pelipalikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getMuoto(), pelipalikka.getVari());
                lisaaPelipalikkaPalikoihin(klooni);
                poistaTaydetRivit();
                setMuoto();
                pelipalikka.setY(-50);
                pelipalikka.setX(200);
                Random r = new Random();
                pelipalikka.setVari(varit.get(r.nextInt(7)));
                palikanNitkutus = 100;
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
    }

    public ArrayList kutsuPelipalikat() {
        ArrayList<Palikka> lista = new ArrayList<Palikka>();
        if (pelipalikka.getMuoto().equals("L")) {
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX() + 20, pelipalikka.getY(), pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() + 20, pelipalikka.getVari()));
            lista.add(new Palikka(pelipalikka.getX(), pelipalikka.getY() + 40, pelipalikka.getVari()));
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
        return lista;
    }

    public void poistaTaydetRivit() {
        int alinRivi = 0;
        ArrayList<Integer> indeksit = new ArrayList<Integer>();
        for (Palikka kuollu : pysahtyneetPalikat) {
            if (kuollu.getY() == 480) {
                alinRivi++;
                indeksit.add(pysahtyneetPalikat.indexOf(kuollu));
                System.out.println(pysahtyneetPalikat.indexOf(kuollu));
            }
        }
        
        if (alinRivi > 20) {
            for (Integer indeksit1 : indeksit) {
                
            }
        }
    }

    public void setMuoto() {
        Random r = new Random();
        int luku = r.nextInt(3);
        if (luku == 2) {
            pelipalikka.setMuoto("NELIO");
        }
        if (luku == 1) {
            pelipalikka.setMuoto("L");
        }
        if (luku == 0) {
            pelipalikka.setMuoto("SUORA");
        }
    }

    public Pelipalikka getPelipalikka() {
        return pelipalikka;
    }

    public ArrayList<Palikka> getPysahtyneetPalikat() {
        return pysahtyneetPalikat;
    }

}
