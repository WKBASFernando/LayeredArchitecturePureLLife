package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.model.DriverAndVehicleDto;
import com.assignment.purelifewaterbottles.model.VehicleDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleBO {
    String getNextID() throws SQLException;
    VehicleDto find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    ArrayList<VehicleDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(VehicleDto vehicleDto) throws SQLException;
    ArrayList<DriverAndVehicleDto> getAllDriversAndVehicles() throws SQLException;
    boolean update(VehicleDto vehicleDto) throws SQLException;
    boolean delete(String vehicleId) throws SQLException;
}
