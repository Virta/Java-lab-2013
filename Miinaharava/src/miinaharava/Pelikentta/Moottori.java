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
    
    public Moottori(Kentta kentta, KenttaProfiili profiili){
        this.profiili = profiili;
        this.kentta = new Kentta(profiili);
    }
    
    public int aukaise(int x, int y){
        Solu solu = kentta.getSolu(x, y);
        if (solu.isAuki()){
            return 0;
        } 
        
        else if (solu.isMiina()){
            return -1;
        } 
        
        else if (solu.getVieressaMiinoja()>0){
            return solu.getVieressaMiinoja();
        } 
        
        else {
            LinkedList<Solu> pino = new LinkedList<>();
            pino.add(solu);
            while (!pino.isEmpty()){
                solu = pino.pop();
                solu.setAuki();
                for (Solu s : solu.getvieruksetSivuilla()){
                    if (s.getVieressaMiinoja()==0){
                        pino.add(s);
                    } else {
                        s.setAuki();
                    }
                }
            }
            return 0;
            
        }
    }
    
}
