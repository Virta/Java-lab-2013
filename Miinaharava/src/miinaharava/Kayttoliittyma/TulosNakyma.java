/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import miinaharava.Entiteetit.VakioProfiilit;

/**
 *
 * @author virta
 */
public class TulosNakyma implements Runnable {

    private JFrame frame;
    private HashMap<String, Kayttaja> pelaajat;
    private HashMap<String, KenttaProfiili> peliProfiilit;
    private LinkedList<Tulos> tulokset;

    public TulosNakyma(HashMap<String, Kayttaja> kayttajat, HashMap<String, KenttaProfiili> profiilit, LinkedList<Tulos> tulokset) {
        this.pelaajat = kayttajat;
        this.peliProfiilit = profiilit;
        this.tulokset = tulokset;
    }

    @Override
    public void run() {
        frame = new JFrame("Miinaharava - tulokset");
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    private void luoKomponentit(Container container) {
        lisaaAlkuTesksti(container);
        lisaaVakioTulokset(container);
        lisaaMuutTuloksetProfiileittain(container);
    }

    private void lisaaAlkuTesksti(Container container) {
        JPanel ylapaneeli = new JPanel(new GridLayout(2, 1));
        ylapaneeli.add(new JLabel("Miinaharavan pelitulokset"));
        lisaaKenttienOtsikot(ylapaneeli);
        container.add(ylapaneeli, BorderLayout.NORTH);
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

        container.add(vakioTulokset, BorderLayout.CENTER);
    }

    private void lisaaTulosPaneeli(JPanel tulosPaneeli, String profiiliNimi) {
        JPanel tulosProfiiliPaneeli = new JPanel(new GridLayout(10, 6));
        lisaaTulosLista(tulosProfiiliPaneeli, profiiliNimi);
        tulosPaneeli.add(tulosProfiiliPaneeli);
    }

    private void lisaaTulosLista(JPanel tulosPaneeli, String profiili) {
        LinkedList<Tulos> tulosLista = new LinkedList<>();
        for (Tulos tulos : this.tulokset) {
            if (tulos.getProfiili().getNimi().equals(profiili)) {
                tulosLista.add(tulos);
            }
        }

        Collections.sort(tulosLista);

        lisaaTulosPaneeliin(tulosLista, tulosPaneeli);
    }

    private void lisaaTulosPaneeliin(LinkedList<Tulos> tulosLista, JPanel tulosPaneeli) {
        int i=1;
        for (Tulos tulos : tulosLista) {
            tulosPaneeli.add(new JLabel(tulos.getPelaaja().getNimimerkki()));
            tulosPaneeli.add(new JLabel(tulos.getProfiili().getNimi()));
            tulosPaneeli.add(new JLabel(tulos.getProfiili().getKoko() + ""));
            tulosPaneeli.add(new JLabel(tulos.getProfiili().getMiinoja() + ""));
            tulosPaneeli.add(new JLabel(tulos.getOnnistuiko() + ""));
            tulosPaneeli.add(new JLabel(tulos.getAika()));
            i++;
            if (i==10){
                break;
            }
        }
    }

    public void lisaaMuutTuloksetProfiileittain(Container container) {
        JPanel muutTuloksetPaneeli = new JPanel(new GridLayout(this.peliProfiilit.size(), 1));

        for (String profiiliNimi : this.peliProfiilit.keySet()) {
            lisaaTulosPaneeli(muutTuloksetPaneeli, profiiliNimi);
        }

        container.add(muutTuloksetPaneeli, BorderLayout.SOUTH);
    }

}
