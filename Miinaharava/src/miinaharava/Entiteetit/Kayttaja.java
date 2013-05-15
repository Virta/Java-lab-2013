/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Entiteetit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author virta
 */
public class Kayttaja {
    private String nimimerkki;
    private HashMap<String, KenttaProfiili> profiilit;
    
    public Kayttaja(String nimimerkki){
        this.nimimerkki = nimimerkki;
        this.profiilit = new HashMap<String, KenttaProfiili>();
    }
    
    public String getNimimerkki(){
        return this.nimimerkki;
    }
    
    public void setNimimerkki(String nimimerkki){
        this.nimimerkki = nimimerkki;
    }
    
    public boolean addProfiili(KenttaProfiili profiili){
        if (!this.profiilit.containsKey(profiili.getNimi())){
            this.profiilit.put(profiili.getNimi(), profiili);
            return true;
        } else {
            return false;
        }
    }
    
    public HashMap<String, KenttaProfiili> getKaikkiProfiilit(){
        return this.profiilit;
    }
    
    public KenttaProfiili getProfiili(String nimi){
        if (this.profiilit.containsKey(nimi)) {
            return this.profiilit.get(nimi);
        } else {
            return null;
        }
    }
    
}
