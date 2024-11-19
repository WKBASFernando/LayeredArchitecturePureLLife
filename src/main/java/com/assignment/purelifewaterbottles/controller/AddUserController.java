package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.dto.UserDto;
import com.assignment.purelifewaterbottles.model.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Setter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {

    @FXML
    private Button btnRemove;

    @FXML
    private ComboBox<String> cmbRemoveUser;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @Setter
    private HomePageController homePageController;

    @FXML
    void addUserOnAction(ActionEvent event) throws SQLException {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String confirmPassword = txtConfirmPassword.getText().trim();

        if (username.isEmpty() || username.contains(" ")) {
            new Alert(Alert.AlertType.ERROR, "Invalid username. Ensure there are no spaces and it is not empty.").show();
            return;
        }

        if (userModel.isUsernameExists(username)) {
            new Alert(Alert.AlertType.ERROR, "Username already exists. Please choose a different username.").show();
            return;
        }

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Password fields cannot be empty.").show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
            return;
        }

        UserDto userDto = new UserDto(username, password);
        boolean isSaved = userModel.saveUser(userDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "User saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save user...!").show();
        }
    }

    @FXML
    void removeUserOnAction(ActionEvent event) throws SQLException {
        String username = cmbRemoveUser.getValue();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = userModel.deleteUser(username);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "User deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete User...!").show();
            }
        }
    }

    @FXML
    void selectOnAction(ActionEvent event) {
        if(cmbRemoveUser != null) {
            btnRemove.setDisable(false);
        }
    }

    UserModel userModel = new UserModel();

    private void loadUsernames() throws SQLException {
        ArrayList<String> usernames = userModel.getAllUsernames();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(usernames);
        cmbRemoveUser.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to Load!").show();
        }
    }

    private void refreshPage() throws SQLException {
        loadUsernames();

        cmbRemoveUser.setPromptText("Select user you want to remove");

        txtUsername.setText(null);
        txtPassword.setText("");
        txtConfirmPassword.setText("");

        btnRemove.setDisable(true);
    }
}
