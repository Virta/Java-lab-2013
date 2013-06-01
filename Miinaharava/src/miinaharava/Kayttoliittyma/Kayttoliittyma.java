/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import miinaharava.Entiteetit.VakioProfiilit;

/**
 *
 * @author virta
 */
public class Kayttoliittyma implements Runnable {
    
    private JFrame frame;
    private HashMap<String, Kayttaja> pelaajat;
    HashMap<String, KenttaProfiili> peliProfiilit;
    HashMap<String, KenttaProfiili> vakioProfiilit;
    LinkedList<Tulos> tulokset;
    
    public Kayttoliittyma (HashMap<String, Kayttaja> kayttajat, HashMap<String, KenttaProfiili> profiilit, VakioProfiilit vakioProfiilit, LinkedList<Tulos> tulokset){
        this.pelaajat = kayttajat;
        this.peliProfiilit = profiilit;
        this.tulokset = tulokset;
        this.vakioProfiilit.put("helppo", vakioProfiilit.getHelppo());
        this.vakioProfiilit.put("keskivaikea", vakioProfiilit.getKeskiVaikea());
        this.vakioProfiilit.put("vaikea", vakioProfiilit.getVaikea());
        
        
    }

    @Override
    public void run() {
        frame = new JFrame("Miinaharava");
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container container){
        
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
}
