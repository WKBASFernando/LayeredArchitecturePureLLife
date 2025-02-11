package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.QueryDAO;
import com.assignment.purelifewaterbottles.dto.ItemDetailDto;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDetailDAOImpl implements QueryDAO<ItemDetailDto> {
    @Override
    public boolean save(Connection connection, ItemDetailDto itemDto) throws SQLException {
        return CrudUtil.execute("insert into item_detail values (?,?,?)", itemDto.getItemId(), itemDto.getWarehouseId(), itemDto.getItemQty());
    }

    @Override
    public boolean update(Connection connection, ItemDetailDto itemDto) throws SQLException {
        return CrudUtil.execute("UPDATE item_detail SET warehouseId = ?, itemQty = ? WHERE itemId = ?", itemDto.getWarehouseId(), itemDto.getItemQty(), itemDto.getItemId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ItemDetailDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }
}
