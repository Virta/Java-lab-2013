/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author virta
 */
public class AloitusNaymaKuuntelija implements  ActionListener {
    
    private JButton uusiPeli;
    private JButton tulokset;
    private JButton kirjaudu;
    private JButton lopeta;
    
    public AloitusNaymaKuuntelija(JButton uusiPeli, JButton tulokset, JButton kirjaudu, JButton lopeta, JFrame frame){
        this.uusiPeli = uusiPeli;
        this.tulokset = tulokset;
        this.kirjaudu = kirjaudu;
        this.lopeta = lopeta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uusiPeli){
            
        } else if (e.getSource() == tulokset){
            
        } else if (e.getSource() == kirjaudu){
            
        } else {
            
        }
    }
    
    private void aloitaUusiPeli(){
        
    }
    
    private void avaaTulokset(){
        
    }
    
    private void kirjaudu(){
        
    }
    
}
