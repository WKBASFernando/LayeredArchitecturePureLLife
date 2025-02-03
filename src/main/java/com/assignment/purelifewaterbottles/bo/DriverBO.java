package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.model.DriverDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverBO {
    String getNextID() throws SQLException;
    DriverDto find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    ArrayList<DriverDto> getAll() throws SQLException;
    boolean save(DriverDto driverDto) throws SQLException;
    boolean update(DriverDto driverDto) throws SQLException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
}
