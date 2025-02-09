package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.SupplierDetailBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.SupplierDetailDAOImpl;
import com.assignment.purelifewaterbottles.dto.SupplierDetailDto;

import java.sql.SQLException;

public class SupplierDetailBOImpl implements SupplierDetailBO {
    SupplierDetailDAOImpl supplierDetailDAO = new SupplierDetailDAOImpl();

    @Override
    public boolean save(SupplierDetailDto supplier) throws SQLException {
        return supplierDetailDAO.save(supplier);
    }

    @Override
    public boolean update(SupplierDetailDto supplier) throws SQLException {
        return supplierDetailDAO.update(supplier);
    }
}
