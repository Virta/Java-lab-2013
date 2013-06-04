/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import miinaharava.Entiteetit.VakioProfiilit;

/**
 * Tämä luokka on miinaharavan käyttöliittymän pohja; tätä luokkaa ja sen JFramea liikutellaan käyttöliittymän eri osien välillä, ja sen 
 * JFrameen tehdään muutoksia aina kyseessä olevan näkymän mukaisesti.
 * @author frojala
 */
public class SisaltoFrame implements Runnable {

    private JFrame frame;
    private HashMap<String, Kayttaja> pelaajat;
    private HashMap<String, KenttaProfiili> peliProfiilit;
    private VakioProfiilit vakioProfiilit;
    private LinkedList<Tulos> tulokset;
    private String kirjautunutNimimerkki;

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
        frame.setPreferredSize(new Dimension(1000, 600));
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
