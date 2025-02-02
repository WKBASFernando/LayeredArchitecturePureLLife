package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.OrderDetailDto;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.SQLException;

public class OrderDetailModel {
    public boolean saveOrder(OrderDetailDto orderDetailDto) throws SQLException {
        return CrudUtil.execute("insert into order_detail values (?,?,?)", orderDetailDto.getOrderId(), orderDetailDto.getItemId(), orderDetailDto.getItem_qty());
    }

    public boolean updateOrder(OrderDetailDto orderDetailDto) throws SQLException {
        return CrudUtil.execute("update order_detail set itemId=?, item_qty=? where orderId=?", orderDetailDto.getItemId(), orderDetailDto.getItem_qty(), orderDetailDto.getOrderId());
    }
}
