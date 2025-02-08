package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.DeliveryBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.DeliveryDAOImpl;
import com.assignment.purelifewaterbottles.model.DeliveryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryBOImpl implements DeliveryBO {
    DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
    @Override
    public boolean save(DeliveryDto deliveryDto) throws SQLException {
        return deliveryDAO.save(deliveryDto);
    }

    @Override
    public ArrayList<DeliveryDto> getAll() throws SQLException {
        return deliveryDAO.getAll();
    }

    @Override
    public boolean update(DeliveryDto deliveryDto) throws SQLException {
        return deliveryDAO.update(deliveryDto);
    }

    @Override
    public boolean delete(String deliveryId) throws SQLException {
        return deliveryDAO.delete(deliveryId);
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return deliveryDAO.getNextID();
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return deliveryDAO.getAllIds();
    }

    @Override
    public DeliveryDto find(String selectedDeliveryId) throws SQLException {
        return deliveryDAO.find(selectedDeliveryId);
    }
}
