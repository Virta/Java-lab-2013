/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Pelikentta;

import miinaharava.Pelikentta.Solu;
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
public class SoluTest {
    
    Solu solu;
    
    public SoluTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        solu = new Solu();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void soluOlemassa(){
        assertNotNull(solu);
    }
    
    @Test
    public void onMiinaAsetusToimii(){
        solu.setMiina();
        assertEquals(true, solu.isMiina());
    }
    
    @Test
    public void miinanLiputToimiiLiputon(){
        assertEquals(0, solu.getFlagi());
    }
    
    @Test
    public void miinanLiputToimiiMerkittyMiinaksi(){
        solu.setFlagit();
        assertEquals(1, solu.getFlagi());
    }
    
    @Test
    public void miinanLiputToimiiMerkittyTuntemattomaksi(){
        solu.setFlagit();
        solu.setFlagit();
        assertEquals(2, solu.getFlagi());
    }
    
    @Test
    public void miinanLiputToimiiTakaisinLiputtomaksi(){
        solu.setFlagit();
        solu.setFlagit();
        solu.setFlagit();
        assertEquals(0, solu.getFlagi());
    }
    
    @Test
    public void viereisetMiinatMerkitty(){
        assertEquals(0, solu.getVieressaMiinoja());
    }
    
    @Test
    public void viereistenLisaaminenOikein(){
        solu.lisaaViereenMiinoja();
        assertEquals(1, solu.getVieressaMiinoja());
        solu.lisaaViereenMiinoja();
        assertEquals(2, solu.getVieressaMiinoja());
    }
    
    @Test
    public void solunAukaisuToimii(){
        assertEquals(false, solu.isAuki());
        solu.setAuki();
        assertEquals(true, solu.isAuki());
    }
    
    @Test
    public void solunAsettaminenMiinaksiToimii(){
        assertEquals(false, solu.isMiina());
        solu.setMiina();
        assertEquals(true, solu.isMiina());
    }
    
    @Test
    public void solullaVieressaUseitaSoluja(){
        Solu s = new Solu();
        solu.lisaaVierusSolu(solu);
        Solu d = new Solu();
        solu.lisaaVierusSolu(solu);
        Solu f = new Solu();
        solu.lisaaVierusSolu(solu);
        Solu g = new Solu();
        solu.lisaaVierusSolu(solu);
        assertEquals(4, solu.getVierukset().size());
    }
    
    @Test
    public void solullaVieressaUseitaSoluja2(){
        Solu s = new Solu();
        solu.lisaaVierusSolu(solu);
        Solu d = new Solu();
        solu.lisaaVierusSolu(solu);
        Solu f = new Solu();
        solu.lisaaVierusSolu(solu);
        assertEquals(3, solu.getVierukset().size());
    }
    
}