package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.custom.CustomerPageDAO;
import com.assignment.purelifewaterbottles.dto.CustomerDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerPageDAO {

    public String getNextID() throws SQLException {
        ResultSet rst = CrudUtil.execute("select customerId from customer order by customerId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("C%03d", newIdIndex);
        }
        return "C001";
    }

    public boolean save(CustomerDto customerDto) throws SQLException {
        return CrudUtil.execute("insert into customer values (?,?,?,?,?)", customerDto.getCustomerId(), customerDto.getName(), customerDto.getAddress(), customerDto.getPhone_no(), customerDto.getEmail());
    }

    public ArrayList<CustomerDto> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer");

        ArrayList<CustomerDto> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            CustomerDto customerDTO = new CustomerDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public boolean update(CustomerDto customerDto) throws SQLException {
        return CrudUtil.execute("update customer set name=?, address=?, phone_no=?, email=? where customerId=?", customerDto.getName(), customerDto.getAddress(), customerDto.getPhone_no(), customerDto.getEmail(), customerDto.getCustomerId());
    }

    public boolean delete(String customerId) throws SQLException {
        return CrudUtil.execute("delete from customer where customerId=?", customerId);
    }

    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select customerId from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }

    public CustomerDto find(String selectedCustomerId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer where customerId=?", selectedCustomerId);

        if (rst.next()) {
            return new CustomerDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
        }
        return null;
    }
}

