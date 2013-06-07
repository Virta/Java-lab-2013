/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.Kuuntelijat;

import javax.swing.JLabel;
import miinaharava.Pelikentta.Moottori;

/**
 * Tämä luokka vastaa pelikentän kellon päivittämisestä.
 * Tästä luokasta luotu olio käynnistetään omassa säikeessää, jotta em. tieto päivittyy jatkuvasti pelin käynnistymisen jälkeen.
 * @author virta
 */
public class KelloPaivittaja implements Runnable {
    
    /**
     * JLabel-olio, johon päivitetään pelin aloituksesta kulunut aika sekunteina.
     */
    private JLabel kello;
    
    /**
     * Moottori, jota pelikenttä käyttää, josta kysellään kellon ja miinatiedon informaatio.
     */
    private Moottori moottori;
    
    /**
     * Sisäinen totuusarvo, jota käytetään säikeen lopetuksen yhteydessä jos pelistä poistutaan ennen kuin peli loppui onnistuneesti.
     */
    private boolean keskeyta;
    
    /**
     * Konstruktori ottaa vastaan JLabel-olio kelloksi, sekä moottorin jolta informaatio päivityksen yhteydessä haetaan.
     * @param kello
     * @param moottori
     */
    public KelloPaivittaja(JLabel kello, Moottori moottori){
        this.kello = kello;
        this.moottori = moottori;
        this.keskeyta = false;
    }

    /**
     * Kun tämän luokan olio luodaan säikeeseen ja säie käynnistetään aloitetaan while-silmukka joka toistuu kunnes peli loppui
     * onnistuneesti tai kunnes sisäinen muuttuja keskeyta saa arvon true, eli tätä luokkaa käyttävä luokka on kutsunut
     * tämän luokan metodia keskeytaPaivitys().
     */
    @Override
    public void run() {
        while (true){
            
            try {
                Thread.sleep(500);
            } catch (Exception e){
                
            }
            
            int aika = (int) moottori.getAika();
            String aikaString = (aika/60)+":"+(aika-((aika/60)*60))+"   ";
            kello.setText(aikaString);
            
            if (keskeyta){
                break;
            }
            
        }
    }

    /**
     * Asettaa sisäisen muuttujan keskeyta-arvoksi true, jolloin run()-metodin while silmukka voidaan keskeyttää tappamatta säiettä
     * jossa tämä luokka on ajossa.
     */
    public void keskeytaPaivitys(){
        this.keskeyta=true;
    }
    
}
