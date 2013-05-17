package miinaharava.Pelikentta;

import java.util.LinkedList;
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
    
    public int aukaiseYksi(int x, int y){
        Solu solu = kentta.getSolu(x, y);
        if (solu.isAuki() || solu.getFlagi()==1){
            return 0;
        } 
        
        else if (solu.isMiina()){
            aukaiseKaikki();
            return -1;
        } 
        
        else if (solu.getVieressaMiinoja()>0){
            solu.setAuki();
            return 0;
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
    
    public int aukaiseMonta(int x, int y){
        if (!kentta.getSolu(x, y).isAuki()){
            return 0;
        }
        if (flagienMaaraOikein(x, y)>0){
            return 0;
        }
        for (int i=x-1;i<x+2;i++){
            for (int k=y-1;k<y+2;k++){
                if (onKartalla(i, k)){
                    int palaute = aukaiseYksi(i, k);
                    if (palaute == -1){
                        return -1;
                    }
                }
            }
        }
        return 0;
    }
    
    private int flagienMaaraOikein(int x, int y){
        int miinaLippuja = 0;
        for (int i=x-1;i<x+2;i++){
            for (int k=y-1;k<y+2;k++){
                if (y==0 && k==0){
                    k++;
                }
                if (onKartalla(i, k) && !kentta.getSolu(i, k).isAuki() && kentta.getSolu(i, k).getFlagi()==1){
                    miinaLippuja++;
                }
            }
        }
        
        return kentta.getSolu(x, y).getVieressaMiinoja() - miinaLippuja;
        
    }
    
    private boolean loppuikoPeli(){
        for (int i=0;i<profiili.getKoko();i++){
            for (int k=0;k<profiili.getKoko();k++){
                Solu s = kentta.getSolu(i, k);
                if (!s.isAuki() && !s.isMiina()){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void aukaiseKaikki(){
        for (int i=0;i<profiili.getKoko();i++){
            for (int k=0;k<profiili.getKoko();k++){
                kentta.getSolu(i, k).setAuki();
            }
        }
    }
    
    private boolean onKartalla(int x, int y){
        return x>=0 && x<profiili.getKoko() && y>=0 && y<profiili.getKoko();
    }
    
}
