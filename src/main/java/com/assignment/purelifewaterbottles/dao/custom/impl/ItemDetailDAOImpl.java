package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.custom.ItemDetailDAO;
import com.assignment.purelifewaterbottles.dto.ItemDetailDto;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.SQLException;

public class ItemDetailDAOImpl {
    public boolean save(ItemDetailDto itemDto) throws SQLException {
        return CrudUtil.execute("insert into item_detail values (?,?,?)", itemDto.getItemId(), itemDto.getWarehouseId(), itemDto.getItemQty());
    }

    public boolean update(ItemDetailDto itemDto) throws SQLException {
        return CrudUtil.execute("UPDATE item_detail SET warehouseId = ?, itemQty = ? WHERE itemId = ?", itemDto.getWarehouseId(), itemDto.getItemQty(), itemDto.getItemId());
    }
}
