/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Aanentoisto;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import miinaharava.Kayttoliittyma.Virheilmoitus;

/**
 *
 * @author frojala
 */
public class Aanet {
    
    public static void toistaAani(String soundName, boolean toistossa) {
        
        if (!toistossa) {
            try {
//                toistossa=true;
                URL url = Aanet.class.getResource(soundName + ".wav");
                AudioClip aani = Applet.newAudioClip(url);
                aani.play();
//                toistossa = false;
                
            } catch (Exception ex) {
//                toistossa = false;
                Virheilmoitus.naytaVirheilmoitus("Virhe äänen toistossa: " + ex.getMessage());
            }
        }
    }

}
