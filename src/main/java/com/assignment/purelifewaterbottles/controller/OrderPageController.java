package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.dto.*;
import com.assignment.purelifewaterbottles.dto.tm.OrderTm;
import com.assignment.purelifewaterbottles.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderPageController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnAddDelivery;

    @FXML
    private Button btnAddItem;

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
    private Label orderDate;

    @FXML
    private TableView<OrderTm> tblOrders;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemQty;

    OrderModel orderModel = new OrderModel();

    @FXML
    void GoToDeliveryPageAction(ActionEvent event) {
        //navigateTo("/view/DeliveryPage.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddDeliveryPage.fxml"));
            Parent root = loader.load();
            AddDeliveryController deliveryPageController = loader.getController();
            deliveryPageController.setOrderPageController(this);

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
    void GoToHomePageAction(ActionEvent event) {
        navigateTo("/view/HomePage.fxml");
    }

    @FXML
    void GoToPaymentPageAction(ActionEvent event) {
        navigateTo("/view/PaymentPage.fxml");
    }

    @FXML
    void deleteButtonAction(ActionEvent event) throws SQLException {
        String orderId = lblOrdId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = orderModel.deleteOrder(orderId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }
    }

    OrderDetailModel orderDetailModel = new OrderDetailModel();

    @FXML
    void saveButtonAction(ActionEvent event) throws SQLException {
        String orderId = lblOrdId.getText();
        String customerId = lblCustomerId.getText();
        String deliveryId = lblDeliveryId.getText();
        String itemId = lblItemId.getText();
        String localDate = orderDate.getText();
        String qty = txtItemQty.getText();
        int itemQty = Integer.parseInt(qty);
        String description = txtDescription.getText();

        txtItemQty.setStyle(txtItemQty.getStyle() + ";-fx-border-color: #7367F0;");
        txtDescription.setStyle(txtDescription.getStyle() + ";-fx-border-color: #7367F0;");

        String itemQtyPattern = "^\\d+$";

        boolean isValidItemQty = qty.matches(itemQtyPattern);

        if (!isValidItemQty) {
            System.out.println(txtItemQty.getStyle());
            txtItemQty.setStyle(txtItemQty.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid Quantity.............");
        }

        if (isValidItemQty) {
            OrderDto orderDto = new OrderDto(orderId, customerId, deliveryId, localDate, description);
            OrderDetailDto orderDetailDto = new OrderDetailDto(orderId, itemId, itemQty);

            boolean isSavedO = orderModel.saveOrder(orderDto);
            boolean isSavedOD = orderDetailModel.saveOrder(orderDetailDto);
            if (isSavedO && isSavedOD ) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Order saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save the order...!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Order ID is invalid!").show();
        }
    }

    @FXML
    void tblOnClickedAction(MouseEvent event) {
        OrderTm orderTm = tblOrders.getSelectionModel().getSelectedItem();
        if (orderTm != null) {

            lblOrdId.setText(orderTm.getOrderId());
            lblCustomerId.setText(orderTm.getCustomerId());
            lblDeliveryId.setText(orderTm.getDeliveryId());
            lblItemId.setText(orderTm.getItemId());
            lblItemId.setText(orderTm.getItemId());
            txtItemQty.setText(String.valueOf(orderTm.getItem_qty()));
            orderDate.setText(orderTm.getOrderDate());
            txtDescription.setText(orderTm.getDescription());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void updateButtonAction(ActionEvent event) throws SQLException {
        String orderId = lblOrdId.getText();
        String customerId = lblCustomerId.getText();
        String deliveryId = lblDeliveryId.getText();
        String itemId = lblItemId.getText();
        String localDate = orderDate.getText();
        String qty = txtItemQty.getText();
        int itemQty = Integer.parseInt(qty);
        String description = txtDescription.getText();

        txtItemQty.setStyle(txtItemQty.getStyle() + ";-fx-border-color: #7367F0;");
        txtDescription.setStyle(txtDescription.getStyle() + ";-fx-border-color: #7367F0;");

        String itemQtyPattern = "^\\d+$";

        boolean isValidItemQty = qty.matches(itemQtyPattern);

        if (!isValidItemQty) {
            System.out.println(txtItemQty.getStyle());
            txtItemQty.setStyle(txtItemQty.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid Quantity.............");
        }

        if (isValidItemQty) {
            OrderDto orderDto = new OrderDto(orderId, customerId, deliveryId, localDate, description);
            OrderDetailDto orderDetailDto = new OrderDetailDto(orderId, itemId, itemQty);

            boolean isUpdateO = orderModel.updateOrder(orderDto);
            boolean isUpdateOD = orderDetailModel.updateOrder(orderDetailDto);

            if (isUpdateO && isUpdateOD) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer updated...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
            }

        }
    }

    public void setCustomerId(String customerId) {
        lblCustomerId.setText(customerId);
    }

    public void setDeliveryId(String deliveryId) {
        lblDeliveryId.setText(deliveryId);
    }

    public void setItemId(String itemId) {
        lblItemId.setText(itemId);
    }


    @FXML
    void addCustomerAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddCustomer.fxml"));
            Parent root = loader.load();
            AddCustomerController addCustomerController = loader.getController();
            addCustomerController.setOrderPageController(this);

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
    void addDeliveryAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeliveryPage.fxml"));
            Parent root = loader.load();
            AddDeliveryController deliveryPageController = loader.getController();
            deliveryPageController.setOrderPageController(this);

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
    void addItemAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddItem.fxml"));
            Parent root = loader.load();
            AddItemController addItemController = loader.getController();
            addItemController.setOrderPageController(this);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTableData() throws SQLException {
        ArrayList<OrderAndDetailDto> orderAndDetailDtos = orderModel.getAllOrders();

        ObservableList<OrderTm> orderTms = FXCollections.observableArrayList();

        for (OrderAndDetailDto orderDto : orderAndDetailDtos) {
            OrderTm orderTm = new OrderTm(orderDto.getOrderId(), orderDto.getCustomerId(), orderDto.getDeliveryId(), orderDto.getItemId(), orderDto.getItem_qty(), orderDto.getOrderDate(), orderDto.getDescription());
            orderTms.add(orderTm);
        }

        tblOrders.setItems(orderTms);
    }

    private void refreshPage() throws SQLException {
        loadNextOrderId();
        loadTableData();

        orderDate.setText(LocalDate.now().toString());

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        lblCustomerId.setText("");
        lblDeliveryId.setText("");
        lblItemId.setText("");

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
