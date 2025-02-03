package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.model.SupplierAndDetailDto;
import com.assignment.purelifewaterbottles.model.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO {
    String getNextID() throws SQLException;
    SupplierDto find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    ArrayList<SupplierDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(SupplierDto supplier) throws SQLException;
    boolean update(SupplierDto supplier) throws SQLException;
    boolean delete(String supplierId) throws SQLException;
    ArrayList<SupplierAndDetailDto> getAllSuppliers() throws SQLException;
}
