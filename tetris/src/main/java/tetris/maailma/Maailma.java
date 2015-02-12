package tetris.maailma;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
/* 
 * Paljo pelin toiminnallisuutta on ängetty tänne (turhankin paljon).
 * 
 */
public class Maailma {

    private int nopeus;

    private ArrayList<Palikka> pysahtyneetPalikat;
    private Pelipalikka pelipalikka;
    private ArrayList<Color> varit;

    public Maailma() {
        this.nopeus = 1;
        this.pelipalikka = new Pelipalikka(200, 100, "L", Color.GREEN);
        this.pysahtyneetPalikat = new ArrayList<Palikka>();
        varit = new ArrayList<Color>();
        luoVarit();

        int kierros = 0;

    }

    public void liikuta() {
        if (pelipalikka.osuukoLattiaan() || osuukoPysahtyneenPalikanPaalle()) {
            Pelipalikka klooni = new Pelipalikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getMuoto(), pelipalikka.getVari());
            lisaaPelipalikkaPalikoihin(klooni);
            pelipalikka.setY(-50);
            pelipalikka.setX(200);
            Random r = new Random();
            pelipalikka.setVari(varit.get(r.nextInt(7)));
        }
        pelipalikka.liiku(osuukoJosLiikutaanVasemmalle());
    }

    public void lisaaPelipalikkaPalikoihin(Pelipalikka klooni) {
        String muoto = klooni.getMuoto();
        if (muoto.equals("L")) {
            pysahtyneetPalikat.add(new Palikka(klooni.getX(), klooni.getY(), klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX() + 20, klooni.getY(), klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX(), klooni.getY() + 20, klooni.getVari()));
            pysahtyneetPalikat.add(new Palikka(klooni.getX(), klooni.getY() + 40, klooni.getVari()));

        }
    }

    public boolean osuukoPysahtyneenPalikanPaalle() {
        for (Palikka palikka : pysahtyneetPalikat) {
            if (pelipalikka.getAlinKohta() - 40 == palikka.getY()) {
                if ((pelipalikka.getX() + 10 <= palikka.getX() + 10) && (pelipalikka.getX() + 20 >= palikka.getX())) {
                    return true;
                }
            }
            if (pelipalikka.getAlinKohta() == palikka.getY()) {
                if ((pelipalikka.getX() <= palikka.getX() + 10) && (pelipalikka.getX() + 10 >= palikka.getX())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean osuukoJosLiikutaanVasemmalle() {
        ArrayList<Palikka> lista = new ArrayList<Palikka>(kutsuPelipalikat());
        for (Palikka palikka : pysahtyneetPalikat) {
//            if (pelipalikka.getAlinKohta() >= palikka.getY() && pelipalikka.getY() <= palikka.getY()+10) {
//                if (pelipalikka.getX() < palikka.getX()+10) {
//                    pelipalikka.setX(palikka.getX()+20);
//                    System.out.println("törmäs");
//                    return true;
//                }
//            }
            for (Palikka listalta : lista) {
                if (pelipalikka.getAlinKohta() >= palikka.getY() && pelipalikka.getY() <= palikka.getY() + 10) {
                    if (listalta.getX() < palikka.getX() + 10) {
                        pelipalikka.setX(palikka.getX() + 20);
                        System.out.println("törmäs");
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
        return lista;
    }

    public Pelipalikka getPelipalikka() {
        return pelipalikka;
    }

    public ArrayList<Palikka> getPelipalikat() {
        return pysahtyneetPalikat;
    }

}
