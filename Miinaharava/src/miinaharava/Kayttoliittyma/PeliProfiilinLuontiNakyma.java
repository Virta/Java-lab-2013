/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import miinaharava.Kayttoliittyma.Kuuntelijat.TakaisinNappiKuuntelija;
import miinaharava.Kayttoliittyma.Kuuntelijat.UudenPeliProfiilinLuontiKuuntelija;

/**
 * Tämä luokka vastaa uuden peliprofiillin näkymän piirtämisestä.
 * 
 * @author virta
 */
public class PeliProfiilinLuontiNakyma implements Runnable {

    /**
     * SisaltoFrame, joka on kaikille käyttöliittymäluokille yhteinen, käytetään samaa ikkunaa.
     */
    private SisaltoFrame nakyma;
    
    /**
     * JFrame, jonka ContentPane (Container-olio) tulee sisältämään kaikki luokan näkyvät komponentit.
     */
    private JFrame frame;
    
    /**
     * Kenttä johon käyttäjä voi syöttää haluamansa profiilin nimen.
     */
    private JTextField profiiliNimikentta;
    
    /**
     * Alasvetovalikko josta käyttäjä voi valita haluamansa kentän koon, koko ei ole muuten valittavissa.
     */
    private JComboBox kentanKoko;
    
    /**
     * Alasvetovalikko josta käyttäjä voi valita haluamansa määrän miinoja kenttään, miinojen määrä ei ole muuten valittavissa, miinojen määräksi sallitaan vain vähemmän kuin koko*koko.
     */
    private JComboBox miinoja;

    public PeliProfiilinLuontiNakyma(SisaltoFrame nakyma) {
        this.nakyma = nakyma;
        this.frame = nakyma.getFrame();
    }

    @Override
    public void run() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setPreferredSize(new Dimension(600, 300));
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luo näkymäkomponentit parametrina saatuun container-olioon.
     * @param container 
     */
    private void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(new JLabel("Miinaharava - uuden peliprofiilin luonti"));
        container.add(luoProfiiliNimikentta());
        container.add(luoKokoJaMiinaValitsimet());
        container.add(luoLuomisNappula());
        container.add(luoTakaisinNappi());
    }

    /**
     * Luo JPanel-olion johon lisätään JLabel saateviestillä sekä JTextField johon pelaaja kirjoittaa haluamansa nimen profiilille.
     * 
     * @return Palauttaa JPanel-olion komponenttina, joka sisältää em. oliot.
     */
    private Component luoProfiiliNimikentta() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.X_AXIS));

        JLabel saate = new JLabel("Anna profiilillesi nimi (max 10 kirjainta): ");
        profiiliNimikentta = new JTextField();

        paneeli.add(saate);
        paneeli.add(profiiliNimikentta);

        return paneeli;
    }

    /**
     * Luo JPanel-olion johon lisätään JComboBox-alasvetovalitsimet kovakoodatuilla valinnoilla.
     * 
     * @return JPanel-olion joka sisältää em. oliot komponettina.
     */
    private Component luoKokoJaMiinaValitsimet() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.X_AXIS));

        paneeli.add(luoKokoValitsin());
        paneeli.add(luoMiinaValitsin());

        return paneeli;
    }

    /**
     * Luo JPanel-olion johon lisätään saateviesti JLabel oliona, sekä JComboBox kovakoodatuilla arvoilla.
     * 
     * @return Palauttaa JPanel-olion komponenttina, joka sitältää em. oliot.
     */
    private Component luoKokoValitsin() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));

        JLabel kokoSaateViesti = new JLabel("Valitse koko:");
        String[] koot = new String[]{"10", "15", "20", "25", "30", "40", "50", "70", "100"};
        this.kentanKoko = new JComboBox(koot);
        kentanKoko.setEditable(false);

        paneeli.add(kokoSaateViesti);
        paneeli.add(kentanKoko);

        return paneeli;
    }

    /**
     * Luo JPanel-olion johon lisätään saateviesti JLabel-oliona, sekä JComboBox kovakoodatuilla arvoilla.
     * 
     * @return Palauttaa JPanel-olion komponenttina, joka sisältää em. oliot.
     */
    private Component luoMiinaValitsin() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));

        JLabel miinaSaateViesti = new JLabel("Valitse miinojen määrä:");
        String[] miinojaKentassa = new String[]{"5", "10", "15", "30", "60", "90", "120", "150", "200", "300", "999"};
        this.miinoja = new JComboBox(miinojaKentassa);
        miinoja.setEditable(false);

        paneeli.add(miinaSaateViesti);
        paneeli.add(miinoja);

        return paneeli;
    }

    /**
     * Luo JButton olion jota painamalla pelaaja saa luotuoa uuden profiilin annetuilla arvoilla.
     * Profiilin luonti ei onnistu jos miinoja on enemmän kuin koko*koko, eikä jos nimi on liian pitkä tai tyhjä.
     * 
     * @return Palauttaa JPanel-olion komponenttina, jossa em. oliot.
     */
    private Component luoLuomisNappula() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.X_AXIS));

        JButton luomisNappi = new JButton("Valmis!");
        JLabel viestikentta = new JLabel();

        UudenPeliProfiilinLuontiKuuntelija kuuntelija = new UudenPeliProfiilinLuontiKuuntelija(luomisNappi, nakyma, kentanKoko, miinoja, viestikentta, profiiliNimikentta);
        luomisNappi.addActionListener(kuuntelija);

        paneeli.add(luomisNappi);
        paneeli.add(viestikentta);

        return paneeli;
    }

    /**
     * Luo geneerisen takaisin-nappulan, jota painamalla näkymä siirtyy päävalikkoon.
     * 
     * @return JButton-olion, joka voidaan lisätä sisältöön komponenttina.
     */
    private JButton luoTakaisinNappi() {
        JButton takaisinNappi = new JButton("Takaisin");
        TakaisinNappiKuuntelija kuuntelija = new TakaisinNappiKuuntelija(takaisinNappi, nakyma);
        takaisinNappi.addActionListener(kuuntelija);
        return takaisinNappi;
    }
}
