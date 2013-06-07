/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import miinaharava.Kayttoliittyma.AloitusNakyma;
import miinaharava.Kayttoliittyma.SisaltoFrame;

/**
 * Tämä luokka on geneerinen takaisin-napin kuuntelija, jonka toiminta vie aina takaisin päävalikkoon.
 * @author virta
 */
public class TakaisinNappiKuuntelija implements ActionListener {
    
    /**
     * Kaikille näkymä- ja kuuntelijaluokille yhteinen näkymä, jonka ikkunaan komponentit piirretään.
     */
    private SisaltoFrame nakyma;
    
    /**
     * JButton-olio jota kuunnellaan toiminnallisuuden toteuttamiseksi.
     */
    private JButton takaisinNappi;
    
    public TakaisinNappiKuuntelija(JButton nappi, SisaltoFrame nakyma){
        this.nakyma = nakyma;
        this.takaisinNappi = nappi;
    }

    /**
     * Painalluksen tapahtuessa luodaan uusi olio AloitusNakymasta ja käynnistetään se.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AloitusNakyma aloitusNakyma = new AloitusNakyma(nakyma);
        SwingUtilities.invokeLater(aloitusNakyma);
    }
    
}
