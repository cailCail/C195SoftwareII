package DAO;

import Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAOcountries {
    /** Observable list of countries for getAllCountries.
     *
     *
     */
    public static ObservableList<countries> getAllCountries() {

        ObservableList<countries> countryList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM countries";
            PreparedStatement pst = JDBC.getConnection().prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("Country_ID");
                String Name = rs.getString("Country");

                countries C = new countries(ID, Name);
                countryList.add(C);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return countryList;
    }


}