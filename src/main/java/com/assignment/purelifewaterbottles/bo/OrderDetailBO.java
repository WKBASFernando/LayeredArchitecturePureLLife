package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.dao.CrudUtil;
import com.assignment.purelifewaterbottles.model.OrderDetailDto;

import java.sql.SQLException;

public interface OrderDetailBO {
    boolean save(OrderDetailDto orderDetailDto) throws SQLException;
    boolean update(OrderDetailDto orderDetailDto) throws SQLException;
}
