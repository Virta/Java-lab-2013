package miinaharava.Pelikentta;

import java.util.LinkedList;
import miinaharava.Entiteetit.KenttaProfiili;

/**
 *
 * @author virta
 */
public class Moottori {
    
    /**
     * Kenttä moottorin sisäiseen käyttöön.
     */
    private Kentta kentta;
    /**
     * Kenttäprofiili jolla moottori alustetaan, jota käytetään kentän luomiseen ja navigoimiseen.
     */
    private KenttaProfiili profiili;
    
    /**
     * Alustaa uuden moottorin annetulla profiililla, kenttä luodaan konstruktorissa annetusta profiilista.
     * 
     * @param profiili KenttaProfiili jolla Moottori alustetaan ja jolla kenttä luodaan.
     */
    public Moottori(KenttaProfiili profiili){
        this.profiili = profiili;
        this.kentta = new Kentta(profiili);
    }
    
    public Kentta getKentta(){
        return this.kentta;
    }
    
    /**
     * Aukaisee yhden solun annetuissa koordinaateissa.
     * 
     * Ei avaa solua joka on jo auki, eikä sellaista solua joka on liputettu miinaksi.
     * Jos avattava solu on miina, palautetaan -1, eli peli päättyy. Samalla avataan kentän kaikki solmut, paljastaen pelaajan virheet.
     * Jos avattavalla solulla on vieressä miinoja palautetaan 0 ja avataan ko. solu.
     * Jos avattavalla solulla ei ole vieressä miinoja, eli on nk. vaaraton, avataan samalla kaikki viereiset vaarattomat solut ja jokainen niiden viereinen solu
     * jolla on vieressä miinoja.
     * 
     * @param x
     * @param y
     * @return Palauttaa 0 jos aukaisu onnistui, eli solu ei ollut miina. Palauttaa -1 jos avattu solmu oli miina.
     */
    public int aukaiseYksi(int x, int y){   //testeissä palauttaa 0? vaikka pitäisi palauttaa -1 miinan avauksesta, käsin tarkistettuna palauttaa -1.
        Solu solu = kentta.getSolu(x, y);
        if (solu.isAuki() || solu.getFlagi()==1){
            return 0;
        } 
        
        else if (solu.isMiina()){
            aukaiseKaikki();
            return -1;
        } 
        
        else if (solu.getVieressaMiinoja()>0){
            solu.setAuki();
            return 0;
        } 
        
        else {
            LinkedList<Solu> pino = new LinkedList<>();
            pino.add(solu);
            while (!pino.isEmpty()){
                solu = pino.pop();
                solu.setAuki();
                for (Solu s : solu.getVierukset()){
                    if (s.getVieressaMiinoja()==0 && !s.isAuki()){
                        pino.add(s);
                    } else {
                        s.setAuki();
                    }
                }
            }
            return 0;
        }
    }
    
    /**
     * Avaa useita solmuja lähtien annetuista koordinaateista.
     * 
     * Käyttäjä voi yrittää avata 3x3 muotoisen alueen, jonka keskipiste on tässä solussa.
     * Jos solu on kiinni, ei tästä pisteestä voi avata useita solmuja.
     * Jos tämä solmu on auki, mutta sen osoittama määrä vieressä olevia miinoja ei ole liputettu, ei solmua voi avata.
     * Esim: jos tässä solmussa on merkitty 2 viereistä miinaa ja tämän solmun ympäröivistä solmuista vain 1 on liputettu miinaksi, avaus ei onnistu.
     * Vastaavasti jos miinalippuja on oikea määrä tai enemmän, aukaisu onnistuu ja metodissa käydään läpi kaikki ympärillä olevat solmut
     * aukaiseYksi - metodilla, palauttaen -1 jos oli virheellinen liputus, 0 jos avaus onnistui ... miinaa.
     * 
     * @param x Solun, josta halutaan avata monta, x-koordinaatti.
     * @param y Solun, josta halutaan avata monta, y-koordinaatti.
     * @return Palauttaa 0 jos avaus onnistui, -1 jos liputettu väärin ja ... miina.
     */
    public int aukaiseMonta(int x, int y){  //täytyy vielä korjata!
        if (!kentta.getSolu(x, y).isAuki()){
            return 0;
        }
        if (flagienMaaraOikein(x, y)>0){
            return 0;
        }
        for (int i=x-1;i<x+2;i++){
            for (int k=y-1;k<y+2;k++){
                if (onKartalla(i, k)){
                    int palaute = aukaiseYksi(i, k);
                    if (palaute == -1){
                        return -1;
                    }
                }
            }
        }
        return 0;
    }
    
    /**
     * Laskee tämän solun ympäröivien solujen miinalippujen määrän.
     * 
     * @param x Solun, jonka ympäristö lasketaan, x-koordinaatti.
     * @param y Solun, jonka ympäristö lasketaan, y-koordinaatti.
     * @return Palauttaa lippujen - ja miinojen määrän erotuksen: -1 jos liikaa lippuja, 0 jos sama määrä lippuja, 1 jos liian vähän lippuja.
     */
    private int flagienMaaraOikein(int x, int y){
        int miinaLippuja = 0;
        for (int i=x-1;i<x+2;i++){
            for (int k=y-1;k<y+2;k++){
                if (y==0 && k==0){
                    k++;
                }
                if (onKartalla(i, k) && !kentta.getSolu(i, k).isAuki() && kentta.getSolu(i, k).getFlagi()==1){
                    miinaLippuja++;
                }
            }
        }
        
        return kentta.getSolu(x, y).getVieressaMiinoja() - miinaLippuja;
        
    }
    
    /**
     * Ei vielä käytössä, täsmennettävä toimintaa.
     * 
     * Tarkistaa onko peli päättynyt.
     * 
     * @return true, jos päättynyt, false jos vielä kesken.
     */
    private boolean loppuikoPeli(){ //täytyy vielä korjata!
        for (int i=0;i<profiili.getKoko();i++){
            for (int k=0;k<profiili.getKoko();k++){
                Solu s = kentta.getSolu(i, k);
                if (!s.isAuki() && !s.isMiina()){
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Nimensä mukaisesti aukaisee kentän kaikki solut.
     */
    public void aukaiseKaikki(){
        for (int i=0;i<profiili.getKoko();i++){
            for (int k=0;k<profiili.getKoko();k++){
                kentta.getSolu(i, k).setAuki();
            }
        }
    }
    
    /**
     * Tarkistaa ovatko annetut koordinaatit profiilin määrittelemän kentän sisäpuolella.
     * 
     * @param x Tarkistettava x-koordinaatti.
     * @param y Tarkistettava y-koordinaatti.
     * @return true, jos molemmat kentän sisällä, muuten false.
     */
    private boolean onKartalla(int x, int y){
        return x>=0 && x<profiili.getKoko() && y>=0 && y<profiili.getKoko();
    }
    
}
