/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import miinaharava.Entiteetit.VakioProfiilit;

/**
 *
 * @author virta
 */
public class TulosNakyma implements Runnable {
    
    private JFrame frame;
    private HashMap<String, Kayttaja> pelaajat;
    private HashMap<String, KenttaProfiili> peliProfiilit;
    private HashMap<String, KenttaProfiili> vakioProfiilit;
    private LinkedList<Tulos> tulokset;
    
    public TulosNakyma(HashMap<String, Kayttaja> kayttajat, HashMap<String, KenttaProfiili> profiilit, VakioProfiilit vakioProfiilit, LinkedList<Tulos> tulokset) {
        this.pelaajat = kayttajat;
        this.peliProfiilit = profiilit;
        this.tulokset = tulokset;
        this.vakioProfiilit = new HashMap<>();
        this.vakioProfiilit.put("helppo", vakioProfiilit.getHelppo());
        this.vakioProfiilit.put("keskivaikea", vakioProfiilit.getKeskiVaikea());
        this.vakioProfiilit.put("vaikea", vakioProfiilit.getVaikea());
    }

    @Override
    public void run() {
        frame = new JFrame("Miinaharava - tulokset");
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container container){
        lisaaAlkuTesksti(container);
        
    }
    
    private void lisaaAlkuTesksti(Container container){
        JPanel ylapaneeli = new JPanel(new GridLayout(1, 1));
        ylapaneeli.add(new JLabel("Miinaharavan pelitulokset"));
        container.add(ylapaneeli, BorderLayout.NORTH);
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
}
