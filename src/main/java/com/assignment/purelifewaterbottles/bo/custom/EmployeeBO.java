package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.model.EmployeeAndSalaryDto;
import com.assignment.purelifewaterbottles.model.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    String getNextID() throws SQLException;
    EmployeeDto find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(EmployeeDto employeeDto) throws SQLException;
    ArrayList<EmployeeAndSalaryDto> getAllWithSalaries() throws SQLException;
    boolean update(EmployeeDto employeeDto) throws SQLException;
    boolean delete(String employeeId) throws SQLException;
}
