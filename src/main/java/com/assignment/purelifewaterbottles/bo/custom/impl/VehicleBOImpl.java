package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.VehicleBO;
import com.assignment.purelifewaterbottles.dao.DAOFactory;
import com.assignment.purelifewaterbottles.dao.custom.VehicleDAO;
import com.assignment.purelifewaterbottles.dto.DriverAndVehicleDto;
import com.assignment.purelifewaterbottles.dto.VehicleDto;
import com.assignment.purelifewaterbottles.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {
  //  VehicleBOImpl vehicleBO = new VehicleBOImpl();
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);
    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return vehicleDAO.getNextID();
    }

    @Override
    public VehicleDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return vehicleDAO.getAllIds();
    }

    @Override
    public ArrayList<VehicleDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Vehicle> vehicles = vehicleDAO.getAll();
        ArrayList<VehicleDto> vehicleDtos = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            vehicleDtos.add(new VehicleDto(
                    vehicle.getVehicleId(),
                    vehicle.getType(),
                    vehicle.getVehicleNumber()
            ));
        }
        return vehicleDtos;
    }

    @Override
    public boolean save(VehicleDto vehicle) throws SQLException, ClassNotFoundException {
        return vehicleDAO.save(new Vehicle(
                vehicle.getVehicleId(),
                vehicle.getType(),
                vehicle.getVehicleNumber()
        ));
    }

    @Override
    public ArrayList<DriverAndVehicleDto> getAllDriversAndVehicles() throws SQLException {
        return vehicleDAO.getAllDriversAndVehicles();
    }

    @Override
    public boolean update(VehicleDto vehicle) throws SQLException, ClassNotFoundException {
        return vehicleDAO.update(new Vehicle(
                vehicle.getVehicleId(),
                vehicle.getType(),
                vehicle.getVehicleNumber()
        ));
    }

    @Override
    public boolean delete(String vehicleId) throws SQLException, ClassNotFoundException {
        return vehicleDAO.delete(vehicleId);
    }
}
