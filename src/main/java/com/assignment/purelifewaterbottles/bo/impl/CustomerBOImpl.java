package com.assignment.purelifewaterbottles.bo.impl;

import com.assignment.purelifewaterbottles.bo.CustomerBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.CustomerDAOImpl;
import com.assignment.purelifewaterbottles.model.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    @Override
    public String getNextID() throws SQLException {
        return customerDAO.getNextID();
    }

    @Override
    public boolean save(CustomerDto customerDto) throws SQLException {
        return customerDAO.save(customerDto);
    }

    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException {
        return customerDAO.getAll();
    }

    @Override
    public boolean update(CustomerDto customerDto) throws SQLException {
        return customerDAO.update(customerDto);
    }

    @Override
    public boolean delete(String customerId) throws SQLException {
        return customerDAO.delete(customerId);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return customerDAO.getAllIds();
    }

    @Override
    public CustomerDto find(String selectedCustomerId) throws SQLException {
        return customerDAO.find(selectedCustomerId);
    }
}
