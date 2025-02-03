package com.assignment.purelifewaterbottles.bo.impl;

import com.assignment.purelifewaterbottles.bo.ItemBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.ItemDAOImpl;
import com.assignment.purelifewaterbottles.model.ItemDto;
import com.assignment.purelifewaterbottles.model.ItemDtoOriginal;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAOImpl itemDAO = new ItemDAOImpl();

    @Override
    public String getNextID() throws SQLException {
        return itemDAO.getNextID();
    }

    @Override
    public ItemDto find(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.find(id);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return itemDAO.getAllIds();
    }

    @Override
    public boolean save(ItemDtoOriginal itemDto) throws SQLException {
        return itemDAO.save(itemDto);
    }

    @Override
    public ArrayList<ItemDto> getAll() throws SQLException {
        return itemDAO.getAll();
    }

    @Override
    public boolean save(ItemDto Dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(Dto);
    }

    @Override
    public boolean update(ItemDto Dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(Dto);
    }

    @Override
    public boolean update(ItemDtoOriginal itemDto) throws SQLException {
        return itemDAO.update(itemDto);
    }

    @Override
    public boolean delete(String itemId) throws SQLException {
        return itemDAO.delete(itemId);
    }

    @Override
    public boolean deductStock(String itemId, int quantity) throws SQLException {
        return itemDAO.deductStock(itemId, quantity);
    }
}
