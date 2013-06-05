/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat;

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
 *
 * @author virta
 */
public class UudenPeliProfiilinLuontiKuuntelija implements ActionListener {
    
    private SisaltoFrame nakyma;
    private JButton luoUusiProfiiliNappi;
    private JComboBox kentanKoko;
    private JComboBox miinoja;
    private JButton uudenProfiilinLuontiinNappi;
    private JLabel viestikentta;
    private JTextField profiiliNimi;
    
    public UudenPeliProfiilinLuontiKuuntelija(JButton nappi, SisaltoFrame nakyma, JComboBox kentanKoko, JComboBox miinoja, JLabel viestikentta, JTextField profiiliNimi){
        this.nakyma = nakyma;
        this.luoUusiProfiiliNappi=nappi;
        this.kentanKoko = kentanKoko;
        this.miinoja = miinoja;
        this.viestikentta = viestikentta;
        this.profiiliNimi = profiiliNimi;
    }
    
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
    
    private void profiilinLuontiin(){
        PeliProfiilinLuontiNakyma peliProfiilinLuontiNakyma = new PeliProfiilinLuontiNakyma(nakyma);
        SwingUtilities.invokeLater(peliProfiilinLuontiNakyma);
    }
    
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
    
    private void naytaVirheilmoitus(String virheilmoText){
        this.nakyma.getFrame().setVisible(false);
        
        JFrame virheFrame = new JFrame("Virhe ohjelmassa!");
        virheFrame.setPreferredSize(new Dimension(100, 100));
        virheFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        virheFrame.getContentPane().add(new JLabel(virheilmoText));
        
        virheFrame.pack();
        virheFrame.setVisible(true);
    }

    private void luoProfiiliJaPalaaPaavalikkoon(String profiiliNimiString, int kentanKokoInteger, int miinojaInteger) {
        KenttaProfiili uusiProfiili = new KenttaProfiili(profiiliNimiString, kentanKokoInteger, miinojaInteger);
        this.nakyma.getPeliProfiilit().put(profiiliNimiString, uusiProfiili);
        this.nakyma.getPelaajat().get(this.nakyma.getKirjautunutNimimerkki()).addProfiili(uusiProfiili);
        
        UudenPelinAloitusNakyma uudenPelinAloitusNakyma = new UudenPelinAloitusNakyma(nakyma);
        SwingUtilities.invokeLater(uudenPelinAloitusNakyma);
    }

    private void tarkistaJaToimi(int miinojaInteger, int kentanKokoInteger) {
        if (miinojaInteger < kentanKokoInteger*kentanKokoInteger){
            String profiiliNimiString = this.profiiliNimi.getText();
            if (profiiliNimiString.length()<11 && !this.nakyma.getPeliProfiilit().keySet().contains(profiiliNimiString)){
                luoProfiiliJaPalaaPaavalikkoon(profiiliNimiString, kentanKokoInteger, miinojaInteger);
            } else {
                viestikentta.setText("Profiilinimi ei kelpaa: on jo käytössä tai väärän pituinen.");
            }
        } else {
            viestikentta.setText("Liikaa miinoja valitussa kentän koossa, valitse toinen kombinaatio!");
        }
    }
    
}
