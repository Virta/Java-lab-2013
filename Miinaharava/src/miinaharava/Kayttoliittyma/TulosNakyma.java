/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import com.sun.imageio.plugins.jpeg.JPEG;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import miinaharava.Entiteetit.VakioProfiilit;
import com.sun.imageio.plugins.jpeg.JPEG;
import java.awt.Color;
import javax.swing.JButton;

/**
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
        container.setLayout(new GridLayout(4, 1));
        lisaaAlkuTesksti(container);
        lisaaVakioTulokset(container);
        lisaaMuutTuloksetProfiileittain(container);
        lisaaTakaisinNappula(container);
    }

    private void lisaaAlkuTesksti(Container container) {
        
        GridLayout containerLayout = new GridLayout(2, 1);
        JPanel ylapaneeli = new JPanel(containerLayout);
        
        
        ylapaneeli.setBorder(BorderFactory.createLineBorder(Color.yellow));
        
        
        
        
        ylapaneeli.add(new JLabel("Miinaharavan pelitulokset"));
        lisaaKenttienOtsikot(ylapaneeli);
        container.add(ylapaneeli);
    }
    
    private void lisaaTakaisinNappula(Container container){
        JButton takaisinNappula = new JButton("Takaisin");
        NakymaKuuntelija kuuntelija = new NakymaKuuntelija(takaisinNappula, this.nakyma);
        takaisinNappula.addActionListener(kuuntelija);
        container.add(takaisinNappula);
    }

    private void lisaaKenttienOtsikot(JPanel paneeli) {
        JPanel otsikot = new JPanel(new GridLayout(1, 6));
        otsikot.add(new JLabel("Nimimerkki"));
        otsikot.add(new JLabel("Kenttä Profiili"));
        otsikot.add(new JLabel("Kentän koko"));
        otsikot.add(new JLabel("Miinoja"));
        otsikot.add(new JLabel("Läpäisy"));
        otsikot.add(new JLabel("Aika"));
        paneeli.add(otsikot);
    }

    private void lisaaVakioTulokset(Container container) {
        JPanel vakioTulokset = new JPanel(new GridLayout(3, 1));

        lisaaTulosPaneeli(vakioTulokset, "Helppo");
        lisaaTulosPaneeli(vakioTulokset, "Keskivaikea");
        lisaaTulosPaneeli(vakioTulokset, "Vaikea");

        container.add(vakioTulokset);
    }

    private void lisaaTulosPaneeli(JPanel tulosPaneeli, String profiiliNimi) {
        LinkedList<Tulos> tulosLista = new LinkedList<>();
        for (Tulos tulos : this.tulokset) {
            if (tulos.getProfiili().getNimi().equals(profiiliNimi)) {
                tulosLista.add(tulos);
            }
        }

        JPanel tulosProfiiliPaneeli = new JPanel(new GridLayout(tulosLista.size(), 1));
        lisaaTulosLista(tulosProfiiliPaneeli, tulosLista);
        tulosPaneeli.add(tulosProfiiliPaneeli);
    }

    private void lisaaTulosLista(JPanel tulosPaneeli, LinkedList<Tulos> tulosLista) {
        Collections.sort(tulosLista);
        for (Tulos tulos : tulosLista) {
            tulosPaneeli.add(lisaaYksittainenTulosPaneeliin(tulos));
        }
    }

    private JPanel lisaaYksittainenTulosPaneeliin(Tulos tulos) {
        JPanel tulosRiviPaneeli = new JPanel(new GridLayout(1, 6));
        tulosRiviPaneeli.add(new JLabel(tulos.getPelaaja().getNimimerkki()));
        tulosRiviPaneeli.add(new JLabel(tulos.getProfiili().getNimi()));
        tulosRiviPaneeli.add(new JLabel(tulos.getProfiili().getKoko() + ""));
        tulosRiviPaneeli.add(new JLabel(tulos.getProfiili().getMiinoja() + ""));
        tulosRiviPaneeli.add(new JLabel(tulos.getOnnistuiko() + ""));
        tulosRiviPaneeli.add(new JLabel(tulos.getAika()));

        return tulosRiviPaneeli;
    }

    public void lisaaMuutTuloksetProfiileittain(Container container) {
        JPanel muutTuloksetPaneeli = new JPanel(new GridLayout(this.peliProfiilit.keySet().size(), 1));

        for (String profiiliNimi : this.peliProfiilit.keySet()) {
            lisaaTulosPaneeli(muutTuloksetPaneeli, profiiliNimi);
        }

        container.add(muutTuloksetPaneeli);
    }
}
