package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PogledajRekordController implements Initializable {
    public ProgressBar progressStepen;
    public Label labelaSpol;
    public Label labelaDatum;
    public Label labelaPrezime;
    public Label labelaJMBG;
    public Label labelaIme;
    public Osoba osoba;
    public String jmbg;
    public RekordiDAO rekordiDAO=new RekordiDAO();
    public ListView djela;
    public TextField jmbgPrati;
    public TextField stepenPrati;

    public void setStringJmbg(String jmbg){
        this.jmbg=jmbg;
    }



    public void actionNazad(ActionEvent actionEvent) throws SQLException, IOException {
        Stage stage1= (Stage) labelaDatum.getScene().getWindow();
        stage1.close();
        rekordiDAO.zatvoriKon();
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainScreen.fxml"));
        stage.setTitle("Kriminalni rekordi");
        stage.setScene(new Scene(root, 1000, 800));
        stage.setResizable(false);
        stage.show();

    }

    public void actionDodajDjelo(ActionEvent actionEvent) throws SQLException, IOException {
//        Stage stage1= (Stage) labelaDatum.getScene().getWindow();
//        stage1.close();
        rekordiDAO.zatvoriKon();
        Stage stage=new Stage();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/dodajDjelo.fxml"));

        Parent root = loader.load();
        dodajDjeloController dodajDjeloController=loader.getController();
        dodajDjeloController.setJmbg(labelaJMBG.getText());
        stage.setTitle("Dodaj djelo");
        stage.setScene(new Scene(root, 1000, 800));
        stage.setResizable(false);
        stage.showAndWait();
        RekordiDAO dao=new RekordiDAO();
        if(stage.getUserData()!=null) {
            djela.getItems().add((KriminalnoDjelo) stage.getUserData());
            dao.getProsjecniStepen().setString(1, jmbg);
            ResultSet rs2 = dao.getProsjecniStepen().executeQuery();
            int prosjek=(int)Double.parseDouble(rs2.getString(1));
            progressStepen.setProgress(prosjek/10.);
            progressStepen.getStyleClass().removeAll("zuta", "crvena", "narandzasta", "zelena", "zelenalight");
            if (progressStepen.getProgress() <= 0.2) {
                progressStepen.getStyleClass().add("zelena");
            } else if (progressStepen.getProgress() <= 0.3) {
                progressStepen.getStyleClass().add("zelenalight");
            } else if (progressStepen.getProgress() <= 0.7) {
                progressStepen.getStyleClass().add("zuta");
            } else if (progressStepen.getProgress() <= 0.8) {
                progressStepen.getStyleClass().add("narandzasta");
            } else {
                progressStepen.getStyleClass().add("crvena");
            }

        }
        dao.zatvoriKon();

    }


    public void actionPogledajNalaz(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/pogledajNalaz.fxml"));
        Parent root=loader.load();
        PogledajNalazController pogledajNalazController=loader.getController();
        pogledajNalazController.setJmb(jmbg);
        pogledajNalazController.setImePrezime(labelaIme.getText()+" "+labelaPrezime.getText());
        Stage stage=new Stage();
        stage.setScene(new Scene(root,774,553));
        stage.setTitle("Nalaz"+labelaIme.getText()+" "+labelaPrezime.getText());
        rekordiDAO.zatvoriKon();
        stage.show();

    }

    public void actionDodajNalaz(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/fxml/dodajNalaz.fxml"));
        Parent root=fxmlLoader.load();
        DodajNalazController dodajNalazController=fxmlLoader.getController();
        dodajNalazController.setJmbg(jmbg);
        Stage stage=new Stage();
        stage.setTitle("Dodaj nalaz  "+jmbg);
        stage.setScene(new Scene(root,774,553));
        stage.setResizable(false);
        rekordiDAO.zatvoriKon();
        stage.show();
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
        if(osoba.getDatumRodjenja()!=null)setLabelaDatum(osoba.toString2());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        jmbgPrati.textProperty().addListener((obs,stara,nova)-> {
            try {
                rekordiDAO.getNadjiDjela().setString(1,nova.toString());
                ResultSet rs=rekordiDAO.getNadjiDjela().executeQuery();
                while(rs.next()){
                    KriminalnoDjelo kriminalnoDjelo=new KriminalnoDjelo();
                    kriminalnoDjelo.setKrivac(rs.getString(1));
                    kriminalnoDjelo.setNaziv(rs.getString(2));
                    kriminalnoDjelo.setOpis(rs.getString(3));
                    kriminalnoDjelo.setDatum(LocalDate.parse(rs.getString(4)));
                    kriminalnoDjelo.setStepen(Double.parseDouble(rs.getString(5)));
                    kriminalnoDjelo.setJedinstveniKod(rs.getString(6));
                    kriminalnoDjelo.setKategorijaDjela(new KategorijaDjela(rs.getString(7)));
                    kriminalnoDjelo.setMjesto(rs.getString(8));
                    djela.getItems().add(kriminalnoDjelo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                rekordiDAO.getProsjecniStepen().setString(1,nova);
                ResultSet rs2=rekordiDAO.getProsjecniStepen().executeQuery();
                if(rs2.getString(1)!=null) {
                    int prosjek = (int)Double.parseDouble(rs2.getString(1));
                    progressStepen.setProgress(prosjek / 10.);
                    progressStepen.getStyleClass().removeAll("zuta", "crvena", "narandzasta", "zelena", "zelenalight");
                    if (progressStepen.getProgress() <= 0.2) {
                        progressStepen.getStyleClass().add("zelena");
                    } else if (progressStepen.getProgress() <= 0.3) {
                        progressStepen.getStyleClass().add("zelenalight");
                    } else if (progressStepen.getProgress() <= 0.7) {
                        progressStepen.getStyleClass().add("zuta");
                    } else if (progressStepen.getProgress() <= 0.8) {
                        progressStepen.getStyleClass().add("narandzasta");
                    } else {
                        progressStepen.getStyleClass().add("crvena");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });





    }

    public void setJmbgPrati(String jmbgPrati) {
        this.jmbgPrati.setText(jmbgPrati);
    }

    public void setStepenPrati(String stepenPrati) {
        this.stepenPrati.setText(stepenPrati);
    }

    public void actionPristupi(MouseEvent mouseEvent) throws IOException {
        KriminalnoDjelo kriminalnoDjelo= (KriminalnoDjelo) djela.getSelectionModel().getSelectedItem();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/pogledajDjelo.fxml"));
        Parent root=loader.load();
        PogledajDjeloController pogledajDjeloController=loader.getController();
        pogledajDjeloController.setKodString(kriminalnoDjelo.getJedinstveniKod());
        pogledajDjeloController.setPratiKod(kriminalnoDjelo.getJedinstveniKod());
        Stage stage=new Stage();
        stage.setScene(new Scene(root,1000,800));
        stage.setTitle("Djelo: "+kriminalnoDjelo.getJedinstveniKod());
        stage.show();


    }
}
