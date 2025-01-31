package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dto.DriverDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverDAO {
    String getNextID() throws SQLException;
    ArrayList<DriverDto> getAll() throws SQLException;
    boolean save(DriverDto driverDto) throws SQLException;
    boolean update(DriverDto driverDto) throws SQLException;
}
