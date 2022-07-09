package controller;

import DAO.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.countries;
import model.customer;
import model.first_level_divisions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** This class initializes the updateCustomerController.  */
public class updateCustomerController implements Initializable {

    Stage stage;
    Parent scene;
    /** Text field customer address.  */
    @FXML
    private TextField updateCustomerAddress;
    /** Comb boxes countries. */
    @FXML
    private ComboBox<countries> updateCustomerCountry;
    /** Text field customer name. */
    @FXML
    private TextField updateCustomerCustomerName;
    /** Text field customer_ID. */
    @FXML
    private TextField updateCustomerCustomer_ID;
    /** Combo box first_level_divisions. */
    @FXML
    private ComboBox<first_level_divisions> updateCustomerFirst_Level_Division;
    /** Text field phone number. */
    @FXML
    private TextField updateCustomerPhoneNumber;
    /** Text field zip code. */
    @FXML
    private TextField updateCustomerPostalCode;
    /** Button to cancel. */
    @FXML
    private Button cancelBtnUpdateCustomer;
    /** Button save. */
    @FXML
    private Button saveBtnUpdateCustomer;
    /**Button to update customer country.
     * @param actionEvent user is able to update the country.
     */
    @FXML
    public void onActionUpdateCustomerCountry(ActionEvent actionEvent) {
        countries selectedCountry = updateCustomerCountry.getSelectionModel().getSelectedItem();
        int cID = selectedCountry.getCountry_ID();

        updateCustomerFirst_Level_Division.setItems(DAOFirst_Level_Divisions.getDivisions(cID));

    }
    /** Button to cancel or exit update customer form.
     * @throws IOException
     */
    @FXML
    public void onActionCancelpdateCustomer(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    /** Update customer first_level_divisions.
     * @param actionEvent updates first_level_divisions.
     */
    @FXML
    public void onActionUpdateCustomerFirst_Level_Division(ActionEvent actionEvent) {
    }
    /**Update customer button.
     * @throws IOException
     */
    @FXML
    public void onActionSaveUpdateCustomer(ActionEvent actionEvent) throws IOException {
        String Name = updateCustomerCustomerName.getText();
        String Address = updateCustomerAddress.getText();
        String Zip = updateCustomerPostalCode.getText();
        String Phone = updateCustomerPhoneNumber.getText();
        int Division = (updateCustomerFirst_Level_Division.getValue().getDivision_ID());
        int Customer = Integer.parseInt(updateCustomerCustomer_ID.getText());

        DAOcustomer.createUpdateCustomer(Name, Address, Zip, Phone, Division, Customer);

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

public void sendCustomer (customer selectedCust) {
    updateCustomerCustomer_ID.setText(String.valueOf(selectedCust.getCustomer_ID()));
    updateCustomerCustomerName.setText(selectedCust.getCustomer_name());
    updateCustomerAddress.setText(selectedCust.getAddress());
    updateCustomerPostalCode.setText(selectedCust.getPostal_code());
    updateCustomerPhoneNumber.setText(selectedCust.getPhone());
    ObservableList<countries> allCountries = DAOcountries.getAllCountries();
    updateCustomerCountry.setItems(allCountries);
// updateCustomer Countries
    for (countries C : allCountries) {
        if (C.getCountry_ID() == selectedCust.getCountry_ID()) {
            updateCustomerCountry.setValue(C);
            break;
        }
    }
    //updateCustomerFirst_Level_Division
    ObservableList<first_level_divisions> filterDivisions = DAOFirst_Level_Divisions.getDivisions(selectedCust.getCountry_ID());
    updateCustomerFirst_Level_Division.setItems(filterDivisions);
    for (first_level_divisions D : filterDivisions) {
        if (D.getDivision_ID() == selectedCust.getDivision_ID()) {
            updateCustomerFirst_Level_Division.setValue(D);
            break;
        }
    }

}
    /** Set items in the county combo box.
     * @param resourceBundle
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCustomerCountry.setItems(DAOcountries.getAllCountries());
    }

}







