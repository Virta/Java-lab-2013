/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat.KelloPaivittaja;
import miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat.PelikenttaKuuntelija;
import miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat.TakaisinNappiKuuntelija;
import miinaharava.Pelikentta.Moottori;

/**
 *
 * @author virta
 */
public class PelikenttaNakyma implements Runnable {

    private SisaltoFrame nakyma;
    private JFrame frame;
    private Moottori moottori;
    private KenttaProfiili profiili;
    private JButton[][] soluPainikkeet;
    private JLabel kello;
    private JLabel miinatieto;
    private KelloPaivittaja paivittaja;

    public PelikenttaNakyma(SisaltoFrame nakyma, KenttaProfiili profiili) {
        this.nakyma = nakyma;
        this.frame = nakyma.getFrame();
        this.profiili = profiili;
    }

    @Override
    public void run() {
        frame.getContentPane().removeAll();
        frame.setPreferredSize(new Dimension(profiili.getKoko()*40, profiili.getKoko()*30+20));
        
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container container){
        container.add(luoAlkutekstit());
        luoMoottori();
        container.add(luoKelloJaMiinaKentat());
        container.add(luoPelikentta());
        container.add(luoTakaisinNappi());
    }
    
    private Component luoAlkutekstit(){
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        
        JLabel teksti = new JLabel("Miinaharava - peli: "+ profiili.getNimi());
        paneeli.add(teksti);
        
        return paneeli;
    }
    
    private Component luoTakaisinNappi(){
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        
        JButton takaisinNappi = new JButton("Takaisin");
        TakaisinNappiKuuntelija kuuntelija = new TakaisinNappiKuuntelija(takaisinNappi, nakyma);
        takaisinNappi.addActionListener(kuuntelija);
        
        paneeli.add(takaisinNappi);
        return paneeli;
    }
    
    private Component luoPelikentta(){
        JPanel paneeli = new JPanel();
        
        paneeli.add(luoSolutKenttaan());
        return paneeli;
    }
    
    private Component luoSolutKenttaan(){
        JPanel soluPaneeli = new JPanel(new GridLayout(profiili.getKoko(), profiili.getKoko()));
        soluPainikkeet = new JButton[profiili.getKoko()][profiili.getKoko()];
        
        luoSoluPainikkeet();
        PelikenttaKuuntelija soluKuuntelija = new PelikenttaKuuntelija(nakyma, soluPainikkeet, this.kello);
        lisaaKuuntelija(soluKuuntelija);
        
        for (int i = 0; i < soluPainikkeet.length; i++) {
            for (int j = 0; j < soluPainikkeet.length; j++) {
                soluPaneeli.add(soluPainikkeet[i][j]);
            }
        }
        
        return soluPaneeli;
    }

    private void luoSoluPainikkeet() {
        for (int i = 0; i < soluPainikkeet.length; i++) {
            for (int j = 0; j < soluPainikkeet.length; j++) {
                soluPainikkeet[i][j] = new JButton(" ");
            }
        }
    }

    private void lisaaKuuntelija(PelikenttaKuuntelija soluKuuntelija) {
        for (int i = 0; i < soluPainikkeet.length; i++) {
            for (int j = 0; j < soluPainikkeet.length; j++) {
                soluPainikkeet[i][j].addActionListener(soluKuuntelija);
            }
        }
    }
    
    private void luoMoottori(){
        this.moottori=new Moottori(profiili);
    }
    
    private Component luoKelloJaMiinaKentat(){
        JPanel kelloPaneeli = new JPanel();
        kelloPaneeli.setLayout(new BoxLayout(kelloPaneeli, BoxLayout.X_AXIS));
        
        int aika = (int) moottori.getAika();
        String aikaString = (aika/60)+":"+(aika-(aika/60));
        
        this.kello = new JLabel(aikaString);
        this.miinatieto = new JLabel(moottori.getKentta().getMiinojaJaljella()+"");
        this.paivittaja = new KelloPaivittaja(kello, moottori, miinatieto);
        
        kelloPaneeli.add(kello);
        return kelloPaneeli;
    }
    
}
