package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.VehicleBO;
import com.assignment.purelifewaterbottles.dao.DAOFactory;
import com.assignment.purelifewaterbottles.dao.custom.VehicleDAO;
import com.assignment.purelifewaterbottles.model.DriverAndVehicleDto;
import com.assignment.purelifewaterbottles.model.VehicleDto;

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
        return vehicleDAO.find(id);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return vehicleDAO.getAllIds();
    }

    @Override
    public ArrayList<VehicleDto> getAll() throws SQLException, ClassNotFoundException {
        return vehicleDAO.getAll();
    }

    @Override
    public boolean save(VehicleDto vehicleDto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.save(vehicleDto);
    }

    @Override
    public ArrayList<DriverAndVehicleDto> getAllDriversAndVehicles() throws SQLException {
        return vehicleDAO.getAllDriversAndVehicles();
    }

    @Override
    public boolean update(VehicleDto vehicleDto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.update(vehicleDto);
    }

    @Override
    public boolean delete(String vehicleId) throws SQLException, ClassNotFoundException {
        return vehicleDAO.delete(vehicleId);
    }
}
