package com.assignment.purelifewaterbottles.bo.impl;

import com.assignment.purelifewaterbottles.bo.DriverBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.DriverDAOImpl;
import com.assignment.purelifewaterbottles.model.DriverDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class DriverBOImpl implements DriverBO {
    DriverDAOImpl driverDAO = new DriverDAOImpl();

    @Override
    public String getNextID() throws SQLException {
        return driverDAO.getNextID();
    }

    @Override
    public DriverDto find(String id) throws SQLException, ClassNotFoundException {
        return driverDAO.find(id);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return driverDAO.getAllIds();
    }

    @Override
    public ArrayList<DriverDto> getAll() throws SQLException {
        return driverDAO.getAll();
    }

    @Override
    public boolean save(DriverDto driverDto) throws SQLException {
        return driverDAO.save(driverDto);
    }

    @Override
    public boolean update(DriverDto driverDto) throws SQLException {
        return driverDAO.update(driverDto);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return driverDAO.delete(id);
    }
}
