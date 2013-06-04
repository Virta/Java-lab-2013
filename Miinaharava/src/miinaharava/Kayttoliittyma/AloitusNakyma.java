/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
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
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container container){
        container.setLayout(new BorderLayout());
        lisaaAloitusteksti(container);
        
        lisaaAloitusNapit(container);
        
    }
    
    public JFrame getFrame(){
        return frame;
    }

    private void lisaaAloitusteksti(Container container) {
        JPanel ylaPaneeli = new JPanel(new GridLayout(3, 1));
        ylaPaneeli.add(new JLabel("Tervetuloa pelaamaan miinaharavaa!"));
        if (!this.nakyma.getKirjautunutNimimerkki().equals("Anon")){
            ylaPaneeli.add(new JLabel("Olet kirjautuneena nimimerkillä "+this.nakyma.getKirjautunutNimimerkki()+"."));
        }
        ylaPaneeli.add(new JLabel("Valitse jokin seuraavista:"));
        container.add(ylaPaneeli, BorderLayout.NORTH);
    }

    private void lisaaAloitusNapit(Container container) {
        JPanel keskipaneeli = new JPanel(new GridLayout(4, 1));
        JButton uusiPeliNappula = new JButton("Uusi peli");
        JButton tulosNappula = new JButton("Tulokset");
        JButton kirjauduNappula = new JButton("Kirjaudu");
//        JButton lopeta = new JButton("Lopeta");
        
        lisaaKuuntelija(uusiPeliNappula, tulosNappula, kirjauduNappula);
        
        lisaaNapitPaneeliin(keskipaneeli, uusiPeliNappula, tulosNappula, kirjauduNappula);
        
        container.add(keskipaneeli, BorderLayout.CENTER);
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
