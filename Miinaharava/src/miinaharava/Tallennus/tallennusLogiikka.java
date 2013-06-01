/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Tallennus;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;

/**
 *
 * @author virta
 */
public class tallennusLogiikka {

    public static void tallenna(LinkedList<Tulos> tulokset) throws IOException {
        try {
            FileWriter kirjoittaja = new FileWriter("Tulokset.txt");
            String alkuosa = "Tämä on automaattisesti generoitu tiedosto, älä muuta, rikot vielä jotain!";
            kirjoittaja.write(alkuosa);
            for (Tulos tulos : tulokset) {
                String tallennettava = tulos.getPelaaja().getNimimerkki()
                        + " "
                        + tulos.getProfiili().getNimi()
                        + " "
                        + tulos.getProfiili().getKoko()
                        + " "
                        + tulos.getProfiili().getMiinoja()
                        + " "
                        + tulos.getAika()
                        +" "
                        + tulos.onnistuiko();
                kirjoittaja.write(tallennettava);
            }
            kirjoittaja.close();
        } catch (IOException iOException) {
        }
    }

    public static void palauta(HashMap<String, Kayttaja> kayttajat, HashMap<String, KenttaProfiili> profiilit, LinkedList<Tulos> tulokset) throws IOException {
        try {
            File tulosTiedosto = new File("Tulokset.txt");
            Scanner lukija = new Scanner(tulosTiedosto, "UTF-8");
            while (lukija.hasNextLine()) {
                String tulosString = lukija.nextLine();
                
                Scanner purkaja = new Scanner(tulosString);
                String nimimerkki = purkaja.next();
                String kenttaProfiiliNimi = purkaja.next();
                String kentanKoko = purkaja.next();
                String kentassaMiinoja = purkaja.next();
                String tuloksenAika = purkaja.next();
                String onnistuiko = purkaja.next();
                purkaja.close();
                
                Kayttaja kayttaja = new Kayttaja(nimimerkki);
                KenttaProfiili profiili = new KenttaProfiili(kenttaProfiiliNimi, Integer.parseInt(kentanKoko), Integer.parseInt(kentassaMiinoja));
                boolean onnistui = Boolean.parseBoolean(onnistuiko);
                Tulos tulos = new Tulos(tuloksenAika, profiili, kayttaja, onnistui);
                
                if (!kayttajat.containsKey(nimimerkki)) {
                    kayttajat.put(nimimerkki, kayttaja);
                }
                
                if (!profiilit.containsKey(kenttaProfiiliNimi)) {
                    profiilit.put(kenttaProfiiliNimi, profiili);
                }
                
                tulokset.add(tulos);
            }
            lukija.close();
        } catch (Exception e){
            naytaVirheilmoitus(e.toString());
        }
        
    }
    
    private static void naytaVirheilmoitus(String virheilmoitus){
        JFrame frame = new JFrame("Virhe!");
        frame.setPreferredSize(new Dimension(100, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.getContentPane().setLayout(new GridLayout(2, 1));
        frame.getContentPane().add(new JLabel("Tulosten latauksessa tapahtui virhe, tuloksia ei voitu ladata:"));
        frame.getContentPane().add(new JLabel(virheilmoitus));
        
        frame.pack();
        frame.setVisible(true);
    }
}
