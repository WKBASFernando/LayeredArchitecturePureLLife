package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.CrudDAO;
import com.assignment.purelifewaterbottles.model.OrderAndDetailDto;
import com.assignment.purelifewaterbottles.model.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<OrderDto> {
    ArrayList<OrderAndDetailDto> getAllOrders() throws SQLException;
}
