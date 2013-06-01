package miinaharava.Pelikentta;

import java.util.LinkedList;
import miinaharava.Entiteetit.KenttaProfiili;

/**
 * Varsinainen pelimoottori; käyttöliittymä kutsuu tämän luokan metodeja aina pelin edetessä, ja saa palautteen kustakin toiminnosta; 
 * tämä luokka käyttää Kenttä-luokkaa joka hallinnoi itse soluja ja niiden kytkeytymisiä.
 * 
 *
 * @author virta
 */
public class Moottori {

    /**
     * Kenttä moottorin sisäiseen käyttöön.
     */
    private Kentta kentta;
    /**
     * Kenttäprofiili jolla moottori alustetaan, jota käytetään kentän luomiseen
     * ja navigoimiseen.
     */
    private KenttaProfiili profiili;
    
    /**
     * Totuusarvo, jolla vältetään kellon asettaminen useasti.
     */
    boolean kelloAloitettu;
    
    /**
     * Muuttuja, johon taltioidaan järjsestelmän aika aloitushetkellä.
     */
    private double aikaAlussa;
    
    /**
     * Muuttuja, johon taltioidaan järjestelmän aika lopetushetkellä.
     */
    private double aikaLopussa;

    /**
     * Alustaa uuden moottorin annetulla profiililla, kenttä luodaan
     * konstruktorissa annetusta profiilista.
     *
     * @param profiili KenttaProfiili jolla Moottori alustetaan ja jolla kenttä
     * luodaan.
     */
    public Moottori(KenttaProfiili profiili) {
        this.profiili = profiili;
        this.kentta = new Kentta(profiili);
        this.aikaAlussa = 0;
        this.aikaLopussa = 0;
        this.kelloAloitettu = false;
    }

    public Kentta getKentta() {
        return this.kentta;
    }
    
    /**
     * Palautetaan aika, joka peliin on kulunut.
     * 
     * Jos metodia kutsutaan kesken pelin, eli aikaLopussa on nolla, palautetaan tähän asti kulunut aika.
     * Muussa tapauksessa peli on päättynyt ja aikaLopussa on suurempi kuin nolla, palautetaan kentän pelaamiseen kulunut aika.
     * 
     * @return Palauttaa metodin kutsuhetkellä kulunut aika pelin aloituksesta, tai pelin lopetukseen kulunut aika.
     */
    public double getAika(){
        if (this.aikaLopussa==0){
            return (System.currentTimeMillis()-this.aikaAlussa)/1000;
        }
        return (this.aikaLopussa-this.aikaAlussa)/1000;
    }

    /**
     * Aukaisee yhden solun annetuissa koordinaateissa.
     *
     * Ei avaa solua joka on jo auki, eikä sellaista solua joka on liputettu
     * miinaksi. Jos avattava solu on miina, palautetaan -1, eli peli päättyy.
     * Samalla avataan kentän kaikki solut, paljastaen pelaajan virheet. Jos
     * avattavalla solulla on vieressä miinoja palautetaan 0 ja avataan ko.
     * solu. Jos avattavalla solulla ei ole vieressä miinoja, eli on nk.
     * vaaraton, avataan samalla kaikki viereiset vaarattomat solut ja jokainen
     * niiden viereinen solu jolla on vieressä miinoja. Kun pelaaja aukaisee ensimmäisen solun
     * otetaan tämänhetkinen aika talteen, kun taas peli loppuu onnistuneesti
     * otetaan senhetkinen aika talteen.
     *
     * @param x
     * @param y
     * @return Palauttaa 0 jos aukaisu onnistui, eli solu ei ollut miina.
     * Palauttaa -1 jos avattu solu oli miina.
     */
    public int aukaiseYksi(int x, int y) {
        Solu solu = kentta.getSolu(x, y);
        
        if (!kelloAloitettu) {
            this.aikaAlussa = System.currentTimeMillis();
            kelloAloitettu = true;
        }
        
        if (solu.isAuki() || solu.getFlagi() == 1) {
            return 0;
            
        } else if (solu.isMiina()) {
            aukaiseKaikki();
            this.aikaLopussa = System.currentTimeMillis();
            return -1;
            
        } else if (solu.getVieressaMiinoja() > 0) {
            solu.setAuki();
            return 0;
            
        } else {
            LinkedList<Solu> pino = new LinkedList<>();
            pino.add(solu);
            while (!pino.isEmpty()) {
                solu = pino.pop();
                solu.setAuki();
                for (Solu s : solu.getVierukset()) {
                    if (s.getVieressaMiinoja() == 0 && !s.isAuki()) {
                        pino.add(s);
                    } else {
                        s.setAuki();
                    }
                }
            }
        }
        
        if (peliLoppuiOnnistuneesti() && kelloAloitettu) {
            this.aikaLopussa = System.currentTimeMillis();
        }
        
        return 0;
    }

    /**
     * Avaa useita soluja lähtien annetuista koordinaateista.
     *
     * Käyttäjä voi yrittää avata 3x3 muotoisen alueen, jonka keskipiste on
     * tässä solussa. Jos solu on kiinni, ei tästä pisteestä voi avata useita
     * soluja. Jos tämä solu on auki, mutta sen osoittama määrä vieressä olevia
     * miinoja ei ole liputettu, ei solua voi avata. Esim: jos tässä solussa on
     * merkitty 2 viereistä miinaa ja tämän solun ympäröivistä soluista vain 1
     * on liputettu miinaksi, avaus ei onnistu. Vastaavasti jos miinalippuja on
     * oikea määrä tai enemmän, aukaisu onnistuu ja metodissa käydään läpi
     * kaikki ympärillä olevat solut aukaiseYksi - metodilla, palauttaen -1 jos
     * oli virheellinen liputus, 0 jos avaus onnistui.
     *
     * @param x Solun, josta halutaan avata monta, x-koordinaatti.
     * @param y Solun, josta halutaan avata monta, y-koordinaatti.
     * @return Palauttaa 0 jos avaus onnistui, -1 jos liputettu väärin ja ...
     * miina.
     */
    public int aukaiseMonta(int x, int y) {
        if (!kentta.getSolu(x, y).isAuki()) {
            return 0;
        }
        
        if (flagienMaaraOikein(x, y) > 0) {
            return 0;
        }
        
        int palautettava = 0;
        for (int i = x - 1; i < x + 2; i++) {
            for (int k = y - 1; k < y + 2; k++) {
                if (onKartalla(i, k)) {
                    int avausPalaute = aukaiseYksi(i, k);
                    if (avausPalaute == -1) {
                        palautettava = -1;
                    }
                }
            }
        }
        
        return palautettava;
    }

    /**
     * Laskee tämän solun ympäröivien solujen miinalippujen määrän.
     *
     * @param x Solun, jonka ympäristö lasketaan, x-koordinaatti.
     * @param y Solun, jonka ympäristö lasketaan, y-koordinaatti.
     * @return Palauttaa lippujen - ja miinojen määrän erotuksen: -1 jos liikaa
     * lippuja, 0 jos sama määrä lippuja, 1 jos liian vähän lippuja.
     */
    private int flagienMaaraOikein(int x, int y) {
        Solu solu = kentta.getSolu(x, y);
        
        int miinaLippuja = 0;
        for (int i = x - 1; i < x + 2; i++) {
            for (int k = y - 1; k < y + 2; k++) {
                if (onKartalla(i, k) && kentta.getSolu(i, k)==solu) {
                    k++;
                }
                if (onKartalla(i, k) && !kentta.getSolu(i, k).isAuki() && kentta.getSolu(i, k).getFlagi() == 1) {
                    miinaLippuja++;
                }
            }
        }

        return kentta.getSolu(x, y).getVieressaMiinoja() - miinaLippuja;

    }

    /**
     * Tarkistaa onko peli päättynyt.
     * 
     * Solut käydään läpi yksitellen, ja jos löytyy sellainen solu joka ei ole miina kiinni, palautetaan false, eli peli ei ole päättynyt.
     *
     * @return true, jos päättynyt, false jos vielä kesken.
     */
    public boolean peliLoppuiOnnistuneesti() {
        for (int i = 0; i < profiili.getKoko(); i++) {
            for (int k = 0; k < profiili.getKoko(); k++) {
                Solu solu = kentta.getSolu(i, k);
                if (!solu.isAuki() && !solu.isMiina()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Nimensä mukaisesti aukaisee kentän kaikki solut.
     */
    public void aukaiseKaikki() {
        for (int i = 0; i < profiili.getKoko(); i++) {
            for (int k = 0; k < profiili.getKoko(); k++) {
                kentta.getSolu(i, k).setAuki();
            }
        }
    }

    /**
     * Tarkistaa ovatko annetut koordinaatit profiilin määrittelemän kentän
     * sisäpuolella.
     *
     * @param x Tarkistettava x-koordinaatti.
     * @param y Tarkistettava y-koordinaatti.
     * @return true, jos molemmat kentän sisällä, muuten false.
     */
    private boolean onKartalla(int x, int y) {
        return x >= 0 && x < profiili.getKoko() && y >= 0 && y < profiili.getKoko();
    }
}
