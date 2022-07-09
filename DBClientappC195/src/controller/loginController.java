package controller;

import DAO.DAOappointments;
import DAO.DAOreports;
import DAO.DAOuser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.appointments;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Logger;
/** This class initializes the loginController. */
public class loginController implements Initializable {


    Stage stage;
    Parent scene;

    /**  Label user name. */
    @FXML
    public Label userName;

    /** Label password.  */
    @FXML
    public Label password;

    /** Exit button.  */
    @FXML
    private Button exitBtnLogin;

    /** Text field password.  */
    @FXML
    private TextField passwordLogin;

    /** Button submit.  */
    @FXML
    private Button submitLogin;

    /** Label for time zone text.  */
    @FXML
    private Label timeZoneTxt;

    /** Text field user name.  */
    @FXML
    private TextField userNameLogin;

    /** ResourceBundle for language directory.  */
    @FXML
    private ResourceBundle myBundle = ResourceBundle.getBundle("languageDirectory/Bundle");

    /** On action exit button.
     * @param event When selected exit program.
     */
    @FXML
    void onActionExitLogin(ActionEvent event) {
        System.exit(0);
    }

    /** On action submit button pressed to determine if correct user name and password were entered.
     * Checking for appointment within 15 minutes of user signing into the application.
     * @throws IOException
     * @param event user name and password will be submitted to the database to check if correct credentials were entered.
     */
    @FXML
    void onActionSubmitLogin(ActionEvent event) throws IOException {
        String userName = userNameLogin.getText();
        String userPassword = passwordLogin.getText();
        int userID = DAOuser.userLogin(userName, userPassword);

        // write to a file 15 minute alert and switch screens
        if (userID > 0) {

            ObservableList<appointments> getAllAppointments = DAOappointments.getAllAppointments();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime now15 = LocalDateTime.now().plusMinutes(15);


            Boolean found = false;
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.hide();
            for (appointments A : getAllAppointments) {

                if (A.getUser_ID() != userID) {
                    continue;
                }
                LocalDateTime start = A.getStart();

                if ((start.isAfter(now) && start.isBefore(now15))) {

                    found = true;


                    Alert alert12 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert12.setTitle("Appointments");
                    alert12.setContentText("Appointment within the next 15 minutes appointment ID: " + A.getAppointment_ID() + " start date and time: " + A.getStart() + ".");
                    alert12.showAndWait();


                }

            }
            if (!found) {
                Alert alert12 = new Alert(Alert.AlertType.CONFIRMATION);
                alert12.setTitle("Appointments");
                alert12.setContentText("No Appointment within 15 minutes!");
                alert12.showAndWait();

            }
            loginTXT("Valid login for " + userName + " at time " + LocalDateTime.now());

            Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
            stage.setScene(new Scene(root));
            stage.show();

        }


        else if (userID < 0) {
            Alert alert13 = new Alert(Alert.AlertType.ERROR);
            alert13.setTitle(myBundle.getString("TITLE"));
            alert13.setContentText(myBundle.getString("INVALID"));
            alert13.showAndWait();

            loginTXT("InValid login for " + userName +" at time " + LocalDateTime.now());

        }


    }

    /** On action text field for user name and password.
     * @param actionEvent user enters login credentials.
     */
    public void onActionUserLogin(ActionEvent actionEvent) {

    }
    /** This method creates the login_activity.txt.
     *
     */
    public static void loginTXT(String message) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(
                    new File("login_activity.txt"),
                    true));
            pw.append(message + "\n");
            pw.flush();
            pw.close();

        } catch (FileNotFoundException ex) {

            ex.printStackTrace();
        }
        return;
    }
/** Utilizes bundle for language changes for main menu of the application.
 * Time zone for login page based upon location.
 * @param url
 * @param resourceBundle
 *
 */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        exitBtnLogin.setText(myBundle.getString("EXIT"));
        submitLogin.setText(myBundle.getString("SUBMIT"));
        password.setText(myBundle.getString("PASSWORD"));
        userName.setText(myBundle.getString("USERNAME"));


        timeZoneTxt.setText(ZoneId.systemDefault().toString());

        LocalDateTime startLocal = LocalDateTime.now();
        ZonedDateTime zdtLocalStart = startLocal.atZone(ZoneId.systemDefault());
        ZonedDateTime zdtESTStart = zdtLocalStart.withZoneSameInstant((ZoneId.of("America/New_York")));
        LocalDateTime startEST = zdtESTStart.toLocalDateTime();
        System.out.println(startLocal);
        System.out.println(startEST);

        Locale locale = Locale.getDefault();
        Locale.setDefault(locale);


    }
}
