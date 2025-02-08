package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.CustomerBO;
import com.assignment.purelifewaterbottles.dao.DAOFactory;
import com.assignment.purelifewaterbottles.dao.custom.CustomerPageDAO;
import com.assignment.purelifewaterbottles.dao.custom.impl.CustomerDAOImpl;
import com.assignment.purelifewaterbottles.entity.Customer;
import com.assignment.purelifewaterbottles.model.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerPageDAO customerPageDAO = (CustomerPageDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return customerPageDAO.getNextID();
    }

    @Override
    public boolean save(CustomerDto customer) throws SQLException, ClassNotFoundException {
        return customerPageDAO.save(new Customer(
                customer.getCustomerId(),
                customer.getName(),
                customer.getAddress(),
                customer.getPhone_no(),
                customer.getEmail()
        ));
    }

    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        ArrayList<Customer> customers = customerPageDAO.getAll();
        for (Customer customer : customers) {
            customerDtos.add(new CustomerDto(
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getPhone_no(),
                    customer.getEmail()
            ));
        }
        return customerDtos;
    }

    @Override
    public boolean update(CustomerDto customer) throws SQLException, ClassNotFoundException {
        return customerPageDAO.update(new Customer(
                customer.getCustomerId(),
                customer.getName(),
                customer.getAddress(),
                customer.getPhone_no(),
                customer.getEmail()
        ));
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return customerPageDAO.delete(customerId);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return customerPageDAO.getAllIds();
    }

    @Override
    public CustomerDto find(String selectedCustomerId) throws SQLException, ClassNotFoundException {
//        return customerPageDAO.find(selectedCustomerId);
        return null;
    }
}
