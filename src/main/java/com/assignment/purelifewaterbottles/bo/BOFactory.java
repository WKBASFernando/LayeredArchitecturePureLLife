package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.bo.custom.CustomerBO;
import com.assignment.purelifewaterbottles.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {

    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOType {
        CUSTOMER, VEHICLE, DELIVERY, DRIVER, EMPLOYEE, SALARY, PAYMENT, USER
    }

        public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case DELIVERY:
                return new DeliveryBOImpl();
            case DRIVER:
                return new DriverBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case USER:
                return new UserBOImpl();

                default:
                    return null;
        }
        }
}
