/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.maailma;

import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ahjyrkia
 */
public class PalikkaTest {
    
    public PalikkaTest() {
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
    public void kaikkiToimiiKunPalikkaLuodaan() {
        Palikka palikka = new Palikka(10,10,Color.red);
        assertEquals(palikka.getX(), 10);
        assertEquals(palikka.getY(), 10);
        assertEquals(palikka.getVari(), Color.red);
    }
}
