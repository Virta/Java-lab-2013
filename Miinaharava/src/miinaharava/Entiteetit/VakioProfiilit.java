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
        this.keskiVaikea = new KenttaProfiili("Keskivaikea", 20, 40);
        this.vaikea = new KenttaProfiili("Vaikea", 25, 100);
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
