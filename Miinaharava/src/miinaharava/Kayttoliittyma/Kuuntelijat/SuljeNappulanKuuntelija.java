/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.Kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Kuuntelija pelitulosikkunan napille.
 * @author virta
 */
public class SuljeNappulanKuuntelija implements ActionListener {
    
    /**
     * JFrame-olio pelikentästä.
     */
    private JFrame pelikenttaFrame;
    
    /**
     * JFrame-olio tulosikkunasta.
     */
    private JFrame tulosikkunaFrame;
    
    /**
     * JButton-olio, sulje.
     */
    private JButton suljePainike;
    
    /**
     * JButton-olio; myös sisällytettävä, jotta sen voi asettaa painettavaksi kun tulosikkuna suljetaan.
     */
    private JButton takaisinNappi;
    
    /**
     * Konstruktorissa alustetaan vain sisäiset muuttujat.
     * @param peliKenttaFrame
     * @param tulosIkkunaFrame
     * @param sulkeNappi
     * @param takaisinNappi 
     */
    public SuljeNappulanKuuntelija(JFrame peliKenttaFrame, JFrame tulosIkkunaFrame, JButton sulkeNappi, JButton takaisinNappi){
        this.pelikenttaFrame = peliKenttaFrame;
        this.tulosikkunaFrame = tulosIkkunaFrame;
        this.suljePainike = sulkeNappi;
        this.takaisinNappi = takaisinNappi;
    }

    /**
     * Poistaa kaiken (dispose) tulosnäkymäikkunaan liittyvän, ja asettaa pelikentän takaisin napin painettavaksi, sekä kohdennettavaksi.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.tulosikkunaFrame.dispose();
        this.takaisinNappi.setEnabled(true);
        this.pelikenttaFrame.setFocusable(true);
    }
    
}
