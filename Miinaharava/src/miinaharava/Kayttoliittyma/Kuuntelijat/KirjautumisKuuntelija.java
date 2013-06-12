/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.Kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Kayttoliittyma.AloitusNakyma;
import miinaharava.Kayttoliittyma.SisaltoFrame;

/**
 * Tämä luokka vastaa kirjautumisnäkymän toiminnallisuudesta.
 * 
 * @author virta
 */
public class KirjautumisKuuntelija implements ActionListener {
    
    /**
     * SisaltoFrame, joka on kaikille näkymä- ja kuuntelijaluokille yhteinen, jonka ikkunaan komponentit piirretään.
     */
    private SisaltoFrame nakyma;
    
    /**
     * JButton-olio, jota kuunnellaan sisäänkirjautumisen toiminnallisuuden toteuttamiseksi.
     */
    private JButton kirjaaSisaanNappi;
    
    /**
     * JButton-olio, jota kuunnellaan uloskirjautumisen toiminnallisuuden toteuttamiseksi.
     */
    private JButton kirjaaUlosNappi;
    
    /**
     * JTextField-olio, josta haetaan nimimerkki käyttäjän painaessa sisäänkirjautumisnappia.
     */
    private JTextField nimiKentta;
    
    /**
     * JLabel-olio, johon tulostetaan käyttäjäystävällinen virheilmoitus jos sisäänkirjautuminen ei onnistunut.
     */
    private JLabel viestikentta;
    
    /**
     * JButton-olio, jota kuunnellaan uuden nimimerkin luonnin toiminnallisuuden toteuttamiseksi.
     */
    private JButton luoUusiNimimerkkiNappi;
    
    /**
     * Konstruktori uloskirjaamisen toteuttamiseksi, uusi kuuntelija tästä luokasta luodaan joko ulos- tai sisäänkirjautumisen toteuttamiseksi.
     * @param kirjaaUlosNappi
     * @param nakyma 
     */
    public KirjautumisKuuntelija(JButton kirjaaUlosNappi, SisaltoFrame nakyma){
        this.nakyma = nakyma;
        this.kirjaaUlosNappi = kirjaaUlosNappi;
    }
    
    /**
     * Konstruktori sisäänkirjaamisen toteuttamiseksi.
     * @param kirjaaSisaanNappi
     * @param nimikentta
     * @param viestikentta
     * @param luoNimimerkki
     * @param nakyma 
     */
    public KirjautumisKuuntelija(JButton kirjaaSisaanNappi, JTextField nimikentta, JLabel viestikentta, JButton luoNimimerkki, SisaltoFrame nakyma){
        this.nakyma = nakyma;
        this.kirjaaSisaanNappi = kirjaaSisaanNappi;
        this.nimiKentta = nimikentta;
        this.viestikentta = viestikentta;
        this.luoUusiNimimerkkiNappi = luoNimimerkki;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == kirjaaSisaanNappi){
            kirjaaSisaan();
        } else if (source == kirjaaUlosNappi){
            kirjaaUlos();
        } else if (source == luoUusiNimimerkkiNappi){
            luoUusiNimimerkki();
        }
    }
    
    /**
     * Hakee nimikentästä käyttäjän syötteen, tarkistaa onko samanmuotoista nimimerkkiä käyttäjäkannassa.
     * Jos käyttäjän syötettä ei ole käyttäjäkannassa, viestikenttään asetetaan virheilmoitus, muutoin palataan aloitusnäkymään
     * joka ilmoittaa viestillä onnistuneesta kirjautumisesta.
     */
    private void kirjaaSisaan(){
        String nimimerkki = nimiKentta.getText();
        if (this.nakyma.getPelaajat().containsKey(nimimerkki)){
            this.nakyma.setKirjautunutNimimerkki(nimimerkki);
            palaaAloitusNakymaan();
        } else {
            nimiKentta.setText("");
            viestikentta.setText("Nimimerkki kirjoitettu väärin tai olematon! Yritä uudestaan tai luo uusi.");
        }
    }
    
    /**
     * Hakee nimikentästä käyttäjän syötteen, tarkistaa onko käyttäjäkannassa samanmuotoista nimimerkkiä.
     * Jos Käyttäjäkannassa on samanmuotoinen nimimerkki uuden nimimerkin luominen ei onnistu ja näytetään viestikentässä virheilmoitus.
     * Jos Käyttäjäkannassa ei ole nimimerkkiä, luodaan uusi Kayttaja-olio kyseiselle nimimerkille ja lisätään se käyttäjäkantaan.
     */
    private void luoUusiNimimerkki(){
        String nimimerkki = nimiKentta.getText();
        if (nimimerkki.length()<11 && nimimerkki.length() > 0 && !nimimerkki.contains(" ")){
            this.nakyma.getPelaajat().put(nimimerkki, new Kayttaja(nimimerkki));
            this.nakyma.setKirjautunutNimimerkki(nimimerkki);
            palaaAloitusNakymaan();
        } else {
            this.nimiKentta.setText("");
            this.viestikentta.setText("Anatamasi nimimerkki liian pitkä, maksimipituus 10 merkkiä!");
        }
    }
    
    /**
     * Jos käyttäjä on kirjautunut hän voi vain painaa kirjaaulos-nappia jolloin kutsutaan tätä metodia;
     * asetetaan kirjautumisnimimerkiksi anonyymi ja palataan aloitusnäkymään.
     */
    private void kirjaaUlos(){
        this.nakyma.setKirjautunutNimimerkki("Anon");
        palaaAloitusNakymaan();
    }
    
    /**
     * Kutsutaan onnistuneen sisäänkirjaamisen, onnistuneen nimimerkin luonnin ja uloskirjaamisen yhteydessä aloitusnäkymään palaamiseen.
     */
    private void palaaAloitusNakymaan(){
        AloitusNakyma aloitusNakyma = new AloitusNakyma(nakyma);
        SwingUtilities.invokeLater(aloitusNakyma);
    }
    
}
