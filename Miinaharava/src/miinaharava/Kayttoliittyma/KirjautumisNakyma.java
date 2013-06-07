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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import miinaharava.Kayttoliittyma.Kuuntelijat.KirjautumisKuuntelija;
import miinaharava.Kayttoliittyma.Kuuntelijat.TakaisinNappiKuuntelija;

/**
 * Tämä luokka vastaa kirjautumisnäkymän piirtämisestä.
 *
 * @author virta
 */
public class KirjautumisNakyma implements Runnable {

    /**
     * JFrame jonka container-olioon lisätään kaikki komponentit.
     */
    private JFrame frame;
    
    /**
     * SisaltoFrame, joka on kaikille käyttöliittymän näkymille sama, muutetaan
     * siis ainoastaan sen sisältöä eikä luoda aina uutta ikkunaa.
     */
    private SisaltoFrame nakyma;

    public KirjautumisNakyma(SisaltoFrame nakyma) {
        this.nakyma = nakyma;
        this.frame = nakyma.getFrame();
    }

    @Override
    public void run() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setPreferredSize(new Dimension(500, 200));
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luo komponentit parametrina saatuun container-olioon: alkuteksti, sisäänkirjautumisen komponentit TAI uloskirjautumiskomponentit sekä takaisin-nappulan.
     * @param container 
     */
    private void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(new JLabel("Miinaharava - kirjautuminen"));

        if (!this.nakyma.getKirjautunutNimimerkki().equals("Anon")) {
            container.add(luoUlosKirjautuminen());
        } else {
            container.add(luoSisaanKirjautuminen());
        }
        container.add(luoTakaisinNappi());
    }

    /**
     * Luo JPanel-olion johon lisätään komponentit sisäänkirjautumiseen.
     * JLabel kehote: sisältää kehotteen käyttäjälle.
     * JTextField nimierkkiSyotto: kenttä, johon käyttäjä syöttää nimimerkkinsä.
     * JLabel viestikentta: viestikenttä johon kirjoitetaan viesti käyttäjälle jos nimimerkki ei kelpaa.
     * JButton sisaanKirjaaNappi: nappi, jota painamalla kuuntelija tarkistaa nimimerkin, kirjaa sisään jos oikein, tai näyttää viestin kertoen miksi kirjautuminen ei onnistunut.
     * JButton luoNimimerkkiNappi: nappi, jota painamalla kuuntelija tarkistaa onko annettu nimimerkki jo käytössä, ja tulostaa viestikentään viestin,  jos nimimerkin luonti ei onnistunut.
     * 
     * @return JPanel-olio, johon sisältyy em. komponentit.
     */
    private Component luoSisaanKirjautuminen() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.X_AXIS));
        JLabel kehote = new JLabel("Syötä nimimerkkisi: ");
        JTextField nimimerkkiSyotto = new JTextField();
        JLabel viestikentta = new JLabel();

        JButton luoNimimerkkiNappi = new JButton("Luo uusi");
        JButton sisaanKirjaaNappi = new JButton("     OK     ");
        KirjautumisKuuntelija kuuntelija = new KirjautumisKuuntelija(sisaanKirjaaNappi, nimimerkkiSyotto, viestikentta, luoNimimerkkiNappi, nakyma);
        sisaanKirjaaNappi.addActionListener(kuuntelija);
        luoNimimerkkiNappi.addActionListener(kuuntelija);

        paneeli.add(kehote);
        paneeli.add(luoNimiJaViestiKenttaPaneeli(nimimerkkiSyotto, viestikentta));
        paneeli.add(luoKirjaamisNapitPaneeli(sisaanKirjaaNappi, luoNimimerkkiNappi));

        return paneeli;
    }

    /**
     * Luo JPanel-olion jossa nimi- ja viestikentät.
     * 
     * @param nimikentta JTextField-olio johon käyttäjä kirjoittaa nimimerkkinsä.
     * @param viestikentta JLabel-olio johon ohjelma tulostaa viestin kirjautumisen epäonnistuessa.
     * @return JPanel-olio joka voidaan lisätä sisältöön komponenttina.
     */
    private Component luoNimiJaViestiKenttaPaneeli(JTextField nimikentta, JLabel viestikentta) {
        JPanel kentat = new JPanel();
        kentat.setLayout(new BoxLayout(kentat, BoxLayout.Y_AXIS));

        kentat.add(nimikentta);
        kentat.add(viestikentta);
        return kentat;
    }

    /**
     * Luo JPanel-olion johon luodaan napit sisään kirjaamista ja uuden nimimerkin luomiseksi.
     * @param sisaanNappi JButton
     * @param uusiNimimerkkiNappi JButton
     * @return JPanel-olio, joka voidaan lisätä sisältöön komponenttina.
     */
    private Component luoKirjaamisNapitPaneeli(JButton sisaanNappi, JButton uusiNimimerkkiNappi) {
        JPanel napit = new JPanel();
        napit.setLayout(new BoxLayout(napit, BoxLayout.Y_AXIS));

        napit.add(sisaanNappi);
        napit.add(uusiNimimerkkiNappi);
        return napit;
    }

    /**
     * Luo JPanel olion joka sisältää komponentit uloskirjaamista varten.
     * 
     * @return JPanel-olion, joka voidaan lisätä sisältöön komponenttina.
     */
    private Component luoUlosKirjautuminen() {
        frame.getContentPane().removeAll();

        JPanel napit = new JPanel();
        napit.setLayout(new BoxLayout(napit, BoxLayout.Y_AXIS));

        JLabel saate = new JLabel("Miinaharava - uloskirjaudu");
        JButton ulosKirjaamisNappi = new JButton("Kirjaudu ulos");
        KirjautumisKuuntelija kuuntelija = new KirjautumisKuuntelija(ulosKirjaamisNappi, nakyma);
        ulosKirjaamisNappi.addActionListener(kuuntelija);
        napit.add(saate);
        napit.add(ulosKirjaamisNappi);
        return napit;
    }

    /**
     * Luo geneerisen takaisin-napin, joka vie aina aloitusnäkymään, eli päävalikkoon.
     * 
     * @return JButton, joka voidaan lisätä sisältöön.
     */
    private JButton luoTakaisinNappi() {
        JButton takaisinNappi = new JButton("Takaisin");
        TakaisinNappiKuuntelija kuuntelija = new TakaisinNappiKuuntelija(takaisinNappi, nakyma);
        takaisinNappi.addActionListener(kuuntelija);
        return takaisinNappi;
    }
}
