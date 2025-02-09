package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.SalaryBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.SalaryDAOImpl;
import com.assignment.purelifewaterbottles.dto.SalaryDto;
import com.assignment.purelifewaterbottles.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {
    SalaryDAOImpl salaryDAO = new SalaryDAOImpl();

    @Override
    public String getNextID() throws SQLException {
        return salaryDAO.getNextID();
    }

    @Override
    public SalaryDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return salaryDAO.getAllIds();
    }

    @Override
    public ArrayList<SalaryDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SalaryDto salaryDto) throws SQLException {
        return salaryDAO.save(new Salary(
                salaryDto.getSalaryId(),
                salaryDto.getEmployeeId(),
                salaryDto.getSalary()
        ));
    }

    @Override
    public boolean update(SalaryDto salaryDto) throws SQLException {
        return salaryDAO.update(new Salary(
                salaryDto.getSalaryId(),
                salaryDto.getEmployeeId(),
                salaryDto.getSalary()
        ));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }
}
