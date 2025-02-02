package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.CrudDAO;
import com.assignment.purelifewaterbottles.model.DriverAndVehicleDto;
import com.assignment.purelifewaterbottles.model.VehicleDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleDAO extends CrudDAO<VehicleDto> {
    ArrayList<DriverAndVehicleDto> getAllDriversAndVehicles() throws SQLException;
}
