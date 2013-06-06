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
 * Tämä luokka on miinaharava-pelin graafisen käyttöliittymän päävalikko; täältä pelaaja valitsee haluamansa toiminnot aina kun ohjelma suoritetaan.
 *
 * @author virta
 */
public class AloitusNakyma implements Runnable {
    
    /**
     * JFrame jonka container-olioon lisätään kaikki komponentit.
     */
    private JFrame frame;
    
    /**
     * SisaltoFrame, joka on kaikille käyttöliittymän näkymille sama, muutetaan siis ainoastaan sen sisältöä eikä luoda aina uutta ikkunaa.
     */
    private SisaltoFrame nakyma;

    
    public AloitusNakyma(SisaltoFrame nakyma){
        this.nakyma = nakyma;
        this.frame = nakyma.getFrame();
    }

    @Override
    public void run() {
        
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setPreferredSize(new Dimension(500, 200));
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private Component naytaContainerinSisalto(Container container){
        JFrame uusiFrame = new JFrame("sialto");
        uusiFrame.setContentPane(container);
        uusiFrame.pack();
        return uusiFrame;
    }
    
    /**
     * Luodaan komponentit container-olioon: aloitus tekstit ja -napit.
     * @param container Ottaa vastaan container-olion, johon kaikki komponentit lisätään.
     */
    
    private void luoKomponentit(Container container){
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(lisaaAloitusteksti());
        container.add(lisaaAloitusNapit());
    }
    
    public JFrame getFrame(){
        return frame;
    }

    /**
     * Lisää aloitustekstit: tervehdysteksti, sekä kirjautumis-ilmoituksen jos on kirjautunut.
     * @return JPanel-olion komponenttina jossa mainitut tekstit.
     */
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

    /**
     * Lisää napit, joista voidaan valita toimenpide.
     * @return JPanel-olion lisättävänä komponenttina, jossa mainitut napit.
     */
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

    /**
     * Lisää parametrina saaduille napeille yhteisen kuuntelijan, joka suorittaa nappien painallusten mukaiset toimenpiteet käyttöliittymän uudelleenpiirtämisessä.
     * @param uusiPeliNappula JButton uuden pelin aloittamiseksi.
     * @param tulosNappula JButton tulosnäkymään siirtymiseksi.
     * @param kirjauduNappula JButton kirjautumisnäkymään siirtymiseksi.
     */
    private void lisaaKuuntelija(JButton uusiPeliNappula, JButton tulosNappula, JButton kirjauduNappula) {
        PaavalikkoKuuntelija kuuntelija = new PaavalikkoKuuntelija(tulosNappula, kirjauduNappula, uusiPeliNappula, nakyma);
        uusiPeliNappula.addActionListener(kuuntelija);
        tulosNappula.addActionListener(kuuntelija);
        kirjauduNappula.addActionListener(kuuntelija);
        
    }

    /**
     * Lisää parametrina saadut napit parametrina saatuun JPanel-olioon.
     * @param keskipaneeli JPanel, johon napit lisätään.
     * @param uusiPeliNappula JButton uuden pelin aloittamiseksi.
     * @param tulosNappula JButton tulosnäkymään siirtymiseksi.
     * @param kirjauduNappula JButton kirjautumisnäkymään siirtymiseksi.
     */
    private void lisaaNapitPaneeliin(JPanel keskipaneeli, JButton uusiPeliNappula, JButton tulosNappula, JButton kirjauduNappula) {
        keskipaneeli.add(uusiPeliNappula);
        keskipaneeli.add(tulosNappula);
        keskipaneeli.add(kirjauduNappula);
    }
    
}
