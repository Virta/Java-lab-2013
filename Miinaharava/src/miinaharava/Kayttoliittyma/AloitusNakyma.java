/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import miinaharava.Entiteetit.VakioProfiilit;

/**
 * 
 * Tämä luokka on koko miinaharava-pelin graafisen käyttöliittymän alku ja juuri; täältä pelaaja valitsee haluamansa toiminnot aina kun ohjelma suoritetaan.
 *
 * @author virta
 */
public class AloitusNakyma implements Runnable {
    
    private JFrame frame;
    private HashMap<String, Kayttaja> pelaajat;
    HashMap<String, KenttaProfiili> peliProfiilit;
    HashMap<String, KenttaProfiili> vakioProfiilit;
    LinkedList<Tulos> tulokset;
    
    public AloitusNakyma (HashMap<String, Kayttaja> kayttajat, HashMap<String, KenttaProfiili> profiilit, VakioProfiilit vakioProfiilit, LinkedList<Tulos> tulokset){
        this.pelaajat = kayttajat;
        this.peliProfiilit = profiilit;
        this.tulokset = tulokset;
        this.vakioProfiilit = new HashMap<>();
        this.vakioProfiilit.put("helppo", vakioProfiilit.getHelppo());
        this.vakioProfiilit.put("keskivaikea", vakioProfiilit.getKeskiVaikea());
        this.vakioProfiilit.put("vaikea", vakioProfiilit.getVaikea());
        
        
    }

    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
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
        JPanel ylaPaneeli = new JPanel(new GridLayout(2, 1));
        ylaPaneeli.add(new JLabel("Tervetuloa pelaamaan miinaharavaa!"));
        ylaPaneeli.add(new JLabel("Valitse jokin seuraavista:"));
        container.add(ylaPaneeli, BorderLayout.NORTH);
    }

    private void lisaaAloitusNapit(Container container) {
        JPanel keskipaneeli = new JPanel(new GridLayout(4, 1));
        JButton uusiPeli = new JButton("Uusi peli");
        JButton tulokset = new JButton("Tulokset");
        JButton kirjaudu = new JButton("Kirjaudu");
//        JButton lopeta = new JButton("Lopeta");
        
        ActionListener aloitusnakumaKuuntelija = new AloitusNaymaKuuntelija(uusiPeli, tulokset, kirjaudu);
        uusiPeli.addActionListener(aloitusnakumaKuuntelija);
        tulokset.addActionListener(aloitusnakumaKuuntelija);
        kirjaudu.addActionListener(aloitusnakumaKuuntelija);
//        lopeta.addActionListener(aloitusnakumaKuuntelija);
        
        keskipaneeli.add(uusiPeli);
        keskipaneeli.add(tulokset);
        keskipaneeli.add(kirjaudu);
//        keskipaneeli.add(lopeta);
        
        container.add(keskipaneeli, BorderLayout.CENTER);
    }
    
}
