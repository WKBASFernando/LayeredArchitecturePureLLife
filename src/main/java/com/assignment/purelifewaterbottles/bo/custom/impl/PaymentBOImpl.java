package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.PaymentBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.PaymentDAOImpl;
import com.assignment.purelifewaterbottles.dto.PaymentDto;
import com.assignment.purelifewaterbottles.entity.Payment;

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
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return paymentDAO.getAllIds();
    }

    @Override
    public ArrayList<PaymentDto> getAll() throws SQLException {
        ArrayList<Payment> payments = paymentDAO.getAll();
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        for (Payment payment : payments) {
            paymentDtos.add(new PaymentDto(
                    payment.getPaymentId(),
                    payment.getOrderId(),
                    payment.getPay_method()
            ));
        }

        return paymentDtos;
    }

    @Override
    public boolean save(PaymentDto payment) throws SQLException {
        return paymentDAO.save(new Payment(
                payment.getPaymentId(),
                payment.getOrderId(),
                payment.getPay_method()
        ));
    }

    @Override
    public boolean update(PaymentDto payment) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(
                payment.getPaymentId(),
                payment.getOrderId(),
                payment.getPay_method()
        ));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }
}
