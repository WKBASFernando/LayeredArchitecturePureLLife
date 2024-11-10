package com.assignment.purelifewaterbottles.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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
