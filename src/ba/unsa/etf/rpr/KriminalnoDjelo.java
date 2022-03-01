package ba.unsa.etf.rpr;

import java.time.LocalDate;
import java.util.ArrayList;

public class KriminalnoDjelo {
    private String jedinstveniKod;
    private String naziv;
    private String opis;
    private LocalDate datum;
    private String mjesto;
    private String jmbgKrivca;
    private KategorijaDjela kategorijaDjela;
    private double stepen;

    public KriminalnoDjelo(String jedinstveniKod, String naziv, String opis, LocalDate datum, String mjesto, String krivac,KategorijaDjela kategorijaDjela, double stepen) {
        this.jedinstveniKod = jedinstveniKod;
        this.naziv = naziv;
        this.opis = opis;
        this.datum = datum;
        this.mjesto = mjesto;
        this.jmbgKrivca = krivac;
        this.kategorijaDjela=kategorijaDjela;
        this.stepen=stepen;

    }

    public KriminalnoDjelo() {

    }


    @Override
    public String toString() {
        return getNaziv()+" "+getJedinstveniKod();
    }

    public String getJedinstveniKod() {
        return jedinstveniKod;
    }

    public void setJedinstveniKod(String jedinstveniKod) {
        this.jedinstveniKod = jedinstveniKod;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getMjesto() {
        return mjesto;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
    }

    public String getKrivac() {
        return jmbgKrivca;
    }

    public void setKrivac(String krivac) {
        this.jmbgKrivca = krivac;
    }

    public KategorijaDjela getKategorijaDjela() {
        return kategorijaDjela;
    }

    public void setKategorijaDjela(KategorijaDjela kategorijaDjela) {
        this.kategorijaDjela = kategorijaDjela;
    }

    public String getJmbgKrivca() {
        return jmbgKrivca;
    }

    public void setJmbgKrivca(String jmbgKrivca) {
        this.jmbgKrivca = jmbgKrivca;
    }

    public double getStepen() {
        return stepen;
    }

    public void setStepen(double stepen) {
        this.stepen = stepen;
    }
}
