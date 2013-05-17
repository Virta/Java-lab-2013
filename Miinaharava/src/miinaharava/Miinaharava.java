package miinaharava;

import java.util.Scanner;
import miinaharava.Entiteetit.KenttaProfiili;
import miinaharava.Entiteetit.VakioProfiilit;
import miinaharava.Pelikentta.Kentta;
import miinaharava.Pelikentta.Moottori;
import miinaharava.Pelikentta.Solu;

/**
 *
 * @author frojala
 */
public class Miinaharava {

    public static void main(String[] args) {

        VakioProfiilit vakioProfiilit = new VakioProfiilit();
        KenttaProfiili profiili = vakioProfiilit.getVaikea();
        KenttaProfiili testiProfiili = new KenttaProfiili("SuperVaikea", 10, 10);
        Kentta kentta = new Kentta(testiProfiili);
        Moottori moottori = new Moottori(testiProfiili);

        Scanner luki = new Scanner(System.in);

        System.out.println("Miinaharava!");
        while (true) {
            tulosta(testiProfiili, moottori);
            System.out.print("syötä x koordinaatti (tai end lopettaaksesi): ");
            String xString = luki.nextLine();
            if (xString.equals("end")){
                break;
            }
            System.out.print("syötä y koordinaatti: ");
            String yString = luki.nextLine();
            int x=0;
            int y=0;
            
            try {
                x = Integer.parseInt(xString);
                y = Integer.parseInt(yString);
            } catch (Exception e){
                System.out.println("Syötit vääriä arvoja!"+e.getLocalizedMessage());
                continue;
            }
            
            int palaute = moottori.aukaise(x, y);
            if (palaute == -1){
                moottori.aukaiseKaikki();
                tulosta(testiProfiili, moottori);
                System.out.println("Miina! Peli loppui!");
                break;
            } else {
                tulosta(testiProfiili, moottori);
            }
        }
    }

    private static void tulosta(KenttaProfiili testiProfiili, Moottori m) {
        for (int i = 0; i < testiProfiili.getKoko(); i++) {
            for (int k = 0; k < testiProfiili.getKoko(); k++) {
                Solu s = m.getKentta().getSolu(i, k);
                if (s.isAuki()) {
                    if (s.isMiina()){
                        System.out.print("O ");
                    }
                    else {
                        System.out.print(s.getVieressaMiinoja() + " ");
                    }
                } 
                else {
                    System.out.print("X ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
