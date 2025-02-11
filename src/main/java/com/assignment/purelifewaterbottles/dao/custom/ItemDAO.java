package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.QueryDAO;
import com.assignment.purelifewaterbottles.dto.ItemDto;
import com.assignment.purelifewaterbottles.dto.ItemDtoOriginal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends QueryDAO<ItemDtoOriginal> {
    ArrayList<ItemDto> getAllItems() throws SQLException;
    boolean deductStock(Connection connection, String itemId, int quantity) throws SQLException;
}
