/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Entiteetti;

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
public class KenttaProfiiliTest {
    
    KenttaProfiili profiili;
    
    public KenttaProfiiliTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        profiili = new KenttaProfiili("Testi", 3, 6);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void profiilinNimiOikein(){
        assertEquals("Testi", profiili.getNimi());
    }
    
    @Test
    public void profiilinNimiAsetusOikein(){
        profiili.setNimi("Toinen");
        assertEquals("Toinen", profiili.getNimi());
    }
    
    @Test
    public void profiilinKokoOikein(){
        assertEquals(3, profiili.getKoko());
    }
    
    @Test
    public void profiilinMiinatOikein(){
        assertEquals(6, profiili.getMiinoja());
    }
    
}