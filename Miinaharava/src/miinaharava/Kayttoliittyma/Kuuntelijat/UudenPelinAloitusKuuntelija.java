/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.Kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import miinaharava.Kayttoliittyma.PelikenttaNakyma;
import miinaharava.Kayttoliittyma.SisaltoFrame;

/**
 * *Tämä luokka vastaa uuden pelin aloituksen toiminnallisuudesta.
 *
 * @author virta
 */
public class UudenPelinAloitusKuuntelija implements ActionListener {

    /**
     * SisaltoFrame, joka on kaikille näkymä- ja kuuntelijaluokille yhteinen,
     * jonka ikkunaan komponentit piirretään.
     */
    private SisaltoFrame nakyma;
    /**
     * JButton-olio jota kuunnellaan uuden pelin aloittamiseksi vakioprofiilista
     * helppo.
     */
    private JButton helppoNappi;
    /**
     * JButton-olio jota kuunnellaan uuden pelin aloittamiseksi vakioprofiilista
     * keskivaikea.
     */
    private JButton keskivaikeaNappi;
    /**
     * JButton-olio jota kuunnellaan uuden pelin aloittamiseksi vakioprofiilista
     * vaikea.
     */
    private JButton vaikeaNappi;
    /**
     * JButton-olio, jota kuunnellaan uuden pelin aloittamiseksi pelaajan omasta
     * profiilista.
     */
    private JButton geneerinen;
    /**
     * Sisäinen muuttuja jossa pidetään tallessa peliprofiilin nimeä jotta tämä
     * luokka osaa käynnistää moottorin oikealla kenttäprofiililla.
     */
    private String profiiliNimi;

    /**
     * Konstruktori jota kutsutaan uuden pelin aloittamiseksi vakioprofiileista.
     *
     * @param helppoN
     * @param keskivN
     * @param vaikaN
     * @param nakyma
     */
    public UudenPelinAloitusKuuntelija(JButton helppoN, JButton keskivN, JButton vaikaN, SisaltoFrame nakyma) {
        this.nakyma = nakyma;
        this.helppoNappi = helppoN;
        this.keskivaikeaNappi = keskivN;
        this.vaikeaNappi = vaikaN;
    }

    /**
     * Konstruktori jota kutsutaan uuden pelin aloittamiseksi pelaajan omasta
     * profiilista.
     *
     * @param nappi
     * @param nakyma
     * @param profiili
     */
    public UudenPelinAloitusKuuntelija(JButton nappi, SisaltoFrame nakyma, String profiili) {
        this.geneerinen = nappi;
        this.nakyma = nakyma;
        this.profiiliNimi = profiili;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == helppoNappi) {
            uusiHelppoPeli();
        } else if (source == keskivaikeaNappi) {
            uusiKeskivaikeaPeli();
        } else if (source == vaikeaNappi) {
            uusiVaikeaPeli();
        } else if (source == geneerinen) {
            uusiCustomPeli();
        }
    }

    /**
     * Luo uuden pelikenttä-olion vastaavasta profiilista.
     */
    private void uusiHelppoPeli() {
        PelikenttaNakyma pelikentta = new PelikenttaNakyma(nakyma, this.nakyma.getVakioProfiilit().getHelppo());
        SwingUtilities.invokeLater(pelikentta);
    }

    /**
     * Luo uuden pelikenttä-olion vastaavasta profiilista.
     */
    private void uusiKeskivaikeaPeli() {
        PelikenttaNakyma pelikentta = new PelikenttaNakyma(nakyma, this.nakyma.getVakioProfiilit().getKeskiVaikea());
        SwingUtilities.invokeLater(pelikentta);
    }

    /**
     * Luo uuden pelikenttä-olion vastaavasta profiilista.
     */
    private void uusiVaikeaPeli() {
        PelikenttaNakyma pelikentta = new PelikenttaNakyma(nakyma, this.nakyma.getVakioProfiilit().getVaikea());
        SwingUtilities.invokeLater(pelikentta);
    }

    /**
     * Luo uuden pelikenttä-olion vastaavasta profiilista.
     */
    private void uusiCustomPeli() {
        PelikenttaNakyma pelikentta = new PelikenttaNakyma(nakyma, this.nakyma.getPeliProfiilit().get(profiiliNimi));
        SwingUtilities.invokeLater(pelikentta);
    }
}
