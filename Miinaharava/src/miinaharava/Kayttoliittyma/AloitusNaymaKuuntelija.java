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
 * Tämä luokka kuuntelee aloitusnäkymässä tapahtuvia nappuloiden panalluksia ja kutsuu muita luokkia painallusten mukaisesti.
 *
 * @author virta
 */
public class AloitusNaymaKuuntelija implements  ActionListener {
    
    private JButton uusiPeli;
    private JButton tulokset;
    private JButton kirjaudu;
//    private JButton lopeta;
//    private JFrame frame;
    
    public AloitusNaymaKuuntelija(JButton uusiPeli, JButton tulokset, JButton kirjaudu){
        this.uusiPeli = uusiPeli;
        this.tulokset = tulokset;
        this.kirjaudu = kirjaudu;
//        this.lopeta = lopeta;
//        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uusiPeli){
            
        } else if (e.getSource() == tulokset){
            
        } else if (e.getSource() == kirjaudu){
            
        } 
    }
    
    private void aloitaUusiPeli(){
        
    }
    
    private void avaaTulokset(){
        
    }
    
    private void kirjaudu(){
        
    }
    
}
