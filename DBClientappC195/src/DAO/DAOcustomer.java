package DAO;

import Helper.JDBC;
import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import model.customer;
import model.first_level_divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/** This is the class customer.
 *  The Observable list of customers in the getAllCustomers.
 * Customer observable array List.
 *
 *
 */
public class DAOcustomer {

    public static ObservableList<customer> getAllCustomers() {

        ObservableList<customer> customers = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM customers, first_level_divisions, countries WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID";
            PreparedStatement pst = JDBC.getConnection().prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("Customer_ID");
                String Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Zip = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                int Division = rs.getInt("Division_ID");
                int Country = rs.getInt("Country_ID");

                customer C = new customer(ID, Name, Address, Zip, Phone, Division, Country);
                customers.add(C);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return customers;
    }
/** The createCustomer creates or inserts new customers into the data base.
 *
 * @param name creates customer name
 * @param address creates customer address
 * @param zip creates customer zip
 * @param phone creates customer phone
 * @param division creates customer division
 *
 *
 */
    public static void createCustomer(String name, String address, String zip, String phone, int division) {
        try {

            String insertCustomer = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?) ";
            PreparedStatement pst1 = JDBC.getConnection().prepareStatement(insertCustomer);
            pst1.setString(1, name);
            pst1.setString(2, address);
            pst1.setString(3, zip);
            pst1.setString(4, phone);
            pst1.setInt(5, division);
            pst1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** The createUpdateCustomer updates customer information already present in the database.
     *
     * @param name updates customer name
     * @param address updates customer address
     * @param zip updates customer zip
     * @param phone updates customer phone
     * @param division updates customer division_ID
     * @param customer updates customer customer_ID
     *
     */
    public static void createUpdateCustomer(String name, String address, String zip, String phone, int division, int customer) {
        try {

            String updateCustomer = "UPDATE customers set Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ? ;";
            PreparedStatement pstu = JDBC.getConnection().prepareStatement(updateCustomer);
            pstu.setString(1, name);
            pstu.setString(2, address);
            pstu.setString(3, zip);
            pstu.setString(4, phone);
            pstu.setInt(5, division);
            pstu.setInt(6, customer);
            pstu.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** the deleteCustomer deletes the selected customer from appointments and customers based on Customer_ID.
     *
     * @param customerID deletes customer based on customer_ID
     *
     *
     */
    public static boolean deleteCustomer (int customerID) {

        try {
            String deleteAppointment = "DELETE from appointments WHERE Customer_ID = ? ;";
            PreparedStatement pstdA = JDBC.getConnection().prepareStatement(deleteAppointment);
            pstdA.setInt(1, customerID);
            pstdA.executeUpdate();

            String deleteCustomer = "DELETE from customers WHERE Customer_ID = ? ;";
            PreparedStatement pstd = JDBC.getConnection().prepareStatement(deleteCustomer);
            pstd.setInt(1, customerID);
            pstd.executeUpdate();

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }





}
