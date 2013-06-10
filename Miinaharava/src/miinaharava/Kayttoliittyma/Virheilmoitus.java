/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author virta
 */
public class Virheilmoitus {
    
    /**
     * Jos tulosten latauksessa tapahtuu virhe, kutsutaan tätä metodia, näytetään graafinen virheilmoitus; käyttäjän sulkiessa ikkunan ohjelma sulkeutuu.
     * @param virheilmoitus 
     */
    
    public static void naytaVirheilmoitus(String virheilmoitus){
        JFrame frame = new JFrame("Virhe!");
        frame.setPreferredSize(new Dimension(600, 100));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new JLabel("Ohjelmassa tapahtui virhe: "), BorderLayout.NORTH);
        frame.getContentPane().add(new JLabel(virheilmoitus), BorderLayout.SOUTH);
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
