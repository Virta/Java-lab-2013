/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Collection;
import java.util.LinkedList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat.TakaisinNappiKuuntelija;
import miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat.UudenPeliProfiilinLuontiKuuntelija;
import miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat.UudenPelinAloitusKuuntelija;

/**
 * Tämä luokka vastaa uuden pelin aloituksesta.
 * @author virta
 */
public class UudenPelinAloitusNakyma implements Runnable {
    
    /**
     * SisaltoFrame joka on kaikille käyttöliittymän näkymille yhteinen.
     */
    private SisaltoFrame nakyma;
    
    /**
     * JFrame, jonka ContentPane (Container-olio) tulee sisältämään kaikki piirrettävät komponentit.
     */
    private JFrame frame;
    
    /**
     * Käyttäjä joka haetaan SisaltoFramen käyttäjälistasta kirjautuneen nimimerkin perusteella.
     */
    private Kayttaja kayttaja;
    
    public UudenPelinAloitusNakyma(SisaltoFrame nakyma){
        this.nakyma =nakyma;
        this.frame = nakyma.getFrame();
        if (!this.nakyma.getKirjautunutNimimerkki().equals("Anon")){
            this.kayttaja = this.nakyma.getPelaajat().get(this.nakyma.getKirjautunutNimimerkki());
        }
    }

    @Override
    public void run() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setPreferredSize(new Dimension(500, 500));
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Luo piirrettävät komponentit parametrina saatuun container-olioon.
     * @param container 
     */
    private void luoKomponentit(Container container){
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(luoAlkuTekstit());
        container.add(luoVakioProfiilinapit());
        container.add(lisaaOmaProfiiliSaateTeksti(this.kayttaja!=null && !this.kayttaja.getKaikkiProfiilit().isEmpty()));
        if (kayttaja != null) {
            container.add(luoMuutProfiiliNapit());
            container.add(luoUudenProfiilinLuontiNappi());
        }
        container.add(luoTakaisinNappi());
    }
    
    /**
     * Luo JPanel-olion joka sisältää JLabel-olioita aloitustekstille ja saateviestille.
     * 
     * @return JPanel olion komponenttina, joka voidaan lisätä sisältöön, joka sisältää em. oliot.
     */
    private Component luoAlkuTekstit(){
        JPanel teksitPaneeli = new JPanel();
        teksitPaneeli.setLayout(new BoxLayout(teksitPaneeli, BoxLayout.Y_AXIS));
        
        JLabel tervehdys = new JLabel("Miinaharava - aloita uusi peli");
        JLabel pelimuodot = new JLabel("Aloita uusi peli alla olevista painikkeista!");
        
        teksitPaneeli.add(tervehdys);
        teksitPaneeli.add(pelimuodot);
        
        return teksitPaneeli;
    }
    
    /**
     * Luo JPanel-olion joka sisältää napit pelin aloittamiselle vakioprofiileista.
     * @return JPanel-olion sisältäen em. napit komponenttina, joka voidaan lisätä sisältöön.
     */
    private Component luoVakioProfiilinapit(){
        JPanel vakioProfiiliPaneeli = new JPanel();
        vakioProfiiliPaneeli.setLayout(new BoxLayout(vakioProfiiliPaneeli, BoxLayout.Y_AXIS));
        
        JPanel helppo = luoUusiPeliNappiPaneeli(this.nakyma.getVakioProfiilit().getHelppo());
        JPanel keskivaikea = luoUusiPeliNappiPaneeli(this.nakyma.getVakioProfiilit().getKeskiVaikea());
        JPanel vaikea = luoUusiPeliNappiPaneeli(this.nakyma.getVakioProfiilit().getVaikea());
        
        luoJaLisaaKuuntelijaVakioProfiileille(helppo, keskivaikea, vaikea);
        
        vakioProfiiliPaneeli.add(helppo);
        vakioProfiiliPaneeli.add(keskivaikea);
        vakioProfiiliPaneeli.add(vaikea);
        
        return vakioProfiiliPaneeli;
    }
    
    /**
     * Luo JPanel-olion johon lisätään napit pelin aloittamiselle käyttäjän omista profiileista.
     * @return JPanel-olion komponenttina, joka sisältää em. napit.
     */
    private Component luoMuutProfiiliNapit(){
        JPanel muutProfiilitPaneeli = new JPanel();
        muutProfiilitPaneeli.setLayout(new BoxLayout(muutProfiilitPaneeli, BoxLayout.Y_AXIS));
        
        LinkedList<JPanel> muutProfiiliNapitPaneelissa = new LinkedList<>();
        
        Collection<KenttaProfiili> pelaajanProfiilit = this.kayttaja.getKaikkiProfiilit().values();
        for (KenttaProfiili kenttaProfiili : pelaajanProfiilit) {
            muutProfiiliNapitPaneelissa.add(luoUusiPeliNappiPaneeli(kenttaProfiili));
        }
        
        luoJaLisaaKuuntelijaMuilleProfiileille(muutProfiiliNapitPaneelissa);
        
        for (JPanel profiiliPaneeli:muutProfiiliNapitPaneelissa){
            muutProfiilitPaneeli.add(profiiliPaneeli);
        }
        
        return muutProfiilitPaneeli;
    }
    
    /**
     * Luo JPanel-olion joka sisältää joko saateviestin käyttäjän profiileille, tai saateviestin ettei pelaaja ole kirjautunut.
     * @param onProfiileja
     * @return JPanel-olion komponenttina, joka sisältää em. JLabel-oliot.
     */
    private Component lisaaOmaProfiiliSaateTeksti(boolean onProfiileja){
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.X_AXIS));
        JLabel viesti = null;
        if (onProfiileja) {
            viesti = new JLabel("Omat profiilisi:");
        } else {
            viesti = new JLabel("Sinulla ei ole omia peliprofiileja. Kirjaudu luodaksesi profiileja!");
        }
        paneeli.add(viesti);
        return paneeli;
    }
    
    /**
     * Luo JPanel-olion joka sisältää JButton-olion jota painamalla pääsee uuden peliprofiilin luontiin.
     * @return JPanel-olion komponenttina, sisältäen em. oliot.
     */
    private Component luoUudenProfiilinLuontiNappi(){
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        
        JButton luoUusiProfiiliNappula = new JButton("Luo uusi profiili");
        UudenPeliProfiilinLuontiKuuntelija kuuntelija = new UudenPeliProfiilinLuontiKuuntelija(luoUusiProfiiliNappula, nakyma);
        luoUusiProfiiliNappula.addActionListener(kuuntelija);
        
        paneeli.add(luoUusiProfiiliNappula);
        return paneeli;
    }
    
    /**
     * Luo uuden JPanel-olion joka sisältää napin uuden pelin aloittamiseksi parametrina saadusta profiilista, sekä JLabel-olion jossa kentän kuvaus.
     * @param profiili
     * @return JPanel-olion komponenttina, joka sisältää em oliot.
     */
    private JPanel luoUusiPeliNappiPaneeli(KenttaProfiili profiili){
        JPanel nappiPaneeli = new JPanel();
        nappiPaneeli.setLayout(new BoxLayout(nappiPaneeli, BoxLayout.X_AXIS));
        
        JButton uusiPeliNappi = new JButton(profiili.getNimi());
        JLabel kenttaKuvaus = new JLabel("Koko: "+profiili.getKoko()+" x "+profiili.getKoko()+", miinoja: "+profiili.getMiinoja());
        
        nappiPaneeli.add(uusiPeliNappi);
        nappiPaneeli.add(kenttaKuvaus);
        
        return nappiPaneeli;
    }

    /**
     * Luo ja lisää parametrina saatuihin nappeihin uuden kuuntelijan vakioprofiili-aloitusnapeille.
     * @param helppo
     * @param keskivaikea
     * @param vaikea 
     */
    private void luoJaLisaaKuuntelijaVakioProfiileille(JPanel helppo, JPanel keskivaikea, JPanel vaikea) {
        JButton helppoNappi = (JButton) helppo.getComponent(0);
        JButton keskiVNappi = (JButton) keskivaikea.getComponent(0);
        JButton vaikeaN = (JButton) vaikea.getComponent(0);
        
        UudenPelinAloitusKuuntelija vakioKuuntelija = new UudenPelinAloitusKuuntelija(helppoNappi, keskiVNappi, vaikeaN, nakyma);
        
        helppoNappi.addActionListener(vakioKuuntelija);
        keskiVNappi.addActionListener(vakioKuuntelija);
        vaikeaN.addActionListener(vakioKuuntelija);
    }
    
    /**
     * Luo ja lisää parametrina saadun listan kullekin JPanel-olion sisältämälle JButton-oliolle toimintokuuntelijan.
     * @param paneelit 
     */
    private void luoJaLisaaKuuntelijaMuilleProfiileille(LinkedList<JPanel> paneelit){
        LinkedList<JButton> napit = new LinkedList<>();
        for (JPanel jPanel : paneelit) {
            napit.add((JButton) jPanel.getComponent(0));
        }
        for (JButton jButton : napit) {
            UudenPelinAloitusKuuntelija kuuntelija = new UudenPelinAloitusKuuntelija(jButton, nakyma, jButton.getText());
            jButton.addActionListener(kuuntelija);
        }
    }
    
    /**
     * Luo geneerisen takaisin-napin, joka vie päävalikkoon.
     * @return JButton-olion joka voidaan lisätä sisältöön.
     */
    private JButton luoTakaisinNappi() {
        JButton takaisinNappi = new JButton("Takaisin");
        TakaisinNappiKuuntelija kuuntelija = new TakaisinNappiKuuntelija(takaisinNappi, nakyma);
        takaisinNappi.addActionListener(kuuntelija);
        return takaisinNappi;
    }
    
}
