package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.dto.DriverAndVehicleDto;
import com.assignment.purelifewaterbottles.dto.VehicleDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleBO extends SuperBO {
    String getNextID() throws SQLException, ClassNotFoundException;
    VehicleDto find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    ArrayList<VehicleDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(VehicleDto vehicleDto) throws SQLException, ClassNotFoundException;
    ArrayList<DriverAndVehicleDto> getAllDriversAndVehicles() throws SQLException;
    boolean update(VehicleDto vehicleDto) throws SQLException, ClassNotFoundException;
    boolean delete(String vehicleId) throws SQLException, ClassNotFoundException;
}
