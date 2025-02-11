package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.EmployeeBO;
import com.assignment.purelifewaterbottles.dao.DAOFactory;
import com.assignment.purelifewaterbottles.dao.custom.impl.EmployeeDAOImpl;
import com.assignment.purelifewaterbottles.dto.EmployeeAndSalaryDto;
import com.assignment.purelifewaterbottles.dto.EmployeeDto;
import com.assignment.purelifewaterbottles.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAOImpl employeeDAO = (EmployeeDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);
    @Override
    public String getNextID() throws SQLException {
        return employeeDAO.getNextID();
    }

    @Override
    public EmployeeDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return employeeDAO.getAllIds();
    }

    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDtos.add(new EmployeeDto(
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getPosition(),
                    employee.getAddress(),
                    employee.getPhoneNumber()
            ));
        }
        return employeeDtos;
    }

    @Override
    public boolean save(EmployeeDto employee) throws SQLException {
        return employeeDAO.save(new Employee(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getPosition(),
                employee.getAddress(),
                employee.getPhoneNumber()
        ));
    }

    @Override
    public ArrayList<EmployeeAndSalaryDto> getAllWithSalaries() throws SQLException {
        return employeeDAO.getAllWithSalaries();
    }

    @Override
    public boolean update(EmployeeDto employee) throws SQLException {
        return employeeDAO.update(new Employee(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getPosition(),
                employee.getAddress(),
                employee.getPhoneNumber()
        ));
    }

    @Override
    public boolean delete(String employeeId) throws SQLException {
        return employeeDAO.delete(employeeId);
    }
}
