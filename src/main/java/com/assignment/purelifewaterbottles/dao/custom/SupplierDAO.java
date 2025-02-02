package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.CrudDAO;
import com.assignment.purelifewaterbottles.model.SupplierAndDetailDto;
import com.assignment.purelifewaterbottles.model.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<SupplierDto> {
    ArrayList<SupplierAndDetailDto> getAllSuppliers() throws SQLException;
}
