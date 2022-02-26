package ba.unsa.etf.rpr;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class RekordiDAO {
    public PreparedStatement pretraziLogin,ubaciLogin,ubaciOsobu,traziOsobe,traziOsobu;
    private Connection conn;

    public RekordiDAO() {
        String url = "jdbc:sqlite:rekordi.db";
        try {
            conn = DriverManager.getConnection(url);
            pretraziLogin=conn.prepareStatement("SELECT * FROM login WHERE username=? AND password=?");
            ubaciLogin=conn.prepareStatement("INSERT INTO login VALUES(?,?)");
            ubaciOsobu=conn.prepareStatement("INSERT INTO osobe VALUES (?,?,?,?,?)");
            traziOsobe= conn.prepareStatement("SELECT * FROM osobe");
            traziOsobu=conn.prepareStatement("SELECT * FROM osobe WHERE jmbg=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPretraziLogin() {
        return pretraziLogin;
    }

    public PreparedStatement getUbaciLogin() {
        return ubaciLogin;
    }

    public PreparedStatement getUbaciOsobu() {
        return ubaciOsobu;
    }

    public PreparedStatement getTraziOsobe() {
        return traziOsobe;
    }

    public ArrayList<Osoba> dajOsobe() throws SQLException {
        ArrayList<Osoba> osobe=new ArrayList<>();
        ResultSet rs=traziOsobe.executeQuery();
        while(rs.next()){
            Osoba osoba=new Osoba();
            osoba.setIme(rs.getString(1));
            osoba.setPrezime(rs.getString(2));
            osoba.setJmbg(rs.getString(3));
            if(rs.getString(4)=="Muško"){
                osoba.setSpol(Osoba.Spol.muško);
            }
            else{
                osoba.setSpol(Osoba.Spol.žensko);
            }
            osoba.setDatumRodjenja(LocalDate.parse(rs.getString(5)));
            osobe.add(osoba);

        }
        return osobe;

    }

    public void zatvoriKon() throws SQLException {
        conn.close();
    }

    public PreparedStatement getTraziOsobu() {
        return traziOsobu;
    }
}
