/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat;

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
 *
 * @author virta
 */
public class KirjautumisKuuntelija implements ActionListener {
    
    private SisaltoFrame nakyma;
    private JButton kirjaaSisaanNappi;
    private JButton kirjaaUlosNappi;
    private JTextField nimiKentta;
    private JLabel viestikentta;
    private JButton luoUusiNimimerkkiNappi;
    
    public KirjautumisKuuntelija(JButton kirjaaUlosNappi, SisaltoFrame nakyma){
        this.nakyma = nakyma;
        this.kirjaaUlosNappi = kirjaaUlosNappi;
    }
    
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
    
    private void luoUusiNimimerkki(){
        String nimimerkki = nimiKentta.getText();
        if (nimimerkki.length()<11){
            this.nakyma.getPelaajat().put(nimimerkki, new Kayttaja(nimimerkki));
            this.nakyma.setKirjautunutNimimerkki(nimimerkki);
            palaaAloitusNakymaan();
        } else {
            this.nimiKentta.setText("");
            this.viestikentta.setText("Anatamasi nimimerkki liian pitkä, maksimipituus 10 merkkiä!");
        }
    }
    
    private void kirjaaUlos(){
        this.nakyma.setKirjautunutNimimerkki("Anon");
        palaaAloitusNakymaan();
    }
    
    private void palaaAloitusNakymaan(){
        AloitusNakyma aloitusNakyma = new AloitusNakyma(nakyma);
        SwingUtilities.invokeLater(aloitusNakyma);
    }
    
}
