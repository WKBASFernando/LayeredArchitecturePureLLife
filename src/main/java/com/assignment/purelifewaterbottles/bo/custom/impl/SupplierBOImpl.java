package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.SupplierBO;
import com.assignment.purelifewaterbottles.dao.DAOFactory;
import com.assignment.purelifewaterbottles.dao.custom.impl.SupplierDAOImpl;
import com.assignment.purelifewaterbottles.dao.custom.impl.SupplierDetailDAOImpl;
import com.assignment.purelifewaterbottles.db.DBConnection;
import com.assignment.purelifewaterbottles.dto.ItemDetailDto;
import com.assignment.purelifewaterbottles.dto.SupplierAndDetailDto;
import com.assignment.purelifewaterbottles.dto.SupplierDetailDto;
import com.assignment.purelifewaterbottles.dto.SupplierDto;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAOImpl supplierDAO = (SupplierDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);
    SupplierDetailDAOImpl supplierDetailDAO = (SupplierDetailDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER_DETAIL);

    @Override
    public String getNextID() throws SQLException {
        return supplierDAO.getNextID();
    }

    @Override
    public SupplierDto find(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.find(id);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return supplierDAO.getAllIds();
    }

    @Override
    public boolean save(SupplierDto supplier, SupplierDetailDto supplierDetailDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean result = false;

        try {
            connection.setAutoCommit(false);
            boolean isSavedSupplier = supplierDAO.save(connection, supplier);
            boolean isSavedDetails = supplierDetailDAO.save(connection, supplierDetailDto);

            if (!isSavedSupplier && ! isSavedDetails) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save supplier: " + supplier.getSupplierId());
                alert.showAndWait();
                throw new SQLException("Failed to save Supplier: " + supplier.getSupplierId());
            }

            connection.commit();
            result = true;
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }

    @Override
    public boolean update(SupplierDto supplier, SupplierDetailDto supplierDetailDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean result = false;

        try {
            connection.setAutoCommit(false);
            boolean isUpdateSupplier = supplierDAO.update(connection, supplier);
            boolean isUpdateDetails = supplierDetailDAO.update(connection, supplierDetailDto);

            if (!isUpdateSupplier && ! isUpdateDetails) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to update Supplier: " + supplier.getSupplierId());
                alert.showAndWait();
                throw new SQLException("Failed to update Supplier: " + supplier.getSupplierId());
            }

            connection.commit();
            result = true;
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }

    @Override
    public boolean delete(String supplierId) throws SQLException {
        return supplierDAO.delete(supplierId);
    }

    @Override
    public ArrayList<SupplierAndDetailDto> getAllSuppliers() throws SQLException {
        return supplierDAO.getAllSuppliers();
    }
}
