package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.OrderDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

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

    public boolean saveOrder(OrderDto orderDto) throws SQLException {
        return CrudUtil.execute("insert into orders values (?,?,?,?)", orderDto.getOrderId(), orderDto.getCustomerId(), orderDto.getDeliveryId(), orderDto.getDescription());
    }

    public ArrayList<OrderDto> getAllOrders() throws SQLException {
        ResultSet rst = CrudUtil.execute("select distinct o.orderId, o.customerId, o.deliveryId, od.itemId, od.item_qty, o.order_date, o.description from orders o join order_detail od on o.orderId = od.orderId");

        ArrayList<OrderDto> orderDtos = new ArrayList<>();
        while (rst.next()) {
            OrderDto orderDto = new OrderDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getDate(6), rst.getString(7));
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    public boolean updateOrder(OrderDto orderDto) throws SQLException {
        return CrudUtil.execute("update orders set customerId=?, deliveryId=?, description=? where orderId=?", orderDto.getCustomerId(), orderDto.getDeliveryId(), orderDto.getDescription(), orderDto.getOrderId());
    }

    public boolean deleteOrder(String orderId) throws SQLException {
        return CrudUtil.execute("delete from orders where orderId=?", orderId);
    }


    public ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select customerId from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }

    public OrderDto findByCustomerId(String selectedCustomerId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from item where itemId=?", selectedCustomerId);

        if (rst.next()) {
            return new OrderDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5));
        }
        return null;
    }

    public ArrayList<String> getAllDeliveryIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select deliveryId from delivery");

        ArrayList<String> deliveryIds = new ArrayList<>();

        while (rst.next()) {
            deliveryIds.add(rst.getString(1));
        }

        return deliveryIds;
    }

    public OrderDto findByDeliveryId(String selectedDeliveryId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from delivery where deliveryId=?", selectedDeliveryId);

        if (rst.next()) {
            return new OrderDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5));
        }
        return null;
    }
}
