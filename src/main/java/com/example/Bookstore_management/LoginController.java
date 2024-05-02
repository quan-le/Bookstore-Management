package com.example.Bookstore_management;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Scene;




public class LoginController {

    @FXML
    private Button close;

    @FXML
    private Button loginBut;

    @FXML
    private StackPane main_pane;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    //this part will start handling the login events (new)
    @FXML
    private void handleLogin() {
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();

        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            showAlert("Please enter your username and password");
        }
        else if (enteredUsername.equals("quanlebeo") && enteredPassword.equals("quangcuto")) {
            showAlert("Login successfully");
            //open the dashboard xd
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                Stage stage = (Stage) loginBut.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Dashboard");
                stage.show();
            } catch (IOException e) {
                showAlert("Cannot load the Dashboard *sad passion noises*");
                e.printStackTrace();
            }
            //Unhandled exception: java.io.IOException (since we have this error, chung ta se xai try-catch va hope no works xd)
        }
        else {
            showAlert("Incorrect username/password, please enter your username and password");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}