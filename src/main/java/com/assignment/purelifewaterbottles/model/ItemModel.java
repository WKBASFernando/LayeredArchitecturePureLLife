package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.ItemDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

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

    public boolean saveItem(ItemDto itemDto) throws SQLException {
        return CrudUtil.execute("insert into item values (?,?,?,?)", itemDto.getItemId(), itemDto.getName(), itemDto.getCapacity(), itemDto.getPrice());
    }

    public ArrayList<ItemDto> getAllItems() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from item");

        ArrayList<ItemDto> itemDtos = new ArrayList<>();

        while (rst.next()) {
            ItemDto itemDto = new ItemDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4));
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    public boolean updateItem(ItemDto itemDto) throws SQLException {
        return CrudUtil.execute("update item set name=?, capacity=?, price=? where itemId=?", itemDto.getName(), itemDto.getCapacity(), itemDto.getPrice(), itemDto.getItemId());
    }

    public boolean deleteItem(String itemId) throws SQLException {
        return CrudUtil.execute("delete from item where itemId=?", itemId);
    }
}