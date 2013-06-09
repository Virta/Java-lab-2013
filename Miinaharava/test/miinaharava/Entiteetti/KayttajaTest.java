/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Entiteetti;

import java.util.HashMap;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author virta
 */
public class KayttajaTest {
    
    Kayttaja k;

    public KayttajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        k = new Kayttaja("Frans");
        KenttaProfiili testiProfiili = new KenttaProfiili("Testi", 10, 10);
        k.addProfiili(testiProfiili);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kayttajanNimiOikein() {
        assertEquals("Frans", k.getNimimerkki());
    }

    @Test
    public void kayttajanNimiAsetusOikein() {
        k.setNimimerkki("Kummajainen");
        assertEquals("Kummajainen", k.getNimimerkki());
    }
    
    @Test
    public void kayttajanKenttaProfiiliOlemassa(){
        KenttaProfiili profiili = k.getProfiili("Testi");
        assertEquals("Testi", profiili.getNimi());
        assertEquals(10, profiili.getKoko());
        assertEquals(10, profiili.getMiinoja());
    }
    
    @Test
    public void kayttajanKenttaProfiilitSaatavilla(){
        HashMap<String, KenttaProfiili> profiilit = k.getKaikkiProfiilit();
        assertNotNull("Ei saatu profiileja", profiilit);
    }
    
    @Test
    public void kayttajanKenttaProfiilitOikein(){
        KenttaProfiili uusiProfiili1 = new KenttaProfiili("uusi1", 20, 5);
        KenttaProfiili uusiProfiili2 = new KenttaProfiili("uusi2", 30, 10);
        k.addProfiili(uusiProfiili1);
        k.addProfiili(uusiProfiili2);
        
        HashMap<String, KenttaProfiili> profiilit = k.getKaikkiProfiilit();
        
        assertEquals(3, profiilit.size());
        assertNotNull("Ei saatu profiileja", profiilit);
        assertEquals("uusi1", k.getProfiili("uusi1").getNimi());
        assertEquals("uusi2", k.getProfiili("uusi2").getNimi());
        
    }
    
}