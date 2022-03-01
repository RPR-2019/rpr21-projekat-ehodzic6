package ba.unsa.etf.rpr;

public class KategorijaDjela {
    private String kategorija;

    public KategorijaDjela(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public String toString() {
        return getKategorija();
    }
}
