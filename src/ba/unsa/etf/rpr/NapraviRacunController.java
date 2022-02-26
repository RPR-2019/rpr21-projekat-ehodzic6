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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NapraviRacunController implements Initializable {
    public ImageView slika;
    public PasswordField lozinka;
    public TextField korisnickoIme;
    private RekordiDAO rekordiDAO=new RekordiDAO();
    public void actionPovratak(ActionEvent actionEvent) {
        Node node=(Node) actionEvent.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image slika1=new Image(getClass().getResourceAsStream("/img/slika.jpg"));
        slika.setImage(slika1);
        slika.setOpacity(0.3);
    }

    public void actionPrijava(ActionEvent actionEvent) throws SQLException {
        if(!korisnickoIme.getText().isBlank() && !lozinka.getText().isBlank()) {
            rekordiDAO.getUbaciLogin().setString(1, korisnickoIme.getText());
            rekordiDAO.getUbaciLogin().setString(2, lozinka.getText());
            rekordiDAO.getUbaciLogin().execute();
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Račun napravljen");
            alert.setHeaderText("Uspješno ste napravili račun");
            alert.showAndWait();
            Stage stage=(Stage) slika.getScene().getWindow();
            stage.close();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText("Polja ne smiju biti prazna!");
            alert.setContentText("Pokušajte ponovo!");
            alert.showAndWait();
        }
    }
}
