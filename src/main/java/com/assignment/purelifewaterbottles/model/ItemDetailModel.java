package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.ItemDetailDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.SQLException;

public class ItemDetailModel {
    public boolean saveItem(ItemDetailDto itemDto) throws SQLException {
        return CrudUtil.execute("insert into item_detail values (?,?,?)", itemDto.getItemId(), itemDto.getWarehouseId(), itemDto.getItemQty());
    }

    public boolean updateItem(ItemDetailDto itemDto) throws SQLException {
        return CrudUtil.execute("UPDATE item_detail SET warehouseId = ?, itemQty = ? WHERE itemId = ?", itemDto.getWarehouseId(), itemDto.getItemQty(), itemDto.getItemId());
    }
}
