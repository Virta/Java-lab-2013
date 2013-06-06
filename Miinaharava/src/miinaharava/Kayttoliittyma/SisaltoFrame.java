/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import miinaharava.Entiteetit.VakioProfiilit;

/**
 * Tämä luokka on miinaharavan käyttöliittymän pohja; tätä luokkaa ja sen JFramea liikutellaan käyttöliittymän eri osien välillä, ja sen 
 * JFrameen tehdään muutoksia aina kyseessä olevan näkymän mukaisesti. Koskaan ei siis piirretä uutta ikkunaa, vaan tämän ikkunan sisältä korvataan ko. näkymän sisällöllä.
 * @author frojala
 */
public class SisaltoFrame implements Runnable {

    /**
     * JFrame, johon kaikki piirrettävät komponentit lisätään.
     */
    private JFrame frame;
    
    /**
     * Hajautustaulu joka sisältää tuloksista ladatut ja uudet ohjelman suorituksen aikana luodut käyttäjät.
     */
    private HashMap<String, Kayttaja> pelaajat;
    
    /**
     * Hajautustaulu joka sisältää tuloksista ladatut ja uudet ohjelman suorituksen aikana luodut peliprofiilit.
     */
    private HashMap<String, KenttaProfiili> peliProfiilit;
    
    /**
     * 
     */
    private VakioProfiilit vakioProfiilit;
    
    /**
     * Lista joka sisältää tulokset; ohjelman suorituksen aikana luodut sekä tulostiedostosta ladatut.
     */
    private LinkedList<Tulos> tulokset;
    
    /**
     * String-olio jota käytetään kirjautumisen näyttämiseen sekä profiilien hakemiseen.
     */
    private String kirjautunutNimimerkki;

    /**
     * 
     * @param kayttajat
     * @param profiilit
     * @param vakioProfiilit
     * @param tulokset 
     */
    public SisaltoFrame(HashMap<String, Kayttaja> kayttajat, HashMap<String, KenttaProfiili> profiilit, VakioProfiilit vakioProfiilit, LinkedList<Tulos> tulokset) {
        this.pelaajat = kayttajat;
        this.peliProfiilit = profiilit;
        this.tulokset = tulokset;
        this.vakioProfiilit = vakioProfiilit;
        this.kirjautunutNimimerkki = "Anon";
    }

    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        AloitusNakyma aloitusNakyma = new AloitusNakyma(this);
        aloitusNakyma.run();
    }

    public String getKirjautunutNimimerkki() {
        return kirjautunutNimimerkki;
    }

    public void setKirjautunutNimimerkki(String kirjautunutNimimerkki) {
        this.kirjautunutNimimerkki = kirjautunutNimimerkki;
    }

    public JFrame getFrame() {
        return frame;
    }

    public HashMap<String, Kayttaja> getPelaajat() {
        return pelaajat;
    }

    public HashMap<String, KenttaProfiili> getPeliProfiilit() {
        return peliProfiilit;
    }

    public VakioProfiilit getVakioProfiilit() {
        return vakioProfiilit;
    }

    public LinkedList<Tulos> getTulokset() {
        return tulokset;
    }
}
