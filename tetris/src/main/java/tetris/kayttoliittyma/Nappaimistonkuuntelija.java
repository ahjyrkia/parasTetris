package tetris.kayttoliittyma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import tetris.maailma.Maailma;
import tetris.maailma.Pelipalikka;
import tetris.paaohjelma.Main;
import tetris.pelimoottori.Pelimoottori;
/*
 * Liikkumisen toiminnallistemisen toteuttava luokka.
 */

public class Nappaimistonkuuntelija implements KeyListener {

    private Pelipalikka pelipalikka;
    private JFrame frame;
    private Maailma maailma;

    public Nappaimistonkuuntelija(Pelipalikka pelihahmo, JFrame frame, Maailma maailma) {
        this.pelipalikka = pelihahmo;
        this.frame = frame;
        this.maailma = maailma;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pelipalikka.liikeKoordinaattienMuutos("right");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pelipalikka.liikeKoordinaattienMuutos("left");
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pelipalikka.kaannaPalikkaa();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pelipalikka.liikeKoordinaattienMuutos("down");
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            pelipalikka.pause();
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            frame.dispose();
            Maailma pelimaailma = new Maailma();
            Kayttoliittyma kayttoliittyma = new Kayttoliittyma(pelimaailma);
            SwingUtilities.invokeLater(kayttoliittyma);
            Pelimoottori moottori = new Pelimoottori(kayttoliittyma, pelimaailma);
            moottori.start();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
