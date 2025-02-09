package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.bo.custom.CustomerBO;
import com.assignment.purelifewaterbottles.bo.custom.impl.CustomerBOImpl;
import com.assignment.purelifewaterbottles.bo.custom.impl.DeliveryBOImpl;
import com.assignment.purelifewaterbottles.bo.custom.impl.DriverBOImpl;
import com.assignment.purelifewaterbottles.bo.custom.impl.VehicleBOImpl;

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
        CUSTOMER, VEHICLE, DELIVERY, DRIVER
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

                default:
                    return null;
        }
        }
}
