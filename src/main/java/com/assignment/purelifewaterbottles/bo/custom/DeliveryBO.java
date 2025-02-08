package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.model.DeliveryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryBO extends SuperBO {
    boolean save(DeliveryDto deliveryDto) throws SQLException;
    ArrayList<DeliveryDto> getAll() throws SQLException;
    boolean update(DeliveryDto deliveryDto) throws SQLException;
    boolean delete(String deliveryId) throws SQLException;
    String getNextID() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    DeliveryDto find(String selectedDeliveryId) throws SQLException;
}
