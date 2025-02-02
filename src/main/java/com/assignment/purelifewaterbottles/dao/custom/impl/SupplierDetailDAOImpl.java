package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.custom.SupplierDetailDAO;
import com.assignment.purelifewaterbottles.dto.SupplierDetailDto;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.SQLException;

public class SupplierDetailDAOImpl implements SupplierDetailDAO {
    public boolean save(SupplierDetailDto supplier) throws SQLException {
        return CrudUtil.execute("insert into supplier_detail values (?,?,?,?)", supplier.getSupplierId(), supplier.getWarehouseId(), supplier.getS_qty(), supplier.getTotalPrice());
    }

    public boolean update(SupplierDetailDto supplier) throws SQLException {
        return CrudUtil.execute("update supplier_detail set warehouseId=?, s_qty=?, totalPrice=? where supplierId=?", supplier.getWarehouseId(), supplier.getS_qty(), supplier.getTotalPrice(), supplier.getSupplierId());
    }
}
