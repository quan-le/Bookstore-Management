package com.example.Bookstore_management;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Alert;

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