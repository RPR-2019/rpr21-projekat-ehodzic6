package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class dodajRekordController implements Initializable {
    public TextField fieldIme,fieldPrezime,fieldJMBG;
    public DatePicker datum;
    public RadioButton radioMusko,radioZensko;
    public ImageView slika;

    public void actionPotvrdi(ActionEvent actionEvent) throws SQLException, IOException {

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
        }else {


            RekordiDAO dao = new RekordiDAO();
            dao.getProvjeriJmbg().setString(1, fieldJMBG.getText());
            ResultSet rs = dao.getProvjeriJmbg().executeQuery();
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText("Rekord već postoji!");
                Random random = new Random();
                alert.setContentText("Osoba sa JMBG-om: " + fieldJMBG.getText() + " već ima rekord!");
                alert.showAndWait();
                Stage stage= (Stage) fieldIme.getScene().getWindow();
                stage.close();

            } else {

                RekordiDAO rekordiDAO = new RekordiDAO();
                rekordiDAO.getUbaciOsobu().setString(1, fieldIme.getText());
                rekordiDAO.getUbaciOsobu().setString(2, fieldPrezime.getText());
                rekordiDAO.getUbaciOsobu().setString(3, fieldJMBG.getText());
                rekordiDAO.getUbaciOsobu().setString(5, datum.getValue().toString());
                if (radioZensko.isSelected()) rekordiDAO.getUbaciOsobu().setString(4, radioZensko.getText());
                else rekordiDAO.getUbaciOsobu().setString(4, radioMusko.getText());
                rekordiDAO.getUbaciOsobu().executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potvrda!");
                alert.setHeaderText("Uspješno ste kreirali rekord!");
                alert.showAndWait();
                Stage stage1 = (Stage) fieldIme.getScene().getWindow();
                stage1.close();
                Stage stage = new Stage();
                rekordiDAO.zatvoriKon();
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainScreen.fxml"));
                stage.setTitle("Kriminalni rekordi");
                stage.setScene(new Scene(root, 1000, 800));
                stage.setResizable(false);
                stage.show();
            }
        }

    }

    public void actionOdustani(ActionEvent actionEvent) throws IOException {
        Node node=(Node) actionEvent.getSource();
        Stage stage1=(Stage) node.getScene().getWindow();
        stage1.close();
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainScreen.fxml"));
        stage.setTitle("Kriminalni rekordi");
        stage.setScene(new Scene(root, 1000, 800));
        stage.setResizable(false);
        stage.show();
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
        Stage stage=(Stage) datum.getScene().getWindow();
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
