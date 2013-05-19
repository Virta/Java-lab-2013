/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.PelikenttaTestit;

import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Pelikentta.Moottori;
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
public class MoottoriTest {

    private Moottori moottori;

    public MoottoriTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void moottoriAukaiseeKaikkiVaarattomatSolutYhdenAukaisusta() {
        KenttaProfiili profiili = new KenttaProfiili("Ei Miinoja", 10, 0);
        this.moottori = new Moottori(profiili);
        
        moottori.aukaiseYksi(0, 0);
        for (int i = 0; i < profiili.getKoko(); i++) {
            for (int k = 0; k < profiili.getKoko(); k++) {
                assertEquals(true, moottori.getKentta().getSolu(i, k).isAuki());
            }
        }
    }

    @Test
    public void moottoriPalauttaaOikeanArvonMiinanAukaisusta() {
        KenttaProfiili miinaProfiili = new KenttaProfiili("testi", 10, 10);
        moottori = new Moottori(miinaProfiili);

        int miinojaAukaistu = 0;
        for (int i = 0; i < miinaProfiili.getKoko(); i++) {
            boolean keskeyta = false;
            for (int k = 0; k < miinaProfiili.getKoko(); k++) {
                if (moottori.getKentta().getSolu(i, k).isMiina()) {
                    int palaute = moottori.aukaiseYksi(i, k);
                    miinojaAukaistu++;
                    assertEquals("Väärin palautettu: "+palaute+", koordinaatit: "+i+", "+k+", monesko: "+miinojaAukaistu, -1, palaute);
                    keskeyta = true;
                }
                if (keskeyta){
                    break;
                }
            }
            if (keskeyta){
                break;
            }
        }
    }

    @Test
    public void moottoriPalauttaaOikeanArvonMiinanVierestaAvattaessa() {
        KenttaProfiili miinaProfiili = new KenttaProfiili("testi", 10, 10);
        moottori = new Moottori(miinaProfiili);

        for (int i = 0; i < miinaProfiili.getKoko(); i++) {
            for (int k = 0; k < miinaProfiili.getKoko(); k++) {
                if (moottori.getKentta().getSolu(i, k).getVieressaMiinoja() > 0) {
                    assertEquals(0, moottori.aukaiseYksi(i, k));
                }
            }
        }
    }

    @Test
    public void moottoriPalauttaaOikeanArvonVaarattomanAukaisusta() {
        KenttaProfiili profiili = new KenttaProfiili("Ei Miinoja", 10, 0);
        this.moottori = new Moottori(profiili);
        
        assertEquals(0, moottori.aukaiseYksi(0, 0));
    }

    @Test
    public void moottoriAukaiseKaikkiToimii() {
        KenttaProfiili profiili = new KenttaProfiili("Ei Miinoja", 10, 0);
        this.moottori = new Moottori(profiili);
        
        moottori.aukaiseKaikki();
        for (int i=0;i<profiili.getKoko();i++){
            for (int k=0;k<profiili.getKoko();k++){
                assertEquals(true, moottori.getKentta().getSolu(i, k).isAuki());
            }
        }
    }
    
    @Test
    public void moottoriAukaiseMontaOikeinAvattaessaSuljettua(){
        KenttaProfiili miinaProfiili = new KenttaProfiili("testi", 10, 10);
        moottori = new Moottori(miinaProfiili);
        
        for (int i=0;i<miinaProfiili.getKoko();i++){
            boolean keskeytaVaarattomanLoytyessa = false;
            for (int k=0;k<miinaProfiili.getKoko();k++){
                if (moottori.getKentta().getSolu(i, k).getVieressaMiinoja()==0){
                    moottori.aukaiseMonta(i, k);
                    assertEquals(false, moottori.getKentta().getSolu(i, k).isAuki());
                    keskeytaVaarattomanLoytyessa = true;
                    break;
                }
            }
            if (keskeytaVaarattomanLoytyessa){
                break;
            }
        }
        
        for (int i=0;i<miinaProfiili.getKoko();i++){
            for (int k=0;k<miinaProfiili.getKoko();k++){
                assertEquals(false, moottori.getKentta().getSolu(i, k).isAuki());
            }
        }
        
    }
    
    @Test
    public void moottoriEiAukaiseMiinaaAukaiseUseitaAvattaessaAvonaista(){
        KenttaProfiili miinaProfiili = new KenttaProfiili("testi", 10, 10);
        moottori = new Moottori(miinaProfiili);
        
        aukaiseYksiVaaraton(miinaProfiili);
        aukaiseAvonaisestaMonta(miinaProfiili);
        
        for (int i=0;i<miinaProfiili.getKoko();i++){
            for (int k=0;k<miinaProfiili.getKoko();k++){
                if (moottori.getKentta().getSolu(i, k).isMiina()){
                    assertEquals(false, moottori.getKentta().getSolu(i, k).isAuki());
                }
            }
        }
    }
    
    @Test
    public void moottoriEiAukaiseMiinaksiLiputettua(){
        KenttaProfiili miinaProfiili = new KenttaProfiili("testi", 10, 1);
        moottori = new Moottori(miinaProfiili);
        
        Solu solu = etsiMiina(miinaProfiili);
        if (solu != null){
            solu.setFlagit();
        }
        
        for (int i=0;i<miinaProfiili.getKoko();i++){
            boolean keskeyta = false;
            for (int k=0;k<miinaProfiili.getKoko();k++){
                if (moottori.getKentta().getSolu(i, k).getVieressaMiinoja()>0){
                    int palauteYhdesta = moottori.aukaiseYksi(i, k);
                    assertEquals(0, palauteYhdesta);
                    int palauteMonesta = moottori.aukaiseMonta(i, k);
                    assertEquals(0, palauteMonesta);
                    keskeyta = true;
                }
                if (keskeyta){
                    break;
                }
            }
            if (keskeyta){
                break;
            }
        }
        
        if (solu != null) {
            assertEquals(false, solu.isAuki());
        }
        
    }
    
    @Test
    public void moottoriAukaiseeVaarinLiputetunJaPalauttaaOikein(){
        KenttaProfiili miinaProfiili = new KenttaProfiili("testi", 10, 1);
        moottori = new Moottori(miinaProfiili);
        
        Solu solu = etsiVaarallinen(miinaProfiili);
        solu.setFlagit();
        
        for (int i=0;i<miinaProfiili.getKoko();i++){
            boolean keskeyta = false;
            for (int k=0;k<miinaProfiili.getKoko();k++){
                if (moottori.getKentta().getSolu(i, k).getVieressaMiinoja()>0 && moottori.getKentta().getSolu(i, k).getFlagi()==0){
                    int palauteYhdesta = moottori.aukaiseYksi(i, k);
                    assertEquals(0, palauteYhdesta);
                    int palauteMonesta = moottori.aukaiseMonta(i, k);
                    assertEquals(-1, palauteMonesta);
                    assertEquals(true, moottori.getKentta().getSolu(i, k).isAuki());
                    keskeyta = true;
                }
                if (keskeyta){
                    break;
                }
            }
            if (keskeyta){
                break;
            }
        }
    }
    
    @Test
    public void moottoriAukaiseeVaarinLiputetunJaAvaaKaikki(){
        KenttaProfiili miinaProfiili = new KenttaProfiili("testi", 10, 1);
        moottori = new Moottori(miinaProfiili);
        
        Solu solu = etsiVaarallinen(miinaProfiili);
        solu.setFlagit();
        
        for (int i=0;i<miinaProfiili.getKoko();i++){
            boolean keskeyta = false;
            for (int k=0;k<miinaProfiili.getKoko();k++){
                if (moottori.getKentta().getSolu(i, k).getVieressaMiinoja()>0 && moottori.getKentta().getSolu(i, k).getFlagi()==0){
                    moottori.aukaiseYksi(i, k);
                    moottori.aukaiseMonta(i, k);
                    keskeyta = true;
                }
                if (keskeyta){
                    break;
                }
            }
            if (keskeyta){
                break;
            }
        }
        for (int i=0;i<miinaProfiili.getKoko();i++){
            for (int k=0;k<miinaProfiili.getKoko();k++){
                assertEquals(true, moottori.getKentta().getSolu(i, k).isAuki());
            }
        }
    }
    
    @Test
    public void moottoriAukaiseeOikeinLiputettuOikein(){
        KenttaProfiili miinaProfiili = new KenttaProfiili("testi", 10, 1);
        moottori = new Moottori(miinaProfiili);
        
        Solu solu = etsiVaarallinen(miinaProfiili);
        solu.setAuki();
        Solu miina = etsiMiina(miinaProfiili);
        miina.setFlagit();
        
        for (int i=0;i<miinaProfiili.getKoko();i++){
            boolean keskeyta = false;
            for (int k=0;k<miinaProfiili.getKoko();k++){
                if (moottori.getKentta().getSolu(i, k).isAuki()){
                    int palaute = moottori.aukaiseMonta(i, k);
                    assertEquals(0, palaute);
                    keskeyta = true;
                }
                if (keskeyta){
                    break;
                }
            }
            if (keskeyta){
                break;
            }
        }
        
        for (int i=0;i<miinaProfiili.getKoko();i++){
            for (int k=0;k<miinaProfiili.getKoko();k++){
                if (moottori.getKentta().getSolu(i, k).isMiina()){
                    assertEquals(false, moottori.getKentta().getSolu(i, k).isAuki());
                } 
                else {
                    assertEquals("Solu kiinni: "+i+", "+k+": miina: "+moottori.getKentta().getSolu(i, k).isMiina()+" lippu: "+moottori.getKentta().getSolu(i, k).getFlagi(),true, moottori.getKentta().getSolu(i, k).isAuki());
                }
            }
        }
        
    }

    @Test
    public void miinatonKenttaPeliLoppuiOnnistuneestiHeti(){
        KenttaProfiili profiili = new KenttaProfiili("Ei Miinoja", 10, 0);
        this.moottori = new Moottori(profiili);
        
        moottori.aukaiseYksi(0, 0);
        assertEquals(true, moottori.peliLoppuiOnnistuneesti());
    }
    
    @Test
    public void yksiMiinaKentassaPeliLoppuiOnnistuneestiHeti(){
        KenttaProfiili profiili = new KenttaProfiili("Yksi miina", 10, 1);
        this.moottori = new Moottori(profiili);
        
        for (int i=0;i<profiili.getKoko();i++){
            if (!moottori.getKentta().getSolu(i, 0).isMiina()){
                moottori.aukaiseYksi(i, 0);
                break;
            }
        }
        
        String message = "";
        if (!moottori.peliLoppuiOnnistuneesti()){
            for (int i=0;i<profiili.getKoko();i++){
                boolean keskeyta = false;
                for (int k=0;k<profiili.getKoko();k++){
                    if (!moottori.getKentta().getSolu(i, k).isAuki()){
                        message = "Koordinaateissa: "+i+", "+k+" virhe, miina: "+moottori.getKentta().getSolu(i, k).isMiina();
                        keskeyta = true;
                    }
                    if (keskeyta){
                        break;
                    }
                }
                if (keskeyta){
                    break;
                }
            }
        }
        
        assertEquals(message, true, moottori.peliLoppuiOnnistuneesti());
    }
    
    @Test
    public void montaMiinaaKentassaPeliLoppuiOnnistuneesti(){
        KenttaProfiili profiili = new KenttaProfiili("Monta miinaa", 10, 10);
        this.moottori = new Moottori(profiili);
        
        aukaiseYksiVaaraton(profiili);
        aukaiseAvonaisestaMonta(profiili);
        
        for (int i=0;i<profiili.getKoko();i++){
            for (int k=0;k<profiili.getKoko();k++){
                Solu solu = moottori.getKentta().getSolu(i, k);
                if (solu.isMiina()){
                    solu.setFlagit();
                }
                if (!solu.isMiina() && !solu.isAuki()){
                    moottori.aukaiseYksi(i, k);
                }
            }
        }
        
        assertEquals(true, moottori.peliLoppuiOnnistuneesti());
        
    }
   
    @Test
    public void moottoriPalauttaaOikeitaAikojaEiMiinojaKentassa(){
        KenttaProfiili profiili = new KenttaProfiili("Ei Miinoja", 10, 0);
        this.moottori = new Moottori(profiili);
        
        aukaiseYksiVaaraton(profiili);
        
        assertEquals(true, moottori.getAika()>0);
    }
    
    @Test
    public void moottoriPalauttaaOikeanAjanYhdenMiinanKentassa() throws InterruptedException{
        KenttaProfiili profiili = new KenttaProfiili("Yksi Miina", 10, 1);
        this.moottori = new Moottori(profiili);
        
        aukaiseYksiVaarallinen(profiili);
        Thread.sleep(1000);
        aukaiseAvonaisestaMonta(profiili);
        int aikaSekunneissa = (int) moottori.getAika();
        assertEquals(1, aikaSekunneissa);
    }
    
    private Solu etsiMiina(KenttaProfiili miinaProfiili) {
        for (int i=0;i<miinaProfiili.getKoko();i++){
            boolean keskeyta = false;
            for (int k=0;k<miinaProfiili.getKoko();k++){
                if (moottori.getKentta().getSolu(i, k).isMiina()){
                    return moottori.getKentta().getSolu(i, k);
                }
            }
        }
        return null;
    }
    
    private Solu etsiVaarallinen(KenttaProfiili miinaProfiili){
        for (int i=0;i<miinaProfiili.getKoko();i++){
            boolean keskeyta = false;
            for (int k=0;k<miinaProfiili.getKoko();k++){
                if (moottori.getKentta().getSolu(i, k).getVieressaMiinoja()>0){
                    return moottori.getKentta().getSolu(i, k);
                }
            }
        }
        return null;
    }
    
    private Solu etsiVaaraton(KenttaProfiili miinaProfiili){
        for (int i=0;i<miinaProfiili.getKoko();i++){
            for (int k=0;k<miinaProfiili.getKoko();k++){
                if (moottori.getKentta().getSolu(i, k).getVieressaMiinoja()==0){
                    return moottori.getKentta().getSolu(i, k);
                }
            }
        }
        return null;
    }
    
    private void aukaiseYksiVaaraton(KenttaProfiili miinaProfiili){
        for (int i=0;i<miinaProfiili.getKoko();i++){
            for (int k=0;k<miinaProfiili.getKoko();k++){
                if (!moottori.getKentta().getSolu(i, k).isMiina()){
                    moottori.aukaiseYksi(i, k);
                    return;
                }
            }
        }
    }
    
    private void aukaiseAvonaisestaMonta(KenttaProfiili miinaProfiili){
        for (int i=0;i<miinaProfiili.getKoko();i++){
            for (int k=0;k<miinaProfiili.getKoko();k++){
                if (moottori.getKentta().getSolu(i, k).getVieressaMiinoja()==0 && moottori.getKentta().getSolu(i, k).isAuki()){
                    moottori.aukaiseMonta(i, k);
                    return;
                }
            }
        }
    }
    
    private void aukaiseYksiVaarallinen(KenttaProfiili profiili){
        for (int i=0;i<profiili.getKoko();i++){
            boolean keskeyta = false;
            for (int k=0;k<profiili.getKoko();k++){
                if (!moottori.getKentta().getSolu(i, k).isMiina() && moottori.getKentta().getSolu(i, k).getVieressaMiinoja()>0){
                    moottori.aukaiseYksi(i, k);
                    keskeyta = true;
                }
                if (keskeyta){
                    break;
                }
            }
            if (keskeyta){
                break;
            }
        }
    }
}
