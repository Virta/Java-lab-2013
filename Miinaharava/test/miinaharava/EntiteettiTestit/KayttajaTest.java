/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.EntiteettiTestit;

import miinaharava.Entiteetit.Kayttaja;
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
}