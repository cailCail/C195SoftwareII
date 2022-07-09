package controller;

import DAO.DAOappointments;
import DAO.DAOcustomer;
import com.sun.javafx.charts.Legend;
import com.sun.javafx.menu.CustomMenuItemBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.appointments;
import model.customer;
import model.first_level_divisions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
/** This class initializes the customerController.  */
public class customerController<newCustomer> implements Initializable {


    public Button updateBtnCustomer;
    public Button deleteBtnCustomer;
    public Button exitBtnCustomer;
    Stage stage;
    Parent scene;

    /** Text customer information label.  */
    @FXML
    public Text customerInfoLbl;

    /** Table view of customer. */
    public TableView<customer> customerTableView;

    /** Customer table column Division_ID.  */
    @FXML
    private TableColumn<customer, Integer> Division_ID;

    /** Add customer button.  */
    @FXML
    private Button addBtnCustomer;

    /**  Customer table column customer address. */
    @FXML
    private TableColumn<customer, String> customerAddress;

    /** Customer table column customer name.  */
    @FXML
    private TableColumn<customer, String> customerCustomerName;

    /** Customer table column customer_ID.  */
    @FXML
    private TableColumn<customer, Integer> customerCustomer_ID;

    /** Customer table column phone number.  */
    @FXML
    private TableColumn<customer, String> customerPhoneNumber;

    /** Customer table column postal code.  */
    @FXML
    private TableColumn<customer, String> customerPostalCode;

    /** On action exit button will display main application form when selected.
     * @param event exit button selected exists customer page.
     * @throws IOException
     */
    @FXML
    void anActionExitBtnCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainApplication.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /** On action save button will display add customers form when selected.
     * @param event add customer button is selected and changes the screen to the add customer form.
     * @throws IOException
     */
    @FXML
    void onActionAddBtnCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /** Selects customer from customer table view for delete.
     * @param event selects customer from table to delete.
     * @return
     * */
    @FXML
    void onActionDeleteBtnCustomer(ActionEvent event) {
        customer selectedC = customerTableView.getSelectionModel().getSelectedItem();

        if (selectedC == null) {
            Alert alert7 = new Alert(Alert.AlertType.ERROR);
            alert7.setTitle("Alert!");
            alert7.setContentText("No customer selected for delete! Please select a customer before selecting the delete button.");
            alert7.showAndWait();
            return;

        } else {
            int cID = selectedC.getCustomer_ID();
            DAOcustomer.deleteCustomer(cID);
            Alert alert8 = new Alert(Alert.AlertType.CONFIRMATION, "This will delete customer " + selectedC + ". Do you want to continue?");
            Optional<ButtonType> result = alert8.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                    Boolean cusDeleted = DAOcustomer.deleteCustomer(selectedC.getCustomer_ID());
                    if (cusDeleted) {
                        Alert deleteAlert5 = new Alert(Alert.AlertType.CONFIRMATION, selectedC + " and all related appointments are deleted.");
                        Optional<ButtonType> result1 = deleteAlert5.showAndWait();

                    } else {
                        Alert deleteAlert6 = new Alert(Alert.AlertType.ERROR);
                        deleteAlert6.setTitle("Alert!");
                        deleteAlert6.setContentText("Unable to delete " + selectedC + " please delete all related appointments.");
                        deleteAlert6.showAndWait();

                    }

                }

                customerTableView.setItems(DAOcustomer.getAllCustomers());


            }
        }

    /** On action when button is selected will display update customer form.
     * @throws IOException
     * @param event button selected then displays updateCustomer form.
     */
    @FXML
    void onActionUpdateBtnCustomer(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/updateCustomer.fxml"));
        loader.load();

        updateCustomerController UCController = loader.getController();
        UCController.sendCustomer(customerTableView.getSelectionModel().getSelectedItem());

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Populates customer table.
     * Lambda when a customer is selected the selected customer and customer_ID are displayed
     *    in the customer information label at the top right side of the screen.
     * @param resourceBundle
     * @param url
     *
     */
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTableView.setItems(DAOcustomer.getAllCustomers());
        customerCustomer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        customerCustomerName.setCellValueFactory(new PropertyValueFactory<>("Customer_name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("Postal_code"));
        customerPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Division_ID.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));

        // Lambda- when a customer is selected the selected customer and customer ID is displayed in the customer information label
        customerTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldCustomer, newCustomer) -> {
           if (newCustomer != null) {
               customerInfoLbl.setText(newCustomer.toString());
           }
       });

    }

}



