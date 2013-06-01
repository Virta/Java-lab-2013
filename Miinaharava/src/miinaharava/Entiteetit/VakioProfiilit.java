package miinaharava.Entiteetit;

/**
 * Luokka, joka muodostaa pelin vakioprofiilit, jotka ovat kovakoodattuja.
 *
 * @author virta
 */
public class VakioProfiilit {
    /**
     * Helppo profiili aloittelijoille, koko 15x15, miinoja 20.
     */
    private KenttaProfiili helppo;
    /**
     * Keskivaikea profiili hieman harjaantuneemmalle, koko 20x20, miinoja 40.
     */
    private KenttaProfiili keskiVaikea;
    /**
     * Vaikea profiili kokeneelle pelaajalle, koko 25x25, miinoja 100.
     */
    private KenttaProfiili vaikea;
    
    /**
     * Konstruktori ei ota parametrejä, vaan luo vakioprofiilit kovakoodatuista arvoista.
     */
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
