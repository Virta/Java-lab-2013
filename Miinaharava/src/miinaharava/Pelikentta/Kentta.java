/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Pelikentta;

import miinaharava.Entiteetit.KenttaProfiili;

/**
 * 
 * Tämä luokka hallinnoi yhteenkytkettyjä soluja solumatrsiisissa, ja niiden toimintoja ja luo uuden matriisin aina uuden pelin alkaessa ylemmän Moottori-luokan kutsusta.
 *
 * @author virta
 */
public class Kentta {

    /**
     * Kentän sisäinen solu-matriisi.
     */
    private Solu[][] solut;
    /**
     * Kenttä tietää tässä muuttujassa kuinka paljon miinoja on liputtamatta, voi mennä negatiiviseksi.
     */
    private int miinojaJaljella;
    /**
     * Profiili jonka mukaan kenttä luodaan ja jonka arvojen avulla kentässä navigoidaan luokan sisäisesti.
     */
    private KenttaProfiili profiili;
    /**
     * Deprecated, eli vanhentunut eikä käytetä tällä hektellä, pitää kirjaa täytyykö lippujen määrä laskea uudelleen.
     */
    private boolean miinatietoPaivitettava;

    /**
     * Konstruktori alustaa luokan sisäiset muuttujat annetun profiilin perusteella ja luo kentän.
     * @param profiili KenttaProfiili jolla kenttä alustetaan.
     */
    public Kentta(KenttaProfiili profiili) {
        this.profiili = profiili;
        this.miinojaJaljella = profiili.getMiinoja();
//        miinatietoPaivitettava = false;

        luoKentta();

    }

    public int getMiinojaJaljella() {
//        if (miinatietoPaivitettava) {
//            paivitaMiinatieto();
//        }
        return this.miinojaJaljella;
    }

    public Solu getSolu(int x, int y) {
//        miinatietoPaivitettava = true;
        return this.solut[x][y];
    }

//    Mahdollisesti toteutettava, mennään ensin palauttamalla solu kutsuvalle metodille. Jos toteutettava, muista miinatiedon päivitys.
//    private void setFlagi(int x, int y){
//        this.solut[x][y].setFlagit();
//    }
//    
//    private int getFlagi(int x, int y){
//        return this.solut[x][y].getFlagi();
//    }
//    
//    public boolean isMiina(int x, int y){
//        return this.solut[x][y].isMiina();
//    }
//    
//    public int vieressaMiinoja(int x, int y){
//        return this.solut[x][y].getVieressaMiinoja();
//    }
    
    /**
     * Metodi, jota käytetään käyttäjän asettaessa lipun solulle.
     * Samalla päivitetään kentän miinatieto, eli kuinka paljon kentässä on liputtamattomia miinoja.
     * 
     * @param x Solun, jonka lippu asetetaan, x-koordinaatti.
     * @param y Solun, jonka lippu asetetaan, y-koordinaatti.
     */
    public void asetaFlagi(int x, int y){
        this.solut[x][y].setFlagit();
        int flagi = this.solut[x][y].getFlagi();
        if (flagi == 1){
            this.miinojaJaljella--;
        } else if (flagi == 2) {
            this.miinojaJaljella++;
        }
    }
    
    /**
     * Deprecated, eli tällä hetkellä ei käytetä mutta jää vielä toistaiseksi, päivittää kentän miinatiedon laskemalla kaikkien solujen liput.
     */
    private void paivitaMiinatieto() {
        int miinat = profiili.getMiinoja();
        for (int i = 0; i < profiili.getKoko(); i++) {
            for (int k = 0; k < profiili.getKoko(); k++) {
                if (solut[i][k].getFlagi() == 1) {
                    miinat--;
                }
            }
        }
        miinojaJaljella = miinat;
        miinatietoPaivitettava = false;
    }

    /**
     * Kutsutaan kostruktorissa kentän luomiseksi, käyttää luokan sisäistä kenttäprofiilia.
     * 
     * luoSolut(koko) kutsutaan solujen itsensä alustamiseksi matriisiin.
     * luoMiinat(miinoja, koko) kutsutaan jotta soluihin asetetaan miinoja.
     * paivitaJaLinkita(koko) kutsutaan, jotta solujen miinatieto päivitetään ja solut linkitetään toisiinsa.
     * 
     */
    private void luoKentta() {
        int koko = this.profiili.getKoko();
        int miinoja = this.profiili.getMiinoja();
        
        luoSolut(koko);
        luoMiinat(miinoja, koko);
        paivitaJaLinkita(koko);
    }

    /**
     * Käy läpi solun ympäristön, ei solua itseään, ja linkittää soluun ympärillä olevat solut.
     * Samalla jos ympärillä on miinoja päivitetään solun miinatieto.
     * 
     * @param x Solun, joka päivitetään, x-koordinaatti.
     * @param y Solun, joka päivitetään, y-koordinaatti.
     */
    private void paivitaJaLinkitaSolu(int x, int y) {
        Solu solu = solut[x][y];
        
        for (int i=x-1;i<x+2;i++){
            for (int k=y-1;k<y+2;k++){
                if (onKartalla(i, k) && solut[i][k]==solu){
                    k++;
                }
                if (onKartalla(i, k)){
                    solu.lisaaVierusSolu(solut[i][k]);
                    if (solut[i][k].isMiina() && !solu.isMiina()){
                        solu.lisaaViereenMiinoja();
                    }
                }
            }
        }

    }
    
    /**
     * Tällä tarkistetaan onko annetut koordinaatit kentässä.
     * 
     * @param x Tarkistettava x-koordinaatti.
     * @param y Tarkistettava y-koordinaatti.
     * @return Palauttaa true, jos koordinaatit profiilin määrittelyn sisällä, false jos ulkopuolella.
     */
    private boolean onKartalla(int x, int y) {
        return (x >= 0 && y >= 0 && x < solut.length && y < solut.length);
    }

    /**
     * Initialisoi kentän solumatriisiin solut.
     * 
     * @param koko Kentän koko, jotta silmukka toimii.
     */
    private void luoSolut(int koko) {
        this.solut = new Solu[koko][koko];
        for (int i = 0; i < koko; i++) {
            for (int k = 0; k < koko; k++) {
                Solu s = new Solu();
                solut[i][k] = s;
            }
        }
    }

    /**
     * Luo kenttään miinat, jossa ei vielä yhtään miinoja.
     * 
     * Käyttää Math.random menetelmää arpoakseen koordinaatit. Random antaa luvun 0..1, tämä kerrotaan kentän koolla, jotta saadaan hajonta koko kentän alueelle.
     * 
     * @param miinoja Kuinka monta miinaa kenttään luodaan.
     * @param koko Kentän koko, jotta metodi arpoo koordinaatit kentän sisälle.
     */
    private void luoMiinat(int miinoja, int koko) {
        for (int m = 0; m < miinoja; m++) {
            int x = (int) (Math.random() * (koko));
            int y = (int) (Math.random() * (koko));
            if (!solut[x][y].isMiina()) {
                solut[x][y].setMiina();
            } else {
                m--;
            }
        }
    }

    /**
     * Käy läpi solumatriisin kaikki solut, ja kutsuu jokaiselle tarkempaa metodia, joka tekee varsinaisen työn.
     * 
     * @param koko Kentän koko, jotta silmukka toimii.
     */
    private void paivitaJaLinkita(int koko) {
        for (int i = 0; i < koko; i++) {
            for (int k = 0; k < koko; k++) {
                if (!solut[i][k].isMiina()) {
                    paivitaJaLinkitaSolu(i, k);
                }
            }
        }
    }
}
