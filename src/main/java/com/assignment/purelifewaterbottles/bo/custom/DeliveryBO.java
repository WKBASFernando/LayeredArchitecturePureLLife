package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.dto.DeliveryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryBO extends SuperBO {
    boolean save(DeliveryDto deliveryDto) throws SQLException, ClassNotFoundException;
    ArrayList<DeliveryDto> getAll() throws SQLException, ClassNotFoundException;
    boolean update(DeliveryDto deliveryDto) throws SQLException, ClassNotFoundException;
    boolean delete(String deliveryId) throws SQLException, ClassNotFoundException;
    String getNextID() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    DeliveryDto find(String selectedDeliveryId) throws SQLException, ClassNotFoundException;
}
