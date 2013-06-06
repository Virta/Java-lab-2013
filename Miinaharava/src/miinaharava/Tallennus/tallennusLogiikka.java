/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Tallennus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;

/**
 * 
 * Luokka joka hallinnoin tallennus logiikkaa; tällä luokalla ei ole kontruktoria, sillä sen ei tarvitse tehdä muuta kuin kirjoittaa
 * sille annettu informaatio tiedostoon, ja palauttaa informaatio tiedostosta ohjelman käynnistyessä.
 *
 * @author virta
 */
public class tallennusLogiikka {

    /**
     * Tallentaa parametrina saadun tuloslistan tietostoon Tulokset.txt.
     * Kaikki tulokset kirjoitetaan aina, tiedoston kaikki informaatio ylikirjoitetaan suoritushetkellä olioina olevasta tiedosta.
     * Ohjelma luo tiedoston aina sulkeutuessa sen hetkisellä informaatiolla; ensimmäisellä suorituskerralla ainoastaan saateviestillä.
     * @param tulokset
     * @throws Exception 
     */
    public static void tallenna(LinkedList<Tulos> tulokset) throws Exception {
        try {
            FileWriter kirjoittaja = new FileWriter("Tulokset.txt");
            String alkuosa = "Tämä on automaattisesti generoitu tiedosto, älä muuta, rikot vielä jotain!\n";
            kirjoittaja.write(alkuosa);
            
            kirjoitaTulosStringit(tulokset, kirjoittaja);
            
            kirjoittaja.close();
            
        } catch (Exception e) {
            tallennuksenVirheilmoitus.naytaVirheilmoitus(e.getMessage());
        }
        
    }
    
    private static void kirjoitaTulosStringit(LinkedList<Tulos> tulokset, FileWriter kirjoittaja) throws IOException {
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
                    + tulos.getOnnistuiko()
                    +"\n";
            kirjoittaja.write(tallennettava);
        }
    }

    /**
     * Palauttaa tiedostosta Tulokset.txt kaikki tulokset jotka siihen on tallennettu.
     * Kaikki tulokset palautetaan ja ladataan olioiksi ohjelmaan.
     * Ohjelman ensimmäisellä suorituskerralla lataaminen palauttaa virheilmoituksen, jos tiedostoa ei ole manuaalisesti luotu.
     * Kun ohjelma suljetaan heti tämän jälkeen luodaan tiedosto tyhjällä sisällöllä, ainoastaan saateviestillä.
     * Seuraava suorituskerta ei anna virhettä.
     * @param kayttajat
     * @param profiilit
     * @param tulokset
     * @throws Exception 
     */
    public static void palauta(HashMap<String, Kayttaja> kayttajat, HashMap<String, KenttaProfiili> profiilit, LinkedList<Tulos> tulokset) throws Exception {
        try {
            File tulosTiedosto = new File("Tulokset.txt");
            Scanner lukija = new Scanner(tulosTiedosto, "UTF-8");
            if (lukija.hasNextLine()){
                lukija.nextLine();
            }
            
            lueJaPuraTulokset(lukija, kayttajat, profiilit, tulokset);
            
            lukija.close();
            
        } catch (Exception e){
            tallennuksenVirheilmoitus.naytaVirheilmoitus(e.getMessage());
        }
        
    }

    /**
     * Lukee ja purkaa yksittäisen tulosrivin komponentteihin ja kutsuu saaduilla olioilla lisaaOliotListoihin-metodia.
     * @param lukija
     * @param kayttajat
     * @param profiilit
     * @param tulokset
     * @throws NumberFormatException Jos kentän koko tai miinojen määrässä on virheellistä informaatiota, esim. jos tuloslistaa on käsin muokattu, heitetään poikkeus ja näytetään virheilmoitus.
     */
    private static void lueJaPuraTulokset(Scanner lukija, HashMap<String, Kayttaja> kayttajat, HashMap<String, KenttaProfiili> profiilit, LinkedList<Tulos> tulokset) throws NumberFormatException {
        
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
            
            lisaaOliotListoihin(kayttajat, nimimerkki, kayttaja, profiilit, kenttaProfiiliNimi, profiili);
            
            tulokset.add(tulos);
            
        }
        
    }

    /**
     * Lisää parametrina saadut oliot käyttäjistä, tuloksista ja peliprofiileista asianmukaisiin listoihin jos niitä ei ole vielä lisätty.
     * @param kayttajat
     * @param nimimerkki
     * @param kayttaja
     * @param profiilit
     * @param kenttaProfiiliNimi
     * @param profiili 
     */
    private static void lisaaOliotListoihin(HashMap<String, Kayttaja> kayttajat, String nimimerkki, Kayttaja kayttaja, HashMap<String, KenttaProfiili> profiilit, String kenttaProfiiliNimi, KenttaProfiili profiili) {
        if (!kayttajat.containsKey(nimimerkki)) {
            kayttajat.put(nimimerkki, kayttaja);
        }
        
        if (!profiilit.containsKey(kenttaProfiiliNimi)) {
            profiilit.put(kenttaProfiiliNimi, profiili);
        }
        
        if (!kayttajat.get(nimimerkki).getKaikkiProfiilit().containsKey(kenttaProfiiliNimi)){
            kayttajat.get(nimimerkki).addProfiili(profiili);
        }
        
    }
    
}
