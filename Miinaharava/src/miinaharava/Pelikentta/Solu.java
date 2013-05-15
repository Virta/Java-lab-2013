/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Pelikentta;

/**
 *
 * @author virta
 */
public class Solu {
    private boolean miina;
    private int lippu;
    private int vieressaMiinoja;
    
    public Solu(){
        this.miina = false;
        this.lippu=0;
        this.vieressaMiinoja=0;
    }

    public boolean isMiina() {
        return miina;
    }

    public void setMiina(boolean miina) {
        this.miina = miina;
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
