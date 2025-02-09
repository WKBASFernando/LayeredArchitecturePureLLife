package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.PaymentBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.PaymentDAOImpl;
import com.assignment.purelifewaterbottles.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAOImpl paymentDAO = new PaymentDAOImpl();

    @Override
    public String getNextID() throws SQLException {
        return paymentDAO.getNextID();
    }

    @Override
    public PaymentDto find(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.find(id);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return paymentDAO.getAllIds();
    }

    @Override
    public ArrayList<PaymentDto> getAll() throws SQLException {
        return paymentDAO.getAll();
    }

    @Override
    public boolean save(PaymentDto paymentDto) throws SQLException {
        return paymentDAO.save(paymentDto);
    }

    @Override
    public boolean update(PaymentDto Dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(Dto);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }
}
