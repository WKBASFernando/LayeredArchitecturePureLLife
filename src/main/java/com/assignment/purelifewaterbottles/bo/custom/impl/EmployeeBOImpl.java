package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.EmployeeBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.EmployeeDAOImpl;
import com.assignment.purelifewaterbottles.model.EmployeeAndSalaryDto;
import com.assignment.purelifewaterbottles.model.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    @Override
    public String getNextID() throws SQLException {
        return employeeDAO.getNextID();
    }

    @Override
    public EmployeeDto find(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.find(id);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return employeeDAO.getAllIds();
    }

    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }

    @Override
    public boolean save(EmployeeDto employeeDto) throws SQLException {
        return employeeDAO.save(employeeDto);
    }

    @Override
    public ArrayList<EmployeeAndSalaryDto> getAllWithSalaries() throws SQLException {
        return employeeDAO.getAllWithSalaries();
    }

    @Override
    public boolean update(EmployeeDto employeeDto) throws SQLException {
        return employeeDAO.update(employeeDto);
    }

    @Override
    public boolean delete(String employeeId) throws SQLException {
        return employeeDAO.delete(employeeId);
    }
}
