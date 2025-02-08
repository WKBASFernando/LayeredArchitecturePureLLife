package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.bo.custom.CustomerBO;
import com.assignment.purelifewaterbottles.bo.custom.impl.CustomerBOImpl;
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
        CUSTOMER, VEHICLE
    }

        public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
                case VEHICLE:
                    return new VehicleBOImpl();
                default:
                    return null;
        }
        }
}
