package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.CrudDAO;
import com.assignment.purelifewaterbottles.model.EmployeeAndSalaryDto;
import com.assignment.purelifewaterbottles.model.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<EmployeeDto> {
    ArrayList<EmployeeAndSalaryDto> getAllWithSalaries() throws SQLException;
}
