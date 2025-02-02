package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.model.CustomerDto;
import com.assignment.purelifewaterbottles.view.tdm.CustomerTm;
import com.assignment.purelifewaterbottles.dao.custom.impl.CustomerDAOImpl;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    @FXML
    private Button btnFinish;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNo;

    @FXML
    private Label lblCusId;

    @FXML
    private Label lblCusName;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @Setter
    private OrderPageController orderPageController;

    @FXML
    void FinishOnAction(ActionEvent event) {
        CustomerTm selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            new Alert(Alert.AlertType.WARNING, "Please select customer..!").show();
        } else {
            orderPageController.setCustomerId(selectedItem.getCustomerId());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void tblOnAction(MouseEvent event) {
        CustomerTm customerTm = tblCustomer.getSelectionModel().getSelectedItem();
        if (customerTm != null) {
            lblCusId.setText(customerTm.getCustomerId());
            lblCusName.setText(customerTm.getName());

            btnFinish.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }
    }
    private void refreshPage() throws Exception {
        loadTableData();

        btnFinish.setDisable(true);

        lblCusName.setText("");
        lblCusId.setText("");
    }

    CustomerDAOImpl customerModel = new CustomerDAOImpl();

    private void loadTableData() throws SQLException {
        ArrayList<CustomerDto> customerDTOS = customerModel.getAll();

        ObservableList<CustomerTm> customerTms = FXCollections.observableArrayList();

        for (CustomerDto customerDto : customerDTOS) {
            CustomerTm customerTM = new CustomerTm(customerDto.getCustomerId(), customerDto.getName(), customerDto.getAddress(), customerDto.getPhone_no(), customerDto.getEmail());
            customerTms.add(customerTM);
        }

        tblCustomer.setItems(customerTms);
    }

}
