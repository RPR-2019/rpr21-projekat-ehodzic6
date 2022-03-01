package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class dodajDjeloController implements Initializable {
    public ChoiceBox choiceKategorija;
    public TextField naziv,mjesto,kod;
    public DatePicker datum;
    public Slider stepen;
    public ListView osteceniList;
    public String jmbg;
    public TextArea opis;

    public void setJmbg(String jmbg){
        this.jmbg=jmbg;
    }

    public void dodajOsobu(ActionEvent actionEvent) throws IOException {
        if(naziv.getText().isBlank() || mjesto.getText().isBlank() || kod.getText().isBlank() || datum.getValue()==null || choiceKategorija.getValue()==null){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Greška!");
            alert.setContentText("Nisu uneseni svi podaci");
            alert.setHeaderText("Greška!");
            alert.showAndWait();
        }
        else{
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/fxml/dodajOstecenuOsobu.fxml"));
            Parent root=fxmlLoader.load();
            dodajOstecenuOsobuController dodajOstecenuOsobuController=fxmlLoader.getController();


            dodajOstecenuOsobuController.setKod(kod.getText());
            Stage stage=new Stage();
            stage.setTitle("Dodaj osobu");
            stage.setScene(new Scene(root,1000,800));
            stage.setResizable(false);
            stage.showAndWait();
            if(stage.getUserData()!=null) {
                Osoba osoba = (Osoba) stage.getUserData();
                osteceniList.getItems().add(osoba);
            }
        }
    }

    public void dodajPravno(ActionEvent actionEvent) throws IOException {
        if(naziv.getText().isBlank() || mjesto.getText().isBlank() || kod.getText().isBlank() || datum.getValue()==null || choiceKategorija.getValue()==null){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Greška!");
            alert.setContentText("Nisu uneseni svi podaci");
            alert.setHeaderText("Greška!");
            alert.showAndWait();
        }
        else{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/dodajPravnoLice.fxml"));
            Parent root=loader.load();
            dodajPravnoLiceController dodajPravnoLiceController=loader.getController();
            dodajPravnoLiceController.setKod(kod.getText());
            Stage stage=new Stage();
            stage.setScene(new Scene(root,600,300));
            stage.setTitle("Dodaj pravno lice");
            stage.setResizable(false);
            stage.showAndWait();
            if(stage.getUserData()!=null ){
                PravnoLice pravnoLice = (PravnoLice) stage.getUserData();
                osteceniList.getItems().add(pravnoLice);
            }

        }
    }

    public void odustani(ActionEvent actionEvent) throws SQLException {
        Stage stage1= (Stage) choiceKategorija.getScene().getWindow();
        stage1.close();
        if(!kod.getText().isBlank()){
            RekordiDAO dao=new RekordiDAO();
            dao.getObrisiOstecene().setString(1,kod.getText());
            dao.getObrisiOstecene().executeUpdate();
        }
    }

    public void potvrdi(ActionEvent actionEvent) throws SQLException {
        if(!(naziv.getText().isBlank() || mjesto.getText().isBlank() || kod.getText().isBlank() || datum.getValue()==null || choiceKategorija.getValue()==null)) {
            RekordiDAO rekordiDAO = new RekordiDAO();
            rekordiDAO.getNadjiDjelo().setString(1,kod.getText());
            ResultSet resultSet=rekordiDAO.getNadjiDjelo().executeQuery();
            if(!resultSet.next()) {
                rekordiDAO.getDodajDjelo().setString(1, jmbg);
                rekordiDAO.getDodajDjelo().setString(2, naziv.getText());
                rekordiDAO.getDodajDjelo().setString(3, opis.getText());
                rekordiDAO.getDodajDjelo().setString(4, datum.getValue().toString());
                rekordiDAO.getDodajDjelo().setDouble(5, stepen.getValue());
                rekordiDAO.getDodajDjelo().setString(6, kod.getText());
                rekordiDAO.getDodajDjelo().setString(7, choiceKategorija.getValue().toString());
                rekordiDAO.getDodajDjelo().setString(8, mjesto.getText());
                rekordiDAO.getDodajDjelo().executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potvrda");
                alert.setHeaderText("Potvrda");
                alert.setContentText("Uspješno ste dodali djelo");
                alert.showAndWait();
                KriminalnoDjelo kriminalnoDjelo = new KriminalnoDjelo(kod.getText(), naziv.getText(), opis.getText(), datum.getValue(), mjesto.getText(), jmbg, new KategorijaDjela(choiceKategorija.getValue().toString()), stepen.getValue());
                Stage stage = (Stage) naziv.getScene().getWindow();
                stage.setUserData(kriminalnoDjelo);
                stage.close();
            }
            else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText("Greška");
                alert.setContentText("Djelo sa unesenim jedinstvenim kodom već postoji");
                alert.showAndWait();
                rekordiDAO.zatvoriKon();
            }
        }
        else{
            if(kod.getText().isBlank()){
                kod.getStyleClass().removeAll("zelenalight");
                kod.getStyleClass().add("greska");
            }
            if(naziv.getText().isBlank()){
                naziv.getStyleClass().removeAll("zelenalight");
                naziv.getStyleClass().add("greska");
            }
            if(opis.getText().isBlank()){
                opis.getStyleClass().removeAll("zelenalight");
                opis.getStyleClass().add("greska");
            }
            if(mjesto.getText().isBlank()){
                mjesto.getStyleClass().removeAll("zelenalight");
                mjesto.getStyleClass().add("greska");
            }
            if(datum.getValue()==null){
                datum.getStyleClass().removeAll("zelenalight");
                datum.getStyleClass().add("greska");
            }
            if(choiceKategorija.getValue()==null){
                choiceKategorija.getStyleClass().removeAll("zelenalight");
                choiceKategorija.getStyleClass().add("greska");
            }
            if(!kod.getText().isBlank()){
                kod.getStyleClass().removeAll("greska");
                kod.getStyleClass().add("zelenalight");
            }
            if(!naziv.getText().isBlank()){
                naziv.getStyleClass().removeAll("greska");
                naziv.getStyleClass().add("zelenalight");
            }
            if(!opis.getText().isBlank()){
                opis.getStyleClass().removeAll("greska");
                opis.getStyleClass().add("zelenalight");
            }
            if(!mjesto.getText().isBlank()){
                mjesto.getStyleClass().removeAll("greska");
                mjesto.getStyleClass().add("zelenalight");
            }
            if(!(datum.getValue()==null)){
                datum.getStyleClass().removeAll("greska");
                datum.getStyleClass().add("zelenalight");
            }
            if(!(choiceKategorija.getValue()==null)){
                choiceKategorija.getStyleClass().removeAll("greska");
                choiceKategorija.getStyleClass().add("zelenalight");
            }
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška");
            alert.setContentText("Polja ne smiju da budu prazna");
            alert.showAndWait();


        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datum.setEditable(false);
        datum.setValue(LocalDate.now());
        ArrayList<KategorijaDjela> kategorijeDjela=new ArrayList<>();
        kategorijeDjela.add(new KategorijaDjela("Ubistvo"));
        kategorijeDjela.add(new KategorijaDjela("Pljačka"));
        kategorijeDjela.add(new KategorijaDjela("Vandalizam"));
        kategorijeDjela.add(new KategorijaDjela("Krađa"));
        kategorijeDjela.add(new KategorijaDjela("Pronevjera"));
        kategorijeDjela.add(new KategorijaDjela("Iznuda"));
        kategorijeDjela.add(new KategorijaDjela("Narušavanje javnog reda i mira"));
        kategorijeDjela.add(new KategorijaDjela("Ostalo"));
        choiceKategorija.setItems(FXCollections.observableArrayList(kategorijeDjela));
        RekordiDAO rekordiDAO=new RekordiDAO();
        ResultSet rs;
        try {
            rekordiDAO.getDajOsteceneOsobe().setString(1,kod.getText());
            rs=rekordiDAO.getDajOsteceneOsobe().executeQuery();
            while(rs.next()){
                Osoba osoba=new Osoba();
                osoba.setIme(rs.getString(2));
                osoba.setPrezime(rs.getString(3));
                osoba.setJmbg(rs.getString(4));
                if(rs.getString(5).equals("Muško")){
                    osoba.setSpol(Osoba.Spol.muško);
                }
                else{
                    osoba.setSpol(Osoba.Spol.žensko);
                }
                osoba.setDatumRodjenja(LocalDate.parse(rs.getString(6)));
                osteceniList.getItems().add(osoba);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) kod.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage.setTitle("Prijava");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();
    }

        public void about(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        Parent root=(new FXMLLoader(getClass().getResource("/fxml/about.fxml"))).load();
        stage.setScene(new Scene(root,600,400));
        stage.setResizable(false);
        stage.show();
        }
}
