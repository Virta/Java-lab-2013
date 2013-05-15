package miinaharava;

import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.VakioProfiilit;
import miinaharava.Pelikentta.Kentta;

/**
 *
 * @author frojala
 */
public class Miinaharava {

    public static void main(String[] args) {
        
        VakioProfiilit vakioProfiilit = new VakioProfiilit();
        KenttaProfiili profiili = vakioProfiilit.getVaikea();
        KenttaProfiili testiProfiili = new KenttaProfiili("SuperVaikea", 100, 1000);
        Kentta kentta = new Kentta(testiProfiili);
        
        for (int i=0;i<profiili.getKoko();i++){
            for (int k=0;k<profiili.getKoko();k++){
                if (kentta.getSolu(i, k).isMiina()){
                    System.out.print("M ");
                } else {
                    System.out.print(kentta.getSolu(i, k).getVieressaMiinoja()+" ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        
    }
}
