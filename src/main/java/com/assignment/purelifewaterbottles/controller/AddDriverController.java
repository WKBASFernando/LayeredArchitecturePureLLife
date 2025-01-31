package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.dto.DriverDto;
import com.assignment.purelifewaterbottles.dto.tm.DriverTm;
import com.assignment.purelifewaterbottles.dao.custom.impl.DriverDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddDriverController implements Initializable {

    @FXML
    private Button btnFinish;

    @FXML
    private TableColumn<?, ?> colDriverFee;

    @FXML
    private TableColumn<?, ?> colDriverId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNo;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private Label lblDriverId;

    @FXML
    private Label lblName;

    @FXML
    private TableView<DriverTm> tblDriiver;

    @Setter
    private AddDeliveryController addDeliveryController;

    @FXML
    void FinishOnAction(ActionEvent event) {
        DriverTm selectedItem = tblDriiver.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a driver..!").show();
        } else {
            addDeliveryController.setDriverId(selectedItem.getDriverId());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void tblClickedAction(MouseEvent event) {
        DriverTm driverTm = tblDriiver.getSelectionModel().getSelectedItem();
        if (driverTm != null) {
            lblDriverId.setText(driverTm.getDriverId());
            lblName.setText(driverTm.getName());

            btnFinish.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colDriverId.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colDriverFee.setCellValueFactory(new PropertyValueFactory<>("driver_fee"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data!");
        }
    }

    private void refreshPage() throws Exception {
        loadTableData();

        lblDriverId.setText("");
        lblName.setText("");

        btnFinish.setDisable(true);
    }

    DriverDAOImpl driverModel = new DriverDAOImpl();

    private void loadTableData() throws Exception {
        ArrayList<DriverDto> driverDtos = driverModel.getAll();

        ObservableList<DriverTm> driverTms = FXCollections.observableArrayList();

        for (DriverDto driverDto : driverDtos) {
            DriverTm driverTm = new DriverTm(driverDto.getDriverId(), driverDto.getVehicleId(), driverDto.getName(), driverDto.getPhoneNo(), driverDto.getDriver_fee());
            driverTms.add(driverTm);
        }

        tblDriiver.setItems(driverTms);
    }
}
