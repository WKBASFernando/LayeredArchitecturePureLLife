package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.dto.OrderDetailDto;

import java.sql.SQLException;

public interface OrderDetailBO extends SuperBO {
    boolean save(OrderDetailDto orderDetailDto) throws SQLException;
    boolean update(OrderDetailDto orderDetailDto) throws SQLException;
}
