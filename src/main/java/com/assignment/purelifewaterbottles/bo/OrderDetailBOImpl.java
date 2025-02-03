package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.dao.CrudUtil;
import com.assignment.purelifewaterbottles.dao.custom.impl.OrderDAOImpl;
import com.assignment.purelifewaterbottles.dao.custom.impl.OrderDetailDAOImpl;
import com.assignment.purelifewaterbottles.model.OrderDetailDto;

import java.sql.SQLException;

public class OrderDetailBOImpl implements OrderDetailBO {
    OrderDetailDAOImpl orderDAO = new OrderDetailDAOImpl();

    @Override
    public boolean save(OrderDetailDto orderDetailDto) throws SQLException {
        return orderDAO.save(orderDetailDto);
    }

    @Override
    public boolean update(OrderDetailDto orderDetailDto) throws SQLException {
        return orderDAO.update(orderDetailDto);
    }
}
