package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.model.PaymentDto;
import com.assignment.purelifewaterbottles.view.tdm.PaymentTm;
import com.assignment.purelifewaterbottles.dao.custom.impl.PaymentDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentPageController implements Initializable {

    @FXML
    private Button btnFinish;

    @FXML
    private ComboBox<String> cmbPayMethod;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPaymentMethod;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblPaymentId;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @Setter
    private OrderPageController orderPageController;

    @FXML
    void FinishOnAction(ActionEvent event) throws Exception {
        String paymentId = lblPaymentId.getText().trim();
        String orderId = lblOrderId.getText().trim();
        String paymentMethod = cmbPayMethod.getValue();

        cmbPayMethod.setStyle("-fx-border-color: #2e86de;");

        boolean isValid = true;

        if (paymentMethod == null || paymentMethod.isEmpty()) {
            cmbPayMethod.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Please select a payment method.").show();
            isValid = false;
        }

        if (isValid) {
            PaymentDto paymentDto = new PaymentDto(paymentId, orderId, paymentMethod);

            boolean isSaved = paymentModel.save(paymentDto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Payment saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Payment...!").show();
            }
        }
    }


    @FXML
    void tblPaymentOnAction(MouseEvent event) {

    }

    public void setOrderId(String orderId) {
        lblOrderId.setText(orderId);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("pay_method"));

        ObservableList<String> paymentMethods = FXCollections.observableArrayList("Cash", "Bank Card", "Bank Transfer");
        cmbPayMethod.setItems(paymentMethods);


        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data!").show();
        }
    }

    private void refreshPage() throws Exception {
        loadTableData();
        loadNextPaymentId();
    }

    PaymentDAOImpl paymentModel = new PaymentDAOImpl();

    private void loadTableData() throws Exception {
        ArrayList<PaymentDto> paymentDtos = paymentModel.getAll();

        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();

        for (PaymentDto paymentDto : paymentDtos) {
            PaymentTm paymentTm = new PaymentTm(paymentDto.getPaymentId(), paymentDto.getOrderId(), paymentDto.getPay_method());
            paymentTms.add(paymentTm);
        }

        tblPayment.setItems(paymentTms);
    }

    private void loadNextPaymentId() throws Exception {
        String nextPaymentId = paymentModel.getNextID();
        lblPaymentId.setText(nextPaymentId);
    }

    public void enableFinishButton() {
        btnFinish.setDisable(false);
    }

    public void disableFinishButton() {
        btnFinish.setDisable(true);
    }
}
