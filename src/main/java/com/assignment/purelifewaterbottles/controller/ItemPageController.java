package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.dto.ItemDetailDto;
import com.assignment.purelifewaterbottles.dto.ItemDto;
import com.assignment.purelifewaterbottles.dto.ItemDtoOriginal;
import com.assignment.purelifewaterbottles.dto.tm.ItemTm;
import com.assignment.purelifewaterbottles.model.ItemDetailModel;
import com.assignment.purelifewaterbottles.model.ItemModel;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemPageController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCapacity;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private AnchorPane content;

    @FXML
    private Label lblItemId;

    @FXML
    private Label lblWarehouseId;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TextField txtCapacity;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtItemQty;

    @FXML
    private TextField txtPrice;

    @FXML
    void HomeOnAction(ActionEvent event) {
        navigateTo("/view/HomePage.fxml");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        navigateTo("/view/SupplierPage.fxml");
    }

    @FXML
    void deleteButtonAction(ActionEvent event) throws Exception {
        String itemId = lblItemId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = itemModel.deleteItem(itemId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    ItemModel itemModel = new ItemModel();
    ItemDetailModel itemDetailModel = new ItemDetailModel();

    @FXML
    void saveButtonAction(ActionEvent event) throws Exception {
        String itemId = lblItemId.getText().trim();
        String warehouseId = lblWarehouseId.getText().trim();
        String itemName = txtItemName.getText().trim();
        String capacity = txtCapacity.getText().trim();
        String priceText = txtPrice.getText().trim();
        String qtyText = txtItemQty.getText().trim();

        txtItemName.setStyle("-fx-border-color: #2e86de;");
        txtCapacity.setStyle("-fx-border-color: #2e86de;");
        txtPrice.setStyle("-fx-border-color: #2e86de;");
        txtItemQty.setStyle("-fx-border-color: #2e86de;");

        String namePattern = "^[A-Za-z ]+$";
        String pricePattern = "\\d+(\\.\\d+)?";
        String qtyPattern = "\\d+";

        boolean isValid = true;

        if (itemName.isEmpty() || capacity.isEmpty() || priceText.isEmpty() || qtyText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required. Please fill in all the fields.").show();
            isValid = false;

            if (itemName.isEmpty()) txtItemName.setStyle("-fx-border-color: red;");
            if (capacity.isEmpty()) txtCapacity.setStyle("-fx-border-color: red;");
            if (priceText.isEmpty()) txtPrice.setStyle("-fx-border-color: red;");
            if (qtyText.isEmpty()) txtItemQty.setStyle("-fx-border-color: red;");
        }

        if (!itemName.matches(namePattern)) {
            txtItemName.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid item name: Only alphabetic characters and spaces are allowed.").show();
            isValid = false;
        }

        if (priceText.isEmpty() || !priceText.matches(pricePattern)) {
            txtPrice.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid price: Please enter a valid positive number.").show();
            isValid = false;
        }

        if (qtyText.isEmpty() || !qtyText.matches(qtyPattern)) {
            txtItemQty.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid quantity: Please enter a valid positive integer.").show();
            isValid = false;
        }

        if (isValid) {
            double price = Double.parseDouble(priceText);
            int qty = Integer.parseInt(qtyText);

            ItemDtoOriginal itemDtoOriginal = new ItemDtoOriginal(itemId, itemName, capacity, price);
            ItemDetailDto itemDetailDto = new ItemDetailDto(itemId, warehouseId, qty);

            boolean isSavedI = itemModel.saveItem(itemDtoOriginal);
            boolean isSavedID = itemDetailModel.saveItem(itemDetailDto);

            if (isSavedI && isSavedID) {
                refreshPage();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item saved successfully!");
                alert.getDialogPane().setStyle("-fx-background-color: #2e86de;");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save item!");
                alert.getDialogPane().setStyle("-fx-background-color: #2e86de;");
                alert.show();
            }
        }
    }

    @FXML
    void tblItemOnAction(MouseEvent event) {
        ItemTm itemTm = tblItem.getSelectionModel().getSelectedItem();
        if (itemTm != null) {
            lblItemId.setText(itemTm.getItemId());
            txtItemName.setText(itemTm.getName());
            txtCapacity.setText(String.valueOf(itemTm.getCapacity()));
            txtPrice.setText(String.valueOf(itemTm.getPrice()));
            txtItemQty.setText(String.valueOf(itemTm.getItemQty()));

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void updateButtonAction(ActionEvent event) throws Exception {
        String itemId = lblItemId.getText().trim();
        String warehouseId = lblWarehouseId.getText().trim();
        String itemName = txtItemName.getText().trim();
        String capacity = txtCapacity.getText().trim();
        String priceText = txtPrice.getText().trim();
        String qtyText = txtItemQty.getText().trim();

        // Initial style reset
        txtItemName.setStyle("-fx-border-color: #2e86de;");
        txtCapacity.setStyle("-fx-border-color: #2e86de;");
        txtPrice.setStyle("-fx-border-color: #2e86de;");
        txtItemQty.setStyle("-fx-border-color: #2e86de;");

        String namePattern = "^[A-Za-z ]+$";
        String pricePattern = "\\d+(\\.\\d+)?";
        String qtyPattern = "\\d+";

        boolean isValid = true;

        if (itemName.isEmpty() || capacity.isEmpty() || priceText.isEmpty() || qtyText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required. Please fill in all the fields.").show();
            isValid = false;

            if (itemName.isEmpty()) txtItemName.setStyle("-fx-border-color: red;");
            if (capacity.isEmpty()) txtCapacity.setStyle("-fx-border-color: red;");
            if (priceText.isEmpty()) txtPrice.setStyle("-fx-border-color: red;");
            if (qtyText.isEmpty()) txtItemQty.setStyle("-fx-border-color: red;");
        }

        if (!itemName.matches(namePattern)) {
            txtItemName.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid item name: Only alphabetic characters and spaces are allowed.").show();
            isValid = false;
        }

        if (priceText.isEmpty() || !priceText.matches(pricePattern)) {
            txtPrice.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid price: Please enter a valid positive number.").show();
            isValid = false;
        }

        if (qtyText.isEmpty() || !qtyText.matches(qtyPattern)) {
            txtItemQty.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid quantity: Please enter a valid positive integer.").show();
            isValid = false;
        }

        if (isValid) {
            double price = Double.parseDouble(priceText);
            int qty = Integer.parseInt(qtyText);

            ItemDtoOriginal itemDtoOriginal = new ItemDtoOriginal(itemId, itemName, capacity, price);
            ItemDetailDto itemDetailDto = new ItemDetailDto(itemId, warehouseId, qty);

            boolean isUpdatedI = itemModel.updateItem(itemDtoOriginal);
            boolean isUpdatedID = itemDetailModel.updateItem(itemDetailDto);

            if (isUpdatedI && isUpdatedID) {
                refreshPage();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item updated successfully!");
                alert.getDialogPane().setStyle("-fx-background-color: #2e86de;");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to update item!");
                alert.getDialogPane().setStyle("-fx-background-color: #2e86de;");
                alert.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data").show();
        }
    }

    private void refreshPage() throws Exception {
        loadTableData();
        loadNextItemId();

        txtItemName.setText("");
        txtItemQty.setText("");
        txtPrice.setText("");
        txtCapacity.setText("");

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void loadNextItemId() throws Exception {
        String nextItemId = itemModel.getNextItemId();
        lblItemId.setText(nextItemId);
    }

    private void loadTableData() throws Exception {
        ArrayList<ItemDto> itemDtos = itemModel.getAllItems();

        ObservableList<ItemTm> itemTms = FXCollections.observableArrayList();

        for (ItemDto itemDto : itemDtos) {
            ItemTm itemTm = new ItemTm(itemDto.getItemId(), itemDto.getName(), itemDto.getCapacity(), itemDto.getPrice(), itemDto.getItemQty());
            itemTms.add(itemTm);
        }

        tblItem.setItems(itemTms);
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

