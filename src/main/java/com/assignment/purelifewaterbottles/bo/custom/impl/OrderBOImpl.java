package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.OrderBO;
import com.assignment.purelifewaterbottles.dao.DAOFactory;
import com.assignment.purelifewaterbottles.dao.custom.impl.OrderDAOImpl;
import com.assignment.purelifewaterbottles.dao.custom.impl.OrderDetailDAOImpl;
import com.assignment.purelifewaterbottles.db.DBConnection;
import com.assignment.purelifewaterbottles.dto.ItemDetailDto;
import com.assignment.purelifewaterbottles.dto.OrderAndDetailDto;
import com.assignment.purelifewaterbottles.dto.OrderDetailDto;
import com.assignment.purelifewaterbottles.dto.OrderDto;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDAOImpl orderDAO = (OrderDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);
    OrderDetailDAOImpl orderDetailDAO = (OrderDetailDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER_DETAIL);
    ItemBOImpl itemBO = (ItemBOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public String getNextID() throws SQLException {
        return orderDAO.getNextID();
    }

    @Override
    public OrderDto find(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.find(id);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return orderDAO.getAllIds();
    }

    @Override
    public ArrayList<OrderAndDetailDto> getAllOrders() throws SQLException {
        return orderDAO.getAllOrders();
    }

    @Override
    public boolean save(OrderDto orderDto, OrderDetailDto orderDetailDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean result = false;

        try {
            connection.setAutoCommit(false);
            ItemDetailDto itemDetailDto = new ItemDetailDto();
            boolean isSavedOrder = orderDAO.save(connection, orderDto);
            boolean isSavedDetails = orderDetailDAO.save(connection, orderDetailDto);
            boolean isStockDeducted = itemBO.deductStock(connection, itemDetailDto.getItemId(), itemDetailDto.getItemQty());

            if (!isSavedOrder && ! isSavedDetails && ! isStockDeducted) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to place Order: " + orderDto.getOrderId());
                alert.showAndWait();
                throw new SQLException("Failed to place Order: " + orderDto.getOrderId());
            }

            connection.commit();
            result = true;
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }

    @Override
    public boolean update(OrderDto orderDto, OrderDetailDto orderDetailDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean result = false;

        try {
            connection.setAutoCommit(false);
            boolean update = orderDAO.update(connection, orderDto);
            boolean updated = orderDetailDAO.update(connection, orderDetailDto);

            if (!update && ! updated) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to place Order: " + orderDto.getOrderId());
                alert.showAndWait();
                throw new SQLException("Failed to place Order: " + orderDto.getOrderId());
            }

            connection.commit();
            result = true;
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }

    @Override
    public boolean delete(String orderId) throws SQLException {
        return orderDAO.delete(orderId);
    }
}
