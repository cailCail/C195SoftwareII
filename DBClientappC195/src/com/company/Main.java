package com.company;

import Helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
/** This is class creates an application for appointments and customers. */
public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
    public static void main(String[] args) {
        System.out.println("I am initialized");
        //Locale.setDefault(new Locale("fr"));
        // write your code here
        JDBC.openConnection();

        launch(args);

        JDBC.closeConnection();



    }

}
