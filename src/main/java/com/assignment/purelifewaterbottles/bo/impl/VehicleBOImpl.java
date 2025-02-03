package com.assignment.purelifewaterbottles.bo.impl;

import com.assignment.purelifewaterbottles.bo.VehicleBO;
import com.assignment.purelifewaterbottles.model.DriverAndVehicleDto;
import com.assignment.purelifewaterbottles.model.VehicleDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {
    VehicleBOImpl vehicleBO = new VehicleBOImpl();

    @Override
    public String getNextID() throws SQLException {
        return vehicleBO.getNextID();
    }

    @Override
    public VehicleDto find(String id) throws SQLException, ClassNotFoundException {
        return vehicleBO.find(id);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return vehicleBO.getAllIds();
    }

    @Override
    public ArrayList<VehicleDto> getAll() throws SQLException, ClassNotFoundException {
        return vehicleBO.getAll();
    }

    @Override
    public boolean save(VehicleDto vehicleDto) throws SQLException {
        return vehicleBO.save(vehicleDto);
    }

    @Override
    public ArrayList<DriverAndVehicleDto> getAllDriversAndVehicles() throws SQLException {
        return vehicleBO.getAllDriversAndVehicles();
    }

    @Override
    public boolean update(VehicleDto vehicleDto) throws SQLException {
        return vehicleBO.update(vehicleDto);
    }

    @Override
    public boolean delete(String vehicleId) throws SQLException {
        return vehicleBO.delete(vehicleId);
    }
}
