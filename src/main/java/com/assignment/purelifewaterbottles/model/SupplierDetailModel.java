package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.SupplierDetailDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.SQLException;

public class SupplierDetailModel {
    public boolean saveSupplier(SupplierDetailDto supplier) throws SQLException {
        return CrudUtil.execute("insert into supplier_detail values (?,?,?,?)", supplier.getSupplierId(), supplier.getWarehouseId(), supplier.getS_qty(), supplier.getTotalPrice());
    }

    public boolean updateSupplier(SupplierDetailDto supplier) throws SQLException {
        return CrudUtil.execute("update supplier_detail set warehouseId=?, s_qty=?, totalPrice=? where supplierId=?", supplier.getWarehouseId(), supplier.getS_qty(), supplier.getTotalPrice(), supplier.getSupplierId());
    }
}
