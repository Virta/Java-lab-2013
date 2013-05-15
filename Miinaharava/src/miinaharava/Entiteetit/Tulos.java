package miinaharava.Entiteetit;

/**
 *
 * @author virta
 */
public class Tulos {
    private String aika;
    private KenttaProfiili profiili;
    private Kayttaja pelaaja;
    
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
