package controller;

import DAO.DAOFirst_Level_Divisions;
import DAO.DAOcountries;
import DAO.DAOcustomer;
import Helper.JDBC;
import com.mysql.cj.jdbc.JdbcConnection;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.countries;
import model.customer;
import model.first_level_divisions;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
/** This class initializes the addCustomerController class.
 *
 */
public class addCustomerController implements Initializable {
    Stage stage;
    Parent scene;

/** Text field customer address.  */
    @FXML
    private TextField addCustomerAddress;

    /** Combo box of countries to select customer country.   */
    @FXML
    private ComboBox<countries> addCustomerCountry;

    /** Text  field for customer name.  */
    @FXML
    private TextField addCustomerCustomerName;

    /** Text field customer_ID.  */
    @FXML
    private TextField addCustomerCustomer_ID;

    /** Combo box of first_level_divisions.  */
    @FXML
    private ComboBox<first_level_divisions> addCustomerFirst_Level_Division;

    /** Text field for customer phone number.  */
    @FXML
    private TextField addCustomerPhoneNumber;

    /** Text field for customer zipcode.  */
    @FXML
    private TextField addCustomerPostalCode;

    /** Cancel button.  */
    @FXML
    private Button cancelBtnAddCustomer;

    /** Save button.  */
    @FXML
    private Button saveBtnAddCustomer;

    /** Selects country based upon country_ID.
     * @param event adds country when selected.
     */
    @FXML
    void onActionAddCustomerCountry(ActionEvent event) {

        countries selectedCountry = addCustomerCountry.getSelectionModel().getSelectedItem();
        int cID = selectedCountry.getCountry_ID();

        addCustomerFirst_Level_Division.setItems(DAOFirst_Level_Divisions.getDivisions(cID));

    }

    /** Cancel button to exit add customer screen.
     * @param event exit when selected.
     * @throws IOException
     */
    @FXML
    void onActionCancelAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /** Combo box of first_level_divisions.
     *@param event first_level_divisions will be selected.
     */
    @FXML
    void onActionCustomerFirst_Level_Division(ActionEvent event) {

    }

    /** Save customer when selected.
     *
     * @throws IOException
     */
    @FXML
    void onActionSaveAddCustomer(ActionEvent event) throws SQLException, IOException {

        String Name = addCustomerCustomerName.getText();
        String Address = addCustomerAddress.getText();
        String Zip = addCustomerPostalCode.getText();
        String Phone = addCustomerPhoneNumber.getText();
        int Division = (addCustomerFirst_Level_Division.getValue().getDivision_ID());

        DAOcustomer.createCustomer(Name, Address, Zip, Phone, Division);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

    }

    /** Sets countries on the country combo box.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerCountry.setItems(DAOcountries.getAllCountries());


    }

}
