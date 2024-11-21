package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.CustomerDto;
import com.assignment.purelifewaterbottles.dto.SupplierAndDetailDto;
import com.assignment.purelifewaterbottles.dto.SupplierDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Supplier;

public class SupplierModel {
    public String getNextSupplierId() throws SQLException {
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

    public boolean saveSupplier(SupplierDto supplier) throws SQLException {
        return CrudUtil.execute("insert into supplier values (?,?,?)", supplier.getSupplierId(), supplier.getSupplingItem(), supplier.getPricePerOneItem());
    }

    public boolean updateSupplier(SupplierDto supplier) throws SQLException {
        return CrudUtil.execute("update supplier set supplingItem=?, pricePerOneItem=? where supplierId=?", supplier.getSupplingItem(), supplier.getPricePerOneItem(), supplier.getSupplierId());
    }

    public boolean deleteSupplier(String supplierId) throws SQLException {
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
