/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Sounds;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import miinaharava.Kayttoliittyma.Virheilmoitus;

/**
 *
 * @author frojala
 */
public class Aanet {
    
    public static void toistaAani(String soundName) {
        try {
            Clip clip = AudioSystem.getClip();
            File soundFile = new File("/home/virta/Ohjelmoinnin harjoitustyö/Javalabra-miinaharava/Miinaharava/src/miinaharava/Sounds/" + soundName + ".wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
            clip.open(stream);
            clip.start();

        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Virheilmoitus.naytaVirheilmoitus("Virhe äänen toistossa: " + ex.getMessage());
        }
    }

}
