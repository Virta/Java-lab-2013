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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat.TakaisinNappiKuuntelija;

/**
 * Tässä luokassa näytetään tulokset, jotka otetaan pääluokalta SisaltoFramesta.
 * 
 * @author virta
 */
public class TulosNakyma implements Runnable {

    private JFrame frame;
    private SisaltoFrame nakyma;
    private HashMap<String, Kayttaja> pelaajat;
    private HashMap<String, KenttaProfiili> peliProfiilit;
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
        
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        lisaaAlkuTesksti(container);
        lisaaVakioTulokset(container);
        lisaaMuutTuloksetProfiileittain(container);
        lisaaTakaisinNappula(container);
    }

    private void lisaaAlkuTesksti(Container container) {
        container.add(new JLabel("Miinaharavan pelitulokset"));
        lisaaKenttienOtsikot(container);
    }
    
    private void lisaaTakaisinNappula(Container container){
        JButton takaisinNappula = new JButton("Takaisin");
        TakaisinNappiKuuntelija kuuntelija = new TakaisinNappiKuuntelija(takaisinNappula, nakyma);
        takaisinNappula.addActionListener(kuuntelija);
        container.add(takaisinNappula);
    }

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

    private void lisaaVakioTulokset(Container container) {

        lisaaTulosPaneeli(container, "Helppo");
        lisaaTulosPaneeli(container, "Keskivaikea");
        lisaaTulosPaneeli(container, "Vaikea");

    }

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

    private void lisaaTulosLista(Container container, LinkedList<Tulos> tulosLista) {
        Collections.sort(tulosLista);
        for (Tulos tulos : tulosLista) {
            container.add(lisaaYksittainenTulosPaneeliin(tulos));
        }
    }

    private Component lisaaYksittainenTulosPaneeliin(Tulos tulos) {
        JPanel tulosRiviPaneeli = new JPanel();
        tulosRiviPaneeli.setLayout(new GridLayout(1, 6));
        
        tulosRiviPaneeli.add(new JLabel(tulos.getPelaaja().getNimimerkki()));
        tulosRiviPaneeli.add(new JLabel(tulos.getProfiili().getNimi()));
        tulosRiviPaneeli.add(new JLabel(tulos.getProfiili().getKoko() + ""));
        tulosRiviPaneeli.add(new JLabel(tulos.getProfiili().getMiinoja() + ""));
        tulosRiviPaneeli.add(new JLabel(tulos.getOnnistuiko() + ""));
        tulosRiviPaneeli.add(new JLabel(tulos.getAika()));
        
        return tulosRiviPaneeli;
    }

    public void lisaaMuutTuloksetProfiileittain(Container container) {

        for (String profiiliNimi : this.peliProfiilit.keySet()) {
            lisaaTulosPaneeli(container, profiiliNimi);
        }

    }
}
