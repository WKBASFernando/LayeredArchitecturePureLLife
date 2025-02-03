package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.model.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
    String getNextID() throws SQLException;

    boolean save(CustomerDto customerDto) throws SQLException;

    ArrayList<CustomerDto> getAll() throws SQLException;

    boolean update(CustomerDto customerDto) throws SQLException;

    boolean delete(String customerId) throws SQLException;

    ArrayList<String> getAllIds() throws SQLException;

    CustomerDto find(String selectedCustomerId) throws SQLException;
}
