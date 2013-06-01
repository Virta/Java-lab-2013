package miinaharava.Entiteetit;

/**
 * Luokka, joka hallinnoi kaikkia kenttäprofiileja, niin vakioita kuin käyttäjän tekemiä.
 * 
 * @author virta
 */
public class KenttaProfiili {
    /**
     * Kenttäprofiilin nimi, määritellään konstruktorissa.
     */
    private String nimi;
    /**
     * Kenttäprofiilin koko.
     */
    private int koko;
    /**
     * Miinojen määrä kenttäprofiilissa.
     */
    private int miinoja;
    
    /**
     * Konstruktori alustaa luokan sisäiset muuttujat.
     * 
     * @param nimi Profiilin nimi.
     * @param koko Profiilin koko.
     * @param miinoja Kentän miinojen lukumäärä.
     */
    public KenttaProfiili(String nimi, int koko, int miinoja){
        this.nimi = nimi;
        this.koko = koko;
        this.miinoja = miinoja;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getKoko() {
        return koko;
    }

    public int getMiinoja() {
        return miinoja;
    }
    
}
