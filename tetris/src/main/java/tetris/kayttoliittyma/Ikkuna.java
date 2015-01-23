package tetris.kayttoliittyma;

import tetris.maailma.Maailma;
import tetris.maailma.Pelipalikka;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import tetris.maailma.Palikka;

public class Ikkuna extends JPanel {

    private ArrayList<Palikka> palikat;
    private Pelipalikka pelipalikka;

    public Ikkuna(Maailma maailma) {
        super.setBackground(Color.BLACK);
        this.palikat = new ArrayList<Palikka>(maailma.getPalikat());
        this.pelipalikka = maailma.getPelipalikka();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //tehään silleen et palat luodaan aina samankokosista neliöistä. hitboxien
        //tarkkailu helpottuu huomattavasti. 
        
        String muoto = pelipalikka.getMuoto();
        if (muoto.equals("L")) {
            g.setColor(Color.cyan);
            
        }
        g.setColor(Color.cyan);
        g.fillRect(pelipalikka.getX(), pelipalikka.getY(), 15, 50);
        g.fillRect(pelipalikka.getX(), pelipalikka.getY(), 35, 15);

    }
}
