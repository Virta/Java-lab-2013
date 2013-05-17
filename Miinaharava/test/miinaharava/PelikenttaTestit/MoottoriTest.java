/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.PelikenttaTestit;

import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Pelikentta.Moottori;
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
public class MoottoriTest {

    private Moottori moottori;
    private KenttaProfiili profiili;

    public MoottoriTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        KenttaProfiili profiili = new KenttaProfiili("Testi", 10, 0);
        this.profiili = profiili;
        this.moottori = new Moottori(profiili);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void moottoriAukaiseeLuukut() {
        moottori.aukaise(0, 0);
        for (int i = 0; i < profiili.getKoko(); i++) {
            for (int k = 0; k < profiili.getKoko(); k++) {
                assertEquals(true, moottori.getKentta().getSolu(i, k).isAuki());
            }
        }
    }

    @Test
    public void moottoriPalauttaaOikeanArvonMiinanAukaisusta() {
        KenttaProfiili miinaProfiili = new KenttaProfiili("testi", 10, 10);
        moottori = new Moottori(miinaProfiili);

        for (int i = 0; i < miinaProfiili.getKoko(); i++) {
            for (int k = 0; k < miinaProfiili.getKoko(); k++) {
                if (moottori.getKentta().getSolu(i, k).isMiina()) {
                    assertEquals(-1, moottori.aukaise(i, k));
                }
            }
        }
    }

    @Test
    public void moottoriPalauttaaOikeanArvonMiinanVierestaAvattaessa() {
        KenttaProfiili miinaProfiili = new KenttaProfiili("testi", 10, 10);
        moottori = new Moottori(miinaProfiili);

        for (int i = 0; i < miinaProfiili.getKoko(); i++) {
            for (int k = 0; k < miinaProfiili.getKoko(); k++) {
                if (moottori.getKentta().getSolu(i, k).getVieressaMiinoja() > 0) {
                    assertEquals(moottori.getKentta().getSolu(i, k).getVieressaMiinoja(), moottori.aukaise(i, k));
                }
            }
        }
    }

    @Test
    public void moottoriPalauttaaOikeanArvonVaarattomanAukaisusta() {
        assertEquals(0, moottori.aukaise(0, 0));
    }

    @Test
    public void moottoriAukaiseeKaikkiToimii() {
        moottori.aukaiseKaikki();
        for (int i=0;i<profiili.getKoko();i++){
            for (int k=0;k<profiili.getKoko();k++){
                assertEquals(true, moottori.getKentta().getSolu(i, k).isAuki());
            }
        }
    }
}
