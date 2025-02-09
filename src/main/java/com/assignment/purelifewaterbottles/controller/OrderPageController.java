package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.bo.custom.impl.OrderBOImpl;
import com.assignment.purelifewaterbottles.dao.custom.impl.ItemDAOImpl;
import com.assignment.purelifewaterbottles.dao.custom.impl.OrderDetailDAOImpl;
import com.assignment.purelifewaterbottles.dto.OrderAndDetailDto;
import com.assignment.purelifewaterbottles.dto.OrderDetailDto;
import com.assignment.purelifewaterbottles.dto.OrderDto;
import com.assignment.purelifewaterbottles.view.tdm.OrderTm;
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

    @FXML
    void GoToHomePageAction(ActionEvent event) {
        navigateTo("/view/HomePage.fxml");
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    private boolean isFromSave = false;

    @FXML
    void GoToPaymentPageAction(ActionEvent event) {
        isFromSave = false;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PaymentPage.fxml"));
            Parent root = loader.load();
            PaymentPageController paymentPageController = loader.getController();
            paymentPageController.setOrderId(lblOrdId.getText());
            paymentPageController.setOrderPageController(this);

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
    void deleteButtonAction(ActionEvent event) throws SQLException {
        String orderId = lblOrdId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = orderBO.delete(orderId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Order deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Order...!").show();
            }
        }
    }

    OrderBOImpl orderBO = new OrderBOImpl();
    OrderDetailDAOImpl orderDetailModel = new OrderDetailDAOImpl();

    @FXML
    void saveButtonAction(ActionEvent event) throws SQLException {
        try {
            String currentOrderId = lblOrdId.getText().trim();
            boolean isSavedSuccessfully = saveData();

            if (isSavedSuccessfully) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order saved successfully!", ButtonType.OK);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    isFromSave = true;
                    openPaymentPage(currentOrderId);
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred while saving the order").show();
            e.printStackTrace();
        }
    }

    private void openPaymentPage(String orderId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PaymentPage.fxml"));
            Parent root = loader.load();
            PaymentPageController paymentPageController = loader.getController();
            paymentPageController.setOrderId(orderId);
            paymentPageController.setOrderPageController(this);

            if (isFromSave) {
                paymentPageController.enableFinishButton();
                lblCustomerId.setText("");
                lblDeliveryId.setText("");
            } else {
                paymentPageController.disableFinishButton();
                lblCustomerId.setText("");
                lblDeliveryId.setText("");
            }

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean saveData() throws SQLException {
        String orderId = lblOrdId.getText().trim();
        String customerId = lblCustomerId.getText().trim();
        String deliveryId = lblDeliveryId.getText().trim();
        String itemId = lblItemId.getText().trim();
        String localDate = orderDate.getText().trim();
        String qty = txtItemQty.getText().trim();
        String description = txtDescription.getText().trim();

        String itemQtyPattern = "^\\d+$";
        boolean isValid = true;

        if (orderId.isEmpty() || customerId.isEmpty() || deliveryId.isEmpty() || itemId.isEmpty() || localDate.isEmpty() || qty.isEmpty() || description.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required. Please fill in all fields.").show();
            isValid = false;
        }

        boolean isValidItemQty = qty.matches(itemQtyPattern);
        if (!isValidItemQty) {
            txtItemQty.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity: Please enter a valid number").show();
            isValid = false;
        } else {
            txtItemQty.setStyle("-fx-border-color: #2e86de;");
        }

        if (description.length() > 255) {
            txtDescription.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Description too long: Please enter a description with fewer than 255 characters.").show();
            isValid = false;
        } else {
            txtDescription.setStyle("-fx-border-color: #2e86de;");
        }

        if (!isValid) {
            return false;
        }

        int itemQty = Integer.parseInt(qty);

        OrderDto orderDto = new OrderDto(orderId, customerId, deliveryId, localDate, description);
        OrderDetailDto orderDetailDto = new OrderDetailDto(orderId, itemId, itemQty);

        boolean isSavedOrder = orderBO.save(orderDto);
        boolean isSavedOrderDetail = orderDetailModel.save(orderDetailDto);

        if (isSavedOrder && isSavedOrderDetail) {
            try {
                boolean isStockDeducted = itemModel.deductStock(itemId, itemQty);
                if(isStockDeducted) {
                    new Alert(Alert.AlertType.INFORMATION, "Saved successfully").show();
                    refreshPage();
                    return true;
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to deduct stock. Please try again.").show();
                    return false;
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error while deducting stock: " + e.getMessage()).show();
                e.printStackTrace();
                return false;
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save the order. Please try again.").show();
            return false;
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

    ItemDAOImpl itemModel = new ItemDAOImpl();

    @FXML
    void updateButtonAction(ActionEvent event) throws SQLException {
        String orderId = lblOrdId.getText().trim();
        String customerId = lblCustomerId.getText().trim();
        String deliveryId = lblDeliveryId.getText().trim();
        String itemId = lblItemId.getText().trim();
        String localDate = orderDate.getText().trim();
        String qty = txtItemQty.getText().trim();
        String description = txtDescription.getText().trim();

        String itemQtyPattern = "^\\d+$";

        boolean isValid = true;

        // Check if all fields are filled
        if (orderId.isEmpty() || customerId.isEmpty() || deliveryId.isEmpty() || itemId.isEmpty() || localDate.isEmpty() || qty.isEmpty() || description.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required. Please fill in all fields.").show();
            isValid = false;
        }

        String orderQty = qty;
        if (!orderQty.equals(qty)){
            new Alert(Alert.AlertType.ERROR, "Cannot update Qty").show();
            isValid = false;
        }

        // Validate item quantity input
        boolean isValidItemQty = qty.matches(itemQtyPattern);
        if (!isValidItemQty) {
            txtItemQty.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity: Please enter a valid number").show();
            isValid = false;
        } else {
            txtItemQty.setStyle("-fx-border-color: #2e86de;"); // Reset to valid style if correct
        }

        if (isValid) {
            // Check if the user is attempting to update the item quantity
            String originalQty = txtItemQty.getText().trim();  // Original value before any changes
            if (!originalQty.equals(qty)) {
                new Alert(Alert.AlertType.ERROR, "Cannot update item quantity. This field is locked.").show();
                return; // Prevent further processing if the item quantity is modified
            }

            // Proceed with the update if item quantity is not changed
            OrderDto orderDto = new OrderDto(orderId, customerId, deliveryId, localDate, description);
            OrderDetailDto orderDetailDto = new OrderDetailDto(orderId, itemId, Integer.parseInt(qty)); // No item quantity update

            // Update order and order details
            boolean isUpdateO = orderBO.update(orderDto);
            boolean isUpdateOD = orderDetailModel.update(orderDetailDto);

            if (isUpdateO && isUpdateOD) {
                try {
                    // Deduct stock based on the original item quantity
                    boolean isStockDeducted = itemModel.deductStock(itemId, Integer.parseInt(qty));
                    if (isStockDeducted) {
                        refreshPage();
                        new Alert(Alert.AlertType.INFORMATION, "Order updated successfully and stock deducted!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to deduct stock. Please try again.").show();
                    }
                } catch (SQLException e) {
                    // Handle the exception and display a meaningful error message
                    new Alert(Alert.AlertType.ERROR, "Error while deducting stock: " + e.getMessage()).show();
                    e.printStackTrace();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update the order. Please try again.").show();
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
        ArrayList<OrderAndDetailDto> orderAndDetailDtos = orderBO.getAllOrders();

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
        String nextOrderId = orderBO.getNextID();
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
            new Alert(Alert.AlertType.ERROR, "Fail to load Data!").show();
        }
    }
}
