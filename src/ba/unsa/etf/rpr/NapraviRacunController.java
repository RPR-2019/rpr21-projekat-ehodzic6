package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class NapraviRacunController implements Initializable {
    public ImageView slika;
    public PasswordField lozinka;
    public TextField korisnickoIme;
    private RekordiDAO rekordiDAO=new RekordiDAO();
    public void actionPovratak(ActionEvent actionEvent) throws SQLException {
        Node node=(Node) actionEvent.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        stage.close();
        rekordiDAO.zatvoriKon();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image slika1=new Image(getClass().getResourceAsStream("/img/slika.jpg"));
        slika.setImage(slika1);
        slika.setOpacity(0.3);
    }

    public void actionPrijava(ActionEvent actionEvent) throws SQLException {
        rekordiDAO.provjeriIme.setString(1,korisnickoIme.getText());
        ResultSet rs1=rekordiDAO.provjeriIme.executeQuery();
        if(rs1.next()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText("Korisničko ime je zauzeto!");
            Random random=new Random();
            alert.setContentText("Pokušajte sa: "+korisnickoIme.getText()+random.nextInt()/1000);
            alert.showAndWait();
            korisnickoIme.setText("");
            lozinka.setText("");
        }
        else if(!korisnickoIme.getText().isBlank() && !lozinka.getText().isBlank()) {
            rekordiDAO.getUbaciLogin().setString(1, korisnickoIme.getText());
            rekordiDAO.getUbaciLogin().setString(2, lozinka.getText());
            rekordiDAO.getUbaciLogin().execute();
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Račun napravljen");
            alert.setHeaderText("Uspješno ste napravili račun");
            alert.showAndWait();
            Stage stage=(Stage) slika.getScene().getWindow();
            stage.close();
            rekordiDAO.zatvoriKon();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText("Polja ne smiju biti prazna!");
            alert.setContentText("Pokušajte ponovo!");
            if(korisnickoIme.getText().isBlank() && lozinka.getText().isBlank()){
                korisnickoIme.getStyleClass().removeAll("greska","zelenalight");
                korisnickoIme.getStyleClass().add("greska");
                lozinka.getStyleClass().removeAll("greska","zelenalight");
                lozinka.getStyleClass().add("greska");

            }
            else if(korisnickoIme.getText().isBlank()){
                korisnickoIme.getStyleClass().removeAll("greska","zelenalight");
                korisnickoIme.getStyleClass().add("greska");
                lozinka.getStyleClass().removeAll("greska","zelenalight");
                lozinka.getStyleClass().add("zelenalight");
            }
            else{
                lozinka.getStyleClass().removeAll("greska","zelenalight");
                lozinka.getStyleClass().add("greska");
                korisnickoIme.getStyleClass().removeAll("greska","zelenalight");
                korisnickoIme.getStyleClass().add("zelenalight");
            }

            alert.showAndWait();
        }


    }
}
