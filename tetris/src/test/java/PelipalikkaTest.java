
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
        palikka.liiku();
        assertEquals(70, palikka.getX());
    }

    @Test
    public void liikkuuOikeinVasemmalle() {
        Pelipalikka palikka = new Pelipalikka(50, 50, "L", Color.GREEN);
        palikka.liikeKoordinaattienMuutos("left");
        palikka.liiku();
        assertEquals(30, palikka.getX());
    }

    @Test
    public void liikkuuOikeinAlas() {
        Pelipalikka palikka = new Pelipalikka(50, 50, "L", Color.GREEN);
        palikka.liikeKoordinaattienMuutos("down");
        palikka.liiku();
        assertEquals(71, palikka.getY());
    }

    @Test
    public void liikkuuOikeinYlos() {
        Pelipalikka palikka = new Pelipalikka(50, 50, "L", Color.GREEN);
        palikka.liikeKoordinaattienMuutos("up");
        palikka.liiku();
        assertEquals(31, palikka.getY());
    }

    @Test
    public void eiMeneVasemmastaSeinastaLapi() {
        Pelipalikka palikka = new Pelipalikka(10, 50, "L", Color.GREEN);
        palikka.liikeKoordinaattienMuutos("left");
        palikka.liiku();
        assertEquals(0, palikka.getX());
    }

    @Test
    public void eiMeneVielakaanVasemmastaSeinastaLapi() {
        Pelipalikka palikka = new Pelipalikka(10, 50, "L", Color.GREEN);
        palikka.liikeKoordinaattienMuutos("left");
        palikka.liiku();
        palikka.liikeKoordinaattienMuutos("left");
        palikka.liiku();
        assertEquals(0, palikka.getX());
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
