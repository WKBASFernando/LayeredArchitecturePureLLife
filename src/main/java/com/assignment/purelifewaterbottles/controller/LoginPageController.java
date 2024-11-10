package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.dto.UserDto;
import com.assignment.purelifewaterbottles.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private PasswordField txtPassword;;

    @FXML
    private TextField txtUsername;

    @FXML
    private AnchorPane content;


    @FXML
    void loginAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        UserDto user = UserModel.authenticateUser(username, password);

        if (user != null) {
            /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText(null);
            Image image = new Image(getClass().getResourceAsStream("/images/PureLife.png"));
            alert.setContentText("Welcome, " + user.getUsername() + "!");
            alert.showAndWait();*/
            navigateTo("/view/HomePage.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
    }
    public void navigateTo(String fxmlPath) {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));

            load.prefWidthProperty().bind(content.widthProperty());
            load.prefHeightProperty().bind(content.heightProperty());

            content.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }
}
