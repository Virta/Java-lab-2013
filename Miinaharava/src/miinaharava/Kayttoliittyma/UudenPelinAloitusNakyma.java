/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author virta
 */
public class UudenPelinAloitusNakyma implements Runnable {
    
    private SisaltoFrame nakyma;
    private JFrame frame;
    
    public UudenPelinAloitusNakyma(SisaltoFrame nakyma){
        this.nakyma =nakyma;
        this.frame = nakyma.getFrame();
    }

    @Override
    public void run() {
        frame.getContentPane().removeAll();
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container container){
        
    }
    
}
