/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.Kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import miinaharava.Kayttoliittyma.SisaltoFrame;
import miinaharava.Pelikentta.Moottori;
import miinaharava.Pelikentta.Solu;

/**
 * Tämä luokka vastaa pelikentässä tapahtuvien painallusten toiminnallisuuden
 * toteuttamisesta, se on vielä kesken.
 *
 * @author virta
 */
public class PelikenttaKuuntelija implements MouseListener {

    /**
     * SisaltoFrame, joka on kaikille näkymä- ja kuuntelijaluokille yhteinen
     * jonka ikkunaan komponentit piirretään.
     */
    private SisaltoFrame nakyma;
    /**
     * Matriisi solupainikkeita, joista varsinainen pelin kulku tapahtuu.
     */
    private JButton[][] soluPainikkeet;
    /**
     * Kellokenttä, johon kellon arvo päivittyy.
     */
    private JLabel kelloKentta;
    /**
     * Miinatietokenttä, johon liputtamattomien miinojen määrä päivittyy.
     */
    private JLabel miinatietoKentta;
    /**
     * Pelimoottori, jota tämä luokka käyttää.
     */
    private Moottori moottori;
    /**
     * KelloPaivittaja-olio, joka käynnistetään tästä luokasta.
     */
    private KelloPaivittaja paivittaja;
    /**
     * Säie johon päivittäjä liitetään, joka käynnistetään tästä luokasta.
     */
    private Thread paivittajaSaie;
    /**
     * Sisäinen muuttuja, jolla ehkäistään säikeen aloittaminen aina uudestaan.
     */
    private boolean paivittajaAloitettu;
    /**
     * Tuloksen muodostamiseen tarvittu profiili.
     */
    private KenttaProfiili profiili;


    public PelikenttaKuuntelija(SisaltoFrame nakyma, JButton[][] solut, JLabel kello, JLabel miinatieto, Moottori moottori, KelloPaivittaja paivittaja, KenttaProfiili profiili) {
        this.nakyma = nakyma;
        this.soluPainikkeet = solut;
        this.kelloKentta = kello;
        this.miinatietoKentta = miinatieto;
        this.soluPainikkeet = solut;
        this.moottori = moottori;
        this.paivittaja = paivittaja;
        this.paivittajaSaie = new Thread(this.paivittajaSaie);
        this.paivittajaAloitettu = false;
        this.profiili = profiili;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int[] koords = haeKoordinaatit(e.getSource());

        int mouseEvent = e.getButton();
        switch (mouseEvent) {
            case 1:
                avaaYksi(koords[0], koords[1]);
                break;
            case 2:
                avaaMonta(koords[0], koords[1]);
                break;
            case 3:
                asetaLippu(koords[0], koords[1]);
                break;
        }
    }

    private void avaaYksi(int x, int y) {
        boolean palaute = moottori.aukaiseYksi(x, y);
        toimiPalautteesta(palaute);
    }

    private void luoTulos(boolean loppuikoOnnistuneesti) {
        Tulos tulos = new Tulos(kelloKentta.getText(), profiili, nakyma.getKirjautunutNimimerkki(), loppuikoOnnistuneesti);
        this.nakyma.getTulokset().add(tulos);
    }

    private void naytaLopetusNakyma() {
        for (int i = 0; i < soluPainikkeet.length; i++) {
            for (int j = 0; j < soluPainikkeet.length; j++) {
                JButton soluPainike = soluPainikkeet[i][j];
                Solu solu = moottori.getKentta().getSolu(j, j);
                soluPainike.setEnabled(false);
                if (solu.isMiina()) {
                    soluPainike.setText("O");
                } else if (solu.isMiina() && solu.getFlagi() > 0) {
                    soluPainike.setText("X");
                }
            }
        }
    }

    private void paivitaNakyma() {
        for (int i = 0; i < soluPainikkeet.length; i++) {
            for (int j = 0; j < soluPainikkeet.length; j++) {
                JButton soluPainike = soluPainikkeet[i][j];
                Solu solu = moottori.getKentta().getSolu(i, j);
                if (solu.isAuki()) {
                    if (solu.getVieressaMiinoja() > 0) {
                        soluPainike.setText(moottori.getKentta().getSolu(i, j).getVieressaMiinoja() + "");
                    } else {
                        soluPainike.setEnabled(false);
                    }
                } else {
                    asetaNapilleFlagi(solu, soluPainike);
                }
            }
        }
    }

    private void asetaLippu(int x, int y) {
        if (!moottori.getKentta().getSolu(x, y).isAuki()) {
            moottori.getKentta().asetaFlagi(x, y);
            paivitaNakyma();
        }
    }

    private void avaaMonta(int x, int y) {
        boolean palaute = moottori.aukaiseMonta(x, y);
        toimiPalautteesta(palaute);
    }

    private int[] haeKoordinaatit(Object source) {
        for (int i = 0; i < soluPainikkeet.length; i++) {
            boolean keskeyta = false;
            for (int j = 0; j < soluPainikkeet.length; j++) {
                if (source == soluPainikkeet[i][j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private void toimiPalautteesta(boolean palaute) {
        if (palaute) {
            if (!paivittajaAloitettu) {
                paivittajaSaie.start();
                paivittajaAloitettu = true;
            }

            paivitaNakyma();

            if (moottori.peliLoppuiOnnistuneesti()) {
                paivittaja.keskeytaPaivitys();
                luoTulos(palaute);
                naytaLopetusNakyma();
            }

        } else {
            paivittaja.keskeytaPaivitys();
            luoTulos(palaute);
            naytaLopetusNakyma();
        }
    }

    private void asetaNapilleFlagi(Solu solu, JButton soluPainike) {
        int flagi = solu.getFlagi();
        String soluText = "";
        switch (flagi) {
            case 0:
                soluText = " ";
                break;
            case 1:
                soluText = "!";
                break;
            case 2:
                soluText = "?";
        }
        soluPainike.setText(soluText);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
}
