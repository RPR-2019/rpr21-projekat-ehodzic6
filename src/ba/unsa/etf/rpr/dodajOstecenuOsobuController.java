package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class dodajOstecenuOsobuController implements Initializable {
    public TextField fieldIme;
    public TextField fieldPrezime;
    public TextField fieldJMBG;
    public DatePicker datum;
    public RadioButton radioMusko;
    public RadioButton radioZensko;
    public ImageView slika;
    private String kod="";
    public dodajOstecenuOsobuController() {
    }

    public void actionOdustani(ActionEvent actionEvent) {
        Stage stage= (Stage) fieldIme.getScene().getWindow();
        stage.close();
    }

    public void actionPotvrdi(ActionEvent actionEvent) throws SQLException {
        if(fieldIme.getText().isBlank()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Polja ne smiju biti prazna");
            alert.setContentText("Pokušaj ponovo");
            alert.showAndWait();
            fieldIme.getStyleClass().removeAll("greska");
            fieldPrezime.getStyleClass().removeAll("greska");
            fieldJMBG.getStyleClass().removeAll("greska");
            datum.getStyleClass().removeAll("greska");
            radioMusko.getStyleClass().removeAll("greska");
            radioZensko.getStyleClass().removeAll("greska");
            fieldIme.getStyleClass().add("greska");
        }
        else if(fieldPrezime.getText().isBlank()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Polja ne smiju biti prazna");
            alert.setContentText("Pokušaj ponovo");
            alert.showAndWait();
            fieldIme.getStyleClass().removeAll("greska");
            fieldPrezime.getStyleClass().removeAll("greska");
            fieldJMBG.getStyleClass().removeAll("greska");
            datum.getStyleClass().removeAll("greska");
            radioMusko.getStyleClass().removeAll("greska");
            radioZensko.getStyleClass().removeAll("greska");

            fieldPrezime.getStyleClass().add("greska");
        }
        else if(fieldJMBG.getText().isBlank()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Polja ne smiju biti prazna");
            alert.setContentText("Pokušaj ponovo");
            alert.showAndWait();
            fieldIme.getStyleClass().removeAll("greska");
            fieldPrezime.getStyleClass().removeAll("greska");
            fieldJMBG.getStyleClass().removeAll("greska");
            datum.getStyleClass().removeAll("greska");
            radioMusko.getStyleClass().removeAll("greska");
            radioZensko.getStyleClass().removeAll("greska");
            fieldJMBG.getStyleClass().add("greska");
        }
        else if(datum.getValue()==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Polja ne smiju biti prazna");
            alert.setContentText("Pokušaj ponovo");
            alert.showAndWait();
            fieldIme.getStyleClass().removeAll("greska");
            fieldPrezime.getStyleClass().removeAll("greska");
            fieldJMBG.getStyleClass().removeAll("greska");
            datum.getStyleClass().removeAll("greska");
            radioMusko.getStyleClass().removeAll("greska");
            radioZensko.getStyleClass().removeAll("greska");
            datum.getStyleClass().add("greska");
        }
        else if(radioMusko.isSelected()==false && radioZensko.isSelected()==false){

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Polja ne smiju biti prazna");
            alert.setContentText("Pokušaj ponovo");
            alert.showAndWait();
            fieldIme.getStyleClass().removeAll("greska");
            fieldPrezime.getStyleClass().removeAll("greska");
            fieldJMBG.getStyleClass().removeAll("greska");
            datum.getStyleClass().removeAll("greska");
            radioMusko.getStyleClass().removeAll("greska");
            radioZensko.getStyleClass().removeAll("greska");
            radioMusko.getStyleClass().add("greska");
            radioZensko.getStyleClass().add("greska");
        }
        else {
            RekordiDAO rekordiDAO = new RekordiDAO();
            rekordiDAO.getDodajOstecenuOsobu().setString(1, kod);
            rekordiDAO.getDodajOstecenuOsobu().setString(2, fieldIme.getText());
            rekordiDAO.getDodajOstecenuOsobu().setString(3, fieldPrezime.getText());
            rekordiDAO.getDodajOstecenuOsobu().setString(4, fieldJMBG.getText());
            if (radioZensko.isSelected()) rekordiDAO.getDodajOstecenuOsobu().setString(5, radioZensko.getText());
            else rekordiDAO.getDodajOstecenuOsobu().setString(5, radioMusko.getText());
            rekordiDAO.getDodajOstecenuOsobu().setString(6, datum.getValue().toString());
            rekordiDAO.getDodajOstecenuOsobu().executeUpdate();
            Stage stage1 = (Stage) fieldIme.getScene().getWindow();
            if (radioZensko.isSelected()) {
                stage1.setUserData(new Osoba(fieldIme.getText(), fieldPrezime.getText(), fieldJMBG.getText(), datum.getValue(), Osoba.Spol.žensko));
            } else {
                stage1.setUserData(new Osoba(fieldIme.getText(), fieldPrezime.getText(), fieldJMBG.getText(), datum.getValue(), Osoba.Spol.muško));
            }
            stage1.close();
        }
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            datum.setEditable(false);
            datum.setValue(LocalDate.now());
            slika.setImage(new Image(getClass().getResourceAsStream("/img/slika.jpg")));
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) fieldIme.getScene().getWindow();
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
