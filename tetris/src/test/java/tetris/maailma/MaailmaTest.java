package tetris.maailma;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.maailma.Maailma;
import tetris.maailma.Pelipalikka;

/**
 *
 * @author ahjyrkia
 */
public class MaailmaTest {

    public MaailmaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void aloitusPelipalikkaOnOlemassa() {
        Maailma maailma = new Maailma();
        Pelipalikka pelipalikka = new Pelipalikka(200, -50, "L", Color.GREEN);
        assertEquals(pelipalikka.getMuoto(), maailma.getPelipalikka().getMuoto());
    }

    @Test
    public void aloitusPelipalikkaOikeassaPaikassa() {
        Maailma maailma = new Maailma();
        assertEquals(200, maailma.getPelipalikka().getX());
        assertEquals(-50, maailma.getPelipalikka().getY());
    }

    @Test
    public void liikutaMetodiToimii() {
        Maailma maailma = new Maailma();
        maailma.liikuta();
        assertEquals(-49, maailma.getPelipalikka().getY());
    }

    @Test
    public void liikutaMetodiToimiiAlasAsti() {
        Maailma maailma = new Maailma();
        for (int i = 0; i < 449; i++) {
            maailma.liikuta();
        }
        assertEquals(399, maailma.getPelipalikka().getY());
    }

    @Test
    public void pelipalikkaSiirtyyAloitusPaikkaanKunOsuuLattiaan() {
        Maailma maailma = new Maailma();
        for (int i = 0; i < 590; i++) {
            maailma.liikuta();
        }
        assertEquals(-50, maailma.getPelipalikka().getY());
    }

    @Test
    public void odottaaNitkutusAjanOsuessaanLattiaan() {
        Maailma maailma = new Maailma();
        for (int i = 0; i < 450; i++) {
            maailma.liikuta();
        }
        assertEquals(400, maailma.getPelipalikka().getY());
    }

    @Test
    public void voiLiikuttaaVielaPysahtyneena() {
        Maailma maailma = new Maailma();
        for (int i = 0; i < 450; i++) {
            maailma.liikuta();
        }
        maailma.getPelipalikka().liikeKoordinaattienMuutos("left");
        maailma.getPelipalikka().liiku(false, false, true, true);
        assertEquals(400, maailma.getPelipalikka().getY());
        assertEquals(180, maailma.getPelipalikka().getX());
    }
}
