package miinaharava.Entiteetit;

/**
 *
 * @author virta
 */
public class Tulos {
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
     * Konstruktori määritteleee parametreina annetut tiedot tuloksen sisäisiin muuttujiin.
     * @param aika Missä ajassa kentän pelaaminen pääättyi, muotoa min:sekuntia, esim 1:20.
     * @param profiili Millä kenttäprofiililla pelattiin.
     * @param pelaaja Kuka pelasi, anon jos käyttäjä ei kirjautunut.
     */
    public Tulos(String aika, KenttaProfiili profiili, Kayttaja pelaaja){
        this.aika = aika;
        this.pelaaja = pelaaja;
        this.profiili = profiili;
    }

    public String getAika() {
        return aika;
    }

    public KenttaProfiili getProfiili() {
        return profiili;
    }

    public Kayttaja getPelaaja() {
        return pelaaja;
    }
    
}
