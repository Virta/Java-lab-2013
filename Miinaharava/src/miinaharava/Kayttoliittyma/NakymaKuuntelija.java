/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import miinaharava.Entiteetit.Tulos;

/**
 *
 * Tämä luokka kuuntelee aloitusnäkymässä tapahtuvia nappuloiden panalluksia ja
 * kutsuu muita luokkia painallusten mukaisesti.
 *
 * @author virta
 */
public class NakymaKuuntelija implements ActionListener {
    
    private JButton uusiPeli;
    private JButton tulokset;
    private JButton kirjaudu;
    private JFrame frame;
    private SisaltoFrame nakyma;
    private JButton takaisinNappula;
    
    public NakymaKuuntelija(JButton uusiPeli, JButton tulokset, JButton kirjaudu, SisaltoFrame nakyma) {
        this.uusiPeli = uusiPeli;
        this.tulokset = tulokset;
        this.kirjaudu = kirjaudu;
        lisaaNakymaJaFrame(nakyma);
    }
    
    public NakymaKuuntelija(JButton takaisinNappula, SisaltoFrame nakyma) {
        lisaaNakymaJaFrame(nakyma);
        this.takaisinNappula = takaisinNappula;
    }
    
    private void lisaaNakymaJaFrame(SisaltoFrame nakyma) {
        this.nakyma = nakyma;
        this.frame = nakyma.getFrame();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uusiPeli) {
            aloitaUusiPeli();
        } else if (e.getSource() == tulokset) {
            avaaTulokset();
        } else if (e.getSource() == kirjaudu) {
            kirjautuminen();
        } else if (e.getSource() == takaisinNappula) {
            takaisinAloitusNakymaan();
        }
    }
    
    private void aloitaUusiPeli() {
    }
    
    private void avaaTulokset() {
        TulosNakyma tulosNakyma = new TulosNakyma(nakyma);
        SwingUtilities.invokeLater(tulosNakyma);
    }
    
    private void kirjautuminen() {
    }
    
    private void takaisinAloitusNakymaan() {
        AloitusNakyma aloitusNakyma = new AloitusNakyma(this.nakyma);
        SwingUtilities.invokeLater(aloitusNakyma);
    }
}
