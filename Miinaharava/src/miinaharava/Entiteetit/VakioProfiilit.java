/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.Entiteetit;

/**
 *
 * @author virta
 */
public class VakioProfiilit {
    
    private KenttaProfiili helppo;
    private KenttaProfiili keskiVaikea;
    private KenttaProfiili vaikea;
    
    public VakioProfiilit(){
        this.helppo = new KenttaProfiili("Helppo", 15, 20);
        this.keskiVaikea = new KenttaProfiili("Keskivaikea", 30, 40);
        this.vaikea = new KenttaProfiili("Vaikea", 50, 60);
    }

    public KenttaProfiili getHelppo() {
        return helppo;
    }

    public KenttaProfiili getKeskiVaikea() {
        return keskiVaikea;
    }

    public KenttaProfiili getVaikea() {
        return vaikea;
    }
    
}
