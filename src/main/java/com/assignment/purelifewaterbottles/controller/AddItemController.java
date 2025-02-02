package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.model.ItemDto;
import com.assignment.purelifewaterbottles.view.tdm.ItemTm;
import com.assignment.purelifewaterbottles.dao.custom.impl.ItemDAOImpl;
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

public class AddItemController implements Initializable {

    @FXML
    private Button btnFinish;

    @FXML
    private TableColumn<?, ?> colCapacity;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private Label lblItemId;

    @FXML
    private Label lblName;

    @FXML
    private TableView<ItemTm> tblItem;

    @Setter
    private OrderPageController orderPageController;

    @FXML
    void FinishOnAction(ActionEvent event) {
        ItemTm selectedItem = tblItem.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            new Alert(Alert.AlertType.WARNING, "Please select item..!").show();
        } else {
            orderPageController.setItemId(selectedItem.getItemId());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void itemTableOnAction(MouseEvent event) {
        ItemTm itemTm = tblItem.getSelectionModel().getSelectedItem();
        if (itemTm != null) {
            lblItemId.setText(itemTm.getItemId());
            lblName.setText(itemTm.getName());

            btnFinish.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data").show();
        }
    }

    private void refreshPage() throws Exception {
        loadTableData();

        lblItemId.setText("");
        lblName.setText("");

        btnFinish.setDisable(true);
    }

    ItemDAOImpl itemModel = new ItemDAOImpl();

    private void loadTableData() throws Exception {
        ArrayList<ItemDto> itemDtos = itemModel.getAll();

        ObservableList<ItemTm> itemTms = FXCollections.observableArrayList();

        for (ItemDto itemDto : itemDtos) {
            ItemTm itemTm = new ItemTm(itemDto.getItemId(), itemDto.getName(), itemDto.getCapacity(), itemDto.getPrice(), itemDto.getItemQty());
            itemTms.add(itemTm);
        }

        tblItem.setItems(itemTms);
    }
}
