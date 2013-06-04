/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat.PaavalikkoKuuntelija;

/**
 * 
 * Tämä luokka on miinaharava-pelin graafisen käyttöliittymän alku; täältä pelaaja valitsee haluamansa toiminnot aina kun ohjelma suoritetaan.
 *
 * @author virta
 */
public class AloitusNakyma implements Runnable {
    
    private JFrame frame;
    private SisaltoFrame nakyma;

    
    public AloitusNakyma(SisaltoFrame nakyma){
        this.nakyma = nakyma;
        this.frame = nakyma.getFrame();
    }

    @Override
    public void run() {
        frame.getContentPane().removeAll();
        frame.setPreferredSize(new Dimension(500, 200));
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container container){
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(lisaaAloitusteksti());
        
        container.add(lisaaAloitusNapit());
        
    }
    
    public JFrame getFrame(){
        return frame;
    }

    private Component lisaaAloitusteksti() {
        JPanel ylaPaneeli = new JPanel();
        ylaPaneeli.setLayout(new BoxLayout(ylaPaneeli, BoxLayout.Y_AXIS));
        ylaPaneeli.add(new JLabel("Tervetuloa pelaamaan miinaharavaa!"));
        if (!this.nakyma.getKirjautunutNimimerkki().equals("Anon")){
            ylaPaneeli.add(new JLabel("Olet kirjautuneena nimimerkillä "+this.nakyma.getKirjautunutNimimerkki()+"."));
        }
        ylaPaneeli.add(new JLabel("Valitse jokin seuraavista:"));
        return ylaPaneeli;
    }

    private Component lisaaAloitusNapit() {
        JPanel keskipaneeli = new JPanel();
        keskipaneeli.setLayout(new BoxLayout(keskipaneeli, BoxLayout.Y_AXIS));
        JButton uusiPeliNappula = new JButton("Uusi peli");
        JButton tulosNappula = new JButton("Tulokset");
        JButton kirjauduNappula = new JButton("Kirjaudu");
//        JButton lopeta = new JButton("Lopeta");
        
        lisaaKuuntelija(uusiPeliNappula, tulosNappula, kirjauduNappula);
        
        lisaaNapitPaneeliin(keskipaneeli, uusiPeliNappula, tulosNappula, kirjauduNappula);
        
        return keskipaneeli;
    }

    private void lisaaKuuntelija(JButton uusiPeliNappula, JButton tulosNappula, JButton kirjauduNappula) {
        PaavalikkoKuuntelija kuuntelija = new PaavalikkoKuuntelija(tulosNappula, kirjauduNappula, uusiPeliNappula, nakyma);
        uusiPeliNappula.addActionListener(kuuntelija);
        tulosNappula.addActionListener(kuuntelija);
        kirjauduNappula.addActionListener(kuuntelija);
        
    }

    private void lisaaNapitPaneeliin(JPanel keskipaneeli, JButton uusiPeliNappula, JButton tulosNappula, JButton kirjauduNappula) {
        keskipaneeli.add(uusiPeliNappula);
        keskipaneeli.add(tulosNappula);
        keskipaneeli.add(kirjauduNappula);
    }
    
}
