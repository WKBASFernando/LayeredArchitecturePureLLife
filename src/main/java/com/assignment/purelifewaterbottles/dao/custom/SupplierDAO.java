package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.QueryDAO;
import com.assignment.purelifewaterbottles.dto.SupplierAndDetailDto;
import com.assignment.purelifewaterbottles.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends QueryDAO<SupplierDto> {
    ArrayList<SupplierAndDetailDto> getAllSuppliers() throws SQLException;
}
