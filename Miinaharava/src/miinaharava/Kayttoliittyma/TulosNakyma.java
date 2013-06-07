/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import miinaharava.Kayttoliittyma.Kuuntelijat.TakaisinNappiKuuntelija;

/**
 * Tämä luokka on vastuussa tulosten esittämisestä; kuten muissakin käyttöliittymäluokissa komponentit piirretään SisaltoFrame näkymäpohjaan.
 * 
 * @author virta
 */
public class TulosNakyma implements Runnable {

    /**
     * JFrame, jonka ContentPane (Container-olio) tulee sisältämään kaikki piirrettävät komponentit.
     */
    private JFrame frame;
    
    /**
     * SisaltoFrame, joka on sama kaikille käyttöliittymäluokille, jonka ikkunaan kaikki komponentit piirretään.
     */
    private SisaltoFrame nakyma;
    
    /**
     * Hajautustaulu, josta kaikki käyttäjät haetaan.
     */
    private HashMap<String, Kayttaja> pelaajat;
    
    /**
     * Hajautustaulu, josta kaikki peliprofiilit haetaan.
     */
    private HashMap<String, KenttaProfiili> peliProfiilit;
    
    /**
     * Tuloslista, josta kaikki tulokset haetaan, joiden mukaan tulos-oliot piirretään näkymään.
     */
    private LinkedList<Tulos> tulokset;

    public TulosNakyma(SisaltoFrame nakyma) {
        this.nakyma = nakyma;
        this.frame = nakyma.getFrame();
        this.pelaajat = nakyma.getPelaajat();
        this.peliProfiilit = nakyma.getPeliProfiilit();
        this.tulokset = nakyma.getTulokset();
    }

    @Override
    public void run() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setPreferredSize(new Dimension(1000, 600));
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Luo kaikki komponentit parametrina saatuun Container-olioon.
     * @param container 
     */
    private void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        lisaaAlkuTesksti(container);
        lisaaVakioTulokset(container);
        lisaaMuutTuloksetProfiileittain(container);
        container.add(luoTakaisinNappi());
    }

    /**
     * Lisää parametrina saatuun Container-olioon JLabel-olion sisältäen saate tekstin.
     * @param container 
     */
    private void lisaaAlkuTesksti(Container container) {
        container.add(new JLabel("Miinaharava - pelitulokset"));
        lisaaKenttienOtsikot(container);
    }
    
    /**
     * Luo geneerisen takaisin-napin jonka painalluksesta näkymä palaa päävalikkoon.
     * @return 
     */
    private JButton luoTakaisinNappi() {
        JButton takaisinNappi = new JButton("Takaisin");
        TakaisinNappiKuuntelija kuuntelija = new TakaisinNappiKuuntelija(takaisinNappi, nakyma);
        takaisinNappi.addActionListener(kuuntelija);
        return takaisinNappi;
    }

    /**
     * Lisää parametrina saatuun Container-olioon JPanel-olion jossa tuloskenttien otsikot JLabel-olioina.
     * @param container 
     */
    private void lisaaKenttienOtsikot(Container container) {
        JPanel otsikot = new JPanel(new GridLayout(1, 6));
        otsikot.add(new JLabel("Nimimerkki"));
        otsikot.add(new JLabel("Kenttä Profiili"));
        otsikot.add(new JLabel("Kentän koko"));
        otsikot.add(new JLabel("Miinoja"));
        otsikot.add(new JLabel("Läpäisy"));
        otsikot.add(new JLabel("Aika"));
        container.add(otsikot);
    }

    /**
     * Lisää parametrina saatuun Container-olioon vakioprofiileilla saadut tulokset.
     * @param container 
     */
    private void lisaaVakioTulokset(Container container) {

        lisaaTulosPaneeli(container, "Helppo");
        lisaaTulosPaneeli(container, "Keskivaikea");
        lisaaTulosPaneeli(container, "Vaikea");

    }

    /**
     * Luo listan tuloksista tietyllä kenttäprofiilin nimellä, ja kutsuu metodia lisaaTulosLista.
     * @param container
     * @param profiiliNimi 
     */
    private void lisaaTulosPaneeli(Container container, String profiiliNimi) {
        LinkedList<Tulos> tulosLista = new LinkedList<>();
        for (Tulos tulos : this.tulokset) {
            if (tulos.getProfiili().getNimi().equals(profiiliNimi)) {
                tulosLista.add(tulos);
            }
        }

        if (tulosLista.size()==0){ return; }
        
        lisaaTulosLista(container, tulosLista);
    }

    /**
     * Kutsuu metodia lisaaYksittainenTulosListaan kullekin tulokselle parametrina annetusta listasta tuloksia (jotka ovat yhden tietyn profiilin tuloksia).
     * @param container
     * @param tulosLista 
     */
    private void lisaaTulosLista(Container container, LinkedList<Tulos> tulosLista) {
        Collections.sort(tulosLista);
        for (Tulos tulos : tulosLista) {
            container.add(lisaaYksittainenTulosPaneeliin(tulos));
        }
    }

    /**
     * Luo JPanel olion GridLeyoutilla, jossa 6 kenttää kullekin tuloksen attribuutille.
     * @param tulos
     * @return JPanel-olion komponenttina, jonka voi lisätä sisältöön, joka sisältää annetun tuloksen.
     */
    private Component lisaaYksittainenTulosPaneeliin(Tulos tulos) {
        JPanel tulosRiviPaneeli = new JPanel();
        tulosRiviPaneeli.setLayout(new GridLayout(1, 6));
        
        tulosRiviPaneeli.add(new JLabel(tulos.getPelaaja()));
        tulosRiviPaneeli.add(new JLabel(tulos.getProfiili().getNimi()));
        tulosRiviPaneeli.add(new JLabel(tulos.getProfiili().getKoko() + ""));
        tulosRiviPaneeli.add(new JLabel(tulos.getProfiili().getMiinoja() + ""));
        tulosRiviPaneeli.add(new JLabel(tulos.getOnnistuiko() + ""));
        tulosRiviPaneeli.add(new JLabel(tulos.getAika()));
        
        return tulosRiviPaneeli;
    }

    /**
     * Kutsutaan lisäämään muut tulokset kuin vakioprofiililla saadut, parametrina saatuun Container-olioon.
     * @param container 
     */
    public void lisaaMuutTuloksetProfiileittain(Container container) {

        for (String profiiliNimi : this.peliProfiilit.keySet()) {
            lisaaTulosPaneeli(container, profiiliNimi);
        }

    }
}
