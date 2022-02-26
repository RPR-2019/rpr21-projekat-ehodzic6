package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class dodajRekordController {
    public TextField fieldIme,fieldPrezime,fieldJMBG;
    public DatePicker datum;
    public RadioButton radioMusko,radioZensko;
    public ImageView slika;

    public void actionPotvrdi(ActionEvent actionEvent) throws SQLException, IOException {
        if(fieldIme.getText().isBlank() || fieldIme.getText().isBlank() || fieldJMBG.getText().isBlank() || datum.getValue()==null || (radioMusko.isSelected()==false && radioZensko.isSelected()==false)){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Polja ne smiju biti prazna");
            alert.setContentText("Pokušaj ponovo");
            alert.showAndWait();
        }
        else{
            RekordiDAO rekordiDAO=new RekordiDAO();
            rekordiDAO.getUbaciOsobu().setString(1,fieldIme.getText());
            rekordiDAO.getUbaciOsobu().setString(2,fieldPrezime.getText());
            rekordiDAO.getUbaciOsobu().setString(3,fieldJMBG.getText());
            rekordiDAO.getUbaciOsobu().setString(5,datum.getValue().toString());
            if(radioZensko.isSelected())rekordiDAO.getUbaciOsobu().setString(4,radioZensko.getText());
            else rekordiDAO.getUbaciOsobu().setString(4,radioMusko.getText());
            rekordiDAO.getUbaciOsobu().executeUpdate();
            Stage stage1= (Stage) fieldIme.getScene().getWindow();
            stage1.close();
            Stage stage=new Stage();
            rekordiDAO.zatvoriKon();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainScreen.fxml"));
            stage.setTitle("Kriminalni rekordi");
            stage.setScene(new Scene(root, 1000, 800));
            stage.setResizable(false);
            stage.show();
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
}
