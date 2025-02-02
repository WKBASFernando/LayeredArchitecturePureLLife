package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.CrudDAO;
import com.assignment.purelifewaterbottles.dto.SupplierAndDetailDto;
import com.assignment.purelifewaterbottles.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<SupplierDto> {
    ArrayList<SupplierAndDetailDto> getAllSuppliers() throws SQLException;
}
