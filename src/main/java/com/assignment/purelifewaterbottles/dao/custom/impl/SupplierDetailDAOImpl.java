package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.QueryDAO;
import com.assignment.purelifewaterbottles.dto.SupplierDetailDto;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDetailDAOImpl implements QueryDAO<SupplierDetailDto> {
    @Override
    public boolean save(Connection connection, SupplierDetailDto supplier) throws SQLException {
        return CrudUtil.execute("insert into supplier_detail values (?,?,?,?)", supplier.getSupplierId(), supplier.getWarehouseId(), supplier.getS_qty(), supplier.getTotalPrice());
    }

    @Override
    public boolean update(Connection connection, SupplierDetailDto supplier) throws SQLException {
        return CrudUtil.execute("update supplier_detail set warehouseId=?, s_qty=?, totalPrice=? where supplierId=?", supplier.getWarehouseId(), supplier.getS_qty(), supplier.getTotalPrice(), supplier.getSupplierId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public SupplierDetailDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }
}
