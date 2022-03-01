package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {

    public ImageView slika;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slika.setImage(new Image(getClass().getResourceAsStream("/img/pozadinaAbout.jpg")));
        slika.setOpacity(0.3);
    }

    public void ok(ActionEvent actionEvent) {
        Stage stage= (Stage) slika.getScene().getWindow();
        stage.close();
    }
}
