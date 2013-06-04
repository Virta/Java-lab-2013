/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import miinaharava.Kayttoliittyma.KirjautumisNakyma;
import miinaharava.Kayttoliittyma.SisaltoFrame;
import miinaharava.Kayttoliittyma.TulosNakyma;

/**
 *
 * @author virta
 */
public class PaavalikkoKuuntelija implements ActionListener {
    
    private SisaltoFrame nakyma;
    private JButton tuloksetNappi;
    private JButton kirjauduNappi;
    private JButton uusiPeliNappi;
    
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
    
    private void uusiPeli(){
        
    }
    
    private void tulokset(){
        TulosNakyma tulosNakyma = new TulosNakyma(nakyma);
        SwingUtilities.invokeLater(tulosNakyma);
    }
    
    private void kirjautuminen(){
        KirjautumisNakyma kirjautumisNakyma = new KirjautumisNakyma(nakyma);
        SwingUtilities.invokeLater(kirjautumisNakyma);
    }
    
}
