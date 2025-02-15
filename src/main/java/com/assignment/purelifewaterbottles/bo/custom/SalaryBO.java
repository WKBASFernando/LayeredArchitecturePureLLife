package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    String getNextID() throws SQLException;
    SalaryDto find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    ArrayList<SalaryDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(SalaryDto salaryDto) throws SQLException;
    boolean update(SalaryDto salaryDto) throws SQLException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
}
