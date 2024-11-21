package com.assignment.purelifewaterbottles.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HomePageController {

    @FXML
    private AnchorPane content;

    @FXML
    void ManageCustomersAction(MouseEvent event) {
        navigateTo("/view/CustomerPage.fxml");

    }

    @FXML
    void ManageEmployeesAction(MouseEvent event) {
        navigateTo("/view/EmployeePage.fxml");

    }

    @FXML
    void ManageItemsAction(MouseEvent event) {
        navigateTo("/view/ItemPage.fxml");

    }

    @FXML
    void ManageOrdersAction(MouseEvent event) {
        navigateTo("/view/OrderPage.fxml");
    }

    @FXML
    void addUserOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddUser.fxml"));
            Parent root = loader.load();
            AddUserController addUserController = loader.getController();
            addUserController.setHomePageController(this);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logOutAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            navigateTo("/view/LoginPage.fxml");
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
