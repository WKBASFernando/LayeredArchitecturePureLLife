package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.ItemDetailBO;
import com.assignment.purelifewaterbottles.dao.custom.impl.ItemDetailDAOImpl;
import com.assignment.purelifewaterbottles.dto.ItemDetailDto;

import java.sql.SQLException;

public class ItemDetailBOImpl implements ItemDetailBO {
    ItemDetailDAOImpl itemDetailDAO = new ItemDetailDAOImpl();

    @Override
    public boolean save(ItemDetailDto itemDto) throws SQLException {
        return itemDetailDAO.save(itemDto);
    }

    @Override
    public boolean update(ItemDetailDto itemDto) throws SQLException {
        return itemDetailDAO.update(itemDto);
    }
}
