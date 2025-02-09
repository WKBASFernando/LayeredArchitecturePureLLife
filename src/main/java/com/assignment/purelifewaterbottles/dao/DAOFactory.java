package com.assignment.purelifewaterbottles.dao;

import com.assignment.purelifewaterbottles.dao.custom.impl.CustomerDAOImpl;
import com.assignment.purelifewaterbottles.dao.custom.impl.DeliveryDAOImpl;
import com.assignment.purelifewaterbottles.dao.custom.impl.DriverDAOImpl;
import com.assignment.purelifewaterbottles.dao.custom.impl.VehicleDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOType {
        CUSTOMER, VEHICLE, DELIVERY, DRIVER
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case DELIVERY:
                return new DeliveryDAOImpl();
            case DRIVER:
                return new DriverDAOImpl();

            default:
                return null;
        }
    }
}
