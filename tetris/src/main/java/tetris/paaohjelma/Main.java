

package tetris.paaohjelma;

import javax.swing.SwingUtilities;
import tetris.kayttoliittyma.Kayttoliittyma;
import tetris.maailma.Maailma;
import tetris.pelimoottori.Pelimoottori;


public class Main {


    public static void main(String[] args) {
        Maailma pelimaailma = new Maailma();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(pelimaailma);
 
        SwingUtilities.invokeLater(kayttoliittyma);
 
        Pelimoottori moottori = new Pelimoottori(kayttoliittyma, pelimaailma);
        moottori.start();

    }
}
