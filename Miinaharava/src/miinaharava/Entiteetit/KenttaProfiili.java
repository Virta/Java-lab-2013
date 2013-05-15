/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Entiteetit;

/**
 *
 * @author virta
 */
public class KenttaProfiili {
    private String nimi;
    private int koko;
    private int miinoja;
    
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
