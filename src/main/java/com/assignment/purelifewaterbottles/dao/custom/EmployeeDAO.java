package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.CrudDAO;
import com.assignment.purelifewaterbottles.dto.EmployeeAndSalaryDto;
import com.assignment.purelifewaterbottles.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO {
    ArrayList<EmployeeAndSalaryDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(EmployeeDto Dto) throws SQLException, ClassNotFoundException;
    boolean update(EmployeeDto Dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String getNextID() throws SQLException, ClassNotFoundException;
}
