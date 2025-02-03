package com.assignment.purelifewaterbottles.bo.impl;

import com.assignment.purelifewaterbottles.bo.OrderBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.OrderDAOImpl;
import com.assignment.purelifewaterbottles.model.OrderAndDetailDto;
import com.assignment.purelifewaterbottles.model.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDAOImpl orderDAO = new OrderDAOImpl();

    @Override
    public String getNextID() throws SQLException {
        return orderDAO.getNextID();
    }

    @Override
    public OrderDto find(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.find(id);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return orderDAO.getAllIds();
    }

    @Override
    public ArrayList<OrderAndDetailDto> getAllOrders() throws SQLException {
        return orderDAO.getAllOrders();
    }

    @Override
    public ArrayList<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        return orderDAO.getAll();
    }

    @Override
    public boolean save(OrderDto orderDto) throws SQLException {
        return orderDAO.save(orderDto);
    }

    @Override
    public boolean update(OrderDto orderDto) throws SQLException {
        return orderDAO.update(orderDto);
    }

    @Override
    public boolean delete(String orderId) throws SQLException {
        return orderDAO.delete(orderId);
    }
}
