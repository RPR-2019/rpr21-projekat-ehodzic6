package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class DodajNalazController {
    public CheckBox agresija;
    public CheckBox piromanija;
    public CheckBox depresija;
    public CheckBox sizofrenija;
    public CheckBox ptsd;
    public CheckBox alkohol;
    public CheckBox psiho;
    public CheckBox psihopatija;
    public CheckBox licnost;
    public CheckBox manipulacija;
    public String jmbg;

    public void setJmbg(String jmbg){
        this.jmbg=jmbg;
    }

    public void cls(ActionEvent actionEvent){
        Stage stage= (Stage) alkohol.getScene().getWindow();
        stage.close();
        
    }

    public void ok(ActionEvent actionEvent) throws SQLException {
        RekordiDAO dao=new RekordiDAO();
        dao.getDodajNalaz().setString(1,jmbg);

        if(agresija.isSelected()){
            dao.getDodajNalaz().setInt(2,1);
        }
        else{
            dao.getDodajNalaz().setInt(2,0);
        }

        if(piromanija.isSelected()){
            dao.getDodajNalaz().setInt(3,1);
        }
        else{
            dao.getDodajNalaz().setInt(3,0);
        }

        if(depresija.isSelected()){
            dao.getDodajNalaz().setInt(4,1);
        }
        else{
            dao.getDodajNalaz().setInt(4,0);
        }

        if(ptsd.isSelected()){
            dao.getDodajNalaz().setInt(5,1);
        }
        else{
            dao.getDodajNalaz().setInt(5,0);
        }

        if(sizofrenija.isSelected()){
            dao.getDodajNalaz().setInt(6,1);
        }
        else{
            dao.getDodajNalaz().setInt(6,0);
        }

        if(alkohol.isSelected()){
            dao.getDodajNalaz().setInt(7,1);
        }
        else{
            dao.getDodajNalaz().setInt(7,0);
        }

        if(psiho.isSelected()){
            dao.getDodajNalaz().setInt(8,1);
        }
        else{
            dao.getDodajNalaz().setInt(8,0);
        }

        if(psihopatija.isSelected()){
            dao.getDodajNalaz().setInt(9,1);
        }
        else{
            dao.getDodajNalaz().setInt(9,0);
        }

        if(licnost.isSelected()){
            dao.getDodajNalaz().setInt(10,1);
        }
        else{
            dao.getDodajNalaz().setInt(10,0);
        }

        if(manipulacija.isSelected()){
            dao.getDodajNalaz().setInt(11,1);
        }
        else{
            dao.getDodajNalaz().setInt(11,0);
        }

        dao.getDodajNalaz().executeUpdate();

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda");
        alert.setHeaderText("Potvrda");
        alert.setContentText("Uspješno ste dodali nalaz!");
        alert.showAndWait();
        Stage stage= (Stage) licnost.getScene().getWindow();
        stage.close();









    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) sizofrenija.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage.setTitle("Prijava");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/img/mupks.png"));
        stage.show();
    }

    public void about(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        Parent root=(new FXMLLoader(getClass().getResource("/fxml/about.fxml"))).load();
        stage.setScene(new Scene(root,600,400));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/img/mupks.png"));
        stage.show();
    }
}
