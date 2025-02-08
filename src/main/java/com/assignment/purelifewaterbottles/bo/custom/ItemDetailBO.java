package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.model.ItemDetailDto;

import java.sql.SQLException;

public interface ItemDetailBO extends SuperBO {
    boolean save(ItemDetailDto itemDto) throws SQLException;
    boolean update(ItemDetailDto itemDto) throws SQLException;
}
