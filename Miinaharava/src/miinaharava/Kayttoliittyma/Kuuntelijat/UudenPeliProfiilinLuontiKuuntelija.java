/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.Kuuntelijat;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Kayttoliittyma.PeliProfiilinLuontiNakyma;
import miinaharava.Kayttoliittyma.SisaltoFrame;
import miinaharava.Kayttoliittyma.UudenPelinAloitusNakyma;

/**
 * Tämä luokka vastaa uuden peliprofiilin luonnin toiminnallisuuden toteuttamisesta.
 * 
 * @author virta
 */
public class UudenPeliProfiilinLuontiKuuntelija implements ActionListener {
   
    /**
     * SisaltoFrame, joka on kaikille näkymä- ja kuuntelijaluokille yhteinen, jonka ikkunaan komponentit piirretään.
     */
    private SisaltoFrame nakyma;
    
    /**
     * JButton-olio jota kuunnellaan uuden profiilin luomisen toteutusta varten.
     */
    private JButton luoUusiProfiiliNappi;
    
    /**
     * JCOmboBox-olio jonka sisältö haetaan peliprofiilin luonnin yhteydessä osoittamaan kentän kokoa.
     */
    private JComboBox kentanKoko;
    
    /**
     * JCOmboBox-olio jonka sisältö haetaan peliprofiilin luonnin yhteydessä osoittamaan miinojen määrää kentässä.
     */
    private JComboBox miinoja;
    
    /**
     * JButton-olio, jota kuunnellaan peliprofiilin luontiin siirtymiseksi.
     */
    private JButton uudenProfiilinLuontiinNappi;
    
    /**
     * JTextField-olio johon asetetaan käyttäjäystävällinen virheilmoitus jos profiilin luonti ei onnistunut.
     */
    private JLabel viestikentta;
    
    /**
     * JLablel-olio, jonka sisältö haetaan peliprofiilin luonnin yhteydessä osoittamaan nimeä jonka käyttäjä asettaa peliprofiililleen.
     */
    private JTextField profiiliNimi;
    
    /**
     * Tätä konstruktoria kutsutaan, kun varsinainen peliprofiilin luontinäkymä luodaan ja käynnistetään.
     * @param nappi
     * @param nakyma
     * @param kentanKoko
     * @param miinoja
     * @param viestikentta
     * @param profiiliNimi 
     */
    public UudenPeliProfiilinLuontiKuuntelija(JButton nappi, SisaltoFrame nakyma, JComboBox kentanKoko, JComboBox miinoja, JLabel viestikentta, JTextField profiiliNimi){
        this.nakyma = nakyma;
        this.luoUusiProfiiliNappi=nappi;
        this.kentanKoko = kentanKoko;
        this.miinoja = miinoja;
        this.viestikentta = viestikentta;
        this.profiiliNimi = profiiliNimi;
    }
    
    /**
     * Tätä konstruktoria kutsutaan kun luodaan ja käynnistetään uuden pelin aloitusnäkymä; aloitusnäkymän kuuntelija oli täynnä siksi täällä.
     * @param nappi
     * @param nakyma 
     */
    public UudenPeliProfiilinLuontiKuuntelija(JButton nappi, SisaltoFrame nakyma){
        this.nakyma = nakyma;
        this.uudenProfiilinLuontiinNappi = nappi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == uudenProfiilinLuontiinNappi){
            profiilinLuontiin();
        } else if (source == luoUusiProfiiliNappi){
            luoUusiProfiili();
        }
    }
    
    /**
     * Kutsutaan jos käyttäjä painaa luo uusi profiili -nappulaa uuden pelin aloitusnäkymässä.
     * Luodaan ja käynnistetään olio luokasta UudenPeliprofiilinLuontiNakyma.
     */
    private void profiilinLuontiin(){
        PeliProfiilinLuontiNakyma peliProfiilinLuontiNakyma = new PeliProfiilinLuontiNakyma(nakyma);
        SwingUtilities.invokeLater(peliProfiilinLuontiNakyma);
    }
    
    /**
     * Kutsutaan kun käyttäjä painaa valmis-nappia peliprofiilin luontinäkymässä.
     * Haetaan JComboBox-olioista kentän kooksi ja miinojen määräksi asetetut arvot, ja muunnetaan ne kokonaisluvuiksi.
     * Jos jostain syystä muuntaminen ei onnistunut näytetään käyttäjäystävällinen virheilmoitus.
     * Kutsutaan metodia tarkistaJaToimi(..), uuuden peliprofiilin luomiseksi.
     */
    private void luoUusiProfiili(){
        String kentanKokoString = (String) kentanKoko.getSelectedItem();
        String miinojaString = (String) miinoja.getSelectedItem();
        int kentanKokoInteger = 0;
        int miinojaInteger = 0;
        
        try {
            kentanKokoInteger = Integer.parseInt(kentanKokoString);
            miinojaInteger = Integer.parseInt(miinojaString);
        } catch (Exception e) {
            naytaVirheilmoitus(e.getMessage());
        }
        
        tarkistaJaToimi(miinojaInteger, kentanKokoInteger);
    }
    
    /**
     * Jos ohjelma heittää poikkeuksen profiilin luonnin yhteydessä, näytetään käyttäjäystävällinen virheilmoitus
     * omassa ikkunassaan, ja poistutaan ohjelmasta sen sulkemisen yhteydessä.
     * @param virheilmoText 
     */
    private void naytaVirheilmoitus(String virheilmoText){
        this.nakyma.getFrame().setVisible(false);
        
        JFrame virheFrame = new JFrame("Virhe ohjelmassa!");
        virheFrame.setPreferredSize(new Dimension(100, 100));
        virheFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        virheFrame.getContentPane().add(new JLabel(virheilmoText));
        
        virheFrame.pack();
        virheFrame.setVisible(true);
    }

    /**
     * Jos käyttäjän antamat parametrit kentän koolle, miinojen määrälle ja nimelle ovat valideja kutsutaan tätä metodia,
     * joka luo uuden KenttaProfiili-olion ja tallentaa sen käyttäjän profiileihin ja peliprofiili-hajautustauluun.
     * Lopuksi palataan uuden pelin aloitusnäkymään.
     * @param profiiliNimiString
     * @param kentanKokoInteger
     * @param miinojaInteger 
     */
    private void luoProfiiliJaPalaaPaavalikkoon(String profiiliNimiString, int kentanKokoInteger, int miinojaInteger) {
        KenttaProfiili uusiProfiili = new KenttaProfiili(profiiliNimiString, kentanKokoInteger, miinojaInteger);
        this.nakyma.getPeliProfiilit().put(profiiliNimiString, uusiProfiili);
        this.nakyma.getPelaajat().get(this.nakyma.getKirjautunutNimimerkki()).addProfiili(uusiProfiili);
        
        UudenPelinAloitusNakyma uudenPelinAloitusNakyma = new UudenPelinAloitusNakyma(nakyma);
        SwingUtilities.invokeLater(uudenPelinAloitusNakyma);
    }

    /**
     * Tarkistaa onko käyttäjän antamat parametrit kentän koolle, miinojen määrälle ja nimelle validit;
     * jos ei näytetään asianmukainen virheilmoitus, muuten kutsutaan metodia luoProfiiliJaPalaaPaavalikkoon(..).
     * @param miinojaInteger
     * @param kentanKokoInteger 
     */
    private void tarkistaJaToimi(int miinojaInteger, int kentanKokoInteger) {
        if (miinojaInteger < kentanKokoInteger*kentanKokoInteger){
            String profiiliNimiString = this.profiiliNimi.getText();
            if (profiiliNimiString.length()<11 && profiiliNimiString.length() >0 && !this.nakyma.getPeliProfiilit().keySet().contains(profiiliNimiString)){
                luoProfiiliJaPalaaPaavalikkoon(profiiliNimiString, kentanKokoInteger, miinojaInteger);
            } else {
                viestikentta.setText("Profiilinimi ei kelpaa: on jo käytössä tai väärän pituinen.");
            }
        } else {
            viestikentta.setText("Liikaa miinoja valitussa kentän koossa, valitse toinen kombinaatio!");
        }
    }
    
}
