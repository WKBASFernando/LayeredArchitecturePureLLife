package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.OrderAndDetailDto;
import com.assignment.purelifewaterbottles.dto.OrderDto;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {

    public String getNextOrderId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select orderId from orders order by orderId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("O%03d", newIdIndex);
        }
        return "O001";
    }

    public ArrayList<OrderAndDetailDto> getAllOrders() throws SQLException {
        ResultSet rst = CrudUtil.execute("select distinct o.orderId, o.customerId, o.deliveryId, od.itemId, od.item_qty, o.order_date, o.description from orders o join order_detail od on o.orderId = od.orderId");

        ArrayList<OrderAndDetailDto> orderAndDetailDtos = new ArrayList<>();
        while (rst.next()) {
            OrderAndDetailDto orderAndDetailDto = new OrderAndDetailDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getString(6), rst.getString(7));
            orderAndDetailDtos.add(orderAndDetailDto);
        }
        return orderAndDetailDtos;
    }

    public boolean saveOrder(OrderDto orderDto) throws SQLException {
        return CrudUtil.execute("insert into orders values (?,?,?,?,?)",
                orderDto.getOrderId(),
                orderDto.getCustomerId(),
                orderDto.getDeliveryId(),
                orderDto.getDescription(),
                orderDto.getOrderDate());

    }

    public boolean updateOrder(OrderDto orderDto) throws SQLException {
        return CrudUtil.execute("update orders set customerId=?, deliveryId=?, description=?, order_date=? where orderId=?", orderDto.getCustomerId(), orderDto.getDeliveryId(), orderDto.getDescription(), orderDto.getOrderDate(), orderDto.getOrderId());
    }

    public boolean deleteOrder(String orderId) throws SQLException {
        return CrudUtil.execute("delete from orders where orderId=?", orderId);
    }
}
