package com.assignment.purelifewaterbottles.dao;

import com.assignment.purelifewaterbottles.dto.DriverDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverCrudDAO<T>{
    String getNextID() throws SQLException;
    ArrayList<T> getAll() throws SQLException;
    boolean save(T driverDto) throws SQLException;
    boolean update(T driverDto) throws SQLException;
}
