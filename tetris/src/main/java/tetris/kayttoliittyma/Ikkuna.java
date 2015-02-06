package tetris.kayttoliittyma;

import tetris.maailma.Maailma;
import tetris.maailma.Pelipalikka;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import tetris.maailma.Palikka;

public class Ikkuna extends JPanel {

    private ArrayList<Pelipalikka> peliPalikat;
    private Pelipalikka pelipalikka;

    public Ikkuna(Maailma maailma) {
        super.setBackground(Color.BLACK);
        this.peliPalikat = new ArrayList<Pelipalikka>(maailma.getPelipalikat());
        this.pelipalikka = maailma.getPelipalikka();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Pelipalikka palikka : peliPalikat) {
            if (palikka.getMuoto().equals("L")) {
                piirraL(g, palikka.getVari(), palikka.getX(), palikka.getY());
            }
        }
        if (pelipalikka.getMuoto().equals("L")) {
            piirraL(g, pelipalikka.getVari(),pelipalikka.getX(), pelipalikka.getY());
        }

    }

    public void paivitaPalikat(ArrayList palikat) {
        peliPalikat = palikat;
    }

    public void piirraL(Graphics g, Color vari, int x, int y) {
        g.setColor(vari);
        g.fillRect(x, y, 20, 20);
        g.fillRect(x + 20, y, 20, 20);
        g.fillRect(x, y + 20, 20, 20);
        g.fillRect(x, y + 40, 20, 20);
    }
}
