package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.dto.UserDto;
import com.assignment.purelifewaterbottles.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LoginPageController {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void loginAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        UserDto user = UserModel.authenticateUser(username, password);

        if (user != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText(null);
            alert.setContentText("Welcome, " + user.getUsername() + "!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
    }

}
