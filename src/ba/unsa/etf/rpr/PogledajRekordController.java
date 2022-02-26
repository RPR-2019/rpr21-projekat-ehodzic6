package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class PogledajRekordController implements Initializable {
    public ProgressBar progressStepen;
    public Label labelaSpol;
    public Label labelaDatum;
    public Label labelaPrezime;
    public Label labelaJMBG;
    public Label labelaIme;
    public Osoba osoba;
    public RekordiDAO rekordiDAO=new RekordiDAO();

    public void actionNazad(ActionEvent actionEvent) {
    }

    public void actionDodajDjelo(ActionEvent actionEvent) {
    }

    public void actionPogledajNalaz(ActionEvent actionEvent) {
    }

    public void actionDodajNalaz(ActionEvent actionEvent) {
    }

    public void setProgressStepen(ProgressBar progressStepen) {
        this.progressStepen = progressStepen;
    }

    public void setLabelaSpol(String labelaSpol) {
        this.labelaSpol.setText(labelaSpol);
    }

    public void setLabelaDatum(String labelaDatum) {
        this.labelaDatum.setText(labelaDatum);
    }

    public void setLabelaPrezime(String labelaPrezime) {
        this.labelaPrezime.setText(labelaPrezime);
    }

    public void setLabelaJMBG(String labelaJMBG) {
        this.labelaJMBG.setText(labelaJMBG);
    }

    public void setLabelaIme(String labelaIme) {
        this.labelaIme.setText(labelaIme);
    }

    public void setOsoba(Osoba osoba) {
        setLabelaIme(osoba.getIme());
        setLabelaPrezime(osoba.getPrezime());
        setLabelaJMBG(osoba.getJmbg());
        setLabelaSpol(osoba.getSpol().toString());
        if(osoba.getDatumRodjenja()!=null)setLabelaDatum(osoba.getDatumRodjenja().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//            setLabelaIme(osoba.getIme());
//            setLabelaPrezime(osoba.getPrezime());
//            setLabelaJMBG(osoba.getJmbg());
//            setLabelaSpol(osoba.getSpol().toString());
//            if(osoba.getDatumRodjenja()!=null)setLabelaDatum(osoba.getDatumRodjenja().toString());
    }
}
