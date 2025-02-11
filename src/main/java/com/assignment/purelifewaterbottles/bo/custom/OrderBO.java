package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.dto.OrderAndDetailDto;
import com.assignment.purelifewaterbottles.dto.OrderDetailDto;
import com.assignment.purelifewaterbottles.dto.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    String getNextID() throws SQLException;
    OrderDto find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    ArrayList<OrderAndDetailDto> getAllOrders() throws SQLException;
    boolean save(OrderDto orderDto, OrderDetailDto orderDetailDto) throws SQLException;
    boolean update(OrderDto orderDto, OrderDetailDto orderDetailDto) throws SQLException;
    boolean delete(String orderId) throws SQLException;
}
