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
    private JLabel kelloKentta;
    private JLabel miinatietoKentta;
    
    public PelikenttaKuuntelija(SisaltoFrame nakyma, JButton[][] solut, JLabel kello, JLabel miinatieto){
        this.nakyma=nakyma;
        this.soluPainikkeet = solut;
        this.kelloKentta = kello;
        this.miinatietoKentta = miinatieto;
        this.soluPainikkeet = soluPainikkeet;
    }

    @Override
    public void actionPerformed(ActionEvent e) { Thread thread;
        
    }
    
}
