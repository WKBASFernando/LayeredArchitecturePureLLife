package com.assignment.purelifewaterbottles.dao;

import java.sql.SQLException;

public interface JoinCrudDAO<T> {
    boolean save(T Dto) throws SQLException, ClassNotFoundException;
    boolean update(T Dto) throws SQLException, ClassNotFoundException;
}
