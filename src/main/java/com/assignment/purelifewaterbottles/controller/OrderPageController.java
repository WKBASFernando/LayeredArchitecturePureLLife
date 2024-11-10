package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.dto.CustomerDto;
import com.assignment.purelifewaterbottles.dto.OrderDto;
import com.assignment.purelifewaterbottles.dto.tm.OrderTm;
import com.assignment.purelifewaterbottles.model.CustomerModel;
import com.assignment.purelifewaterbottles.model.OrderModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderPageController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbCusId;

    @FXML
    private ComboBox<String> cmbDelId;

    @FXML
    private ComboBox<String> cmbItemId1;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colDeliveryId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colOrderDate;


    @FXML
    private AnchorPane content;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblDeliveryId;

    @FXML
    private Label lblItemId;

    @FXML
    private Label lblOrdId;

    @FXML
    private TableView<OrderTm> tblOrders;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemQty;

    OrderModel orderModel = new OrderModel();

    @FXML
    void GoToDeliveryPageAction(ActionEvent event) {
        navigateTo("/view/DeliveryPage.fxml");
    }

    @FXML
    void GoToHomePageAction(ActionEvent event) {
        navigateTo("/view/HomePage.fxml");
    }

    @FXML
    void GoToPaymentPageAction(ActionEvent event) {
        navigateTo("/view/PaymentPage.fxml");
    }

    @FXML
    void deleteButtonAction(ActionEvent event) {

    }

    @FXML
    void saveButtonAction(ActionEvent event) {

    }

    @FXML
    void tblOnClickedAction(MouseEvent event) {

    }

    @FXML
    void updateButtonAction(ActionEvent event) {

    }

    CustomerDto customerDto = new CustomerDto();

    @FXML
    void cmbCusIdOnAction(ActionEvent event) throws SQLException {
        String selectedCustomerId = cmbCusId.getSelectionModel().getSelectedItem();
        OrderDto orderDto = orderModel.findByCustomerId(selectedCustomerId);

        // If customer found (orderDto not null)
        if (orderDto != null) {

            // FIll customer related labels
            lblCustomerId.setText(customerDto.getName());
        }
    }

    @FXML
    void cmbDelIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbItemIdOnAction(ActionEvent event) {

    }

    private void loadTableData() throws SQLException {
        ArrayList<OrderDto> orderDtos = orderModel.getAllOrders();

        ObservableList<OrderTm> orderTms = FXCollections.observableArrayList();

        for (OrderDto orderDto : orderDtos) {
            OrderTm orderTm = new OrderTm(orderDto.getOrderId(), orderDto.getCustomerId(), orderDto.getDeliveryId(), orderDto.getItemId(), orderDto.getItem_qty(), orderDto.getOrderDate(), orderDto.getDescription());
            orderTms.add(orderTm);
        }

        tblOrders.setItems(orderTms);
    }

    private void refreshPage() throws SQLException {
        loadNextOrderId();
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtItemQty.setText("");
        txtDescription.setText("");
    }

    public void loadNextOrderId() throws SQLException {
        String nextOrderId = orderModel.getNextOrderId();
        lblOrdId.setText(nextOrderId);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colDeliveryId.setCellValueFactory(new PropertyValueFactory<>("deliveryId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("item_qty"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));


        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }
    }
}
