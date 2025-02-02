package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.custom.OrderDetailDAO;
import com.assignment.purelifewaterbottles.dto.OrderDetailDto;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    public boolean save(OrderDetailDto orderDetailDto) throws SQLException {
        return CrudUtil.execute("insert into order_detail values (?,?,?)", orderDetailDto.getOrderId(), orderDetailDto.getItemId(), orderDetailDto.getItem_qty());
    }

    public boolean update(OrderDetailDto orderDetailDto) throws SQLException {
        return CrudUtil.execute("update order_detail set itemId=?, item_qty=? where orderId=?", orderDetailDto.getItemId(), orderDetailDto.getItem_qty(), orderDetailDto.getOrderId());
    }
}
