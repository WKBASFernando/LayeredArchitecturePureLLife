package com.assignment.purelifewaterbottles.bo.custom;

import com.assignment.purelifewaterbottles.bo.SuperBO;
import com.assignment.purelifewaterbottles.model.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    UserDto authenticateUser(String username, String password);
    ArrayList<String> getAllUsernames() throws SQLException;
    UserDto findByUsername(String selectedUsername) throws SQLException;
    boolean delete(String selectedUsername) throws SQLException;
    String getNextID() throws SQLException, ClassNotFoundException;
    UserDto find(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllIds() throws SQLException;
    ArrayList<UserDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(UserDto userDto) throws SQLException;
    boolean update(UserDto Dto) throws SQLException, ClassNotFoundException;
    boolean isUsernameExists(String username) throws SQLException;
}
