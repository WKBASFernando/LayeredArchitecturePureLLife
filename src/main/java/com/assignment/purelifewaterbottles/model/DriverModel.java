package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.CustomerDto;
import com.assignment.purelifewaterbottles.dto.DriverDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverModel {
    public ArrayList<DriverDto> getAllDrivers() throws SQLException {
        ResultSet rst = CrudUtil.execute("select driverId, vehicleId, name, phoneNo, driver_fee from driver");

        ArrayList<DriverDto> driverDtos = new ArrayList<>();

        while (rst.next()) {
            DriverDto driverDto = new DriverDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getDouble(5));
            driverDtos.add(driverDto);
        }
        return driverDtos;
    }
}
