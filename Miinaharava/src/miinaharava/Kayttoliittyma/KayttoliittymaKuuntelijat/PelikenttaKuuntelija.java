/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import miinaharava.Kayttoliittyma.SisaltoFrame;

/**
 *
 * @author virta
 */
public class PelikenttaKuuntelija implements ActionListener {
    
    private SisaltoFrame nakyma;
    private JButton[][] soluPainikkeet;
    
    public PelikenttaKuuntelija(SisaltoFrame nakyma, JButton[][] solut, JLabel kello){
        this.nakyma=nakyma;
        this.soluPainikkeet = solut;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
