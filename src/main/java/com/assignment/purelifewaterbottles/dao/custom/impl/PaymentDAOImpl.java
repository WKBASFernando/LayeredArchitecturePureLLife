package com.assignment.purelifewaterbottles.dao.custom.impl;


import com.assignment.purelifewaterbottles.dao.custom.PaymentDAO;
import com.assignment.purelifewaterbottles.dto.PaymentDto;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public String getNextID() throws SQLException {
        ResultSet rst = CrudUtil.execute("select paymentId from payment order by paymentId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }
        return "P001";
    }

    @Override
    public PaymentDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<PaymentDto> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from payment");

        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        while (rst.next()) {
            PaymentDto paymentDto = new PaymentDto(rst.getString(1), rst.getString(2), rst.getString(3));
            paymentDtos.add(paymentDto);
        }
        return paymentDtos;
    }

    @Override
    public boolean save(PaymentDto paymentDto) throws SQLException {
        return CrudUtil.execute("insert into payment values(?, ?, ?)", paymentDto.getPaymentId(), paymentDto.getOrderId(), paymentDto.getPay_method());
    }

    @Override
    public boolean update(PaymentDto Dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
