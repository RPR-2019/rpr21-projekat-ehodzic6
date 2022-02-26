package ba.unsa.etf.rpr;

import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.util.Date;

public class Osoba {
    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    enum Spol{
        muško,
        žensko,
        nedef;

    }
    String ime;
    String prezime;
    String jmbg;
    LocalDate datumRodjenja;
    Spol spol;

    public Osoba(String ime, String prezime, String jmbg, LocalDate datumRodjenja, Spol spol) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.datumRodjenja = datumRodjenja;
        this.spol = spol;
    }
    public Osoba(){
        ime="";
        prezime="";
        jmbg="";
        spol=Spol.nedef;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Spol getSpol() {
        return spol;
    }

    public void setSpol(Spol spol) {
        this.spol = spol;
    }


}
