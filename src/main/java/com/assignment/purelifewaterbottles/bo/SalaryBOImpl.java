package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.dao.custom.impl.SalaryDAOImpl;
import com.assignment.purelifewaterbottles.model.SalaryDto;

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
        return salaryDAO.find(id);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return salaryDAO.getAllIds();
    }

    @Override
    public ArrayList<SalaryDto> getAll() throws SQLException, ClassNotFoundException {
        return salaryDAO.getAll();
    }

    @Override
    public boolean save(SalaryDto salaryDto) throws SQLException {
        return salaryDAO.save(salaryDto);
    }

    @Override
    public boolean update(SalaryDto salaryDto) throws SQLException {
        return salaryDAO.update(salaryDto);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }
}
