package ba.unsa.etf.rpr;

import java.util.Date;

public class Rekord {
    private String naziv;
    private String opis;
    private Date datum;
    private int stepen;

    public Rekord(String naziv, String opis, Date datum, int stepen) {
        this.naziv = naziv;
        this.opis = opis;
        this.datum = datum;
        this.stepen = stepen;
    }
}
