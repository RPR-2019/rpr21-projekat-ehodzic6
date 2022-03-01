package ba.unsa.etf.rpr;

public class PravnoLice {
    private String naziv;

    public PravnoLice(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
