/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.EntiteettiTestit;

import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import miinaharava.Entiteetit.VakioProfiilit;
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
public class TulosTest {
    
    Tulos tulos;
    Kayttaja pelaaja;
    KenttaProfiili profiili;
    VakioProfiilit vakio;
    
    public TulosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pelaaja = new Kayttaja("Frans");
        profiili = new KenttaProfiili("TestiProfiili", 10, 10);
        vakio = new VakioProfiilit();
        tulos = new Tulos("1:10", profiili, pelaaja, true);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testaaPelaaja(){
        assertEquals("Frans", tulos.getPelaaja().getNimimerkki());
    }
    
    @Test
    public void testaaKenttaProfiili(){
        assertEquals("TestiProfiili", tulos.getProfiili().getNimi());
    }
    
    @Test
    public void testaaVakioKenttaProfiili(){
        tulos = new Tulos("1:10", vakio.getHelppo(), pelaaja, true);
        assertEquals("Helppo", tulos.getProfiili().getNimi());
    }
    
    @Test
    public void testaaTuloksenAika(){
        assertEquals("1:10", tulos.getAika());
    }
    
    @Test
    public void testaaEpaonnistunutTulos(){
        tulos = new Tulos("1:11", profiili, pelaaja, false);
        assertEquals(false, tulos.onnistuiko());
    }
    
    @Test
    public void testaaOnnistunutTulos(){
        tulos = new Tulos("1:11", profiili, pelaaja, true);
        assertEquals(true, tulos.onnistuiko());
    }
    
}