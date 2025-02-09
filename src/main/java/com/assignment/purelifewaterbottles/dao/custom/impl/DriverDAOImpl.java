package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.custom.DriverDAO;
import com.assignment.purelifewaterbottles.entity.Driver;
import com.assignment.purelifewaterbottles.dto.DriverDto;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverDAOImpl implements DriverDAO {
    @Override
    public String getNextID() throws SQLException {
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

    @Override
    public Driver find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Driver> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select driverId, vehicleId, name, phoneNo, driver_fee from driver");

        ArrayList<Driver> drivers = new ArrayList<>();

        while (rst.next()) {
            Driver driver = new Driver(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(5));
            drivers.add(driver);
        }
        return drivers;
    }

    @Override
    public boolean save(Driver driverDto) throws SQLException {
        return CrudUtil.execute("insert into driver values (?,?,?,?,?)", driverDto.getDriverId(), driverDto.getVehicleId(), driverDto.getDriver_fee(), driverDto.getName(), driverDto.getPhoneNo());
    }

    @Override
    public boolean update(Driver driverDto) throws SQLException {
        return CrudUtil.execute("update driver set vehicleId=?, driver_fee=?, name=?, phoneNo=? where driverId=?", driverDto.getVehicleId(), driverDto.getDriver_fee(), driverDto.getName(), driverDto.getPhoneNo(), driverDto.getDriverId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
