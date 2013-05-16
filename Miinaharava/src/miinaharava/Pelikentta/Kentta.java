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
        luoSolut(koko);
        luoMiinat(miinoja, koko);
        paivitaJaLinkita(koko);
    }

    private void paivitaJaLinkitaSolu(int x, int y) {
        Solu solu = solut[x][y];
        int yla = y - 1;
        int ala = y + 1;
        int vasen = x - 1;
        int oikea = x + 1;

        if (onKartalla(vasen, yla)) {
            if (solut[vasen][yla].isMiina()) {
                solu.lisaaViereenMiinoja();
            }
            solu.lisaaVierusSolu(solut[vasen][yla]);
        }
        if (onKartalla(x, yla)) {
            if (solut[x][yla].isMiina()) {
                solu.lisaaViereenMiinoja();
            }
            solu.lisaaVierusSolu(solut[x][yla]);
            solu.lisaaSivuVierus(solut[x][yla]);
        }
        if (onKartalla(oikea, yla)) {
            if (solut[oikea][yla].isMiina()) {
                solu.lisaaViereenMiinoja();
            }
            solu.lisaaVierusSolu(solut[oikea][yla]);
        }
        if (onKartalla(vasen, y)) {
            if (solut[vasen][y].isMiina()) {
                solu.lisaaViereenMiinoja();
            }
            solu.lisaaVierusSolu(solut[vasen][y]);
            solu.lisaaSivuVierus(solut[vasen][y]);
        }
        if (onKartalla(oikea, y)) {
            if (solut[oikea][y].isMiina()) {
                solu.lisaaViereenMiinoja();
            }
            solu.lisaaVierusSolu(solut[oikea][y]);
            solu.lisaaSivuVierus(solut[oikea][y]);
        }
        if (onKartalla(vasen, ala)) {
            if (solut[vasen][ala].isMiina()) {
                solu.lisaaViereenMiinoja();
            }
            solu.lisaaVierusSolu(solut[vasen][ala]);
        }
        if (onKartalla(x, ala)) {
            if (solut[x][ala].isMiina()) {
                solu.lisaaViereenMiinoja();
            }
            solu.lisaaVierusSolu(solut[x][ala]);
            solu.lisaaSivuVierus(solut[x][ala]);
        }
        if (onKartalla(oikea, ala)) {
            if (solut[oikea][ala].isMiina()) {
                solu.lisaaViereenMiinoja();
            }
            solu.lisaaVierusSolu(solut[oikea][ala]);
        }
    }

    private boolean onKartalla(int x, int y) {
        return (x >= 0 && y >= 0 && x < solut.length && y < solut.length);
    }

    private void luoSolut(int koko) {
        for (int i = 0; i < koko; i++) {
            for (int k = 0; k < koko; k++) {
                Solu s = new Solu();
                solut[i][k] = s;
            }
        }
    }

    private void luoMiinat(int miinoja, int koko) {
        for (int m = 0; m < miinoja; m++) {
            int x = (int) (Math.random() * (koko - 1));
            int y = (int) (Math.random() * (koko - 1));
            if (!solut[x][y].isMiina()) {
                solut[x][y].setMiina();
            } else {
                m--;
            }
        }
    }

    private void paivitaJaLinkita(int koko) {
        for (int i = 0; i < koko; i++) {
            for (int k = 0; k < koko; k++) {
                if (!solut[i][k].isMiina()) {
                    paivitaJaLinkitaSolu(k, k);
                }
            }
        }
    }
}
