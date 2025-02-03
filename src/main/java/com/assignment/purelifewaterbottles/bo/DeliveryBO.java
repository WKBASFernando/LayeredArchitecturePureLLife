package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.model.DeliveryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryBO {
    boolean save(DeliveryDto deliveryDto) throws SQLException;
    ArrayList<DeliveryDto> getAll() throws SQLException;
    boolean update(DeliveryDto deliveryDto) throws SQLException;
    boolean delete(String deliveryId) throws SQLException;
    String getNextID() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    DeliveryDto find(String selectedDeliveryId) throws SQLException;
}
