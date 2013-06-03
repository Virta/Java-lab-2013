package miinaharava.Entiteetit;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;

/**
 * Luokka jonka olioina kaikki tulokset ilmenevät, olioita ei suoraan tallenneta tiedostoon, vaan ne muunnetaan H(uman)R(eadable)-muotoon ja tallennetaan tiedostoon.
 *
 * @author virta
 */
public class Tulos implements Comparable<Object>{
    /**
     * Aika tallennetaan suoritus aikana tässä vaiheessa String-oliona.
     */
    private String aika;
    /**
     * Kenttäprofiili tallennetaan suoritus aikana sellaisenaan.
     */
    private KenttaProfiili profiili;
    /**
     * Käyttäjä tallennetaan suoritus aikana sellaisenaan.
     */
    private Kayttaja pelaaja;
    /**
     * Tallennetaan totuusarvona päättyikö peli onnistuneesti.
     */
    private boolean loppuikoOnnistuneesti;
    
    /**
     * Konstruktori määritteleee parametreina annetut tiedot tuloksen sisäisiin muuttujiin.
     * @param aika Missä ajassa kentän pelaaminen pääättyi, muotoa min:sekuntia, esim 1:20.
     * @param profiili Millä kenttäprofiililla pelattiin.
     * @param pelaaja Kuka pelasi, anon jos käyttäjä ei kirjautunut.
     * @param loppuikoOnnistuneesti Tallennetaan, jotta pelaaja voi tarkastella erikseen epäonnistumisia ja onnistumisia.
     */
    public Tulos(String aika, KenttaProfiili profiili, Kayttaja pelaaja, boolean loppuikoOnnistuneesti){
        this.aika = aika;
        this.pelaaja = pelaaja;
        this.profiili = profiili;
        this.loppuikoOnnistuneesti = loppuikoOnnistuneesti;
    }

    public String getAika() {
        return this.aika;
    }

    public KenttaProfiili getProfiili() {
        return this.profiili;
    }

    public Kayttaja getPelaaja() {
        return this.pelaaja;
    }
    
    public boolean getOnnistuiko(){
        return this.loppuikoOnnistuneesti;
    }

    @Override
    public int compareTo(Object o) {
        Tulos verrattava = (Tulos) o;
        String[] verrattavanAika = verrattava.getAika().split(":");
        int verrattavanMinuutit = Integer.parseInt(verrattavanAika[0]);
        int verrattavanSekunnit = Integer.parseInt(verrattavanAika[1]);
        
        String[] tamanAika = this.aika.split(":");
        int tamanMinuutit = Integer.parseInt(tamanAika[0]);
        int tamanSekunnit = Integer.parseInt(tamanAika[1]);
        
        if (verrattavanMinuutit==tamanMinuutit){
            return tamanSekunnit-verrattavanSekunnit;
        } else {
            return tamanMinuutit-verrattavanMinuutit;
        }
    }
    
}
