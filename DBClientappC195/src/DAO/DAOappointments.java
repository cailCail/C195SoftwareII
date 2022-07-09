package DAO;

import Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.appointments;

import java.sql.*;
import java.time.LocalDateTime;

/** This class calls appointments from the model.appointments.
 */
public class DAOappointments {
/** Has an observable list of appointments in an observable array.
 *
 *  @return returns the appointmentList.
 *
 */
    public static ObservableList<appointments> getAllAppointments() {
        ObservableList<appointments> appointmentList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM appointments;";
            PreparedStatement pst = JDBC.getConnection().prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int AppointID = rs.getInt("Appointment_ID");
                String AppointTitle = rs.getString("Title");
                String AppointDesc = rs.getString("Description");
                String location = rs.getString("Location");
                int AppointContact = rs.getInt("Contact_ID");
                String AppointType = rs.getString("Type");
                LocalDateTime AppointStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime AppointEnd = rs.getTimestamp("End").toLocalDateTime();
                int AppointUID = rs.getInt("User_ID");
                int AppointCID = rs.getInt("Customer_ID");


                appointments A = new appointments(AppointID, AppointTitle, AppointDesc, AppointContact, AppointType, AppointStart, AppointEnd, AppointUID, AppointCID, location);
                appointmentList.add(A);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return appointmentList;
    }

    /**
     * The createAppointments creates new appointments in the database.
     *
     * @param title       creates appointment title
     * @param description creates appointment description
     * @param location    creates appointment location
     * @param contact     creates appointment contact
     * @param type        creates appointment type
     * @param startDate   creates appointment start date/time
     * @param endTime     creates appointment end date/time
     * @param cID         creates appointment customer_ID
     * @param uID         creates appointment user_ID
     *
     */
    public static void createAppointments(String title, String description, String location, Integer contact, String type, LocalDateTime startDate, LocalDateTime endTime, int cID, int uID) {
        try {

            String insertAppointment = "INSERT INTO appointments (Title, Description, Location, Contact_ID, Type, Start, End, Customer_ID, User_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
            PreparedStatement pstApp = JDBC.getConnection().prepareStatement(insertAppointment);
            pstApp.setString(1, title);
            pstApp.setString(2, description);
            pstApp.setString(3, location);
            pstApp.setInt(4, contact);
            pstApp.setString(5, type);
            pstApp.setTimestamp(6, Timestamp.valueOf(startDate));
            pstApp.setTimestamp(7, Timestamp.valueOf(endTime));
            pstApp.setInt(8, cID);
            pstApp.setInt(9, uID);
            pstApp.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * The createUpdateAppointments updates appointments in the database.
     *
     * @param appID               updates the appointment_ID
     * @param title               updates the title
     * @param description         updates the description
     * @param location            updates the location
     * @param contact_ID          updates the contact_ID
     * @param type                updates the title
     * @param updateEndDateTime   updates the end time
     * @param updateStartDateTime updates the start time
     * @param customer_ID         updates customer_ID
     * @param user_ID             updates user_ID
     *
     */
    public static void createUpdateAppointments(int appID, String title, String description, String location, int contact_ID, String type, LocalDateTime updateStartDateTime, LocalDateTime updateEndDateTime, int customer_ID, int user_ID) {
        try {

            String updateAppointment = "UPDATE appointments set Title = ?, Description = ?, Location = ?, Contact_ID  = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ? WHERE appointment_ID = ?  ";
            PreparedStatement pstupdate = JDBC.getConnection().prepareStatement(updateAppointment);

            pstupdate.setString(1, title);
            pstupdate.setString(2, description);
            pstupdate.setString(3, location);
            pstupdate.setInt(4, contact_ID);
            pstupdate.setString(5, type);
            pstupdate.setTimestamp(6, Timestamp.valueOf(updateStartDateTime));
            pstupdate.setTimestamp(7, Timestamp.valueOf(updateEndDateTime));
            pstupdate.setInt(8, customer_ID);
            pstupdate.setInt(9, user_ID);
            pstupdate.setInt(10, appID);
            pstupdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * deleteCustomer deletes appointments from the database.
     *
     * @param aID returns appointment_ID
     * @return returns boolean false
     *
     */
    public static boolean deleteCustomer(int aID) {

        try {

            String deleteAppointment = "DELETE from appointments WHERE Appointment_ID = ? ;";
            PreparedStatement delpstd = JDBC.getConnection().prepareStatement(deleteAppointment);
            delpstd.setInt(1, aID);
            delpstd.executeUpdate();


        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    /**
     * ObservableList of appointments to get allAppByMonth in an observable array list.
     *
     * @return appointmentByMonth
     *
     */
    public static ObservableList<appointments> getAllAppByMonth() {
        ObservableList<appointments> appointmentByMonth = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM appointments WHERE month(Start) = month(now());";
            PreparedStatement viewpst = JDBC.getConnection().prepareStatement(SQL);
            ResultSet rs = viewpst.executeQuery();
            while (rs.next()) {
                int AppointID = rs.getInt("Appointment_ID");
                String AppointTitle = rs.getString("Title");
                String AppointDesc = rs.getString("Description");
                String location = rs.getString("Location");
                int AppointContact = rs.getInt("Contact_ID");
                String AppointType = rs.getString("Type");
                LocalDateTime AppointStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime AppointEnd = rs.getTimestamp("End").toLocalDateTime();
                int AppointUID = rs.getInt("User_ID");
                int AppointCID = rs.getInt("Customer_ID");


                appointments AByMonth = new appointments(AppointID, AppointTitle, AppointDesc, AppointContact, AppointType, AppointStart, AppointEnd, AppointUID, AppointCID, location);
                appointmentByMonth.add(AByMonth);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return appointmentByMonth;

    }

    /**
     * Observable List of appointments in the getAllAppByWeek.
     *
     * @return appointmentByWeek
     *
     */
    public static ObservableList<appointments> getAllAppByWeek() {
        ObservableList<appointments> appointmentByWeek = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM appointments WHERE yearweek(Start) = yearweek(Now());";
            PreparedStatement weekpst = JDBC.getConnection().prepareStatement(SQL);
            ResultSet rs = weekpst.executeQuery();
            while (rs.next()) {
                int AppointID = rs.getInt("Appointment_ID");
                String AppointTitle = rs.getString("Title");
                String AppointDesc = rs.getString("Description");
                String location = rs.getString("Location");
                int AppointContact = rs.getInt("Contact_ID");
                String AppointType = rs.getString("Type");
                LocalDateTime AppointStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime AppointEnd = rs.getTimestamp("End").toLocalDateTime();
                int AppointUID = rs.getInt("User_ID");
                int AppointCID = rs.getInt("Customer_ID");


                appointments AByWeek = new appointments(AppointID, AppointTitle, AppointDesc, AppointContact, AppointType, AppointStart, AppointEnd, AppointUID, AppointCID, location);
                appointmentByWeek.add(AByWeek);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return appointmentByWeek;

    }

}















