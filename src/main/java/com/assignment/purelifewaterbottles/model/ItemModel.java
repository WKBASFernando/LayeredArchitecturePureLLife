package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.ItemDto;
import com.assignment.purelifewaterbottles.dto.ItemDtoOriginal;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public String getNextItemId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select itemId from item order by itemId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("I%03d", newIdIndex);
        }
        return "I001";
    }

    public boolean saveItem(ItemDtoOriginal itemDto) throws SQLException {
        return CrudUtil.execute("insert into item values (?,?,?,?)", itemDto.getItemId(), itemDto.getName(), itemDto.getCapacity(), itemDto.getPrice());
    }

    public ArrayList<ItemDto> getAllItems() throws SQLException {
        ResultSet rst = CrudUtil.execute("select i.itemId, i.name, i.capacity, i.price, id.itemQty from item i join item_detail id on i.itemId = id.itemId");

        ArrayList<ItemDto> itemDtos = new ArrayList<>();

        while (rst.next()) {
            ItemDto itemDto = new ItemDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getInt(5));
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    public boolean updateItem(ItemDtoOriginal itemDto) throws SQLException {
        return CrudUtil.execute("update item set name=?, capacity=?, price=? where itemId=?", itemDto.getName(), itemDto.getCapacity(), itemDto.getPrice(), itemDto.getItemId());
    }

    public boolean deleteItem(String itemId) throws SQLException {
        return CrudUtil.execute("delete from item where itemId=?", itemId);
    }

    public boolean deductStock(String itemId, int quantity) throws SQLException {
        return CrudUtil.execute("UPDATE item_detail SET itemQty = CASE WHEN itemQty >= ? THEN itemQty - ? END WHERE itemId = ?", quantity, quantity, itemId);
    }
}