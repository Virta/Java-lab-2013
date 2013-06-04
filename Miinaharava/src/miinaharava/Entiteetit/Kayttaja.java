package miinaharava.Entiteetit;

import java.util.HashMap;

/**
 * Luokka hallinnoimaan käyttäjän tietoja ajon aikana.
 * 
 * @author virta
 */
public class Kayttaja {
    /**
     * Käyttäjän tiedot, ei salasana toiminnallisuutta koska ei välttämätön.
     * 
     */
    private String nimimerkki;
    /**
     * Käyttäjän customoidut kenttäprofiilit tallennetaan suorituksen yhteydessä hajautustauluun.
     */
    private HashMap<String, KenttaProfiili> profiilit;
    
    /**
     * Luo uuden käyttäjän parametrina annetulla nimimerkillä, ja luo hajautustaulun kenttäprofiileille.
     * 
     * Konstruktori ottaa String-olion vastaan nimimerkkinä, nimimerkin pituuden tarkistus tehdään kutsuvassa metodissa.
     * 
     * @param nimimerkki String-olio, käyttäjän syöte.
     */
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
    
    /**
     * Lisää uuden kenttäprofiilin käyttäjän kustomoituihin profiileihin.
     * 
     * @param profiili Kenttäprofiili, joka lisätään käyttäjän profiileihin.
     * @return palauttaa true, jos lisäys onnistui eli samannimistä profiilia ei ole olemassa. Jos nimi on jo käytössä, palautetaan false.
     */
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
