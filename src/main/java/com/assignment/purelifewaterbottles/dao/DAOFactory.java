package com.assignment.purelifewaterbottles.dao;

import com.assignment.purelifewaterbottles.dao.custom.impl.*;

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
            CUSTOMER, VEHICLE, DELIVERY, DRIVER, EMPLOYEE, SALARY, PAYMENT, USER, ITEM, ITEM_DETAIL, ORDER, ORDER_DETAIL, SUPPLIER, SUPPLIER_DETAIL
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
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case USER:
                return new UserDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ITEM_DETAIL:
                return new ItemDetailDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SUPPLIER_DETAIL:
                return new SupplierDetailDAOImpl();

            default:
                return null;
        }
    }
}
