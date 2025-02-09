package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.ItemDAOImpl;
import com.assignment.purelifewaterbottles.dto.ItemDto;
import com.assignment.purelifewaterbottles.dto.ItemDtoOriginal;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
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
