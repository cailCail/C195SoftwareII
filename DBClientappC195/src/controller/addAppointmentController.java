package controller;

import DAO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
/**  This class initializes the addAppointmentController. */
public class addAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     * Combo box of customer for customer_ID.
     */
    @FXML
    public ComboBox<customer> addCustomerID;

    /**
     * Combo box of LocalTime for start time of appointment.
     */
    @FXML
    public ComboBox<LocalTime> startTimeAppointment;

    /**
     * Text field customer_ID.
     */
    @FXML
    public TextField addAppointmentCustomer_ID;

    /**
     * Combo box of users for user_ID.
     */
    @FXML
    public ComboBox<users> comboUser_ID;

    /**
     * Combo box of string for appointment location.
     */
    @FXML
    private ComboBox<String> addAppointmentLocation;

    /**
     * Combo box of contacts.
     */
    @FXML
    private ComboBox<contacts> addContactAppointment;

    /**
     * Text field of appointment_ID.
     */
    @FXML
    private TextField appointment_IDAddCustomer;

    /**
     * Button to exit.
     */
    @FXML
    private Button cancelBtnAddCustomer;

    /**
     * Text field of customer_ID.
     */
    @FXML
    private TextField customer_IDAddCustomer;

    /**
     * Text field of description.
     */
    @FXML
    private TextField descriptionAddCustomer;

    /**
     * Combo box of end appointment time.
     */
    @FXML
    private ComboBox<LocalTime> endTimeAddAppointment;

    /**
     * Save button.
     */
    @FXML
    private Button saveBtnAddCustomer;

    /**
     * Date picker start date appointment.
     */
    @FXML
    private DatePicker startDateAddAppointment;

    /**
     * Combo box of timestamp start appointments.
     */
    @FXML
    private ComboBox<Timestamp> startTimeAddAppointment;

    /**
     * Text field title.
     */
    @FXML
    private TextField titleAddCustomer;

    /**
     * Combo box of string  for type of appointment.
     */
    @FXML
    private ComboBox<String> typeAddAppointment;

    /**
     * Text field of user_ID.
     */
    @FXML
    private TextField user_IDAddCustomer;

    /**
     * Date time formatter for pattern yyyy-mm-dd.
     */
    @FXML
    private final DateTimeFormatter dateDTF = DateTimeFormatter.ofPattern("yyyy-mm-dd");

    /**
     * Add appointment location when selected.
     *
     * @param event add appointment when select appointment from combo box.
     */
    @FXML
    void onActionAddAppointmentLocation(ActionEvent event) {
        if (event.getSource() == addAppointmentLocation) {
            addAppointmentLocation.getSelectionModel().getSelectedItem();

        }
    }

    /**
     * On action add contact.
     *
     * @param event add contact when selected contact from combo box.
     */
    @FXML
    void onActionAddContactAppointment(ActionEvent event) {

    }

    /**
     * Cancel button is selected.
     *
     * @param event when cancel button is selected the application changes to the main application form.
     */
    @FXML
    void onActionCancelAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainApplication.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * The end time appointment is selected from the combo box.
     *
     * @param event adds appointment end time to the appointment.
     */
    @FXML
    void onActionEndTimeAddAppointment(ActionEvent event) {
    }

    /**
     * Button saves added customer information.
     *
     * @param event the save button is selected.
     * @return
     * @throws IOException
     */
    @FXML
    void onActionSaveAddCustomer(ActionEvent event) throws IOException {

        String Title = titleAddCustomer.getText();
        String Description = descriptionAddCustomer.getText();
        String Location = addAppointmentLocation.getValue();
        int Contact = addContactAppointment.getValue().getContact_ID();
        String Type = typeAddAppointment.getValue();

        LocalDate dateDTF = startDateAddAppointment.getValue();
        LocalTime StartTime = startTimeAppointment.getValue();
        LocalTime EndTime = endTimeAddAppointment.getValue();
        int cID = addCustomerID.getValue().getCustomer_ID();
        int uID = comboUser_ID.getValue().getUser_ID();
        LocalDateTime startDateTime = LocalDateTime.of(dateDTF, StartTime);
        LocalDateTime endDateTime = LocalDateTime.of(dateDTF, EndTime);

        ZonedDateTime zdtLocalStart = startDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime zdtLocalEnd = endDateTime.atZone(ZoneId.systemDefault());

        ZonedDateTime zdtESTStart = zdtLocalStart.withZoneSameInstant((ZoneId.of("America/New_York")));
        ZonedDateTime zdtESTEnd = zdtLocalEnd.withZoneSameInstant((ZoneId.of("America/New_York")));

        LocalTime startVerifyTime = zdtESTStart.toLocalTime();
        LocalTime endVerifyTime = zdtESTEnd.toLocalTime();

        LocalTime estStartBusinessDay = LocalTime.of(8, 0, 0);
        LocalTime estEndBusinessDay = LocalTime.of(22, 0, 0);

        if (startVerifyTime.isBefore(estStartBusinessDay) || startVerifyTime.isAfter(estEndBusinessDay) ||
                endVerifyTime.isBefore(estStartBusinessDay) || endVerifyTime.isAfter(estEndBusinessDay)) {

            Alert alert10 = new Alert(Alert.AlertType.ERROR);
            alert10.setTitle("Not Valid");
            alert10.setContentText("The appointment selected is outside of business hours. Please select an appointment between 8 AM and 10 PM EST.");
            alert10.showAndWait();
            return;
        }

        /** The observable list of appointments to ensure that appointments do not overlap.
         * @return appointment not added if appointment overlap is present.
         */
        ObservableList<appointments> getAllAppointments = DAOappointments.getAllAppointments();

        for (appointments A : getAllAppointments) {
            LocalDate cDate = startDateAddAppointment.getValue();
            LocalDateTime cStart = A.getStart();
            LocalDateTime cEnd = A.getEnd();
            LocalDateTime proposedStart = LocalDateTime.of(dateDTF, StartTime);
            LocalDateTime proposedEnd = LocalDateTime.of(dateDTF, EndTime);


            if (A.getCustomer_ID() != cID) {
                continue;

            }
            if ((cStart.isAfter(proposedStart) || cStart.isEqual(proposedStart)) && (cStart.isBefore(proposedEnd))) {

                Alert alert15 = new Alert(Alert.AlertType.ERROR);
                alert15.setTitle("Not Valid");
                alert15.setContentText("The appointment selected overlaps an existing appointment ");
                alert15.showAndWait();
                return;
            }
            if (cEnd.isAfter(proposedStart) && (cEnd.isBefore(proposedEnd) || cEnd.isEqual(proposedEnd))) {
                Alert alert15 = new Alert(Alert.AlertType.ERROR);
                alert15.setTitle("Not Valid");
                alert15.setContentText("The appointment selected overlaps an existing appointment ");
                alert15.showAndWait();
                return;

            }
            if ((cStart.isBefore(proposedStart) || cStart.isEqual(proposedStart)) && ((cEnd.isAfter(proposedEnd) || cEnd.isEqual(proposedEnd)))) {

                Alert alert15 = new Alert(Alert.AlertType.ERROR);
                alert15.setTitle("Not Valid");
                alert15.setContentText("The appointment selected overlaps an existing appointment ");
                alert15.showAndWait();
                return;

            }


        }
/** The DAO.appointments.createAppointments method creates appointments.
 * @param Title
 * @param Description
 * @param Location
 * @param Contact
 * @param Type
 * @param startDateTime
 * @param endDateTime
 * @param cID
 * @param uID
 */
        DAOappointments.createAppointments(Title, Description, Location, Contact, Type, startDateTime, endDateTime, cID, uID);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainApplication.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

    }

    /**
     * Combo box add start date of appointment to appointment details.
     *
     * @param event adds start date selected from combo box.
     */

    @FXML
    void onActionStartDateAddAppointment(ActionEvent event) {


    }

    /**
     * Combo box add start time of appointment to appointment details.
     *
     * @param event adds start time selected from combo box.
     */

    @FXML
    void onActionStartTimeAddAppointment(ActionEvent event) {


    }

    /**
     * Combo box add type of appointment to appointment details.
     *
     * @param event adds type of appointment selected from combo box.
     */

    @FXML
    void onActionTypeAddAppointment(ActionEvent event) {

    }

    /**
     * Combo box add user_ID to appointment details.
     *
     * @param actionEvent adds user_ID selected from combo box.
     */

    public void onActionComboUser_ID(ActionEvent actionEvent) {

    }

    /**
     * Combo box add customer_ID to appointment details.
     *
     * @param actionEvent adds customer_ID from combo box.
     */

    public void onActionAddCustomerID(ActionEvent actionEvent) {

    }

    /**
     * Sets the combo box choices for user_ID, customer_ID, contacts, location, and type.
     *
     * Set hours and minutes in the startTimeAppointment and endTimeAddAppointment combo boxes.
     *
     * @param resourceBundle
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboUser_ID.setItems(DAOuser.getAllUsers());
        addCustomerID.setItems(DAOcustomer.getAllCustomers());
        addContactAppointment.setItems(DAOcontacts.getAllContacts());
        addAppointmentLocation.setItems(Helper.listManager.locOptions);
        typeAddAppointment.setItems(Helper.listManager.meetingType);


        for (int i = 0; i < 24; i++) {
            startTimeAppointment.getItems().add(LocalTime.of(i, 0));

        }
        for (int i = 1; i < 25; i++) {
            if (i <= 23)
                endTimeAddAppointment.getItems().add(LocalTime.of(i, 0));
            else

                endTimeAddAppointment.getItems().add(LocalTime.of(0, 0));
        }
    }


}





