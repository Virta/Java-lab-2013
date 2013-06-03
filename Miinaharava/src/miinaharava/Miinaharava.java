package miinaharava;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import miinaharava.Entiteetit.Kayttaja;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.Tulos;
import miinaharava.Entiteetit.VakioProfiilit;
import miinaharava.Kayttoliittyma.AloitusNakyma;
import miinaharava.Pelikentta.Kentta;
import miinaharava.Pelikentta.Moottori;
import miinaharava.Pelikentta.Solu;
import miinaharava.Tallennus.tallennusLogiikka;

/**
 * Tämä on Miinaharavan pääluokka, se palauttaa tulokset tiedostosta, käynnistää käyttöliittymän ja tallentaa tulokset lopetettaessa.
 * @author frojala
 * 
 * 
 */
public class Miinaharava {

    public static void main(String[] args) throws InterruptedException, Exception {
        
        HashMap<String, Kayttaja> pelaajat = new HashMap<>();
        HashMap<String, KenttaProfiili> peliProfiilit = new HashMap<>();
        VakioProfiilit vakioProfiilit = new VakioProfiilit();
        LinkedList<Tulos> tulokset = new LinkedList<>();
        Tulos tulos = new Tulos("1:11", new KenttaProfiili("joku", 10, 10), new Kayttaja("Jaska"), true);
//        tulokset.add(tulos);
//        tallennusLogiikka.tallenna(tulokset);
        tallennusLogiikka.palauta(pelaajat, peliProfiilit, tulokset);
//        tulokset.clear();
        AloitusNakyma kayttoliittyma = new AloitusNakyma(pelaajat, peliProfiilit, vakioProfiilit, tulokset);
        SwingUtilities.invokeLater(kayttoliittyma);
        
        tallennusLogiikka.tallenna(tulokset);
        
    }
    
}

//        VakioProfiilit vakioProfiilit = new VakioProfiilit();
//        KenttaProfiili profiili = vakioProfiilit.getVaikea();
//        KenttaProfiili testiProfiili = new KenttaProfiili("SuperVaikea", 10, 1);
//        Kentta kentta = new Kentta(testiProfiili);
//        Moottori moottori = new Moottori(testiProfiili);
//
//        Scanner luki = new Scanner(System.in);
//        
//        tulostaFlageilla(testiProfiili, moottori);
//        for (int i = 0; i < testiProfiili.getKoko(); i++) {
//            boolean keskeyta = false;
//            for (int k = 0; k < testiProfiili.getKoko(); k++) {
//                if (moottori.getKentta().getSolu(i, k).getVieressaMiinoja()>0) {
//                    moottori.aukaiseYksi(i, k);
//                    keskeyta = true;
//                }
//                if (keskeyta){
//                    break;
//                }
//            }
//            if (keskeyta){
//                break;
//            }
//        }
//        
//        tulostaFlageilla(testiProfiili, moottori);
//        for (int i = 0; i < testiProfiili.getKoko(); i++) {
//            for (int k = 0; k < testiProfiili.getKoko(); k++) {
//                if (moottori.getKentta().getSolu(i, k).isMiina()) {
//                    moottori.getKentta().asetaFlagi(i, k);
//                }
//            }
//        }
//        
//        tulostaFlageilla(testiProfiili, moottori);
//        
//        Thread.sleep(2000);
//        
//        for (int i = 0; i < testiProfiili.getKoko(); i++) {
//            boolean keskeyta = false;
//            for (int k = 0; k < testiProfiili.getKoko(); k++) {
//                if (moottori.getKentta().getSolu(i, k).isAuki()) {
//                    moottori.aukaiseMonta(i, k);
//                    keskeyta = true;
//                }
//                if (keskeyta){
//                    break;
//                }
//            }
//            if (keskeyta){
//                break;
//            }
//        }
//        
//        tulostaFlageilla(testiProfiili, moottori);
//        System.out.println(moottori.getAika());
//        int aika = (int) moottori.getAika();
//        System.out.println(aika);
        
//        tulostaFlageilla(testiProfiili, moottori);
//
//
//        System.out.println("Miinaharava!");
//        while (true) {
//            tulosta(testiProfiili, moottori);
//            System.out.print("syötä x koordinaatti (new uuteen peliin tai end lopettaaksesi): ");
//            String xString = luki.nextLine();
//            if (xString.equals("end")){
//                break;
//            } 
//            if (xString.equals("new")){
//                moottori = new Moottori(testiProfiili);
//                continue;
//            }
//            System.out.print("syötä y koordinaatti: ");
//            String yString = luki.nextLine();
//            int x=0;
//            int y=0;
//            
//            try {
//                x = Integer.parseInt(xString);
//                y = Integer.parseInt(yString);
//            } catch (Exception e){
//                System.out.println("Syötit vääriä arvoja!");
//                continue;
//            }
//            
//            if (x<0 || x>=testiProfiili.getKoko() || y<0 || y>=testiProfiili.getKoko()){
//                System.out.println("Syötit arvoja kentän ulkopuolella!");
//                continue;
//            }
//            
//            int palaute = moottori.aukaiseYksi(x, y);
//            System.out.println(palaute);
//            if (palaute == -1){
//                tulosta(testiProfiili, moottori);
//                System.out.println("Miina! Peli loppui! Syötä new aloittaaksesi uuden pelin.");
//            } 
//        }
//    }
//
//    private static void tulosta(KenttaProfiili testiProfiili, Moottori m) {
//        for (int i = 0; i < testiProfiili.getKoko(); i++) {
//            for (int k = 0; k < testiProfiili.getKoko(); k++) {
//                Solu s = m.getKentta().getSolu(i, k);
//                if (s.isAuki()) {
//                    if (s.isMiina()) {
//                        System.out.print("M ");
//                    } else {
//                        System.out.print(s.getVieressaMiinoja() + " ");
//                    }
//                } else {
//                    System.out.print("X ");
//                }
//            }
//            System.out.println("");
//        }
//        System.out.println("");
//    }
//
//    private static void tulostaFlageilla(KenttaProfiili testiProfiili, Moottori moottori) {
//        for (int i = 0; i < testiProfiili.getKoko(); i++) {
//            for (int k = 0; k < testiProfiili.getKoko(); k++) {
//                if (moottori.getKentta().getSolu(i, k).getFlagi() == 1) {
//                    System.out.print("F ");
//                } else if (moottori.getKentta().getSolu(i, k).isMiina()) {
//                    System.out.print("M ");
//                } else if (!moottori.getKentta().getSolu(i, k).isAuki()) {
//                    System.out.print("K ");
//                } else {
//                    System.out.print("A ");
//                }
//            }
//            System.out.println("");
//        }
//        System.out.println("");
//    }
//}
