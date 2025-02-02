package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.custom.SalaryDAO;
import com.assignment.purelifewaterbottles.dto.SalaryDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {

    public String getNextID() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT salaryId FROM salary ORDER BY salaryId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            if (lastId != null && lastId.startsWith("SA")) {
                String substring = lastId.substring(2);

                try {
                    int i = Integer.parseInt(substring);
                    int newIdIndex = i + 1;
                    return String.format("SA%03d", newIdIndex);
                } catch (NumberFormatException e) {
                    throw new SQLException("Invalid ID format: " + lastId, e);
                }
            } else {
                throw new SQLException("Invalid ID format or missing prefix 'SA': " + lastId);
            }
        }
        return "SA001";
    }

    @Override
    public SalaryDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<SalaryDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(SalaryDto salaryDto) throws SQLException {
        return CrudUtil.execute("insert into salary values (?,?,?)", salaryDto.getSalaryId(), salaryDto.getEmployeeId(), salaryDto.getSalary());
    }

    public boolean update(SalaryDto salaryDto) throws SQLException {
        return CrudUtil.execute("update salary set employeeId=?, salary=? where salaryId=?", salaryDto.getEmployeeId(), salaryDto.getSalary(), salaryDto.getSalaryId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
