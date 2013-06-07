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
 * Tämä luokka vastaa pelikentässä tapahtuvien painallusten toiminnallisuuden toteuttamisesta, se on vielä kesken.
 * @author virta
 */
public class PelikenttaKuuntelija implements ActionListener {
    
    /**
     * SisaltoFrame, joka on kaikille näkymä- ja kuuntelijaluokille yhteinen jonka ikkunaan komponentit piirretään.
     */
    private SisaltoFrame nakyma;
    
    /**
     * Matriisi solupainikkeita, joista varsinainen pelin kulku tapahtuu.
     */
    private JButton[][] soluPainikkeet;
    
    /**
     * Kellokenttä, johon kellon arvo päivittyy.
     */
    private JLabel kelloKentta;
    
    /**
     * Miinatietokenttä, johon liputtamattomien miinojen määrä päivittyy.
     */
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
