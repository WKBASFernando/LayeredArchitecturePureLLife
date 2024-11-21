package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.dto.SupplierAndDetailDto;
import com.assignment.purelifewaterbottles.dto.SupplierDetailDto;
import com.assignment.purelifewaterbottles.dto.SupplierDto;
import com.assignment.purelifewaterbottles.dto.tm.SupplierTm;
import com.assignment.purelifewaterbottles.model.SupplierDetailModel;
import com.assignment.purelifewaterbottles.model.SupplierModel;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierPageController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colItem;

    @FXML
    private TableColumn<?, ?> colPricePerUnit;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private AnchorPane content;

    @FXML
    private Label lblSupplierId;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblWarehouseId;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtPricePerUnit;

    @FXML
    private TextField txtQty;

    @FXML
    void HomeOnAction(ActionEvent event) {
        navigateTo("/view/HomePage.fxml");
    }

    @FXML
    void btnItemOnAction(ActionEvent event) {
         navigateTo("/view/ItemPage.fxml");
    }

    @FXML
    void totalButtonAction(ActionEvent event) {
        boolean isValid = true;

        if (txtQty.getText().trim().isEmpty() || txtPricePerUnit.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required. Please fill in all fields.").show();
            isValid = false;
        }

        String qtyText = txtQty.getText().trim();
        String qtyPattern = "^\\d+$";
        if (!qtyText.matches(qtyPattern) || Integer.parseInt(qtyText) <= 0) {
            txtQty.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity: Must be a positive integer").show();
            isValid = false;
        } else {
            txtQty.setStyle("-fx-border-color: #2e86de;");
        }

        String pricePerUnitText = txtPricePerUnit.getText().trim();
        try {
            double pricePerUnit = Double.parseDouble(pricePerUnitText);
            if (pricePerUnit <= 0) {
                txtPricePerUnit.setStyle("-fx-border-color: red;");
                new Alert(Alert.AlertType.ERROR, "Invalid Price: Must be a positive number").show();
                isValid = false;
            } else {
                txtPricePerUnit.setStyle("-fx-border-color: #2e86de;");
            }
        } catch (NumberFormatException e) {
            txtPricePerUnit.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Price: Must be a number").show();
            isValid = false;
        }

        if (isValid) {
            int qty = Integer.parseInt(qtyText);
            double pricePerUnit = Double.parseDouble(pricePerUnitText);
            double total = (double) qty * pricePerUnit;

            lblTotal.setText(String.format("%.2f", total));
        }
    }


    @FXML
    void deleteButtonAction(ActionEvent event) throws Exception {
        String supplierId = lblSupplierId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = supplierModel.deleteSupplier(supplierId);
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

    SupplierModel supplierModel = new SupplierModel();
    SupplierDetailModel supplierDetailModel = new SupplierDetailModel();

    @FXML
    void saveButtonAction(ActionEvent event) throws Exception {
        String supplierId = lblSupplierId.getText().trim();
        String itemName = txtItemName.getText().trim();
        String pricePerUnitText = txtPricePerUnit.getText().trim();
        String qtyText = txtQty.getText().trim();
        String totalText = lblTotal.getText().trim();
        String warehouseId = lblWarehouseId.getText().trim();

        boolean isValid = true;

        String itemNamePattern = "^[a-zA-Z\\s]+$";
        if (!itemName.matches(itemNamePattern)) {
            txtItemName.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Item Name: Only letters and spaces allowed").show();
            isValid = false;
        } else {
            txtItemName.setStyle("-fx-border-color: #2e86de;");
        }

        try {
            double pricePerUnit = Double.parseDouble(pricePerUnitText);
            if (pricePerUnit <= 0) {
                txtPricePerUnit.setStyle("-fx-border-color: red;");
                new Alert(Alert.AlertType.ERROR, "Invalid Price: Must be a positive number").show();
                isValid = false;
            } else {
                txtPricePerUnit.setStyle("-fx-border-color: #2e86de;");
            }
        } catch (NumberFormatException e) {
            txtPricePerUnit.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Price: Must be a number").show();
            isValid = false;
        }

        String qtyPattern = "^\\d+$";
        if (!qtyText.matches(qtyPattern) || Integer.parseInt(qtyText) <= 0) {
            txtQty.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity: Must be a positive integer").show();
            isValid = false;
        } else {
            txtQty.setStyle("-fx-border-color: #2e86de;");
        }

        if (isValid) {
            int qty = Integer.parseInt(qtyText);
            double pricePerUnit = Double.parseDouble(pricePerUnitText);
            double total = Double.parseDouble(totalText);

            SupplierDto supplierDto = new SupplierDto(supplierId, itemName, pricePerUnit);
            SupplierDetailDto supplierDetailDto = new SupplierDetailDto(supplierId, warehouseId, qty, total);

            boolean isSavedS = supplierModel.saveSupplier(supplierDto);
            boolean isSavedSD = supplierDetailModel.saveSupplier(supplierDetailDto);

            if (isSavedS && isSavedSD) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Supplier Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Save Supplier").show();
            }
        }
    }

    @FXML
    void tblSupplierOnAction(MouseEvent event) {
        SupplierTm supplierTm = tblSupplier.getSelectionModel().getSelectedItem();
        if (supplierTm != null) {
            lblSupplierId.setText(supplierTm.getSupplierId());
            txtItemName.setText(supplierTm.getSupplingItem());
            txtPricePerUnit.setText(String.valueOf(supplierTm.getPricePerOneItem()));
            txtQty.setText(String.valueOf(supplierTm.getS_qty()));
            lblTotal.setText(String.valueOf(supplierTm.getTotalPrice()));

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            btnSave.setDisable(true);
        }
    }

    @FXML
    void updateButtonAction(ActionEvent event) throws Exception {
        String supplierId = lblSupplierId.getText().trim();
        String itemName = txtItemName.getText().trim();
        String pricePerUnitText = txtPricePerUnit.getText().trim();
        String qtyText = txtQty.getText().trim();
        String totalText = lblTotal.getText().trim();
        String warehouseId = lblWarehouseId.getText().trim();

        boolean isValid = true;

        String itemNamePattern = "^[a-zA-Z\\s]+$";
        if (!itemName.matches(itemNamePattern)) {
            txtItemName.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Item Name: Only letters and spaces allowed").show();
            isValid = false;
        } else {
            txtItemName.setStyle("-fx-border-color: #2e86de;");
        }

        try {
            double pricePerUnit = Double.parseDouble(pricePerUnitText);
            if (pricePerUnit <= 0) {
                txtPricePerUnit.setStyle("-fx-border-color: red;");
                new Alert(Alert.AlertType.ERROR, "Invalid Price: Must be a positive number").show();
                isValid = false;
            } else {
                txtPricePerUnit.setStyle("-fx-border-color: #2e86de;");
            }
        } catch (NumberFormatException e) {
            txtPricePerUnit.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Price: Must be a number").show();
            isValid = false;
        }

        String qtyPattern = "^\\d+$";
        if (!qtyText.matches(qtyPattern) || Integer.parseInt(qtyText) <= 0) {
            txtQty.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity: Must be a positive integer").show();
            isValid = false;
        } else {
            txtQty.setStyle("-fx-border-color: #2e86de;");
        }

        if (isValid) {
            int qty = Integer.parseInt(qtyText);
            double pricePerUnit = Double.parseDouble(pricePerUnitText);
            double total = Double.parseDouble(totalText);

            SupplierDto supplierDto = new SupplierDto(supplierId, itemName, pricePerUnit);
            SupplierDetailDto supplierDetailDto = new SupplierDetailDto(supplierId, warehouseId, qty, total);

            boolean isUpdatedS = supplierModel.updateSupplier(supplierDto);
            boolean isUpdatedSD = supplierDetailModel.updateSupplier(supplierDetailDto);

            if (isUpdatedS && isUpdatedSD) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Supplier Updated").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Update Supplier").show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("supplingItem"));
        colPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("pricePerOneItem"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("s_qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        try{
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data!").show();
        }
    }

    private void refreshPage() throws Exception {
        loadNextSupplierId();
        loadTableData();

        txtItemName.setText("");
        txtPricePerUnit.setText("");
        txtQty.setText("");
        lblTotal.setText("");

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void loadNextSupplierId() throws Exception {
        String nextSupplierId = supplierModel.getNextSupplierId();
        lblSupplierId.setText(nextSupplierId);
    }

    private void loadTableData() throws Exception {
        ArrayList<SupplierAndDetailDto> supplierAndDetailDtos = supplierModel.getAllSuppliers();

        ObservableList<SupplierTm> supplierTms = FXCollections.observableArrayList();

        for (SupplierAndDetailDto supplierAndDetailDto : supplierAndDetailDtos) {
            SupplierTm supplierTm = new SupplierTm(supplierAndDetailDto.getSupplierId(), supplierAndDetailDto.getSupplingItem(), supplierAndDetailDto.getPricePerOneItem(), supplierAndDetailDto.getS_qty(), supplierAndDetailDto.getTotalPrice());
            supplierTms.add(supplierTm);
        }
        tblSupplier.setItems(supplierTms);
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
