package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class dodajPravnoLiceController {
    public TextField nazivPravnogLica;
    public String kod;

    public void odustani(ActionEvent actionEvent) {
        Stage stage= (Stage) nazivPravnogLica.getScene().getWindow();
        stage.close();
    }

    public void potvrdi(ActionEvent actionEvent) throws SQLException {
        if(nazivPravnogLica.getText().isBlank()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška!");
            alert.setContentText("Naziv pravnog lica ne smije biti prazan!");
            alert.showAndWait();
            nazivPravnogLica.getStyleClass().removeAll("greska");
            nazivPravnogLica.getStyleClass().add("greska");
        }
        else{

            PravnoLice pravnoLice=new PravnoLice(nazivPravnogLica.getText());
            Stage stage1= (Stage) nazivPravnogLica.getScene().getWindow();
            stage1.setUserData(pravnoLice);
            RekordiDAO dao=new RekordiDAO();
            dao.getDodajPravnoLice().setString(1,kod);
            dao.getDodajPravnoLice().setString(2,nazivPravnogLica.getText());
            dao.getDodajPravnoLice().executeUpdate();
            stage1.close();

        }
    }

    public void setKod(String kod) {
        this.kod = kod;
    }
}
