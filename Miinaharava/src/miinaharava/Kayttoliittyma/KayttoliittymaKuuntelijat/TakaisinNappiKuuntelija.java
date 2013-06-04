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
 *
 * @author virta
 */
public class TakaisinNappiKuuntelija implements ActionListener {
    
    private SisaltoFrame nakyma;
    private JButton takaisinNappi;
    
    public TakaisinNappiKuuntelija(JButton nappi, SisaltoFrame nakyma){
        this.nakyma = nakyma;
        this.takaisinNappi = nappi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AloitusNakyma aloitusNakyma = new AloitusNakyma(nakyma);
        SwingUtilities.invokeLater(aloitusNakyma);
    }
    
}
