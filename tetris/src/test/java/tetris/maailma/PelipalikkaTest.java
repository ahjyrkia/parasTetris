package tetris.maailma;


import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.maailma.Pelipalikka;

/**
 *
 * @author ahjyrkia
 */
public class PelipalikkaTest {

    public PelipalikkaTest() {
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
    public void liikkuuOikeinOikealle() {
        Pelipalikka palikka = new Pelipalikka(50, 50, "L", Color.GREEN);
        palikka.liikeKoordinaattienMuutos("right");
        palikka.liiku(false, false, true, true);
        assertEquals(70, palikka.getX());
    }

    @Test
    public void liikkuuOikeinVasemmalle() {
        Pelipalikka palikka = new Pelipalikka(50, 50, "L", Color.GREEN);
        palikka.liikeKoordinaattienMuutos("left");
        palikka.liiku(false, false, true, true);
        assertEquals(30, palikka.getX());
    }

    @Test
    public void liikkuuOikeinAlas() {
        Pelipalikka palikka = new Pelipalikka(50, 50, "L", Color.GREEN);
        palikka.liikeKoordinaattienMuutos("down");
        palikka.liiku(false, false, false, false);
        assertEquals(71, palikka.getY());
    }

    @Test
    public void palikkaPyoriiL() {
        Pelipalikka palikka = new Pelipalikka(50, 50, "L", Color.GREEN);
        palikka.kaannaPalikkaa();
        assertEquals("L2", palikka.getMuoto());
    }

    @Test
    public void palikkaPyoriiL2() {
        Pelipalikka palikka = new Pelipalikka(50, 50, "L2", Color.GREEN);
        palikka.kaannaPalikkaa();
        assertEquals("L3", palikka.getMuoto());
    }

    @Test
    public void palikkaPyoriiL3() {
        Pelipalikka palikka = new Pelipalikka(50, 50, "L3", Color.GREEN);
        palikka.kaannaPalikkaa();
        assertEquals("L4", palikka.getMuoto());
    }

    @Test
    public void palikkaPyoriiSUORA() {
        Pelipalikka palikka = new Pelipalikka(50, 50, "SUORA", Color.GREEN);
        palikka.kaannaPalikkaa();
        assertEquals("SUORA2", palikka.getMuoto());
    }

    @Test
    public void palikkaPyoriiSUORA2() {
        Pelipalikka palikka = new Pelipalikka(50, 50, "SUORA2", Color.GREEN);
        palikka.kaannaPalikkaa();
        palikka.kaannaPalikkaa();
        assertEquals("SUORA2", palikka.getMuoto());
    }

    @Test
    public void eiMeneVasemmastaSeinastaLapi() {
        Pelipalikka palikka = new Pelipalikka(10, 50, "L", Color.GREEN);
        palikka.liikeKoordinaattienMuutos("left");
        palikka.liiku(false, false, false, false);
        assertEquals(0, palikka.getX());
    }

    @Test
    public void eiMeneVielakaanVasemmastaSeinastaLapi() {
        Pelipalikka palikka = new Pelipalikka(10, 50, "L", Color.GREEN);
        palikka.liikeKoordinaattienMuutos("left");
        palikka.liiku(false, true, true, true);
        palikka.liikeKoordinaattienMuutos("left");
        palikka.liiku(false, true, true, true);
        assertEquals(0, palikka.getX());
    }

    @Test
    public void eiMeneOikeastaSeinastaLapi() {
        Pelipalikka palikka = new Pelipalikka(360, 50, "L", Color.GREEN);
        palikka.liikeKoordinaattienMuutos("right");
        palikka.liiku(false, false, true, true);
        palikka.liikeKoordinaattienMuutos("right");
        palikka.liiku(false, false, true, true);
        assertEquals(360, palikka.getX());
    }

    @Test
    public void eiMeneLattiastaLapi() {
        Pelipalikka palikka = new Pelipalikka(50, 450, "L", Color.GREEN);
        palikka.liikeKoordinaattienMuutos("down");
        palikka.liikeKoordinaattienMuutos("down");

        boolean b = false;
        if (palikka.getY() < 500) {
            b = true;
        }
        assertTrue(b);
    }
}
