package com.assignment.purelifewaterbottles.dao;

import com.assignment.purelifewaterbottles.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T Dto) throws SQLException, ClassNotFoundException;
    boolean update(T Dto) throws SQLException, ClassNotFoundException;
//    boolean exist(String id) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String getNextID() throws SQLException, ClassNotFoundException;
    T find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
}
