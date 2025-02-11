package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.custom.SupplierDAO;
import com.assignment.purelifewaterbottles.dto.SupplierAndDetailDto;
import com.assignment.purelifewaterbottles.dto.SupplierDto;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    public String getNextID() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT supplierId FROM supplier ORDER BY supplierId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            if (lastId != null && lastId.startsWith("SU")) {
                String substring = lastId.substring(2);

                try {
                    int i = Integer.parseInt(substring);
                    int newIdIndex = i + 1;
                    return String.format("SU%03d", newIdIndex);
                } catch (NumberFormatException e) {
                    throw new SQLException("Invalid ID format: " + lastId, e);
                }
            } else {
                throw new SQLException("Invalid ID format or missing prefix 'SU': " + lastId);
            }
        }
        return "SU001";
    }

    @Override
    public SupplierDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }

    public boolean save(Connection connection, SupplierDto supplier) throws SQLException {
        return CrudUtil.execute("insert into supplier values (?,?,?)", supplier.getSupplierId(), supplier.getSupplingItem(), supplier.getPricePerOneItem());
    }

    public boolean update(Connection connection, SupplierDto supplier) throws SQLException {
        return CrudUtil.execute("update supplier set supplingItem=?, pricePerOneItem=? where supplierId=?", supplier.getSupplingItem(), supplier.getPricePerOneItem(), supplier.getSupplierId());
    }

    public boolean delete(String supplierId) throws SQLException {
        return CrudUtil.execute("delete from supplier where supplierId = ?", supplierId);
    }

    public ArrayList<SupplierAndDetailDto> getAllSuppliers() throws SQLException {
        ResultSet rst = CrudUtil.execute("select s.supplierId, s.supplingItem, s.pricePerOneItem, sd.s_qty, sd.totalPrice from supplier s join supplier_detail sd on s.supplierId = sd.supplierId");

        ArrayList<SupplierAndDetailDto> supplierAndDetailDtos = new ArrayList<>();

        while (rst.next()) {
            SupplierAndDetailDto supplierAndDetailDto = new SupplierAndDetailDto(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4), rst.getDouble(5));
            supplierAndDetailDtos.add(supplierAndDetailDto);
        }
        return supplierAndDetailDtos;
    }
}
