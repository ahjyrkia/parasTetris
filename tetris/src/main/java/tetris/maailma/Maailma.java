package tetris.maailma;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Maailma {

    private int nopeus;

    private ArrayList<Pelipalikka> pelipalikat;
    private Pelipalikka pelipalikka;
    private ArrayList<Color> varit;

    public Maailma() {
        this.nopeus = 1;
        this.pelipalikka = new Pelipalikka(200, 100, "L", Color.GREEN);
        this.pelipalikat = new ArrayList<Pelipalikka>();
        varit = new ArrayList<Color>();
        luoVarit();

        int kierros = 0;

    }

    public void liikuta() {
        if (pelipalikka.osuukoLattiaan()) {
            Pelipalikka klooni = new Pelipalikka(pelipalikka.getX(), pelipalikka.getY(), pelipalikka.getMuoto(), pelipalikka.getVari());
            pelipalikat.add(klooni);
            pelipalikka.setY(-50);
            pelipalikka.setX(200);
            Random r = new Random();
            pelipalikka.setVari(varit.get(r.nextInt(5)));
            System.out.println("meni tänne");
        }
        pelipalikka.liiku();
    }

    public void luoVarit() {
        varit.add(Color.red);
        varit.add(Color.BLUE);
        varit.add(Color.CYAN);
        varit.add(Color.GREEN);
        varit.add(Color.YELLOW);
        varit.add(Color.WHITE);
    }

    public Pelipalikka getPelipalikka() {
        return pelipalikka;
    }

    public ArrayList<Pelipalikka> getPelipalikat() {
        return pelipalikat;
    }

}
