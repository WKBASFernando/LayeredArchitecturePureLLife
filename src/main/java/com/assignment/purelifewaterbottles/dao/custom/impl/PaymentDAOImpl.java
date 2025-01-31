package com.assignment.purelifewaterbottles.dao.custom.impl;


import com.assignment.purelifewaterbottles.dto.PaymentDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl {
    public String getNextPaymentId() throws SQLException {
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

    public ArrayList<PaymentDto> getAllPayments() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from payment");

        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        while (rst.next()) {
            PaymentDto paymentDto = new PaymentDto(rst.getString(1), rst.getString(2), rst.getString(3));
            paymentDtos.add(paymentDto);
        }
        return paymentDtos;
    }

    public boolean savePayment(PaymentDto paymentDto) throws SQLException {
        return CrudUtil.execute("insert into payment values(?, ?, ?)", paymentDto.getPaymentId(), paymentDto.getOrderId(), paymentDto.getPay_method());
    }
}
