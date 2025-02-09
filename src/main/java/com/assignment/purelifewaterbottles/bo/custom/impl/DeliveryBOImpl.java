package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.DeliveryBO;
import com.assignment.purelifewaterbottles.dao.DAOFactory;
import com.assignment.purelifewaterbottles.dao.custom.DeliveryDAO;
import com.assignment.purelifewaterbottles.entity.Delivery;
import com.assignment.purelifewaterbottles.dto.DeliveryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryBOImpl implements DeliveryBO {
    DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DELIVERY);
    @Override
    public boolean save(DeliveryDto delivery) throws SQLException, ClassNotFoundException {
        return deliveryDAO.save(new Delivery(
                delivery.getDeliveryId(),
                delivery.getDriverId(),
                delivery.getLocation(),
                delivery.getDelivery_fee()
        ));
    }

    @Override
    public ArrayList<DeliveryDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Delivery> deliveryList = deliveryDAO.getAll();
        ArrayList<DeliveryDto> deliveryDtoList = new ArrayList<>();
        for (Delivery delivery : deliveryList) {
            deliveryDtoList.add(new DeliveryDto(
                    delivery.getDeliveryId(),
                    delivery.getDriverId(),
                    delivery.getLocation(),
                    delivery.getDelivery_fee()
            ));
        }
        return deliveryDtoList;
    }

    @Override
    public boolean update(DeliveryDto delivery) throws SQLException, ClassNotFoundException {
        return deliveryDAO.update(new Delivery(
                delivery.getDeliveryId(),
                delivery.getDriverId(),
                delivery.getLocation(),
                delivery.getDelivery_fee()
        ));
    }

    @Override
    public boolean delete(String deliveryId) throws SQLException, ClassNotFoundException {
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
    public DeliveryDto find(String selectedDeliveryId) throws SQLException, ClassNotFoundException {
        return null;
    }
}
