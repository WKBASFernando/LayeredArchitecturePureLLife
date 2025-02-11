package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.QueryDAO;
import com.assignment.purelifewaterbottles.dto.OrderAndDetailDto;
import com.assignment.purelifewaterbottles.dto.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends QueryDAO<OrderDto> {
    ArrayList<OrderAndDetailDto> getAllOrders() throws SQLException;
}
