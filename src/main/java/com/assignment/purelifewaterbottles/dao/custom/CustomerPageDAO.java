package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.CrudDAO;
import com.assignment.purelifewaterbottles.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerPageDAO extends CrudDAO<CustomerDto> {
    ArrayList<String> getAllIds() throws SQLException;
    CustomerDto find(String selectedCustomerId) throws SQLException;
}
