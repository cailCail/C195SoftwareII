package DAO;

import Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.countries;
import model.customer;
import model.first_level_divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/** The class DAOFirst_Level_Divisions is the first level divisions.
 *
 */

public class DAOFirst_Level_Divisions {
    /**
     * The observable list of first_level_divisions for getDivisions.
     *
     * @param countryID first_level_divisions based on Country_ID
     * @return FLDList
     *
     */

    public static ObservableList<first_level_divisions> getDivisions(int countryID) {
        ObservableList<first_level_divisions> FLDList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM first_level_divisions WHERE Country_ID = ?; ";

            PreparedStatement pst = JDBC.getConnection().prepareStatement(SQL);
            pst.setInt(1, countryID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int DivisionID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                int Country = rs.getInt("Country_ID");
                first_level_divisions FLD = new first_level_divisions(DivisionID, Division, Country);
                FLDList.add(FLD);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FLDList;
    }


}









