package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.dto.SupplierDetailDto;

import java.sql.SQLException;

public interface SupplierDetailBO extends SuperBO {
    boolean save(SupplierDetailDto supplier) throws SQLException;
    boolean update(SupplierDetailDto supplier) throws SQLException;
}
