package tetris.kayttoliittyma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.maailma.Pelipalikka;
/*
 * Liikkumisen toiminnallistemisen toteuttava luokka.
 */

public class Nappaimistonkuuntelija implements KeyListener {

    private Pelipalikka pelipalikka;

    public Nappaimistonkuuntelija(Pelipalikka pelihahmo) {
        this.pelipalikka = pelihahmo;
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

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
