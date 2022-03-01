package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class pogledajOsobuController implements Initializable {
    public Label imeLabel;
    public Label prezimeLabel;
    public Label jmbgLabel;
    public Label datumLabel;
    public Label spolLabel;
    public TextField jmbgPrati;
    public ImageView slika;

    public void setJmbgPrati(String string){
        jmbgPrati.setText(string);
    }


    public void actionPotvrdi(ActionEvent actionEvent) {
        Stage stage=(Stage) imeLabel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slika.setImage(new Image(getClass().getResourceAsStream("/img/slika.jpg")));
        jmbgPrati.textProperty().addListener((obs,stara,nova)-> {
            RekordiDAO dao=new RekordiDAO();
            try {
                dao.getDajOstecenuOsobu().setString(1,nova);
                ResultSet rs=dao.getDajOstecenuOsobu().executeQuery();
                imeLabel.setText(rs.getString(2));
                prezimeLabel.setText(rs.getString(3));
                jmbgLabel.setText(rs.getString(4));
                spolLabel.setText(rs.getString(5));
                Osoba osoba=new Osoba();
                osoba.setDatumRodjenja(LocalDate.parse(rs.getString(6)));
                datumLabel.setText(osoba.toString2());
            } catch (SQLException e) {
                e.printStackTrace();
            }


        });
    }
}
