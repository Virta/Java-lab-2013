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
import miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat.TakaisinNappiKuuntelija;
import miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat.UudenPeliProfiilinLuontiKuuntelija;

/**
 *
 * @author virta
 */
public class PeliProfiilinLuontiNakyma implements Runnable {

    private SisaltoFrame nakyma;
    private JFrame frame;
    private JTextField profiiliNimikentta;
    private JComboBox kentanKoko;
    private JComboBox miinoja;

    public PeliProfiilinLuontiNakyma(SisaltoFrame nakyma) {
        this.nakyma = nakyma;
        this.frame = nakyma.getFrame();
    }

    @Override
    public void run() {
        frame.getContentPane().removeAll();
        frame.setPreferredSize(new Dimension(600, 300));
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(new JLabel("Miinaharava - uuden peliprofiilin luonti"));
        container.add(luoProfiiliNimikentta());
        container.add(luoKokoJaMiinaValitsimet());
        container.add(luoLuomisNappula());
        container.add(luoTakaisinNappi());
    }

    private Component luoProfiiliNimikentta() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.X_AXIS));

        JLabel saate = new JLabel("Anna profiilillesi nimi (max 10 kirjainta): ");
        profiiliNimikentta = new JTextField();

        paneeli.add(saate);
        paneeli.add(profiiliNimikentta);

        return paneeli;
    }

    private Component luoKokoJaMiinaValitsimet() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.X_AXIS));

        paneeli.add(luoKokoValitsin());
        paneeli.add(luoMiinaValitsin());

        return paneeli;
    }

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

    private JButton luoTakaisinNappi() {
        JButton takaisinNappi = new JButton("Takaisin");
        TakaisinNappiKuuntelija kuuntelija = new TakaisinNappiKuuntelija(takaisinNappi, nakyma);
        takaisinNappi.addActionListener(kuuntelija);
        return takaisinNappi;
    }
}
