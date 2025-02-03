package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.dao.CrudUtil;
import com.assignment.purelifewaterbottles.model.ItemDetailDto;

import java.sql.SQLException;

public interface ItemDetailBO {
    boolean save(ItemDetailDto itemDto) throws SQLException;
    boolean update(ItemDetailDto itemDto) throws SQLException;
}
