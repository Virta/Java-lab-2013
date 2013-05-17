package miinaharava.Pelikentta;

import java.util.LinkedList;
import java.util.List;
import miinaharava.Entiteetit.KenttaProfiili;

/**
 *
 * @author virta
 */
public class Moottori {
    
    private Kentta kentta;
    private KenttaProfiili profiili;
    
    public Moottori(KenttaProfiili profiili){
        this.profiili = profiili;
        this.kentta = new Kentta(profiili);
    }
    
    public Kentta getKentta(){
        return this.kentta;
    }
    
    
    
    public int aukaise(int x, int y){
        Solu solu = kentta.getSolu(x, y);
        if (solu.isAuki()){
            return 0;
        } 
        
        else if (solu.isMiina()){
            solu.setAuki();
            return -1;
        } 
        
        else if (solu.getVieressaMiinoja()>0){
            solu.setAuki();
            return solu.getVieressaMiinoja();
        } 
        
        else {
            LinkedList<Solu> pino = new LinkedList<>();
            pino.add(solu);
            while (!pino.isEmpty()){
                solu = pino.pop();
                solu.setAuki();
                for (Solu s : solu.getVierukset()){
                    if (s.getVieressaMiinoja()==0 && !s.isAuki()){
                        pino.add(s);
                    } else {
                        s.setAuki();
                    }
                }
            }
            return 0;
        }
    }
    
    public void aukaiseKaikki(){
        for (int i=0;i<profiili.getKoko();i++){
            for (int k=0;k<profiili.getKoko();k++){
                kentta.getSolu(i, k).setAuki();
            }
        }
    }
    
}
