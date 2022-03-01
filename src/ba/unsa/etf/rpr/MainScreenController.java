package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    public ListView listaOsoba;
    public ImageView sar;
    public ImageView bih;
    public ImageView ks;
    private RekordiDAO rekordiDAO=new RekordiDAO();
    private Osoba osoba=new Osoba();

    public void actionDodaj(ActionEvent actionEvent) throws IOException, SQLException {
        Node node=(Node) actionEvent.getSource();
        Stage stage1=(Stage)node.getScene().getWindow();
        stage1.close();
        rekordiDAO.zatvoriKon();
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/dodajRekord.fxml"));
        stage.setTitle("Dodaj rekord");
        stage.setScene(new Scene(root, 1000, 800));
        stage.setResizable(false);
        stage.show();
    }

    public void actionOdjava(ActionEvent actionEvent) throws IOException, SQLException {
        Node node=(Node) actionEvent.getSource();
        Stage stage1=(Stage)node.getScene().getWindow();
        stage1.close();
        rekordiDAO.zatvoriKon();
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage.setTitle("Prijava");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listaOsoba.setItems(FXCollections.observableArrayList(rekordiDAO.dajOsobe()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

          sar.setImage(new Image(getClass().getResourceAsStream("/img/grb.png")));
           ks.setImage(new Image(getClass().getResourceAsStream("/img/ks.png")));
           bih.setImage(new Image(getClass().getResourceAsStream("/img/grbBih.png")));

    }

    public void onMouseLista(MouseEvent mouseEvent) throws IOException, SQLException {
        if(listaOsoba.getSelectionModel().getSelectedItem()==null){

        }
        else{
            osoba=(Osoba) listaOsoba.getSelectionModel().getSelectedItem();
            rekordiDAO.getTraziOsobu().setString(1,((Osoba) listaOsoba.getSelectionModel().getSelectedItem()).getJmbg());
            ResultSet rs=rekordiDAO.getTraziOsobu().executeQuery();
            if(rs.getString(4).equals("Muško")){
                osoba.setSpol(Osoba.Spol.muško);
            }
            else{osoba.setSpol(Osoba.Spol.žensko);}
            Stage stage=new Stage();
            Stage stage1=(Stage) listaOsoba.getScene().getWindow();
            rekordiDAO.zatvoriKon();
            stage1.close();
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/fxml/pogledajRekord.fxml"));
            Parent root=fxmlLoader.load();
            stage.setTitle(osoba.toString());
            PogledajRekordController pogledajRekordController=fxmlLoader.getController();
            pogledajRekordController.setOsoba(osoba);
            pogledajRekordController.setStringJmbg(osoba.jmbg);
            pogledajRekordController.setJmbgPrati(osoba.jmbg);
            stage.setScene(new Scene(root,1000,800));
            stage.setResizable(false);
            stage.show();



        }
    }

    public Osoba getOsoba(){
        return osoba;
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) sar.getScene().getWindow();
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
