/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Tallennus;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author virta
 */
public class tallennuksenVirheilmoitus {
    
    /**
     * Jos tulosten latauksessa tapahtuu virhe, kutsutaan tätä metodia, näytetään graafinen virheilmoitus; käyttäjän sulkiessa ikkunan ohjelma sulkeutuu.
     * @param virheilmoitus 
     */
    
    public static void naytaVirheilmoitus(String virheilmoitus){
        JFrame frame = new JFrame("Virhe!");
        frame.setPreferredSize(new Dimension(600, 100));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        frame.getContentPane().setLayout(new GridLayout(2, 1));
        frame.getContentPane().add(new JLabel("Tulosten käsittelyssä tapahtui virhe:"));
        frame.getContentPane().add(new JLabel(virheilmoitus));
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
