package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.ItemBO;
import com.assignment.purelifewaterbottles.dao.DAOFactory;
import com.assignment.purelifewaterbottles.dao.custom.impl.ItemDAOImpl;
import com.assignment.purelifewaterbottles.dao.custom.impl.ItemDetailDAOImpl;
import com.assignment.purelifewaterbottles.db.DBConnection;
import com.assignment.purelifewaterbottles.dto.ItemDetailDto;
import com.assignment.purelifewaterbottles.dto.ItemDto;
import com.assignment.purelifewaterbottles.dto.ItemDtoOriginal;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAOImpl itemDAO = (ItemDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);
    ItemDetailDAOImpl itemDetailDAO = (ItemDetailDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM_DETAIL);

    @Override
    public String getNextID() throws SQLException {
        return itemDAO.getNextID();
    }

    @Override
    public ItemDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return itemDAO.getAllIds();
    }

    @Override
    public boolean save(ItemDtoOriginal itemDto, ItemDetailDto itemDetailDto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            boolean itemSavedI = itemDAO.save(connection, itemDto);
            boolean itemSavedID = itemDetailDAO.save(connection, itemDetailDto);
            if (!itemSavedI && !itemSavedID) {
                throw new SQLException("Failed to save item: " + itemDto.getItemId());
            }

            connection.commit();
            result = true;
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }

    @Override
    public ArrayList<ItemDto> getAll() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllItems();
    }

    @Override
    public boolean update(ItemDtoOriginal itemDto, ItemDetailDto itemDetailDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            boolean itemUpdateI = itemDAO.update(connection, itemDto);
            boolean itemUpdateID = itemDetailDAO.update(connection, itemDetailDto);
            if (!itemUpdateI && !itemUpdateID) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save item: " + itemDto.getItemId());
                alert.showAndWait();
                throw new SQLException("Failed to save item: " + itemDto.getItemId());
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
    public boolean delete(String itemId) throws SQLException {
        return itemDAO.delete(itemId);
    }

    @Override
    public boolean deductStock(Connection connection, String itemId, int quantity) throws SQLException {
        return itemDAO.deductStock(connection, itemId, quantity);
    }
}
