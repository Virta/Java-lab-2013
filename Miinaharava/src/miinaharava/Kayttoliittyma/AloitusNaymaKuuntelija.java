/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * Tämä luokka kuuntelee aloitusnäkymässä tapahtuvia nappuloiden panalluksia ja
 * kutsuu muita luokkia painallusten mukaisesti.
 *
 * @author virta
 */
public class AloitusNaymaKuuntelija implements ActionListener {

    private JButton uusiPeli;
    private JButton tulokset;
    private JButton kirjaudu;
//    private JButton lopeta;
    private JFrame frame;
    private AloitusNakyma nakyma;

    public AloitusNaymaKuuntelija(JButton uusiPeli, JButton tulokset, JButton kirjaudu, JFrame frame, AloitusNakyma nakyma) {
        this.uusiPeli = uusiPeli;
        this.tulokset = tulokset;
        this.kirjaudu = kirjaudu;
//        this.lopeta = lopeta;
        this.frame = frame;
        this.nakyma = nakyma;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uusiPeli) {
            aloitaUusiPeli();
        } else if (e.getSource() == tulokset) {
            avaaTulokset();
        } else if (e.getSource() == kirjaudu) {
            kirjautuminen();
        }
    }

    private void aloitaUusiPeli() {
    }

    private void avaaTulokset() {
        TulosNakyma tulosNakyma = new TulosNakyma(nakyma.getPelaajat(), nakyma.getPeliProfiilit(), nakyma.getTulokset());
        frame.setVisible(false);
        SwingUtilities.invokeLater(tulosNakyma);
        frame.setVisible(true);
    }

    private void kirjautuminen() {
    }
}
