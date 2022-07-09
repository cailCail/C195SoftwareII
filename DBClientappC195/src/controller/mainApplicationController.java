package controller;

import DAO.DAOappointments;
import DAO.DAOcustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.appointments;
import model.customer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class initializes the mainApplicationController. */

public class mainApplicationController<newAppointments> implements Initializable {

    Stage stage;
    Parent scene;
/** Button to delete appointments. */
    @FXML
    public Button deleteAppointmentInformation;
    /** Table column appointment_Id. */
    @ FXML
    public TableColumn appointment_IDCOL;
    /** Table column title. */
    @FXML
    public TableColumn titleCOL;
    /** Table column description. */
    @FXML
    public TableColumn descCOL;
    /** Table column location. */
    @FXML
    public TableColumn locationCOL;
    /** Table column contact_ID.  */
    @FXML
    public TableColumn contactCOL;
    /** Table column type. */
    @FXML
    public TableColumn typeCOL;
    /** Table column start. */
    @FXML
    public TableColumn startCOL;
    /**Table column end.  */
    @FXML
    public TableColumn endCOL;
    /** Table column customer_ID. */
    @FXML
    public TableColumn customer_IDCOL;
    /** Table column user_ID.  */
    @FXML
    public TableColumn userCOL;
    /**  Table view for all appointments. */
    @FXML
    public TableView<appointments> allAppointment;
    /** Button to add appointments. */
    @FXML
    private Button addBtnAppointment;
    /** Button to view all customer form. */
    @FXML
    private Button customerAppointmentsCustomerInformation;
    /** Button to delete customer.  */
    @FXML
    private Button deleteCustomerInformation;
    /** Button to exit. */
    @FXML
    private Button exitCustomerInformation;
    /** Radio button to view all appointments by Month.  */
    @FXML
    private RadioButton radioBtnMonthCustomerInformation;
    /** Radio button to view all appointments. */
    @FXML
    private RadioButton radioBtnViewAllAppointments;
    /** Radio button to view all appointments by week. */
    @FXML
    private RadioButton radioBtnWeek;
    /** Toggle button grouping radio buttons. */
    @FXML
    private ToggleGroup toggleCustomerInformation;
    /** Toggle button grouping radio buttons. */
    @FXML
    private Button updateCustomerInformation;

    /** On action exit when button selected will exit the current screen.
     * @throws IOException
     */
    @FXML
    void onActionExit(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

    }
    /** All appointments within the current month will display in the table view.
     * @param event radio button is selected all appointments will display in the table.
     */
    @FXML
    void onActionMonth(ActionEvent event) {
        allAppointment.setItems(DAOappointments.getAllAppByMonth());
    }

    /** Update customer allows the user to update or change selected customer application details.
     @param event customer information is selected and moved to the update application form in the correct boxes and combo boxes.
     @throws IOException
     */
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/updateAppointment.fxml"));
        loader.load();

        updateAppointmentController UAppController = loader.getController();
        UAppController.sendAppointment(allAppointment.getSelectionModel().getSelectedItem());

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** View all appointments.
     @param event radio button is selected to view all appointments in the table.
     */
    @FXML
    void onActionViewAllAppointments(ActionEvent event) {
        allAppointment.setItems(DAOappointments.getAllAppointments());
    }
    /** Button selected allows user to view the customer form.
     * @param event button selected changes the appointment form to the customer form. */
    @FXML
    void onActionViewAllCustomers(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    /** User is able to view all appointments by week.
     * @param event radio button is selected all appointments by week are displayed in the table.  */
    @FXML
    void onActionWeek(ActionEvent event) {
        allAppointment.setItems(DAOappointments.getAllAppByWeek());
    }
    /** Button adds appointments to the database.
     @param actionEvent adds new appointment details to database.  */
    @FXML
    public void onAddAppointmentBtn(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/addAppointment.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

    }

    /**  Deletes the selected appointment.
     * @param actionEvent  highlighted appointment is deleted from the database.
     *
     */
    public void onActionDeleteAppointment(ActionEvent actionEvent) {
        appointments selectedApp = allAppointment.getSelectionModel().getSelectedItem();

        if (selectedApp == null) {
            Alert alert7 = new Alert(Alert.AlertType.ERROR);
            alert7.setTitle("Alert!");
            alert7.setContentText("No appointment selected for delete! Please select an appointment before selecting the delete button.");
            alert7.showAndWait();
            return;
        }

        try {
            int aID = selectedApp.getAppointment_ID();
            DAOappointments.deleteCustomer(aID);
            Alert alert8 = new Alert(Alert.AlertType.CONFIRMATION, "This will delete appointment id: " + selectedApp.getAppointment_ID() + " " + selectedApp.getType() + ". Do you want to continue?");
            Optional<ButtonType> result = alert8.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                allAppointment.setItems(DAOappointments.getAllAppointments());
            }

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        allAppointment.setItems(DAOappointments.getAllAppointments());
    }

    /** This method sets the appointments table.
     * @param resourceBundle
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allAppointment.setItems(DAOappointments.getAllAppointments());


        appointment_IDCOL.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        titleCOL.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descCOL.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locationCOL.setCellValueFactory(new PropertyValueFactory<>("Location"));
        typeCOL.setCellValueFactory(new PropertyValueFactory<>("Type"));

        startCOL.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCOL.setCellValueFactory(new PropertyValueFactory<>("End"));
        customer_IDCOL.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        userCOL.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        contactCOL.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));

    }


}

