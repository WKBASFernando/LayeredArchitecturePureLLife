package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.dao.CrudUtil;
import com.assignment.purelifewaterbottles.model.OrderAndDetailDto;
import com.assignment.purelifewaterbottles.model.OrderDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO {
    String getNextID() throws SQLException;
    OrderDto find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    ArrayList<OrderAndDetailDto> getAllOrders() throws SQLException;
    ArrayList<OrderDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(OrderDto orderDto) throws SQLException;
    boolean update(OrderDto orderDto) throws SQLException;
    boolean delete(String orderId) throws SQLException;
}
