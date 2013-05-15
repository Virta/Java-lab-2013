/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.PelikenttaTestit;

import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Pelikentta.Kentta;
import miinaharava.Pelikentta.Solu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author virta
 */
public class KenttaTest {
    
    Kentta kentta;
    
    public KenttaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        KenttaProfiili testiProfiili = new KenttaProfiili("testi", 10, 10);
        kentta = new Kentta(testiProfiili);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void kentallaOnProfiiliJossaMiinoja(){
        assertEquals(10, kentta.getMiinojaJaljella());
    }
    
    @Test
    public void kentassaOikeaMaaraMiinoja(){
        int miinoja = 0;
        for (int i=0;i<10;i++){
            for (int k=0;k<10;k++){
                Solu s = kentta.getSolu(i, k);
                if (s.isMiina()){
                    miinoja ++ ;
                }
            }
        }
        assertEquals(10, miinoja);
    }
    
    @Test
    public void kentanMiinatietoPaivittyyOikein(){
        kentta.getSolu(1, 1);
        assertEquals(10, kentta.getMiinojaJaljella());
        
        kentta.getSolu(2, 2).setFlagit();
        assertEquals(9, kentta.getMiinojaJaljella());
        
        kentta.getSolu(3, 3).setFlagit();
        assertEquals(8, kentta.getMiinojaJaljella());
    }
    
    @Test
    public void kentanMiinatietoPaivittyyOikein2(){
        kentta.getSolu(2, 2).setFlagit();
        assertEquals(9, kentta.getMiinojaJaljella());
        
        kentta.getSolu(2, 2).setFlagit();
        assertEquals(10, kentta.getMiinojaJaljella());
        
        kentta.getSolu(2, 2).setFlagit();
        assertEquals(10, kentta.getMiinojaJaljella());
    }
    
    @Test
    public void viereistenSolujenMiinatietoPaivittyyOikein(){
        for (int i=0;i<10;i++){
            for (int k=0;k<10;k++){
                Solu solu = kentta.getSolu(i, k);
                if (solu.getVieressaMiinoja()>0){
                    assertEquals(laskeMiinat(i, k), solu.getVieressaMiinoja());
                }
            }
        }
    }
    
    private int laskeMiinat(int x, int y){
        int miinoja = 0;
        
        int yla = x-1;
        int ala = x+1;
        int vasen = y-1;
        int oikea = y+1;
        
        if (onKartallaJaMiina(yla, vasen)){
            miinoja ++ ;
        }
        if (onKartallaJaMiina(yla, y)){
            miinoja ++ ;
        }
        if (onKartallaJaMiina(yla, oikea)){
            miinoja ++ ;
        }
        if (onKartallaJaMiina(x, vasen)){
            miinoja ++ ;
        }
        if (onKartallaJaMiina(x, oikea)){
            miinoja ++ ;
        }
        if (onKartallaJaMiina(ala, vasen)){
            miinoja ++ ;
        }
        if (onKartallaJaMiina(ala, y)){
            miinoja ++ ;
        }
        if (onKartallaJaMiina(ala, oikea)){
            miinoja ++ ;
        }
        
        return miinoja;
    }
    
    private boolean onKartallaJaMiina(int x, int y){
        if (x>0 && y>0 && x<10 && y<10){
            return kentta.getSolu(x, y).isMiina();
        }
        return false;
    }
}