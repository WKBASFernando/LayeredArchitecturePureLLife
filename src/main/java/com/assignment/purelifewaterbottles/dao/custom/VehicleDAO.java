package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.CrudDAO;
import com.assignment.purelifewaterbottles.dto.DriverAndVehicleDto;
import com.assignment.purelifewaterbottles.dto.VehicleDto;
import com.assignment.purelifewaterbottles.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleDAO extends CrudDAO<Vehicle> {
    ArrayList<DriverAndVehicleDto> getAllDriversAndVehicles() throws SQLException;
}
