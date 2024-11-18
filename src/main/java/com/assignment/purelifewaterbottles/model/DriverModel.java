package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.CustomerDto;
import com.assignment.purelifewaterbottles.dto.DriverDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverModel {
    public String getNextDriverId() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT driverId FROM driver ORDER BY driverId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            if (lastId != null && lastId.startsWith("DR")) {
                String substring = lastId.substring(2);

                try {
                    int i = Integer.parseInt(substring);
                    int newIdIndex = i + 1;
                    return String.format("DR%03d", newIdIndex);
                } catch (NumberFormatException e) {
                    throw new SQLException("Invalid ID format: " + lastId, e);
                }
            } else {
                throw new SQLException("Invalid ID format or missing prefix 'DR': " + lastId);
            }
        }
        return "DR001";
    }

    public ArrayList<DriverDto> getAllDrivers() throws SQLException {
        ResultSet rst = CrudUtil.execute("select driverId, vehicleId, name, phoneNo, driver_fee from driver");

        ArrayList<DriverDto> driverDtos = new ArrayList<>();

        while (rst.next()) {
            DriverDto driverDto = new DriverDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(5));
            driverDtos.add(driverDto);
        }
        return driverDtos;
    }

    public boolean saveDriver(DriverDto driverDto) throws SQLException {
        return CrudUtil.execute("insert into driver values (?,?,?,?,?)", driverDto.getDriverId(), driverDto.getVehicleId(), driverDto.getDriver_fee(), driverDto.getName(), driverDto.getPhoneNo());
    }

    public boolean updateDriver(DriverDto driverDto) throws SQLException {
        return CrudUtil.execute("update driver set vehicleId=?, driver_fee=?, name=?, phoneNo=? where driverId=?", driverDto.getVehicleId(), driverDto.getDriver_fee(), driverDto.getName(), driverDto.getPhoneNo(), driverDto.getDriverId());
    }

    public boolean deleteDriver(String driverId) throws SQLException {
        return CrudUtil.execute("delete from driver where driverId=?", driverId);
    }
}
