/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.TallennusLogiikkaTestit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import miinaharava.Tallennus.tallennusLogiikka;
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
public class tallennusLogiikkaTest {

    public tallennusLogiikkaTest() {
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
    public void tallentaaSaateviestin() throws Exception {
        tallennusLogiikka.tallenna(new LinkedList<Tulos>());
        File tulokset = new File("Tulokset.txt");
        assertNotNull(tulokset);
        Scanner lukija = new Scanner(tulokset);
        assertEquals("Tämä on automaattisesti generoitu tiedosto, älä muuta, rikot vielä jotain!", lukija.nextLine());
    }

    @Test
    public void tallentaaTuloksen() throws Exception {
        LinkedList<Tulos> tulokset = new LinkedList<>();
        lisaaTulosListaan(tulokset);

        tallennusLogiikka.tallenna(tulokset);
        File tulosTiedosto = new File("Tulokset.txt");
        assertNotNull(tulokset);

        Scanner lukija = new Scanner(tulosTiedosto);
        lukija.nextLine();
        String tulosString = "";
        if (lukija.hasNextLine()) {
            tulosString = lukija.nextLine();
        } else {
            throw new AssertionError("Ei tuloksia tiedostossa!");
        }
        lukija.close();

        Scanner purkaja = new Scanner(tulosString);
        assertEquals("Jaska", purkaja.next());
        assertEquals("joku", purkaja.next());
        assertEquals("10", purkaja.next());
        assertEquals("10", purkaja.next());
        assertEquals("1:10", purkaja.next());
        assertEquals("true", purkaja.next());
        purkaja.close();

    }

    @Test
    public void tallentaaUseitaTuloksia() throws Exception {
        LinkedList<Tulos> tulokset = new LinkedList<>();
        lisaaTulosListaan(tulokset);
        lisaaTulosListaan(tulokset);
        lisaaTulosListaan(tulokset);

        tallennusLogiikka.tallenna(tulokset);

        File tulosTiedosto = new File("Tulokset.txt");
        Scanner lukija = new Scanner(tulosTiedosto);
        lukija.nextLine();
        while (lukija.hasNextLine()) {
            String tulosString = lukija.nextLine();
            Scanner purkaja = new Scanner(tulosString);
            assertEquals("Jaska", purkaja.next());
            assertEquals("joku", purkaja.next());
            assertEquals("10", purkaja.next());
            assertEquals("10", purkaja.next());
            assertEquals("1:10", purkaja.next());
            assertEquals("true", purkaja.next());
            purkaja.close();
        }
        lukija.close();
        
    }
    
    @Test
    public void palauttaaTuloksenOikein() throws Exception{
        LinkedList<Tulos> tulokset = new LinkedList<>();
        lisaaTulosListaan(tulokset);
        tallennusLogiikka.tallenna(tulokset);
        
        tulokset.clear();
        HashMap<String, Kayttaja> kayttajat = new HashMap<>();
        HashMap<String, KenttaProfiili> profiilit = new HashMap<>();
        
        tallennusLogiikka.palauta(kayttajat, profiilit, tulokset);
        
        assertEquals(true, kayttajat.containsKey("Jaska"));
        assertEquals(true, profiilit.containsKey("joku"));
        assertEquals(Tulos.class, tulokset.getFirst().getClass());
    }
    
    private void lisaaTulosListaan(LinkedList<Tulos> tulokset){
        Tulos tulos = new Tulos("1:10", new KenttaProfiili("joku", 10, 10), new Kayttaja("Jaska"), true);
        tulokset.add(tulos);
    }
}