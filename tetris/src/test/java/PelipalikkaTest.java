
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
        Pelipalikka palikka = new Pelipalikka(50, 50);
        palikka.liikeKoordinaattienMuutos("right");
        palikka.liiku();
        assertEquals(70, palikka.getX());
    }

    @Test
    public void liikkuuOikeinVasemmalle() {
        Pelipalikka palikka = new Pelipalikka(50, 50);
        palikka.liikeKoordinaattienMuutos("left");
        palikka.liiku();
        assertEquals(30, palikka.getX());
    }

    @Test
    public void liikkuuOikeinAlas() {
        Pelipalikka palikka = new Pelipalikka(50, 50);
        palikka.liikeKoordinaattienMuutos("down");
        palikka.liiku();
        assertEquals(70, palikka.getY());
    }

    @Test
    public void liikkuuOikeinYlos() {
        Pelipalikka palikka = new Pelipalikka(50, 50);
        palikka.liikeKoordinaattienMuutos("up");
        palikka.liiku();
        assertEquals(30, palikka.getY());
    }

    @Test
    public void eiMeneVasemmastaSeinastaLapi() {
        Pelipalikka palikka = new Pelipalikka(10, 50);
        palikka.liikeKoordinaattienMuutos("left");
        palikka.liiku();
        assertEquals(0, palikka.getX());
    }

    @Test
    public void eiMeneVielakaanVasemmastaSeinastaLapi() {
        Pelipalikka palikka = new Pelipalikka(10, 50);
        palikka.liikeKoordinaattienMuutos("left");
        palikka.liiku();
        palikka.liikeKoordinaattienMuutos("left");
        palikka.liiku();
        assertEquals(0, palikka.getX());
    }

}
