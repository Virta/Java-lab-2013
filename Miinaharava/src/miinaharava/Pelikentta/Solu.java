/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Pelikentta;

import java.util.LinkedList;

/**
 *
 * @author virta
 */
public class Solu {
    private boolean miina;
    private int lippu;
    private int vieressaMiinoja;
    private boolean auki;
    private LinkedList<Solu> vierukset;
    private LinkedList<Solu> vieruksetSivuilla;
    
    public Solu(){
        this.miina = false;
        this.auki = false;
        this.lippu=0;
        this.vieressaMiinoja=0;
        this.vierukset = new LinkedList<>();
        this.vieruksetSivuilla = new LinkedList<>();
    }
    
    public void lisaaSivuVierus(Solu solu){
        this.vieruksetSivuilla.add(solu);
    }
    
    public LinkedList<Solu> getvieruksetSivuilla(){
        return this.vieruksetSivuilla;
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
