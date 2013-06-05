package miinaharava;

import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.SwingUtilities;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import miinaharava.Entiteetit.VakioProfiilit;
import miinaharava.Kayttoliittyma.SisaltoFrame;
import miinaharava.Tallennus.tallennusLogiikka;

/**
 * Tämä on Miinaharavan pääluokka, se palauttaa tulokset tiedostosta, käynnistää käyttöliittymän ja tallentaa tulokset lopetettaessa.
 * @author frojala
 * 
 * 
 */
public class Miinaharava {

    public static void main(String[] args) throws InterruptedException, Exception {
        
        HashMap<String, Kayttaja> pelaajat = new HashMap<>();
        HashMap<String, KenttaProfiili> peliProfiilit = new HashMap<>();
        VakioProfiilit vakioProfiilit = new VakioProfiilit();
        LinkedList<Tulos> tulokset = new LinkedList<>();
        Tulos tulos = new Tulos("1:11", new KenttaProfiili("joku", 10, 10), new Kayttaja("Jaska"), true);
//        tulokset.add(tulos);
//        tallennusLogiikka.tallenna(tulokset);
        tallennusLogiikka.palauta(pelaajat, peliProfiilit, tulokset);
//        tulokset.clear();
        SisaltoFrame kayttoliittyma = new SisaltoFrame(pelaajat, peliProfiilit, vakioProfiilit, tulokset);
        SwingUtilities.invokeLater(kayttoliittyma);
        
        tallennusLogiikka.tallenna(tulokset);
        
    }
    
}
