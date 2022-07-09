package DAO;

import Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.customer;
import model.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** The class DAOuser creates the model for user.
 *
 */
public class DAOuser {
    /**
     * The observable list of users for getAllUsers.
     *
     * @return userList
     *
     */
    public static ObservableList<users> getAllUsers() {
        ObservableList<users> userList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT User_ID, User_Name, Password FROM users;";
            PreparedStatement userpst = JDBC.getConnection().prepareStatement(SQL);
            ResultSet rs = userpst.executeQuery();
            while (rs.next()) {
                int usID = rs.getInt("User_ID");
                String Name = rs.getString("User_Name");
                String Password = rs.getString("Password");

                users U = new users(usID, Name, Password);
                userList.add(U);
            }
        } catch (SQLException E) {
            E.printStackTrace();
        }

        return userList;
    }

    /**
     * The userLogin is validate users to the application utilizing the user name and password.
     *
     * @param user_Name
     * @param password
     * @return returns the User_ID
     *
     *
     */

    public static int userLogin(String user_Name, String password) {

        try {
            String SQL = "SELECT * FROM users WHERE User_Name = ? AND Password = ?;";
            PreparedStatement loginpst = JDBC.getConnection().prepareStatement(SQL);
            loginpst.setString(1, user_Name);
            loginpst.setString(2, password);
            ResultSet rs = loginpst.executeQuery();
            if (rs.next()) {
                return rs.getInt("User_ID");
            }

        } catch (SQLException E) {
            E.printStackTrace();
        }
        return -1;

    }
}
