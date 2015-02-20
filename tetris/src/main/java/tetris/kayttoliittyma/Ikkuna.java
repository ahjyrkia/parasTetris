package tetris.kayttoliittyma;

import tetris.maailma.Maailma;
import tetris.maailma.Pelipalikka;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import tetris.maailma.Palikka;

/*
 * Graafisen puolen piirtäminen tapahtuu täällä.
 */
public class Ikkuna extends JPanel {

    private ArrayList<Palikka> pysahtyneetPalikat;
    private Pelipalikka pelipalikka;

    public Ikkuna(Maailma maailma) {
        super.setBackground(Color.BLACK);
        this.pysahtyneetPalikat = new ArrayList<Palikka>(maailma.getPysahtyneetPalikat());
        this.pelipalikka = maailma.getPelipalikka();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Palikka palikka : pysahtyneetPalikat) {
            g.setColor(palikka.getVari());
            g.fillRect(palikka.getX(), palikka.getY(), 20, 20);
        }
        if (pelipalikka.getMuoto().equals("L")) {
            piirraL(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());
        }
        if (pelipalikka.getMuoto().equals("NELIO")) {
            piirraNELIO(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());

        }
        if (pelipalikka.getMuoto().equals("SUORA")) {
            piirraSUORA(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());
        }


    }

    public void paivitaPalikat(ArrayList palikat) {
        pysahtyneetPalikat = palikat;
    }
    public void piirraSUORA(Graphics g, Color vari, int x, int y) {
        g.setColor(vari);
        g.fillRect(x, y, 20, 20);
        g.fillRect(x + 20, y, 20, 20);
        g.fillRect(x + 40, y, 20, 20);
        g.fillRect(x + 60, y, 20, 20);
    }

    public void piirraNELIO(Graphics g, Color vari, int x, int y) {
        g.setColor(vari);
        g.fillRect(x, y, 20, 20);
        g.fillRect(x + 20, y, 20, 20);
        g.fillRect(x, y + 20, 20, 20);
        g.fillRect(x + 20, y + 20, 20, 20);
    }

    public void piirraL(Graphics g, Color vari, int x, int y) {
        g.setColor(vari);
        g.fillRect(x, y, 20, 20);
        g.fillRect(x + 20, y, 20, 20);
        g.fillRect(x, y + 20, 20, 20);
        g.fillRect(x, y + 40, 20, 20);
    }
}
