package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.QueryDAO;
import com.assignment.purelifewaterbottles.dto.OrderDetailDto;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements QueryDAO<OrderDetailDto> {
    @Override
    public boolean save(Connection connection, OrderDetailDto orderDetailDto) throws SQLException {
        return CrudUtil.execute("insert into order_detail values (?,?,?)", orderDetailDto.getOrderId(), orderDetailDto.getItemId(), orderDetailDto.getItem_qty());
    }

    @Override
    public boolean update(Connection connection, OrderDetailDto orderDetailDto) throws SQLException {
        return CrudUtil.execute("update order_detail set itemId=?, item_qty=? where orderId=?", orderDetailDto.getItemId(), orderDetailDto.getItem_qty(), orderDetailDto.getOrderId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public OrderDetailDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }
}
