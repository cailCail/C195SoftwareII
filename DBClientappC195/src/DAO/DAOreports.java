package DAO;

import Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.appointments;
import model.customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

/** This class DAOreports creates the model for reports.
 */
public class DAOreports {
    /**
     * Observable list of customer called getAllCustomerReports.
     *
     * @param countryID customers by the Country_ID.
     * @return customersReport
     */

    public static ObservableList<customer> getAllCustomersReport(int countryID) {

        ObservableList<customer> customersReport = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM customers, first_level_divisions, countries WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID AND countries.Country_ID = ?";
            PreparedStatement reportpst = JDBC.getConnection().prepareStatement(SQL);
            reportpst.setInt(1, countryID);
            ResultSet rs = reportpst.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("Customer_ID");
                String Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Zip = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                int Division = rs.getInt("Division_ID");
                int Country = rs.getInt("Country_ID");

                customer CReport = new customer(ID, Name, Address, Zip, Phone, Division, Country);
                customersReport.add(CReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return customersReport;
    }

    /**
     * The string getAllAppMonthType is to search for appointments based on the month and type and then returns the count.
     *
     * @param monthName     searches appointments for the selected month.
     * @param typeOfAppoint searches appointments for the selected type of appointment.
     * @return the count of the type of appointment in the same month.
     *
     */
    public static String getAllAppMonthType(String typeOfAppoint, String monthName) {

        try {
            String SQL = "SELECT count(*) FROM appointments WHERE monthname(Start) = ? and Type = ?;";
            PreparedStatement MTCpst = JDBC.getConnection().prepareStatement(SQL);
            MTCpst.setString(2, typeOfAppoint);
            MTCpst.setString(1, monthName);
            ResultSet rs = MTCpst.executeQuery();
            while (rs.next()) {
                return String.valueOf(rs.getInt(1));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return "0";
    }

    /**
     * The observable list of appointments for getAllAppContactID.
     *lambda is used to filter reports in the table view based on selected Contact_ID
     * @param contactID filter appointments based on Contact_ID
     * @return true if contact_ID matches the appointments contact_ID then that appointment is filtered to the table view.

     */
// lambda to filter contact ID in reports
    public static ObservableList<appointments> getAllAppContactID(int contactID) {
        ObservableList<appointments> allAppointments = DAOappointments.getAllAppointments();
        ObservableList<appointments> contactAppointments = allAppointments.filtered(a -> {
            if (contactID == a.getContact_ID()) {
                return true;
            }
            return false;
        });
        return contactAppointments;

    }
}


