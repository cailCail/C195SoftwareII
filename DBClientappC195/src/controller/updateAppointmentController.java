package controller;

import DAO.*;
import Helper.listManager;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
/** This class initializes updateAppointmentController.  */
public class updateAppointmentController implements Initializable {
    Stage stage;
    Parent scene;
    /** text field update customer_ID. */
    @FXML
    public TextField updateCustomer_ID;
    /** Combo box of customer_ID. */
    @FXML
    public ComboBox<customer> updateCust_ID;
    /** Combo box of user_Id. */
    @FXML
    public ComboBox<users> comboBxUpdateUser_ID;
    /** Combo box of location. */
    @FXML
    private ComboBox<String> addAppointmentLocation;
    /** Combo box of contacts. */
    @FXML
    private ComboBox<contacts> addContactAppointment;
    /** Combo box of end time. */
    @FXML
    private ComboBox<LocalTime> endTimeAddAppointment;
    /**Combo box of start time.  */
    @FXML
    private ComboBox<LocalTime> startTimeAddAppointment;
    /** Combo box of type. */
    @FXML
    private ComboBox<String> typeAddAppointment;

    /** Text field appointment_ID. */
    @FXML
    private TextField appointment_IDAddCustomer;
    /** Button cancel to exit update appointment.  */
    @FXML
    private Button cancelBtnAddCustomer;
    /** Text field customer_ID. */
    @FXML
    private TextField customer_IDAddCustomer;
    /** Text field description. */
    @FXML
    private TextField descriptionAddCustomer;

    /** Button to save the customer. */
    @FXML
    private Button saveBtnAddCustomer;
    /** Date picker for start date. */
    @FXML
    private DatePicker startDateAddAppointment;

    /** Text field title.  */
    @FXML
    private TextField titleAddCustomer;

    /** Text field user_ID. */
    @FXML
    private TextField user_IDAddCustomer;

    /** Date time formatter.  */
    private final DateTimeFormatter updateDTF = DateTimeFormatter.ofPattern("yyyy-mm-dd");

    /** Appointment location.
     * @param event selected all appointment location.
     */
    @FXML
    void onActionAdAppointmentLocation(ActionEvent event) {

    }
    /** Contact for appointment.
     * @param event selects contact for appointment.
     */
    @FXML
    void onActionAddContactAppointment(ActionEvent event) {

    }
    /** Button to exit customer update form.
     * @param event exists update form.
     */
    @FXML
    void onActionCancelAddCustomer(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainApplication.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

/**Action to end appointment time.
 * @param event add end time appointment. */
    @FXML
    void onActionEndTimeAddAppointment(ActionEvent event) {

    }
    /** Save updated customer information to data base and checks for appointment overlap.
     * @param actionEvent adds information to database.
     * @throws IOException
     * @return remain on current form does not allow adding to the database if alert is triggered.
     */
    @FXML
    void onActionSaveAddCustomer(ActionEvent actionEvent) throws IOException, SQLException {

        int appID = Integer.parseInt(appointment_IDAddCustomer.getText());
        String title = titleAddCustomer.getText();
        String description = descriptionAddCustomer.getText();
        String location = addAppointmentLocation.getValue();
        int contact_ID = addContactAppointment.getValue().getContact_ID();
        String type = typeAddAppointment.getValue();

        LocalDate updateDTF = startDateAddAppointment.getValue();
        LocalTime updateStartTime = startTimeAddAppointment.getValue();
        LocalTime updateEndTime = endTimeAddAppointment.getValue();
        int customer_ID = updateCust_ID.getValue().getCustomer_ID();
        int user_ID = comboBxUpdateUser_ID.getValue().getUser_ID();

        LocalDateTime updateStartDateTime = LocalDateTime.of(updateDTF, updateStartTime);
        LocalDateTime updateEndDateTime = LocalDateTime.of(updateDTF, updateEndTime);

        ObservableList<appointments> getAllAppointments = DAOappointments.getAllAppointments();
        Boolean Overlap = true;

        for (appointments A : getAllAppointments) {
            LocalDate cDate = startDateAddAppointment.getValue();
            LocalDateTime cStart = A.getStart();
            LocalDateTime cEnd = A.getEnd();
            LocalDateTime proposedStart = LocalDateTime.of(updateDTF, updateStartTime);
            LocalDateTime proposedEnd = LocalDateTime.of(updateDTF, updateEndTime);


            if (A.getCustomer_ID() != customer_ID || A.getAppointment_ID() == appID) {
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


        DAOappointments.createUpdateAppointments(appID, title, description, location, contact_ID, type, updateStartDateTime, updateEndDateTime, customer_ID, user_ID);

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainApplication.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

    }

    /** Start date and time appointment on action combo box.
     * @param event add a start date to the database.  */
    @FXML
    void onActionStartDateAddAppointment(ActionEvent event) {

    }
    /** Start time combo box.
     * @param event add a start time to the database. */
    @FXML
    void onActionStartTimeAddAppointment(ActionEvent event) {

    }
    /** Type combo box.
     * @param event add type of appointment to the database. */
    @FXML
    void onActionTypeAddAppointment(ActionEvent event) {

    }
    /**Update customer_ID combo box.
     * @param actionEvent update a customer_ID to add to the appointment.
     */
    public void onActionComboBxUpdateCustomer_ID(ActionEvent actionEvent) {

    }
    /** Update user_ID combo box.
     * @param actionEvent update a user_ID to add to the appointment. */
    public void onActionComboBxUpdateUser_ID(ActionEvent actionEvent) {


    }
    /** User selected an appointment which sends information to the update appointment form and back to the appointment form table view.
     * @param selectedApp the appointment selected to be modified.
     * @throws DateTimeException
     */
    public void sendAppointment(appointments selectedApp) {
        try {
            appointment_IDAddCustomer.setText(String.valueOf(selectedApp.getAppointment_ID()));
            titleAddCustomer.setText(selectedApp.getTitle());
            descriptionAddCustomer.setText(selectedApp.getDescription());
            addAppointmentLocation.setValue(selectedApp.getLocation());

            typeAddAppointment.setValue(selectedApp.getType());

            startDateAddAppointment.setValue(selectedApp.getStart().toLocalDate());
            startTimeAddAppointment.setValue(selectedApp.getStart().toLocalTime());
            endTimeAddAppointment.setValue(selectedApp.getEnd().toLocalTime());

            for (customer C : updateCust_ID.getItems()) {
                if (C.getCustomer_ID() == selectedApp.getCustomer_ID()) {
                    updateCust_ID.setValue(C);
                    break;
                }
            }
            for (users U : comboBxUpdateUser_ID.getItems()) {
                if (U.getUser_ID() == selectedApp.getUser_ID()) {
                    comboBxUpdateUser_ID.setValue(U);
                    break;
                }
            }
            for (contacts Con : addContactAppointment.getItems()) {
                if (Con.getContact_ID() == selectedApp.getContact_ID()) {
                    addContactAppointment.setValue(Con);
                    break;

                }
            }
            ObservableList<appointments> getAllAppointments = DAOappointments.getAllAppointments();
        } catch (DateTimeException D) {
            D.printStackTrace();
        }

    }
/** Action to update customer_ID.
 * @param actionEvent on action updates customer_ID. */
    public void onActionupdateCustomer_ID(ActionEvent actionEvent) {
    }

    public void onActionAddAppointmentLocation(ActionEvent actionEvent) {
    }

    /** Initialize the combo boxes.
     * Set hours and minutes to the combo boxes.
     * @param url
     * @param resourceBundle  */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addAppointmentLocation.setItems(Helper.listManager.locOptions);
        typeAddAppointment.setItems(listManager.meetingType);

        comboBxUpdateUser_ID.setItems(DAOuser.getAllUsers());
        updateCust_ID.setItems(DAOcustomer.getAllCustomers());
        addContactAppointment.setItems(DAOcontacts.getAllContacts());


        for (int i = 0; i < 24; i++) {
            startTimeAddAppointment.getItems().add(LocalTime.of(i, 0));
        }
        for (int i = 1; i < 25; i++) {
            if (i <= 23)
                endTimeAddAppointment.getItems().add(LocalTime.of(i, 0));
            else

                endTimeAddAppointment.getItems().add(LocalTime.of(0, 0));
        }

    }



}




