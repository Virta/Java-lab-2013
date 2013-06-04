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
 *
 * @author virta
 */
public class UudenPelinAloitusNakyma implements Runnable {
    
    private SisaltoFrame nakyma;
    private JFrame frame;
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
//        frame.setContentPane(new Container());
        frame.setPreferredSize(new Dimension(500, 500));
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
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
    
    private Component luoAlkuTekstit(){
        JPanel teksitPaneeli = new JPanel();
        teksitPaneeli.setLayout(new BoxLayout(teksitPaneeli, BoxLayout.Y_AXIS));
        
        JLabel tervehdys = new JLabel("Miinaharava - aloita uusi peli");
        JLabel pelimuodot = new JLabel("Aloita uusi peli alla olevista painikkeista!");
        
        teksitPaneeli.add(tervehdys);
        teksitPaneeli.add(pelimuodot);
        
        return teksitPaneeli;
    }
    
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
    
    private Component luoUudenProfiilinLuontiNappi(){
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        
        JButton luoUusiProfiiliNappula = new JButton("Luo uusi profiili");
        UudenPeliProfiilinLuontiKuuntelija kuuntelija = new UudenPeliProfiilinLuontiKuuntelija(luoUusiProfiiliNappula, nakyma);
        luoUusiProfiiliNappula.addActionListener(kuuntelija);
        
        paneeli.add(luoUusiProfiiliNappula);
        return paneeli;
    }
    
    private JPanel luoUusiPeliNappiPaneeli(KenttaProfiili profiili){
        JPanel nappiPaneeli = new JPanel();
        nappiPaneeli.setLayout(new BoxLayout(nappiPaneeli, BoxLayout.X_AXIS));
        
        JButton uusiPeliNappi = new JButton(profiili.getNimi());
        JLabel kenttaKuvaus = new JLabel("Koko: "+profiili.getKoko()+" x "+profiili.getKoko()+", miinoja: "+profiili.getMiinoja());
        
        nappiPaneeli.add(uusiPeliNappi);
        nappiPaneeli.add(kenttaKuvaus);
        
        return nappiPaneeli;
    }

    private void luoJaLisaaKuuntelijaVakioProfiileille(JPanel helppo, JPanel keskivaikea, JPanel vaikea) {
        JButton helppoNappi = (JButton) helppo.getComponent(0);
        JButton keskiVNappi = (JButton) keskivaikea.getComponent(0);
        JButton vaikeaN = (JButton) vaikea.getComponent(0);
        
        UudenPelinAloitusKuuntelija vakioKuuntelija = new UudenPelinAloitusKuuntelija(helppoNappi, keskiVNappi, vaikeaN, nakyma);
        
        helppoNappi.addActionListener(vakioKuuntelija);
        keskiVNappi.addActionListener(vakioKuuntelija);
        vaikeaN.addActionListener(vakioKuuntelija);
    }
    
    private void luoJaLisaaKuuntelijaMuilleProfiileille(LinkedList<JPanel> paneelit){
        LinkedList<JButton> napit = new LinkedList<>();
        for (JPanel jPanel : paneelit) {
            napit.add((JButton) jPanel.getComponent(0));
        }
        for (JButton jButton : napit) {
            UudenPelinAloitusKuuntelija kuuntelija = new UudenPelinAloitusKuuntelija(jButton, nakyma);
            jButton.addActionListener(kuuntelija);
        }
    }
    
    private JButton luoTakaisinNappi() {
        JButton takaisinNappi = new JButton("Takaisin");
        TakaisinNappiKuuntelija kuuntelija = new TakaisinNappiKuuntelija(takaisinNappi, nakyma);
        takaisinNappi.addActionListener(kuuntelija);
        return takaisinNappi;
    }
    
}
