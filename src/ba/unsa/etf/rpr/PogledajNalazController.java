package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PogledajNalazController implements Initializable {
    public Label agr;
    public Label pir;
    public Label dep;
    public Label ptsd;
    public Label siz;
    public Label alk;
    public Label sups;
    public Label psih;
    public Label lic;
    public Label man;
    public TextField jmb;
    public Label ime;

    public void setImePrezime(String ime){
        this.ime.setText(ime);

    }
    public void setJmb(String jmb){
        this.jmb.setText(jmb);
    }

    public void ok(ActionEvent actionEvent) {
        Stage stage= (Stage) agr.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jmb.textProperty().addListener((obs,stara,nova)-> {
            RekordiDAO dao = new RekordiDAO();
            try {
                dao.getDajNalaz().setString(1, nova);
                ResultSet rs = dao.getDajNalaz().executeQuery();
                if (!rs.isClosed()) {
                    if (rs.getInt(2) == 1) {
                        agr.setText(agr.getText() + " POZITIVNO");
                    } else {
                        agr.setText(agr.getText() + " NEGATIVNO");
                    }

                    if (rs.getInt(3) == 1) {
                        pir.setText(pir.getText() + " POZITIVNO");
                    } else {
                        pir.setText(pir.getText() + " NEGATIVNO");
                    }

                    if (rs.getInt(4) == 1) {
                        dep.setText(dep.getText() + " POZITIVNO");
                    } else {
                        dep.setText(dep.getText() + " NEGATIVNO");
                    }

                    if (rs.getInt(5) == 1) {
                        ptsd.setText(ptsd.getText() + " POZITIVNO");
                    } else {
                        ptsd.setText(ptsd.getText() + " NEGATIVNO");
                    }

                    if (rs.getInt(6) == 1) {
                        siz.setText(siz.getText() + " POZITIVNO");
                    } else {
                        siz.setText(siz.getText() + " NEGATIVNO");
                    }

                    if (rs.getInt(7) == 1) {
                        alk.setText(alk.getText() + " POZITIVNO");
                    } else {
                        alk.setText(alk.getText() + " NEGATIVNO");
                    }

                    if (rs.getInt(8) == 1) {
                        sups.setText(sups.getText() + " POZITIVNO");
                    } else {
                        sups.setText(sups.getText() + " NEGATIVNO");
                    }

                    if (rs.getInt(9) == 1) {
                        psih.setText(psih.getText() + " POZITIVNO");
                    } else {
                        psih.setText(psih.getText() + " NEGATIVNO");
                    }

                    if (rs.getInt(10) == 1) {
                        lic.setText(lic.getText() + " POZITIVNO");
                    } else {
                        lic.setText(lic.getText() + " NEGATIVNO");
                    }

                    if (rs.getInt(11) == 1) {
                        man.setText(man.getText() + " POZITIVNO");
                    } else {
                        man.setText(man.getText() + " NEGATIVNO");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) siz.getScene().getWindow();
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
