/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Pelikentta;

import miinaharava.Entiteetit.KenttaProfiili;

/**
 *
 * @author virta
 */
public class Kentta {

    private Solu[][] solut;
    private int miinojaJaljella;
    private KenttaProfiili profiili;
    private boolean miinatietoPaivitettava;

    public Kentta(KenttaProfiili profiili) {
        this.profiili = profiili;
        int miinoja = profiili.getMiinoja();
        int koko = profiili.getKoko();
        solut = new Solu[koko][koko];
        miinojaJaljella = miinoja;
        miinatietoPaivitettava = false;

        luoKentta(koko, miinoja);

    }

    public int getMiinojaJaljella() {
        if (miinatietoPaivitettava) {
            paivitaMiinatieto();
        }
        return this.miinojaJaljella;
    }

    public Solu getSolu(int x, int y) {
        miinatietoPaivitettava = true;
        return this.solut[x][y];
    }

//    Mahdollisesti toteutettava, mennään ensin palauttamalla solmu kutsuvalle metodille. Jos toteutettava, muista miinatiedon päivitys.
//    private void setFlagi(int x, int y){
//        this.solut[x][y].setFlagit();
//    }
//    
//    private int getFlagi(int x, int y){
//        return this.solut[x][y].getFlagi();
//    }
//    
//    public boolean isMiina(int x, int y){
//        return this.solut[x][y].isMiina();
//    }
//    
//    public int vieressaMiinoja(int x, int y){
//        return this.solut[x][y].getVieressaMiinoja();
//    }
    private int arvoX(int koko) {
        return (int) (Math.random() * (koko - 1));
    }

    private int arvoY(int koko) {
        return (int) (Math.random() * (koko - 1));
    }

    private void luoKentta(int koko, int miinoja) {
        for (int i = 0; i < koko; i++) {
            for (int k = 0; k < koko; k++) {
                Solu s = new Solu();
                solut[i][k] = s;
            }
        }

        for (int m = 0; m < miinoja; m++) {
            int x = arvoX(koko);
            int y = arvoY(koko);
            solut[x][y].setMiina(true);
        }
        for (int i = 0; i < koko; i++) {
            for (int k = 0; k < koko; k++) {
                if (solut[i][k].isMiina()){
                    paivitaViereistenMiinatieto(i, k);
                }
            }
        }
    }

    private void paivitaMiinatieto() {
        int miinat = profiili.getMiinoja();
        for (int i = 0; i < profiili.getKoko(); i++) {
            for (int k = 0; k < profiili.getKoko(); k++) {
                if (solut[i][k].getFlagi() == 1) {
                    miinat--;
                }
            }
        }
        miinojaJaljella = miinat;
        miinatietoPaivitettava = false;
    }

    private void paivitaViereistenMiinatieto(int x, int y) {
        int yla = x - 1;
        int ala = x + 1;
        int vasen = y - 1;
        int oikea = y + 1;

        if (onKartallaJaEiMiina(yla, vasen)) {
            solut[yla][vasen].lisaaViereenMiinoja();
        }
        if (onKartallaJaEiMiina(yla, y)) {
            solut[yla][y].lisaaViereenMiinoja();
        }
        if (onKartallaJaEiMiina(yla, oikea)) {
            solut[yla][oikea].lisaaViereenMiinoja();
        }
        if (onKartallaJaEiMiina(x, vasen)) {
            solut[x][vasen].lisaaViereenMiinoja();
        }
        if (onKartallaJaEiMiina(x, oikea)) {
            solut[x][oikea].lisaaViereenMiinoja();
        }
        if (onKartallaJaEiMiina(ala, vasen)) {
            solut[ala][vasen].lisaaViereenMiinoja();
        }
        if (onKartallaJaEiMiina(ala, y)) {
            solut[ala][y].lisaaViereenMiinoja();
        }
        if (onKartallaJaEiMiina(ala, oikea)) {
            solut[ala][oikea].lisaaViereenMiinoja();
        }
    }

    private boolean onKartallaJaEiMiina(int x, int y) {
        if (x > 0 && y > 0 && x < solut.length && y < solut.length) {
            return solut[x][y].isMiina();
        }
        return false;
    }
}
