package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ImageView slika;
    public TextField korisnickoIme;
    public PasswordField lozinka;
    private RekordiDAO rekordiDAO=new RekordiDAO();
    public Hyperlink napraviRacun;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image slika1=new Image(getClass().getResourceAsStream("/img/slika.jpg"));
        slika.setImage(slika1);
        slika.setOpacity(0.3);
    }

    public void napraviRacun(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/napraviRacun.fxml"));
        stage.setTitle("Napravi račun");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();
    }

    public void actionPrijava(ActionEvent actionEvent) throws IOException {
        try {
            PreparedStatement pretraga=rekordiDAO.getPretraziLogin();
            pretraga.setString(1,korisnickoIme.getText());
            pretraga.setString(2,lozinka.getText());
            ResultSet resultSet=pretraga.executeQuery();
            if(resultSet.isClosed()){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText("Pogrešno korisničko ime ili lozinka");
                alert.showAndWait();
            }
            else{
                Node node= (Node) actionEvent.getSource();
                Stage stage1= (Stage) node.getScene().getWindow();
                stage1.close();
                rekordiDAO.zatvoriKon();
                Stage stage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainScreen.fxml"));
                stage.setTitle("Kriminalni rekordi");
                stage.setScene(new Scene(root, 1000, 800));
                stage.setResizable(false);
                stage.show();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
