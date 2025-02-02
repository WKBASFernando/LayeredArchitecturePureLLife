package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.db.DBConnection;
import com.assignment.purelifewaterbottles.model.CustomerDto;
import com.assignment.purelifewaterbottles.view.tdm.CustomerTm;
import com.assignment.purelifewaterbottles.dao.custom.impl.CustomerDAOImpl;
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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class CustomerPageController implements Initializable {
    public TextField txtEmail;
    public TableColumn colEmail;

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

    private void refreshPage() throws SQLException {
        loadNextCustomerId();
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnEmail.setDisable(true);
        btnReport.setDisable(true);

        txtName.setText("");
        txtAddress.setText("");
        txtPhoneNo.setText("");
        txtEmail.setText("");
    }

    CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    private void loadTableData() throws SQLException {
        ArrayList<CustomerDto> customerDTOS = customerDAO.getAll();

        ObservableList<CustomerTm> customerTms = FXCollections.observableArrayList();

        for (CustomerDto customerDto : customerDTOS) {
            CustomerTm customerTM = new CustomerTm(customerDto.getCustomerId(), customerDto.getName(), customerDto.getAddress(), customerDto.getPhone_no(), customerDto.getEmail());
            customerTms.add(customerTM);
        }

        tblCustomer.setItems(customerTms);
    }

    public void loadNextCustomerId() throws SQLException {
        String nextCustomerId = customerDAO.getNextID();
        lblCusId.setText(nextCustomerId);
    }

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnEmail;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNo;

    @FXML
    private Label lblCusId;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNo;

    @FXML
    private AnchorPane content;

    @FXML
    void deleteButtonAction(ActionEvent event) throws SQLException {
        String customerId = lblCusId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = customerDAO.delete(customerId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }
    }

    @FXML
    void saveButtonAction(ActionEvent event) throws SQLException {
        String customerId = lblCusId.getText().trim();
        String name = txtName.getText().trim();
        String address = txtAddress.getText().trim();
        String phone = txtPhoneNo.getText().trim();
        String email = txtEmail.getText().trim();

        txtName.setStyle("-fx-border-color: #2e86de;");
        txtAddress.setStyle("-fx-border-color: #2e86de;");
        txtPhoneNo.setStyle("-fx-border-color: #2e86de;");
        txtEmail.setStyle("-fx-border-color: #2e86de;");

        String namePattern = "^[A-Za-z ]+$";
        String phonePattern = "^\\d+$";
        String emailPattern = "^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$";

        boolean isValid = true;

        if (!name.matches(namePattern)) {
            txtName.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid name: Only alphabetic characters and spaces are allowed.").show();
            isValid = false;
        }

        if (!phone.matches(phonePattern)) {
            txtPhoneNo.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid phone number: Please enter a valid numeric phone number.").show();
            isValid = false;
        }

        if (!email.matches(emailPattern) && !email.isEmpty()) {
            txtEmail.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid email: Please enter a valid email address.").show();
            isValid = false;
        }

        if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields except email are required. Please fill them in.").show();
            isValid = false;
        }

        if (isValid) {
            CustomerDto customerDTO = new CustomerDto(customerId, name, address, phone, email);

            boolean isSaved = customerDAO.save(customerDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save customer...!").show();
            }
        }
    }

    @FXML
    void tblOnClickedAction(MouseEvent event) {
        CustomerTm customerTm = tblCustomer.getSelectionModel().getSelectedItem();
        if (customerTm != null) {
            lblCusId.setText(customerTm.getCustomerId());
            txtName.setText(customerTm.getName());
            txtAddress.setText(customerTm.getAddress());
            txtPhoneNo.setText(customerTm.getPhone_no());
            txtEmail.setText(customerTm.getEmail());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            btnEmail.setDisable(false);
            btnReport.setDisable(false);
        }
    }

    @FXML
    void resetAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void updateButtonAction(ActionEvent event) throws SQLException {
        String customerId = lblCusId.getText().trim();
        String name = txtName.getText().trim();
        String address = txtAddress.getText().trim();
        String phone = txtPhoneNo.getText().trim();
        String email = txtEmail.getText().trim();

        txtName.setStyle("-fx-border-color: #2e86de;");
        txtAddress.setStyle("-fx-border-color: #2e86de;");
        txtPhoneNo.setStyle("-fx-border-color: #2e86de;");
        txtEmail.setStyle("-fx-border-color: #2e86de;");

        String namePattern = "^[A-Za-z ]+$";
        String phonePattern = "^\\d+$";
        String emailPattern = "^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$";

        boolean isValid = true;

        if (!name.matches(namePattern)) {
            txtName.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid name: Only alphabetic characters and spaces are allowed.").show();
            isValid = false;
        }

        if (!phone.matches(phonePattern)) {
            txtPhoneNo.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid phone number: Please enter a valid numeric phone number.").show();
            isValid = false;
        }

        if (!email.matches(emailPattern) && !email.isEmpty()) {
            txtEmail.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid email: Please enter a valid email address.").show();
            isValid = false;
        }

        if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields except email are required. Please fill them in.").show();
            isValid = false;
        }

        if (isValid) {
            CustomerDto customerDTO = new CustomerDto(customerId, name, address, phone, email);

            boolean isUpdate = customerDAO.update(customerDTO);
            if (isUpdate) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer updated...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update customer...!").show();
            }
        }
    }

    @FXML
    void SendMailAction(ActionEvent event) {
        CustomerTm selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            new Alert(Alert.AlertType.WARNING, "Please select customer..!");
            return;
        }

        try {
            // Load the mail dialog from FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SendMailView.fxml"));
            Parent load = loader.load();

            SendMailController sendMailController = loader.getController();

            String email = selectedItem.getEmail();
            sendMailController.setCustomerEmail(email);

            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Send email");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/mail_icon.png")));

            // Set window as modal
            stage.initModality(Modality.APPLICATION_MODAL);

            Window underWindow = btnUpdate.getScene().getWindow();
            stage.initOwner(underWindow);

            stage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui..!");
            e.printStackTrace();
        }
    }

    @FXML
    void generateReportAction(ActionEvent event) {
        CustomerTm customerTM = tblCustomer.getSelectionModel().getSelectedItem();

        if (customerTM == null) {
            return;
        }

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/reports/CustomerReportPureLife.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("P_Date", LocalDate.now().toString());
            parameters.put("P_Customer_Id", customerTM.getCustomerId());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
//           e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }
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

    @FXML
    void HomeAction(ActionEvent event) {
        navigateTo("/view/HomePage.fxml");
    }

}
