/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.EntiteettiTestit;

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
public class VakioProfiiliTest {
    
    VakioProfiilit vakiot;
    
    public VakioProfiiliTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        vakiot = new VakioProfiilit();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void onHelppo(){
        assertEquals("Helppo", vakiot.getHelppo().getNimi());
        assertEquals(15, vakiot.getHelppo().getKoko());
        assertEquals(20, vakiot.getHelppo().getMiinoja());
    }
    
    @Test
    public void onKeskiVaikea(){
        assertEquals("Keskivaikea", vakiot.getKeskiVaikea().getNimi());
        assertEquals(20, vakiot.getKeskiVaikea().getKoko());
        assertEquals(40, vakiot.getKeskiVaikea().getMiinoja());
    }
    
    @Test
    public void onVaikea(){
        assertEquals("Helppo", vakiot.getHelppo().getNimi());
        assertEquals(25, vakiot.getVaikea().getKoko());
        assertEquals(100, vakiot.getVaikea().getMiinoja());
    }
}