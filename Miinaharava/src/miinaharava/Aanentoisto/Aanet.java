/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Aanentoisto;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import miinaharava.Kayttoliittyma.Virheilmoitus;

/**
 *
 * @author frojala
 */
public class Aanet implements Runnable {
    
    private String soundName;
    
    public Aanet(String name){
        this.soundName = name;
    }
    
    private void toistaAani() {
            try {
                URL url = Aanet.class.getResource(this.soundName+".wav");
                AudioInputStream stream = AudioSystem.getAudioInputStream(url);
                AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(url);
                AudioFormat format = fileFormat.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                SourceDataLine line = null;
                if (AudioSystem.isLineSupported(info)){
                    try {
                        line = (SourceDataLine) AudioSystem.getLine(info);
                        line.open(format);
                    } catch (final Exception ex) {
                        Virheilmoitus.naytaVirheilmoitus("Äänentoistossa virhe: "+ex.getClass()+" virhe: "+ex.getMessage());
                    }
                }
                
                if (line != null){
                    line.start();
                    int bytesRead = 0;
                    byte[] data = new byte[524288]; //128kb puskuria
                    try {
                        while (bytesRead!=-1){
                            bytesRead = stream.read(data, 0, data.length);
                            if (bytesRead>=0){
                                line.write(data, 0, bytesRead);
                            }
                        }
                        line.drain();
                        line.close();
                    } catch (final Exception ex){
                        Virheilmoitus.naytaVirheilmoitus("Äänentoistossa virhe: "+ex.getClass()+" virhe: "+ex.getMessage());
                    }
                }
                
            } catch (final UnsupportedAudioFileException | IOException ex) {
                Virheilmoitus.naytaVirheilmoitus("Virhe äänen toistossa: " + ex.getMessage());
            }
        
    }

    @Override
    public void run() {
        toistaAani();
    }
    
}
