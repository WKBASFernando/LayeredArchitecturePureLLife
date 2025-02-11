package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.dto.SupplierAndDetailDto;
import com.assignment.purelifewaterbottles.dto.SupplierDetailDto;
import com.assignment.purelifewaterbottles.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    String getNextID() throws SQLException;
    SupplierDto find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    boolean save(SupplierDto supplier, SupplierDetailDto supplierDetailDto) throws SQLException;
    boolean update(SupplierDto supplier, SupplierDetailDto supplierDetailDto) throws SQLException;
    boolean delete(String supplierId) throws SQLException;
    ArrayList<SupplierAndDetailDto> getAllSuppliers() throws SQLException;
}
