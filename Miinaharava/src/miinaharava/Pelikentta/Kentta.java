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

    private void luoKentta(int koko, int miinoja) {
        for (int i = 0; i < koko; i++) {
            for (int k = 0; k < koko; k++) {
                Solu s = new Solu();
                solut[i][k] = s;
            }
        }

        for (int m = 0; m < miinoja; m++) {
            int x = (int) (Math.random() * (koko - 1));
            int y = (int) (Math.random() * (koko - 1));
            if (!solut[x][y].isMiina()) {
                solut[x][y].setMiina(true);
            } else {
                m--;
            }
        }

        for (int i = 0; i < koko; i++) {
            for (int k = 0; k < koko; k++) {
                if (!solut[i][k].isMiina()) {
                    paivitaViereistenMiinatieto(i, k);
                }
            }
        }

    }

    private void paivitaViereistenMiinatieto(int x, int y) {
        Solu solu = solut[x][y];
        int yla = y - 1;
        int ala = y + 1;
        int vasen = x - 1;
        int oikea = x + 1;

        if (onKartallaJaMiina(vasen, yla)) {
            solu.lisaaViereenMiinoja();
        }
        if (onKartallaJaMiina(x, yla)) {
            solu.lisaaViereenMiinoja();
        }
        if (onKartallaJaMiina(oikea, yla)) {
            solu.lisaaViereenMiinoja();
        }
        if (onKartallaJaMiina(vasen, y)) {
            solu.lisaaViereenMiinoja();
        }
        if (onKartallaJaMiina(oikea, y)) {
            solu.lisaaViereenMiinoja();
        }
        if (onKartallaJaMiina(vasen, ala)) {
            solu.lisaaViereenMiinoja();
        }
        if (onKartallaJaMiina(x, ala)) {
            solu.lisaaViereenMiinoja();
        }
        if (onKartallaJaMiina(oikea, ala)) {
            solu.lisaaViereenMiinoja();
        }
    }

    private boolean onKartallaJaMiina(int x, int y) {
        if (x >= 0 && y >= 0 && x < solut.length && y < solut.length) {
            return solut[x][y].isMiina();
        }
        return false;
    }
}
