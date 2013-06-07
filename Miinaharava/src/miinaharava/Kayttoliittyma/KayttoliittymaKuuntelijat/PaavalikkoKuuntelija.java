/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import miinaharava.Kayttoliittyma.KirjautumisNakyma;
import miinaharava.Kayttoliittyma.SisaltoFrame;
import miinaharava.Kayttoliittyma.TulosNakyma;
import miinaharava.Kayttoliittyma.UudenPelinAloitusNakyma;

/**
 * Tämä luokka vastaa päävalikon toiminnallisuuden toteuttamisesta.
 * @author virta
 */
public class PaavalikkoKuuntelija implements ActionListener {
    
    /**
     * SisaltoFrame joka on kaikille näkymä- ja kuuntelijaluokille yhteinen, jonka ikkunaan komponentit piirretään.
     */
    private SisaltoFrame nakyma;
    
    /**
     * JButton-olio, jota painamalla aloitetaan tulos-näkymä.
     */
    private JButton tuloksetNappi;
    
    /**
     * JButton-olio, jota painamalla aloitetaan kirjautumisnäkymä.
     */
    private JButton kirjauduNappi;
    
    /**
     * JButton-olio, jota painamalla aloitetaan uuden pelin aloitusnäkymä.
     */
    private JButton uusiPeliNappi;
    
    /**
     * Ottaa vastaan SisaltoFramen, johon piirretään komponentit, ja JButton-oliot joita kuunnellaan.
     * @param tulosNappi
     * @param kirjauduNappi
     * @param uusiPNappi
     * @param nakyma 
     */
    public PaavalikkoKuuntelija(JButton tulosNappi, JButton kirjauduNappi, JButton uusiPNappi, SisaltoFrame nakyma){
        this.nakyma = nakyma;
        this.kirjauduNappi = kirjauduNappi;
        this.uusiPeliNappi = uusiPNappi;
        this.tuloksetNappi = tulosNappi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == tuloksetNappi){
            tulokset();
        } else if (source == kirjauduNappi){
            kirjautuminen();
        } else if (source == uusiPeliNappi){
            uusiPeli();
        }
    }
    
    /**
     * Luo uuden olion UudenPelinAloitusNakyma-luokasta, ja käynnistää sen.
     */
    private void uusiPeli(){
        UudenPelinAloitusNakyma uusiPeliNakyma = new UudenPelinAloitusNakyma(nakyma);
        SwingUtilities.invokeLater(uusiPeliNakyma);
    }
    
    /**
     * Luo uuden olion TulosNakyma-luokasta, ja käynnistää sen.
     */
    private void tulokset(){
        TulosNakyma tulosNakyma = new TulosNakyma(nakyma);
        SwingUtilities.invokeLater(tulosNakyma);
    }
    
    /**
     * Luo uuden olion KirjautumisNakyma-luokasta ja käynnistää sen.
     */
    private void kirjautuminen(){
        KirjautumisNakyma kirjautumisNakyma = new KirjautumisNakyma(nakyma);
        SwingUtilities.invokeLater(kirjautumisNakyma);
    }
    
}
