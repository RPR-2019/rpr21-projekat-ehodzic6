package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PogledajDjeloController implements Initializable {
    public Label kod;
    public Label naziv;
    public Label mjesto;
    public Label datum;
    public Label kategorija;
    public TextArea opis;
    public ProgressBar stepen;
    public ListView osteceniList;
    public String kodString;
    public TextField pratiKod;

    public void actionUredu(ActionEvent actionEvent) {
        Stage stage= (Stage) mjesto.getScene().getWindow();
        stage.close();
    }

    public void setPratiKod(String string){
        pratiKod.setText(string);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        opis.setEditable(false);
        pratiKod.textProperty().addListener((obs,stara,nova)-> {
            RekordiDAO dao=new RekordiDAO();
            try {
                dao.getNadjiDjelo().setString(1,nova);
                ResultSet rs=dao.getNadjiDjelo().executeQuery();
                naziv.setText(rs.getString(2));
                opis.setText(rs.getString(3));
                Osoba osoba=new Osoba();
                LocalDate datum1=LocalDate.parse(rs.getString(4));
                osoba.setDatumRodjenja(datum1);
                datum.setText(osoba.toString2());
                double step=Double.parseDouble(rs.getString(5));
                stepen.setProgress(step/10);
                stepen.getStyleClass().removeAll("zuta", "crvena", "narandzasta", "zelena", "zelenalight");
                if (stepen.getProgress() <= 0.2) {
                    stepen.getStyleClass().add("zelena");
                } else if (stepen.getProgress() <= 0.3) {
                    stepen.getStyleClass().add("zelenalight");
                } else if (stepen.getProgress() <= 0.7) {
                    stepen.getStyleClass().add("zuta");
                } else if (stepen.getProgress() <= 0.8) {
                    stepen.getStyleClass().add("narandzasta");
                } else {
                    stepen.getStyleClass().add("crvena");
                }
                kod.setText(nova);
                kategorija.setText(rs.getString(7));
                mjesto.setText(rs.getString(8));

                dao.getDajPravnaLica().setString(1,nova);
                ResultSet rs2=dao.getDajPravnaLica().executeQuery();
                while(rs2.next()){
                    osteceniList.getItems().add(new PravnoLice(rs2.getString(2)));
                }
                dao.getDajOsteceneOsobe().setString(1,nova);
                ResultSet rs3=dao.getDajOsteceneOsobe().executeQuery();
                while(rs3.next()){
                    if(rs3.getString(5).equals("Muško")) {
                        osteceniList.getItems().add(new Osoba(rs3.getString(2), rs3.getString(3), rs3.getString(4), LocalDate.parse(rs3.getString(6)), Osoba.Spol.muško ));
                    }
                    else{
                        osteceniList.getItems().add(new Osoba(rs3.getString(2), rs3.getString(3), rs3.getString(4), LocalDate.parse(rs3.getString(6)), Osoba.Spol.žensko ));

                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }


        });
    }

    public void setKodString(String kodString) {
        this.kodString = kodString;
    }

    public void pristupi(MouseEvent mouseEvent) throws IOException {
        if(osteceniList.getSelectionModel().getSelectedItem()!=null){
            try {
                Osoba osoba = (Osoba) osteceniList.getSelectionModel().getSelectedItem();
                String jmbg = osoba.getJmbg();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pogledajOsobu.fxml"));
                Parent root = loader.load();
                pogledajOsobuController pogledajOsobuController = loader.getController();
                pogledajOsobuController.setJmbgPrati(jmbg);
                Stage stage = new Stage();
                stage.setTitle(osoba.getIme() + " " + osoba.getPrezime());
                stage.setScene(new Scene(root, 1000, 800));
                stage.setResizable(false);
                stage.show();
            }
            catch(ClassCastException e){
                Stage stage=new Stage();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/pogledajPravno.fxml"));
                Parent root=loader.load();
                PogledajPravnoLiceController pogledajPravnoLiceController=loader.getController();
                pogledajPravnoLiceController.setNazivPrati(osteceniList.getSelectionModel().getSelectedItem().toString());
                stage.setTitle(osteceniList.getSelectionModel().getSelectedItem().toString());
                stage.setScene(new Scene(root, 600,300));
                stage.setResizable(false);
                stage.show();
            }
        }
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) kod.getScene().getWindow();
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
