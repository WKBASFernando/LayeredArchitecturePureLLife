package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.CrudDAO;
import com.assignment.purelifewaterbottles.model.ItemDto;
import com.assignment.purelifewaterbottles.model.ItemDtoOriginal;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<ItemDto> {
    boolean save(ItemDtoOriginal itemDto) throws SQLException;
    boolean update(ItemDtoOriginal itemDto) throws SQLException;
    boolean deductStock(String itemId, int quantity) throws SQLException;
}
