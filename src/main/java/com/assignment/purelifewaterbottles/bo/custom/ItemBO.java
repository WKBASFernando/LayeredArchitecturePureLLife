package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.ItemDAOImpl;
import com.assignment.purelifewaterbottles.dto.ItemDetailDto;
import com.assignment.purelifewaterbottles.dto.ItemDto;
import com.assignment.purelifewaterbottles.dto.ItemDtoOriginal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    String getNextID() throws SQLException;
    ItemDto find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    ArrayList<ItemDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(ItemDtoOriginal Dto, ItemDetailDto detailDto) throws SQLException, ClassNotFoundException;
    boolean update(ItemDtoOriginal itemDto, ItemDetailDto itemDetailDto) throws SQLException, ClassNotFoundException;
    boolean delete(String itemId) throws SQLException;
    boolean deductStock(Connection connection, String itemId, int quantity) throws SQLException;
}
