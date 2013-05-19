/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.PelikenttaTestit;

import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.VakioProfiilit;
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
    public void kenttaOlemassa(){
        assertNotNull(kentta);
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
    public void kentassaOikeaMaaraMiinojaVakioHelppo(){
        VakioProfiilit vakiot = new VakioProfiilit();
        kentta = new Kentta(vakiot.getHelppo());
        int miinoja = 0;
        for (int i=0;i<vakiot.getHelppo().getKoko();i++){
            for (int k=0;k<vakiot.getHelppo().getKoko();k++){
                Solu s = kentta.getSolu(i, k);
                if (s.isMiina()){
                    miinoja ++ ;
                }
            }
        }
        assertEquals(vakiot.getHelppo().getMiinoja(), miinoja);
    }
    
    @Test
    public void kentassaOikeaMaaraMiinojaVakioKeskivaikea(){
        VakioProfiilit vakiot = new VakioProfiilit();
        kentta = new Kentta(vakiot.getKeskiVaikea());
        int miinoja = 0;
        for (int i=0;i<vakiot.getKeskiVaikea().getKoko();i++){
            for (int k=0;k<vakiot.getKeskiVaikea().getKoko();k++){
                Solu s = kentta.getSolu(i, k);
                if (s.isMiina()){
                    miinoja ++ ;
                }
            }
        }
        assertEquals(vakiot.getKeskiVaikea().getMiinoja(), miinoja);
    }
    
    @Test
    public void kentassaOikeaMaaraMiinojaVakioVaikea(){
        VakioProfiilit vakiot = new VakioProfiilit();
        kentta = new Kentta(vakiot.getVaikea());
        int miinoja = 0;
        for (int i=0;i<vakiot.getVaikea().getKoko();i++){
            for (int k=0;k<vakiot.getVaikea().getKoko();k++){
                Solu s = kentta.getSolu(i, k);
                if (s.isMiina()){
                    miinoja ++ ;
                }
            }
        }
        assertEquals(vakiot.getVaikea().getMiinoja(), miinoja);
    }
    
    @Test
    public void kentanMiinatietoPaivittyyOikein(){
        kentta.getSolu(1, 1);
        assertEquals(10, kentta.getMiinojaJaljella());
        
        kentta.asetaFlagi(2, 2);
        assertEquals(9, kentta.getMiinojaJaljella());
        
        kentta.asetaFlagi(3, 3);
        assertEquals(8, kentta.getMiinojaJaljella());
    }
    
    @Test
    public void kentanMiinatietoPaivittyyOikein2(){
        kentta.asetaFlagi(2, 2);
        assertEquals(9, kentta.getMiinojaJaljella());
        
        kentta.asetaFlagi(2, 2);
        assertEquals(10, kentta.getMiinojaJaljella());
        
        kentta.asetaFlagi(2, 2);
        assertEquals(10, kentta.getMiinojaJaljella());
    }
    
    @Test
    public void viereistenSolujenMiinatietoPaivittyyOikein(){
        for (int i=0;i<10;i++){
            for (int k=0;k<10;k++){
                Solu solu = kentta.getSolu(i, k);
                if (!solu.isMiina()){
                    assertEquals(laskeMiinat(i, k), solu.getVieressaMiinoja());
                }
            }
        }
    }
    
    @Test
    public void miinoillaEiMerkittyViereisiaMiinoja(){
        for (int i=0;i<10;i++){
            for (int k=0;k<10;k++){
                if (kentta.getSolu(i, k).isMiina()){
                    assertEquals(0, kentta.getSolu(i, k).getVieressaMiinoja());
                }
            }
        }
    }
    
    private int laskeMiinat(int x, int y){
        int miinoja = 0;
        
        int yla = y - 1;
        int ala = y + 1;
        int vasen = x - 1;
        int oikea = x + 1;

        if (onKartallaJaMiina(vasen, yla)) {
            miinoja++;
        }
        if (onKartallaJaMiina(x, yla)) {
            miinoja++;
        }
        if (onKartallaJaMiina(oikea, yla)) {
            miinoja++;
        }
        if (onKartallaJaMiina(vasen, y)) {
            miinoja++;
        }
        if (onKartallaJaMiina(oikea, y)) {
            miinoja++;
        }
        if (onKartallaJaMiina(vasen, ala)) {
            miinoja++;
        }
        if (onKartallaJaMiina(x, ala)) {
            miinoja++;
        }
        if (onKartallaJaMiina(oikea, ala)) {
            miinoja++;
        }
        
        return miinoja;
    }
    
    private boolean onKartallaJaMiina(int x, int y){
        if (x>=0 && y>=0 && x<10 && y<10){
            return kentta.getSolu(x, y).isMiina();
        }
        return false;
    }
}