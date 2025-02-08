package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.custom.CustomerPageDAO;
import com.assignment.purelifewaterbottles.entity.Customer;
import com.assignment.purelifewaterbottles.model.CustomerDto;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerPageDAO {

    @Override
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

    @Override
    public boolean save(Customer customer) throws SQLException {
        return CrudUtil.execute("insert into customer values (?,?,?,?,?)", customer.getCustomerId(), customer.getName(), customer.getAddress(), customer.getPhone_no(), customer.getEmail());
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer");

        ArrayList<Customer> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            Customer customerDTO = new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        return CrudUtil.execute("update customer set name=?, address=?, phone_no=?, email=? where customerId=?", customer.getName(), customer.getAddress(), customer.getPhone_no(), customer.getEmail(), customer.getCustomerId());
    }

    @Override
    public boolean delete(String customerId) throws SQLException {
        return CrudUtil.execute("delete from customer where customerId=?", customerId);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select customerId from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }

    @Override
    public Customer find(String selectedCustomerId) throws SQLException {
//        ResultSet rst = CrudUtil.execute("select * from customer where customerId=?", selectedCustomerId);
//
//        if (rst.next()) {
//            return new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
//        }
        return null;
    }
}

