package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.custom.EmployeeDAO;
import com.assignment.purelifewaterbottles.dto.EmployeeAndSalaryDto;
import com.assignment.purelifewaterbottles.dto.EmployeeDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public String getNextID() throws SQLException {
        ResultSet rst = CrudUtil.execute("select employeeId from employee order by employeeId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("E%03d", newIdIndex);
        }
        return "E001";
    }

    public boolean save(EmployeeDto employeeDto) throws SQLException {
        return CrudUtil.execute("insert into employee values (?,?,?,?,?)", employeeDto.getEmployeeId(), employeeDto.getName(), employeeDto.getPosition(), employeeDto.getAddress(), employeeDto.getPhoneNumber());
    }

    public ArrayList<EmployeeAndSalaryDto> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select e.employeeId, s.salaryId, e.name, e.address, e.phoneNumber, e.position, s.salary from employee e join salary s where e.employeeId = s.employeeId");

        ArrayList<EmployeeAndSalaryDto> employeeAndSalaryDtos = new ArrayList<>();

        while (rst.next()) {
            EmployeeAndSalaryDto employeeAndSalaryDto = new EmployeeAndSalaryDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getDouble(7));
            employeeAndSalaryDtos.add(employeeAndSalaryDto);
        }
        return employeeAndSalaryDtos;
    }

    public boolean update(EmployeeDto employeeDto) throws SQLException {
        return CrudUtil.execute("update employee set name=?, position=?, address=?, phoneNumber=? where employeeId=?", employeeDto.getName(), employeeDto.getPosition(), employeeDto.getAddress(), employeeDto.getPhoneNumber(), employeeDto.getEmployeeId());
    }

    public boolean delete(String employeeId) throws SQLException {
        return CrudUtil.execute("delete from employee where employeeId=?", employeeId);
    }
}
