package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.dao.custom.impl.ItemDAOImpl;
import com.assignment.purelifewaterbottles.model.ItemDto;
import com.assignment.purelifewaterbottles.model.ItemDtoOriginal;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO {
    ItemDAOImpl itemDAO = new ItemDAOImpl();
    String getNextID() throws SQLException;
    ItemDto find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    boolean save(ItemDtoOriginal itemDto) throws SQLException;
    ArrayList<ItemDto> getAll() throws SQLException;
    boolean save(ItemDto Dto) throws SQLException, ClassNotFoundException;
    boolean update(ItemDto Dto) throws SQLException, ClassNotFoundException;
    boolean update(ItemDtoOriginal itemDto) throws SQLException;
    boolean delete(String itemId) throws SQLException;
    boolean deductStock(String itemId, int quantity) throws SQLException;
}
