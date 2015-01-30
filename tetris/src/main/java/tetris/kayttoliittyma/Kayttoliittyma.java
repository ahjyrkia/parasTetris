package tetris.kayttoliittyma;


import tetris.maailma.Maailma;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
 
public class Kayttoliittyma implements Runnable {
 
    private JFrame frame;
    private Maailma maailma;
    private Ikkuna ikkuna;
 
    public Kayttoliittyma(Maailma maailma) {
        this.maailma = maailma;
    }
 
    @Override
    public void run() {
        frame = new JFrame("parasTetris");
        frame.setPreferredSize(new Dimension(400, 500));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
 
        Nappaimistonkuuntelija kuuntelija = new Nappaimistonkuuntelija(maailma.getPelipalikka());
        frame.addKeyListener(kuuntelija);
         
        frame.pack();
        frame.setVisible(true);
    }
 
    public void luoKomponentit(Container container) {
        ikkuna = new Ikkuna(maailma);
        container.add(ikkuna);
    }
 
    public void piirra(ArrayList palikat) {
        if (ikkuna == null) {
            return;
        }
        ikkuna.paivitaPalikat(palikat);
        ikkuna.repaint();
    }
 
    public JFrame getFrame() {
        return frame;
    }
}