/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.Color;
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
import miinaharava.Kayttoliittyma.Kuuntelijat.KelloPaivittaja;
import miinaharava.Kayttoliittyma.Kuuntelijat.PelikenttaKuuntelija;
import miinaharava.Kayttoliittyma.Kuuntelijat.TakaisinNappiKuuntelija;
import miinaharava.Pelikentta.Moottori;

/**
 * Tämä luokka vastaa miinaharavan pelin varsinaisesta pelinäkymästä.
 *
 * @author virta
 */
public class PelikenttaNakyma implements Runnable {

    /**
     * Kaikille käyttöliittymän näkymille yhteinen pohjaikkuna.
     */
    private SisaltoFrame nakyma;
    /**
     * JFrame, jonka ContentPane (Container-olio) tulee sisältämään näytettävät
     * komponentit.
     */
    private JFrame frame;
    /**
     * Pelimoottori, jota tämän luokan kuuntelija PelikenttaKuuntelija-olio
     * käyttää pelin kulussa, koska se luodaan omassa metodissa se sisällytetään
     * sisäisenä muuttujana joka on helpompi antaa uudelle kuuntelijalle.
     */
    private Moottori moottori;
    /**
     * Kenttäprofiili jonka mukaan kentän JPanel-olion GridLayout, moottori ja
     * sisäinen muuttuja soluPainikkeet alustetaan.
     */
    private KenttaProfiili profiili;
    /**
     * Matriisi johon tallennetaan solupainikkeet jotka piirretään
     * käyttöliittymässä.
     */
    private JButton[][] soluPainikkeet;
    /**
     * JLabel, johon päivitetään peliin kulunut aika, sisäisenä muuttujana jotta
     * alustaminen ja myhöempi toiminnallisuuden vastuun siirtäminen
     * kuuntelijalle ja sitä myöten KelloPäivittäjälle onnistuu.
     */
    private JLabel kello;
    /**
     * JLabel, johon päivitetään liputettujen miinojen määrä, sisäisenä
     * muuttujana jotta alustaminen ja myöhempi toiminnallisuuden vastuun
     * siirtäminen kuuntelijalle ja sitä myöten KelloPaivittaja-oliolle
     * onnistuu.
     */
    private JLabel miinatieto;
    /**
     * KelloPaivittaja-olio, joka alustetaan tässä luokassa, ja jonka
     * varsinainen toiminnallisuuden aloittaminen siirretään
     * PelikenttaKuuntelija-luokan oliolle.
     */
    private KelloPaivittaja paivittaja;
    /**
     * Sisäisenä muuttujana, jotta nappi voidaan antaa kuuntelijalle.
     */
    private JButton takaisinNappi;

    public PelikenttaNakyma(SisaltoFrame nakyma, KenttaProfiili profiili) {
        this.nakyma = nakyma;
        this.frame = nakyma.getFrame();
        this.profiili = profiili;
    }

    @Override
    public void run() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setPreferredSize(new Dimension(profiili.getKoko() * 45, profiili.getKoko() * 35));
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luo ja lisää kaikki näkymän piirrettävät komponentit parametrina saatuun
     * Container-olioon.
     *
     * @param container
     */
    private void luoKomponentit(Container container) {
        container.add(luoAlkutekstit());
        luoMoottori();
        container.add(luoKelloMiinaKentatTakaisinNappi());
        container.add(luoPelikentta());
    }

    /**
     * Luo JPanel-olion joka sisältää alkutekstin.
     *
     * @return JPanel-olion komponenttina sisältäen em. oliot, joka voidaan
     * lisätä sisältöön.
     */
    private Component luoAlkutekstit() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));

        JLabel teksti = new JLabel("Miinaharava - peli: " + profiili.getNimi());
        paneeli.add(teksti);

        return paneeli;
    }

    /**
     * Luo JPanel-olion johon lisätään geneerinen takaisin nappi joka vie aina
     * päävalikkoon.
     *
     * @return JPanel-olion komponenttina sisältäen em. olion, joka voidaan
     * lisätä sisältöön.
     */
    private Component luoTakaisinNappi() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));

        JButton takaisinNappi = new JButton("Takaisin");
        TakaisinNappiKuuntelija kuuntelija = new TakaisinNappiKuuntelija(takaisinNappi, nakyma);
        takaisinNappi.addActionListener(kuuntelija);

        this.takaisinNappi = takaisinNappi;
        paneeli.add(takaisinNappi);
        return paneeli;
    }

    /**
     * Luo JPanel-olion johon lisätään metodin luoSolutKenttaan() palauttama
     * komponentti.
     *
     * @return JPanel-olion komponenttina, sisältäen kaikki pelikenttään
     * liittyvät solut.
     */
    private Component luoPelikentta() {
        JPanel paneeli = new JPanel();

        paneeli.add(luoSolutKenttaan());
        return paneeli;
    }

    /**
     * Luo uuden JPanel-olion johon luodaan uusi GridLayout joka alustetaan
     * profiilin tiedoilla. Kutsuu metodia luoSoluPainikkeet painikkeiden
     * alustamiseksi. Luo uuden PelikenttaKuuntelija-olion jolle annetaan
     * SisaltoFrame, solupainikkeet, aiemmin luotu kello ja miinatietokenttä
     * sekä päivittäjä, pelimoottori. Lisää alustetut solut solumatriisista
     * (Solu[][]-olio) paneeliin piirrettäväksi.
     *
     * @return JPanel-olion komponenttina joka sisältää em. oliot.
     */
    private Component luoSolutKenttaan() {
        JPanel soluPaneeli = new JPanel(new GridLayout(profiili.getKoko(), profiili.getKoko()));
        soluPainikkeet = new JButton[profiili.getKoko()][profiili.getKoko()];

        luoSoluPainikkeet();
        PelikenttaKuuntelija soluKuuntelija = new PelikenttaKuuntelija(nakyma, soluPainikkeet, kello, miinatieto, moottori, paivittaja, profiili, takaisinNappi);
        lisaaKuuntelija(soluKuuntelija);

        for (int i = 0; i < soluPainikkeet.length; i++) {
            for (int j = 0; j < soluPainikkeet.length; j++) {
                soluPaneeli.add(soluPainikkeet[i][j]);
            }
        }

        return soluPaneeli;
    }

    /**
     * Alustaa kaikki solupainikkeet solumatriisiin tyhjällä arvolla.
     */
    private void luoSoluPainikkeet() {
        for (int i = 0; i < soluPainikkeet.length; i++) {
            for (int j = 0; j < soluPainikkeet.length; j++) {
                soluPainikkeet[i][j] = new JButton(" ");
            }
        }
    }

    /**
     * Lisää parametrina saadun kuuntelijan jokaiselle solulle solumatriisissa
     * (Solut[][]-olio).
     *
     * @param soluKuuntelija
     */
    private void lisaaKuuntelija(PelikenttaKuuntelija soluKuuntelija) {
        for (int i = 0; i < soluPainikkeet.length; i++) {
            for (int j = 0; j < soluPainikkeet.length; j++) {
                soluPainikkeet[i][j].addMouseListener(soluKuuntelija);
            }
        }
    }

    /**
     * Alustaa uuden moottorin tälle luokalle annetulla profiililla.
     */
    private void luoMoottori() {
        this.moottori = new Moottori(profiili);
    }

    /**
     * Luo JLabel-oliot kellolle ja miinatiedolle ja alustaa ne moottorilta
     * kyselyllä saatavilla tiedoilla. Luodaan samaan paneeliin myös
     * takaisin-nappi, jotta on ylhäällä saatavilla myös isossa kentässä.
     *
     * @return JPanel-olion komponenttina sisältäen em. oliot.
     */
    private Component luoKelloMiinaKentatTakaisinNappi() {
        JPanel kelloPaneeli = new JPanel();
        kelloPaneeli.setLayout(new BoxLayout(kelloPaneeli, BoxLayout.X_AXIS));

        int aika = (int) moottori.getAika();
        String aikaString = (aika / 60) + ":" + (aika - (aika / 60)) + "   ";

        this.kello = new JLabel(aikaString);
        this.miinatieto = new JLabel("     Miinoja: " + moottori.getKentta().getMiinojaJaljella());
        this.paivittaja = new KelloPaivittaja(kello, moottori);
        
        kelloPaneeli.add(kello);
        kelloPaneeli.add(luoTakaisinNappi());
        kelloPaneeli.add(miinatieto);
        return kelloPaneeli;
    }
}
