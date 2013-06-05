/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma.KayttoliittymaKuuntelijat;

import javax.swing.JLabel;
import miinaharava.Pelikentta.Moottori;

/**
 *
 * @author virta
 */
public class KelloPaivittaja implements Runnable {
    
    private JLabel kello;
    private Moottori moottori;
    private JLabel miinojaJaljella;
    
    public KelloPaivittaja(JLabel kello, Moottori moottori, JLabel miinoja){
        this.kello = kello;
        this.moottori = moottori;
        this.miinojaJaljella = miinojaJaljella;
    }

    @Override
    public void run() {
        while (true){
            
            try {
                Thread.sleep(500);
            } catch (Exception e){
                
            }
            
            int aika = (int) moottori.getAika();
            String aikaString = (aika/60)+":"+(aika-((aika/60)*60));
            kello.setText(aikaString);
            
        }
    }
    
    public void paivitaMiinaTieto(){
        miinojaJaljella.setText(moottori.getKentta().getMiinojaJaljella()+"");
    }
    
}
