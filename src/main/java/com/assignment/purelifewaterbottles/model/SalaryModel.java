package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.SalaryDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {

    public String getNextSalaryId() throws SQLException {
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

    public boolean saveSalary(SalaryDto salaryDto) throws SQLException {
        return CrudUtil.execute("insert into salary values (?,?,?)", salaryDto.getSalaryId(), salaryDto.getEmployeeId(), salaryDto.getSalary());
    }

    public boolean updateSalary(SalaryDto salaryDto) throws SQLException {
        return CrudUtil.execute("update salary set employeeId=?, salary=? where salaryId=?", salaryDto.getEmployeeId(), salaryDto.getSalary(), salaryDto.getSalaryId());
    }
}
