package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.dto.DeliveryDto;
import com.assignment.purelifewaterbottles.dto.tm.DeliveryTm;
import com.assignment.purelifewaterbottles.model.DeliveryModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddDeliveryController implements Initializable {

    @FXML
    private Button btnFinish;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<?, ?> colDelFee;

    @FXML
    private TableColumn<?, ?> colDelId;

    @FXML
    private TableColumn<?, ?> colDriId;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private Label lblDelId;

    @FXML
    private Label lblDriId;

    @FXML
    private TableView<DeliveryTm> tblDelivery;

    @FXML
    private TextField txtDelFee;

    @FXML
    private TextField txtLocation;

    @Setter
    private OrderPageController orderPageController;

    @FXML
    void FinishOnAction(ActionEvent event) {
        DeliveryTm deliveryTm = tblDelivery.getSelectionModel().getSelectedItem();
        if (deliveryTm == null) {
            new Alert(Alert.AlertType.WARNING, "Please select delivery..!").show();
        } else {
            orderPageController.setDeliveryId(deliveryTm.getDeliveryId());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    DeliveryModel deliveryModel = new DeliveryModel();

    @FXML
    void saveOnAction(ActionEvent event) throws Exception {
        String deliveryId = lblDelId.getText();
        String driverId = lblDriId.getText();
        String location = txtLocation.getText();
        double deliveryFee = Double.parseDouble(txtDelFee.getText());


        DeliveryDto deliveryDto = new DeliveryDto(deliveryId, driverId, location, deliveryFee);

        boolean isSaved = deliveryModel.saveDelivery(deliveryDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Delivery saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save delivery...!").show();
        }
    }

    @FXML
    void tblOnAction(MouseEvent event) {
        DeliveryTm deliveryTm = tblDelivery.getSelectionModel().getSelectedItem();
        if (deliveryTm != null) {
            lblDelId.setText(deliveryTm.getDeliveryId());
            lblDriId.setText(deliveryTm.getDriverId());
            txtLocation.setText(deliveryTm.getLocation());
            txtDelFee.setText(String.valueOf(deliveryTm.getDelivery_fee()));

            btnFinish.setDisable(false);
        }
    }

    public void setDriverId(String driverId) {
        lblDriId.setText(driverId);
    }

    @FXML
    void AddDriverOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddDriver.fxml"));
            Parent root = loader.load();
            AddDriverController addDriverController = loader.getController();
            addDriverController.setAddDeliveryController(this);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colDelId.setCellValueFactory(new PropertyValueFactory<>("deliveryId"));
        colDriId.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colDelFee.setCellValueFactory(new PropertyValueFactory<>("delivery_fee"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load data!").show();
        }
    }

    private void refreshPage() throws Exception {
        loadNextDeliveryId();
        loadTableData();

        lblDelId.setText("");
        lblDriId.setText("");
        txtLocation.setText("");
        txtDelFee.setText("");

        btnFinish.setDisable(true);
    }

    private void loadNextDeliveryId() throws Exception {
        String nextDeliveryId = dm.getNextDeliveryId();
        Platform.runLater(() -> {
            lblDelId.setText(nextDeliveryId);
        });
    }

    DeliveryModel dm = new DeliveryModel();

    private void loadTableData() throws Exception {
        ArrayList<DeliveryDto> deliveryDtos = dm.getAllDeliveries();

        ObservableList<DeliveryTm> deliveryTms = FXCollections.observableArrayList();

        for (DeliveryDto deliveryDto : deliveryDtos) {
            DeliveryTm deliveryTm = new DeliveryTm(deliveryDto.getDeliveryId(), deliveryDto.getDriverId(), deliveryDto.getLocation(), deliveryDto.getDelivery_fee());
            deliveryTms.add(deliveryTm);
        }

        tblDelivery.setItems(deliveryTms);
    }


}
