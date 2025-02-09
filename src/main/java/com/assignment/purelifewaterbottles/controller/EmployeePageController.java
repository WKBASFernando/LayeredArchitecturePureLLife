package com.assignment.purelifewaterbottles.controller;

import com.assignment.purelifewaterbottles.bo.custom.impl.EmployeeBOImpl;
import com.assignment.purelifewaterbottles.bo.custom.impl.SalaryBOImpl;
import com.assignment.purelifewaterbottles.dto.EmployeeAndSalaryDto;
import com.assignment.purelifewaterbottles.dto.EmployeeDto;
import com.assignment.purelifewaterbottles.dto.SalaryDto;
import com.assignment.purelifewaterbottles.view.tdm.EmployeeTm;
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

public class EmployeePageController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbPosition;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmployeeID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNo;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colSalaryId;

    @FXML
    private AnchorPane content;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private Label lblSalaryId;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNo;

    @FXML
    private TextField txtSalary;

    @FXML
    void deleteButtonAction(ActionEvent event) throws Exception {
        String employeeId = lblEmployeeId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = employeeBO.delete(employeeId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Employee deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Employee...!").show();
            }
        }
    }

    @FXML
    void driverOnAction(ActionEvent event) {
        navigateTo("/view/DriverPage.fxml");
    }

    @FXML
    void homeOnAction(ActionEvent event) {

        navigateTo("/view/HomePage.fxml");
    }

    @FXML
    void resetOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void tblEmployeeAction(MouseEvent event) {
        EmployeeTm employeeTm = tblEmployee.getSelectionModel().getSelectedItem();
        if (employeeTm != null) {
            lblEmployeeId.setText(employeeTm.getEmployeeId());
            lblSalaryId.setText(employeeTm.getSalaryId());
            txtName.setText(employeeTm.getName());
            txtAddress.setText(employeeTm.getAddress());
            txtPhoneNo.setText(String.valueOf(employeeTm.getPhoneNumber()));
            cmbPosition.setValue(employeeTm.getPosition());
            txtSalary.setText(String.valueOf(employeeTm.getSalary()));

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void saveButtonAction(ActionEvent event) throws Exception {
        String employeeId = lblEmployeeId.getText();
        String salaryId = lblSalaryId.getText();
        String name = txtName.getText().trim();
        String address = txtAddress.getText().trim();
        String phoneNoText = txtPhoneNo.getText().trim();
        String salaryText = txtSalary.getText().trim();
        String position = cmbPosition.getValue();

        txtName.setStyle("-fx-border-color: #2e86de;");
        txtPhoneNo.setStyle("-fx-border-color: #2e86de;");
        txtSalary.setStyle("-fx-border-color: #2e86de;");

        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
            txtName.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid name. Please enter a valid name with alphabetic characters only.").show();
            return;
        }

        if (phoneNoText.isEmpty() || !phoneNoText.matches("\\d+")) {
            txtPhoneNo.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid phone number. Please enter a valid numeric phone number.").show();
            return;
        }

        if (salaryText.isEmpty() || !salaryText.matches("\\d+(\\.\\d+)?")) {
            txtSalary.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid salary. Please enter a valid positive number.").show();
            return;
        }
        double salary = Double.parseDouble(salaryText);

        EmployeeDto employeeDto = new EmployeeDto(employeeId, name, position, address, phoneNoText);
        SalaryDto salaryDto = new SalaryDto(salaryId, employeeId, salary);

        boolean isSavedE = employeeBO.save(employeeDto);
        boolean isSavedS = salaryBO.save(salaryDto);

        if (isSavedE && isSavedS) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Employee saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save the Employee...!").show();
        }
    }

    @FXML
    void updateButtonAction(ActionEvent event) throws Exception {
        String employeeId = lblEmployeeId.getText();
        String salaryId = lblSalaryId.getText();
        String name = txtName.getText().trim();
        String address = txtAddress.getText().trim();
        String phoneNoText = txtPhoneNo.getText().trim();
        String salaryText = txtSalary.getText().trim();
        String position = cmbPosition.getValue();

        txtName.setStyle("-fx-border-color: #2e86de;");
        txtPhoneNo.setStyle("-fx-border-color: #2e86de;");
        txtSalary.setStyle("-fx-border-color: #2e86de;");

        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
            txtName.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid name. Please enter a valid name with alphabetic characters only.").show();
            return;
        }

        if (phoneNoText.isEmpty() || !phoneNoText.matches("\\d+")) {
            txtPhoneNo.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid phone number. Please enter a valid numeric phone number.").show();
            return;
        }

        if (salaryText.isEmpty() || !salaryText.matches("\\d+(\\.\\d+)?")) {
            txtSalary.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid salary. Please enter a valid positive number.").show();
            return;
        }
        double salary = Double.parseDouble(salaryText);

        EmployeeDto employeeDto = new EmployeeDto(employeeId, name, position, address, phoneNoText);
        SalaryDto salaryDto = new SalaryDto(salaryId, employeeId, salary);

        boolean isUpdatedE = employeeBO.update(employeeDto);
        boolean isUpdatedS = salaryBO.update(salaryDto);

        if (isUpdatedE && isUpdatedS) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Employee updated successfully...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update the Employee...!").show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        ObservableList<String> positions = FXCollections.observableArrayList("Manager", "Assistant Manager", "Accountant", "Store Keeper", "Worker", "Security");
        cmbPosition.setItems(positions);

        try{
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Data!").show();
        }
    }

    private void refreshPage() throws Exception {
        loadNextEmployeeId();
        loadNextSalaryId();
        loadTableData();

        txtName.setText("");
        txtPhoneNo.setText("");
        txtSalary.setText("");
        txtAddress.setText("");

        cmbPosition.setValue(null);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(false);
    }

    EmployeeBOImpl employeeBO = new EmployeeBOImpl();
    SalaryBOImpl salaryBO = new SalaryBOImpl();

    private void loadNextEmployeeId() throws Exception {
        String nextEmployeeId = employeeBO.getNextID();
        lblEmployeeId.setText(nextEmployeeId);
    }

    private void loadNextSalaryId() throws Exception {
        String nextSalaryId = salaryBO.getNextID();
        lblSalaryId.setText(nextSalaryId);
    }

    private void loadTableData() throws Exception {
        ArrayList<EmployeeAndSalaryDto> employeeAndSalaryDtos = employeeBO.getAllWithSalaries();

        ObservableList<EmployeeTm> employeeTms = FXCollections.observableArrayList();

        for (EmployeeAndSalaryDto employeeAndSalaryDto : employeeAndSalaryDtos) {
            EmployeeTm employeeTm = new EmployeeTm(employeeAndSalaryDto.getEmployeeId(), employeeAndSalaryDto.getSalaryId(), employeeAndSalaryDto.getName(), employeeAndSalaryDto.getAddress(), employeeAndSalaryDto.getPhoneNumber(), employeeAndSalaryDto.getPosition(), employeeAndSalaryDto.getSalary());
            employeeTms.add(employeeTm);
        }

        tblEmployee.setItems(employeeTms);
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
