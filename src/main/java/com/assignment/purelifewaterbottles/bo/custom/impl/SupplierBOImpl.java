package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.SupplierBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.SupplierDAOImpl;
import com.assignment.purelifewaterbottles.model.SupplierAndDetailDto;
import com.assignment.purelifewaterbottles.model.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAOImpl supplierDAO = new SupplierDAOImpl();

    @Override
    public String getNextID() throws SQLException {
        return supplierDAO.getNextID();
    }

    @Override
    public SupplierDto find(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.find(id);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return supplierDAO.getAllIds();
    }

    @Override
    public ArrayList<SupplierDto> getAll() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll();
    }

    @Override
    public boolean save(SupplierDto supplier) throws SQLException {
        return supplierDAO.save(supplier);
    }

    @Override
    public boolean update(SupplierDto supplier) throws SQLException {
        return supplierDAO.update(supplier);
    }

    @Override
    public boolean delete(String supplierId) throws SQLException {
        return supplierDAO.delete(supplierId);
    }

    @Override
    public ArrayList<SupplierAndDetailDto> getAllSuppliers() throws SQLException {
        return supplierDAO.getAllSuppliers();
    }
}
