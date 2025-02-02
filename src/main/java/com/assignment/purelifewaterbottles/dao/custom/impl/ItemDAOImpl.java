package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.custom.ItemDAO;
import com.assignment.purelifewaterbottles.model.ItemDto;
import com.assignment.purelifewaterbottles.model.ItemDtoOriginal;
import com.assignment.purelifewaterbottles.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    public String getNextID() throws SQLException {
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

    @Override
    public ItemDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }

    public boolean save(ItemDtoOriginal itemDto) throws SQLException {
        return CrudUtil.execute("insert into item values (?,?,?,?)", itemDto.getItemId(), itemDto.getName(), itemDto.getCapacity(), itemDto.getPrice());
    }

    public ArrayList<ItemDto> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select i.itemId, i.name, i.capacity, i.price, id.itemQty from item i join item_detail id on i.itemId = id.itemId");

        ArrayList<ItemDto> itemDtos = new ArrayList<>();

        while (rst.next()) {
            ItemDto itemDto = new ItemDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getInt(5));
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    @Override
    public boolean save(ItemDto Dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ItemDto Dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean update(ItemDtoOriginal itemDto) throws SQLException {
        return CrudUtil.execute("update item set name=?, capacity=?, price=? where itemId=?", itemDto.getName(), itemDto.getCapacity(), itemDto.getPrice(), itemDto.getItemId());
    }

    public boolean delete(String itemId) throws SQLException {
        return CrudUtil.execute("delete from item where itemId=?", itemId);
    }

    public boolean deductStock(String itemId, int quantity) throws SQLException {
        return CrudUtil.execute("UPDATE item_detail SET itemQty = CASE WHEN itemQty >= ? THEN itemQty - ? END WHERE itemId = ?", quantity, quantity, itemId);
    }
}