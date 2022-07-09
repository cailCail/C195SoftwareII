package DAO;

import Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAOcontacts {
    /** Observable list of contacts for getAllContacts
     *
     *
     * */

    public static ObservableList<contacts> getAllContacts() {
        ObservableList<contacts> contactList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT Contact_ID, Contact_Name, Email from contacts;";
            PreparedStatement conpst = JDBC.getConnection().prepareStatement(SQL);
            ResultSet rs = conpst.executeQuery();
            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                contacts Con = new contacts(contactID, name, email);
                contactList.add(Con);
            }

        } catch (SQLException E) {
            E.printStackTrace();
        }

        return contactList;
    }

}
