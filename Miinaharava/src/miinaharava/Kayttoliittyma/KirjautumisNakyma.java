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
import javax.swing.JTextField;
import miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat.KirjautumisKuuntelija;
import miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat.TakaisinNappiKuuntelija;

/**
 *
 * @author virta
 */
public class KirjautumisNakyma implements Runnable {

    private SisaltoFrame nakyma;
    private JFrame frame;

    public KirjautumisNakyma(SisaltoFrame nakyma) {
        this.nakyma = nakyma;
        this.frame = nakyma.getFrame();
    }

    @Override
    public void run() {
        frame.getContentPane().removeAll();
        frame.setPreferredSize(new Dimension(500, 200));
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(new JLabel("Miinaharava - kirjautuminen"));

        if (!this.nakyma.getKirjautunutNimimerkki().equals("Anon")) {
            container.add(luoUlosKirjautuminen());
        } else {
            container.add(luoSisaanKirjautuminen());
        }
        container.add(luoTakaisinNappi());
    }

    private Component luoSisaanKirjautuminen() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.X_AXIS));

        JLabel kehote = new JLabel("Syötä nimimerkkisi: ");
        JTextField nimimerkkiSyotto = new JTextField();
        JLabel viestikentta = new JLabel();
        
        JButton luoNimimerkkiNappi = new JButton("Luo uusi");
        JButton sisaanKirjaaNappi = new JButton("     OK     ");
        KirjautumisKuuntelija kuuntelija = new KirjautumisKuuntelija(sisaanKirjaaNappi, nimimerkkiSyotto, viestikentta, luoNimimerkkiNappi, nakyma);
        sisaanKirjaaNappi.addActionListener(kuuntelija);
        luoNimimerkkiNappi.addActionListener(kuuntelija);

        paneeli.add(kehote);
        paneeli.add(luoNimiJaViestiKenttaPaneeli(nimimerkkiSyotto, viestikentta));
        paneeli.add(luoKirjaamisNapitPaneeli(sisaanKirjaaNappi, luoNimimerkkiNappi));
        
        return paneeli;
    }
    
    private Component luoNimiJaViestiKenttaPaneeli(JTextField nimikentta, JLabel viestikentta){
        JPanel kentat = new JPanel();
        kentat.setLayout(new BoxLayout(kentat, BoxLayout.Y_AXIS));
        
        kentat.add(nimikentta);
        kentat.add(viestikentta);
        return kentat;
    }

    private Component luoKirjaamisNapitPaneeli(JButton sisaanNappi, JButton uusiNimimerkkiNappi) {
        JPanel napit = new JPanel();
        napit.setLayout(new BoxLayout(napit, BoxLayout.Y_AXIS));

        napit.add(sisaanNappi);
        napit.add(uusiNimimerkkiNappi);
        return napit;
    }

    private Component luoUlosKirjautuminen() {
        frame.getContentPane().removeAll();
        
        JPanel napit = new JPanel();
        napit.setLayout(new BoxLayout(napit, BoxLayout.Y_AXIS));
        
        JButton ulosKirjaamisNappi = new JButton("Kirjaudu ulos");
        KirjautumisKuuntelija kuuntelija = new KirjautumisKuuntelija(ulosKirjaamisNappi, nakyma);
        ulosKirjaamisNappi.addActionListener(kuuntelija);
        napit.add(ulosKirjaamisNappi);
        return napit;
    }

    private JButton luoTakaisinNappi() {
        JButton takaisinNappi = new JButton("Takaisin");
        TakaisinNappiKuuntelija kuuntelija = new TakaisinNappiKuuntelija(takaisinNappi, nakyma);
        takaisinNappi.addActionListener(kuuntelija);
        return takaisinNappi;
    }
    
}
