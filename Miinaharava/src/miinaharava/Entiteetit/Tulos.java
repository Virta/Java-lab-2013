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
    
    public boolean onnistuiko(){
        return this.loppuikoOnnistuneesti;
    }
    
}
