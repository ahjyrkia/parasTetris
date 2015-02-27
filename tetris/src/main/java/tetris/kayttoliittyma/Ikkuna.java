package tetris.kayttoliittyma;

import tetris.maailma.Maailma;
import tetris.maailma.Pelipalikka;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import tetris.maailma.Palikka;

/*
 * Graafisen puolen piirtäminen tapahtuu täällä.
 */
public class Ikkuna extends JPanel {

    private ArrayList<Palikka> pysahtyneetPalikat;
    private Pelipalikka pelipalikka;
    private int tiedostonkirjoitusapumuuttuja = 0;

    public Ikkuna(Maailma maailma) {
        super.setBackground(Color.BLACK);
        this.pysahtyneetPalikat = new ArrayList<Palikka>(maailma.getPysahtyneetPalikat());
        this.pelipalikka = maailma.getPelipalikka();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        pelipalikka.kyltinVilkkumisMuuttuja();
        if (pelipalikka.kyltinVari() == 0) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.red);
        }
        g.drawString("parasTetris", 430, 60);
        g.setColor(Color.white);
        String score = "score: " + pelipalikka.getScore();
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString(score, 415, 130);
        g.drawString("next:", 415, 200);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        g.drawString("P = pause", 411, 270);
        g.drawString("Q = quit", 411, 285);
        g.drawString("R = restart", 411, 300);
        g.drawString("left/right arrow = move", 411, 315);
        g.drawString("up arrow = rotate", 411, 330);
        g.drawString("down arrow = speed up", 411, 345);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.red);
        g.drawString("top5", 415, 397);

        g.setColor(Color.blue);
        g.fillRect(400, 0, 200, 5);
        g.fillRect(400, 100, 200, 5);
        g.fillRect(400, 495, 200, 5);
        g.fillRect(595, 0, 5, 600);
        g.fillRect(400, 0, 5, 600);
        piirraSeuraavaPalikka(g, pelipalikka.getSeuraavaMuoto(), pelipalikka.getSeuraavaVari());
        for (Palikka palikka : pysahtyneetPalikat) {
            g.setColor(palikka.getVari());
            g.fillRect(palikka.getX(), palikka.getY(), 20, 20);
        }
        if (pelipalikka.getMuoto().equals("L")) {
            piirraL(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());
        }
        if (pelipalikka.getMuoto().equals("L2")) {
            piirraL2(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());
        }
        if (pelipalikka.getMuoto().equals("L3")) {
            piirraL3(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());
        }
        if (pelipalikka.getMuoto().equals("L4")) {
            piirraL4(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());

        }

        if (pelipalikka.getMuoto().equals("NELIO")) {
            piirraNELIO(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());

        }
        if (pelipalikka.getMuoto().equals("SUORA")) {
            piirraSUORA(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());
        }
        if (pelipalikka.getMuoto().equals("SUORA2")) {
            piirraSUORA2(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());
        }
        if (pelipalikka.getMuoto().equals("KOLMIO")) {
            piirraKOLMIO(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());
        }
        if (pelipalikka.getMuoto().equals("KOLMIO2")) {
            piirraKOLMIO2(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());
        }
        if (pelipalikka.getMuoto().equals("KOLMIO3")) {
            piirraKOLMIO3(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());
        }
        if (pelipalikka.getMuoto().equals("KOLMIO4")) {
            piirraKOLMIO4(g, pelipalikka.getVari(), pelipalikka.getX(), pelipalikka.getY());
        }
        if (pelipalikka.getGameover()) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g.setColor(Color.red);
            g.drawString("GAME OVER", 45, 200);
            g.setColor(Color.white);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
            g.drawString(score, 110, 250);
            if (tiedostonkirjoitusapumuuttuja == 0) {
                scorenTalletus();
            }

        }
        if (pelipalikka.getPause()) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.setColor(Color.white);
            g.drawString("PRESS P TO CONTINUE ", 40, 200);

        }

        if (new File("scoret.txt").isFile()) {
            try {
                FileReader lukija = new FileReader("scoret.txt");
                Scanner sc = new Scanner(lukija);
                ArrayList<Integer> scoret = new ArrayList<Integer>();
                while (sc.hasNextLine()) {
                    scoret.add(Integer.parseInt(sc.nextLine()));
                }
                int y = 420;
                Collections.sort(scoret);
                Collections.reverse(scoret);

                g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                g.setColor(Color.white);
                int top5 = scoret.size();
                if (scoret.size() > 4) {
                    top5 = 5;
                }
                int sija = 1;
                for (int i = 0; i < top5; i++) {
                    g.drawString(sija+".   " + scoret.get(i), 415, y);
                    y += 15;
                    sija++;

                }

                sc.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ikkuna.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Päivittää pysähtyneiden palikoiden listan.
     *
     * @param palikat
     */
    public void paivitaPalikat(ArrayList palikat) {
        pysahtyneetPalikat = palikat;
    }

    /**
     * Piirtää SUORA-muotoisen palikan. Parametreina tulee väri ja koordinaatit.
     * Sama logiikka muissa palikoiden piirtämis-metodeissa.
     *
     * @param g
     * @param vari
     * @param x
     * @param y
     */
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

    public void piirraL2(Graphics g, Color vari, int x, int y) {
        g.setColor(vari);
        g.fillRect(x, y, 20, 20);
        g.fillRect(x + 20, y, 20, 20);
        g.fillRect(x + 40, y, 20, 20);
        g.fillRect(x, y - 20, 20, 20);
    }

    private void piirraL3(Graphics g, Color vari, int x, int y) {
        g.setColor(vari);
        g.fillRect(x + 20, y, 20, 20);
        g.fillRect(x + 20, y - 20, 20, 20);
        g.fillRect(x + 20, y - 40, 20, 20);
        g.fillRect(x, y, 20, 20);
    }

    private void piirraL4(Graphics g, Color vari, int x, int y) {
        g.setColor(vari);
        g.fillRect(x + 40, y, 20, 20);
        g.fillRect(x + 40, y - 20, 20, 20);
        g.fillRect(x + 20, y - 20, 20, 20);
        g.fillRect(x, y - 20, 20, 20);
    }

    private void piirraSUORA2(Graphics g, Color vari, int x, int y) {
        g.setColor(vari);
        g.fillRect(x, y, 20, 20);
        g.fillRect(x, y - 20, 20, 20);
        g.fillRect(x, y - 40, 20, 20);
        g.fillRect(x, y - 60, 20, 20);
    }

    private void piirraKOLMIO(Graphics g, Color vari, int x, int y) {
        g.setColor(vari);
        g.fillRect(x, y, 20, 20);
        g.fillRect(x + 20, y, 20, 20);
        g.fillRect(x + 40, y, 20, 20);
        g.fillRect(x + 20, y - 20, 20, 20);
    }

    private void piirraKOLMIO2(Graphics g, Color vari, int x, int y) {
        g.setColor(vari);
        g.fillRect(x, y - 20, 20, 20);
        g.fillRect(x + 20, y, 20, 20);
        g.fillRect(x + 20, y - 20, 20, 20);
        g.fillRect(x + 20, y - 40, 20, 20);
    }

    private void piirraKOLMIO3(Graphics g, Color vari, int x, int y) {
        g.setColor(vari);
        g.fillRect(x, y - 20, 20, 20);
        g.fillRect(x + 20, y, 20, 20);
        g.fillRect(x + 20, y - 20, 20, 20);
        g.fillRect(x + 40, y - 20, 20, 20);
    }

    private void piirraKOLMIO4(Graphics g, Color vari, int x, int y) {
        g.setColor(vari);
        g.fillRect(x, y, 20, 20);
        g.fillRect(x, y - 20, 20, 20);
        g.fillRect(x, y - 40, 20, 20);
        g.fillRect(x + 20, y - 20, 20, 20);
    }

    /**
     * Piirtää ruudun info-puolelle seuraavan palikan "next:" tekstin viereen.
     *
     * @param g
     * @param muoto
     * @param vari
     */
    private void piirraSeuraavaPalikka(Graphics g, String muoto, Color vari) {
        if (muoto.equals("SUORA")) {
            piirraSUORA(g, vari, 500, 185);
        }
        if (muoto.equals("L")) {
            piirraL(g, vari, 500, 185);
        }
        if (muoto.equals("NELIO")) {
            piirraNELIO(g, vari, 500, 185);
        }
        if (muoto.equals("KOLMIO")) {
            piirraKOLMIO(g, vari, 500, 185);
        }
    }

    /**
     * Tallettaa scoret tiedostoon.
     */
    private void scorenTalletus() {

        try {
            tiedostonkirjoitusapumuuttuja++;
            String filename = "scoret.txt";
            FileWriter fw = new FileWriter(filename, true);
            fw.write(pelipalikka.getScore() + "\n");
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(Ikkuna.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
