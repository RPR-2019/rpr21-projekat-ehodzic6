package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PogledajPravnoLiceController implements Initializable {
    public TextField nazivPrati;
    public Label labelNaziv;

    public void setNazivPrati(String string){
        nazivPrati.setText(string);
    }

    public void potvrdi(ActionEvent actionEvent) {
        Stage stage=(Stage) labelNaziv.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nazivPrati.textProperty().addListener((obs,stara,nova)-> {
            labelNaziv.setText(nova);
        });

    }
}
