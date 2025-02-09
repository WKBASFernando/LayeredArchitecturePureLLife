package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.DriverBO;
import com.assignment.purelifewaterbottles.dao.DAOFactory;
import com.assignment.purelifewaterbottles.dao.custom.impl.DriverDAOImpl;
import com.assignment.purelifewaterbottles.dto.DriverDto;
import com.assignment.purelifewaterbottles.entity.Driver;

import java.sql.SQLException;
import java.util.ArrayList;

public class DriverBOImpl implements DriverBO {
    DriverDAOImpl driverDAO = (DriverDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER);

    @Override
    public String getNextID() throws SQLException {
        return driverDAO.getNextID();
    }

    @Override
    public DriverDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return driverDAO.getAllIds();
    }

    @Override
    public ArrayList<DriverDto> getAll() throws SQLException {
        ArrayList<Driver> drivers = driverDAO.getAll();
        ArrayList<DriverDto> driverDtos = new ArrayList<>();
        for (Driver driver : drivers) {
            driverDtos.add(new DriverDto(
                    driver.getDriverId(),
                    driver.getVehicleId(),
                    String.valueOf(driver.getDriver_fee()),
                    driver.getName(),
                    Double.parseDouble(driver.getPhoneNo())
            ));
        }
        return driverDtos;
    }

    @Override
    public boolean save(DriverDto driver) throws SQLException {
        return driverDAO.save(new Driver(
                driver.getDriverId(),
                driver.getVehicleId(),
                String.valueOf(driver.getDriver_fee()),
                driver.getName(),
                Double.parseDouble(driver.getPhoneNo())
        ));
    }

    @Override
    public boolean update(DriverDto driver) throws SQLException {
        return driverDAO.update(new Driver(
                driver.getDriverId(),
                driver.getVehicleId(),
                String.valueOf(driver.getDriver_fee()),
                driver.getName(),
                Double.parseDouble(driver.getPhoneNo())
        ));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return driverDAO.delete(id);
    }
}
