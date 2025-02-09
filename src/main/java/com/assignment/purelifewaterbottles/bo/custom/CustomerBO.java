package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    String getNextID() throws SQLException, ClassNotFoundException;

    boolean save(CustomerDto customerDto) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException;

    boolean update(CustomerDto customerDto) throws SQLException, ClassNotFoundException;

    boolean delete(String customerId) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllIds() throws SQLException;

    CustomerDto find(String selectedCustomerId) throws SQLException, ClassNotFoundException;
}
