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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** This class initializes the reportCustomerController. */
public class reportCustomerController implements Initializable {

    Stage stage;
    Parent scene;


    /**
     * Button exit.
     */
    @FXML
    private Button btnExitReport;
    /**
     * Table view for contacts report.
     */
    @FXML
    private TableView<appointments> contactsTableView;
    /**
     * Table view for customer report.
     */
    @FXML
    private TableView<customer> countryTableView;
    /**
     * Table column for address.
     */
    @FXML
    private TableColumn<?, ?> reportAddressCountry;
    /**
     * Column appointment_ID.
     */
    @FXML
    private TableColumn<?, ?> reportAppointment_ID;
    /**
     * Column contact_ID.
     */
    @FXML
    private TableColumn<?, ?> reportContact;
    /**
     * Combo box of contacts.
     */
    @FXML
    private ComboBox<contacts> reportContacts;
    /**
     * Combo box of countries.
     */
    @FXML
    private ComboBox<countries> reportCountry;
    /**
     * Label for the type and month count.
     */
    @FXML
    private Label reportCount;
    /**
     * Column customer name.
     */
    @FXML
    private TableColumn<?, ?> reportCustomerNameCountry;
    /**
     * Column customer_ID.
     */
    @FXML
    private TableColumn<?, ?> reportCustomer_ID;
    /**
     * Column customer_ID for the country table.
     */
    @FXML
    private TableColumn<?, ?> reportCustomer_IDCountry;
    /**
     * Column description table for the country table.
     */
    @FXML
    private TableColumn<?, ?> reportDescription;
    /**
     * Column for the division_ID for the country table.
     */
    @FXML
    private TableColumn<?, ?> reportDivision_IDCountry;
    /**
     * Column for end.
     */
    @FXML
    private TableColumn<?, ?> reportEnd;
    /**
     * Column for location.
     */
    @FXML
    private TableColumn<?, ?> reportLocation;
    /**
     * Combo box for month.
     */
    @FXML
    private ComboBox<String> reportMonth;
    /**
     * Column for phone number in the country table.
     */
    @FXML
    private TableColumn<?, ?> reportPhoneNumberCountry;
    /**
     * Column for postal code in the country table.
     */
    @FXML
    private TableColumn<?, ?> reportPostalCodeCountry;
    /**
     * Column start.
     */
    @FXML
    private TableColumn<?, ?> reportStart;
    /**
     * Column title.
     */
    @FXML
    private TableColumn<?, ?> reportTitle;
    /**
     * Combo box for report types.
     */
    @FXML
    private ComboBox<String> reportType;
    /**
     * Column type.
     */
    @FXML
    private TableColumn<?, ?> reportTypeColumn;
    /**
     * Column user_ID.
     */
    @FXML
    private TableColumn<?, ?> reportUser_ID;

    /**
     * Reports by month.
     *
     * @param actionEvent report by month.
     */
    @FXML
    public void onActionReportMonth(ActionEvent actionEvent) {

    }

    /**
     * Select a contact by contact_ID to filter a table of by contact.
     *
     * @param actionEvent User will choose a contact from the combo box which will change the table.
     */
    @FXML
    public void onActionReportContacts(ActionEvent actionEvent) {

        contacts selectedAppoint = reportContacts.getSelectionModel().getSelectedItem();
        int contactID = selectedAppoint.getContact_ID();

        contactsTableView.setItems(DAOreports.getAllAppContactID(contactID));
    }

    /**
     * Exit reports form.
     *
     * @param event user will exit the report form.
     * @throws IOException
     */
    @FXML
    void onActionExitReport(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * List of customers by their country.
     *
     * @param actionEvent table view will filter customers by country.
     */
    @FXML
    public void onActionReportCountry(ActionEvent actionEvent) {
        countries selectedCountry = reportCountry.getSelectionModel().getSelectedItem();
        int countryID = selectedCountry.getCountry_ID();
        countryTableView.setItems(DAOreports.getAllCustomersReport(countryID));
    }

    /**
     * Set customer information in the customer table on the report form.
     *
     * @param selectedCust Observable list of customers.
     * @return sets selected customers in the table if meets selection criteria.
     */
    private ObservableList<countries> reportCustomer(customer selectedCust) {

        reportCustomer_IDCountry.setText(String.valueOf(selectedCust.getCustomer_ID()));
        reportCustomerNameCountry.setText(selectedCust.getCustomer_name());
        reportAddressCountry.setText(selectedCust.getAddress());
        reportPostalCodeCountry.setText(selectedCust.getPostal_code());
        reportPhoneNumberCountry.setText(selectedCust.getPhone());
        reportDivision_IDCountry.setText(String.valueOf(selectedCust.getDivision_ID()));
        ObservableList<countries> CusByCountry = DAOcountries.getAllCountries();
        reportCountry.setItems(CusByCountry);
// updateCustomer Countries
        for (countries C : CusByCountry) {
            if (C.getCountry_ID() == selectedCust.getCountry_ID()) {
                reportCountry.setValue(C);
                break;
            }
        }
        return CusByCountry;

    }

    /**
     * Populates combo boxes and populates the both tables on the report form.
     *
     * @param resourceBundle
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // populate combo boxes
        reportType.setItems(listManager.meetingType);
        reportContacts.setItems(DAOcontacts.getAllContacts());
        reportCountry.setItems(DAOcountries.getAllCountries());
        reportMonth.setItems(listManager.monthName);
        //countryTableView.setItems(DAOreports.getAllCustomersReport());

// populate top table appointments
        contactsTableView.setItems(DAOappointments.getAllAppointments());
        reportAppointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        reportTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        reportDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        reportLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));
        reportTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));

        reportStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
        reportEnd.setCellValueFactory(new PropertyValueFactory<>("End"));
        reportCustomer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        reportUser_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        reportContact.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));

// populate bottom table customer information
        countryTableView.setItems(DAOcustomer.getAllCustomers());
        reportCustomer_IDCountry.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        reportCustomerNameCountry.setCellValueFactory(new PropertyValueFactory<>("Customer_name"));
        reportAddressCountry.setCellValueFactory(new PropertyValueFactory<>("Address"));
        reportPostalCodeCountry.setCellValueFactory(new PropertyValueFactory<>("Postal_code"));
        reportPhoneNumberCountry.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        reportDivision_IDCountry.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));


    }


    public void onActionReportTypeMonth(ActionEvent actionEvent) {
        String selectedType = reportType.getSelectionModel().getSelectedItem();
        String selectedMonth = reportMonth.getSelectionModel().getSelectedItem();
        if (selectedType == null || selectedMonth == null) {
            return;
        }
        reportCount.setText(DAOreports.getAllAppMonthType(selectedType, selectedMonth));


    }
}
