/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Pelikentta;

import java.util.LinkedList;

/**
 * Luokka joka määrittelee kunkin solun kentässä, solu tietää vain itsensä tiedot ja ketä vieruksia sillä on, 
 * kaikki varsinainen hallinnointi tapahtuu tätä luokkaa käyttävässä Kenttä-luokassa.
 *
 * @author virta
 */
public class Solu {
    /**
     * Onko solu miina.
     */
    private boolean miina;
    /**
     * Mikä lippu solulle on asetettu.
     */
    private int lippu;
    /**
     * Kuinka paljon solun vieressä on miinoja, jos ei itse miina.
     */
    private int vieressaMiinoja;
    /**
     * Onko solu avattu.
     */
    private boolean auki;
    /**
     * Lista kaikista 8:sta viereisistä soluista.
     */
    private LinkedList<Solu> vierukset;
    
    /**
     * Alustetaan solu: ei miina, kiinni, ei lippuja, ei vieresä miinoja, uusi lista vieruksille.
     */
    public Solu(){
        this.miina = false;
        this.auki = false;
        this.lippu=0;
        this.vieressaMiinoja=0;
        this.vierukset = new LinkedList<>();
    }
    
    public void lisaaVierusSolu(Solu solu){
        this.vierukset.add(solu);
    }
    
    public LinkedList<Solu> getVierukset(){
        return this.vierukset;
    }

    public boolean isMiina() {
        return miina;
    }

    public boolean isAuki() {
        return auki;
    }

    public void setAuki() {
        this.auki = true;
    }

    public void setMiina() {
        this.miina = true;
    }

    public int getFlagi() {
        return lippu;
    }

    /**
     * Kutsuttaessa lisää lippu-lukua yhdellä.
     * 
     * Toimii syklillisesti, 0..2:n. Eli lippuja on kahdenlaisia: lippu 1 on miinalippu, lippu 2 on kysymysmerkki.
     * Miinaksi liputettua ei voi aukaista (määritellään Moottori-luokassa) ja kysymysmerkki ei tee mitään: on vain käyttäjän muistilappu.
     * 
     */
    public void setFlagit() {
        lippu++;
        if (lippu==3){
            lippu=0;
        }
    }

    public int getVieressaMiinoja() {
        return vieressaMiinoja;
    }
    
    public void lisaaViereenMiinoja(){
        this.vieressaMiinoja+=1 ;
    }

//    public void setVieressaMiinoja(int vieressaMiinoja) {
//        this.vieressaMiinoja = vieressaMiinoja;
//    }

}
