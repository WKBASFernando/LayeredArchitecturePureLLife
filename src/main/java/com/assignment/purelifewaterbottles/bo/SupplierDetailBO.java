package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.model.SupplierDetailDto;

import java.sql.SQLException;

public interface SupplierDetailBO {
    boolean save(SupplierDetailDto supplier) throws SQLException;
    boolean update(SupplierDetailDto supplier) throws SQLException;
}
