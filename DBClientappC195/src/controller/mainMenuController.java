package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** This class initializes the mainMenuController. */
public class mainMenuController implements Initializable {
    Stage stage;
    Parent scene;
    /** Button to exit.  */
    @FXML
    private Button btnExit;
    /** Button to enter main form. */
    @FXML
    private Button btnMainForm;
    /** Button to enter reports form.  */
    @FXML
    private Button btnReports;
    /** Button exits the application.
     * @throws IOException
     * @param event application returns to the login form. */
    @FXML
    void onActionExitApplication(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    /** Button views the main application form. *
     * @throws IOException
     * @param event application returns to the main application.
     */

    @FXML
    void onActionMainApplication(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainApplication.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    /** Button displays the report form.
     * @param event button changes the screen to the report form.
     * @throws  IOException
     */
    @FXML
    void onActionReports(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/reportCustomer.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    /** The initialize method for the main menu.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
