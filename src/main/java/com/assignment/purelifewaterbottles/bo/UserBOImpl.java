package com.assignment.purelifewaterbottles.bo;

import com.assignment.purelifewaterbottles.dao.CrudUtil;
import com.assignment.purelifewaterbottles.dao.custom.impl.UserDAOImpl;
import com.assignment.purelifewaterbottles.db.DBConnection;
import com.assignment.purelifewaterbottles.model.UserDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAOImpl userDAO = new UserDAOImpl();
    public UserDto authenticateUser(String username, String password) {
        return userDAO.authenticateUser(username, password);
    }

    public ArrayList<String> getAllUsernames() throws SQLException {
        return userDAO.getAllUsernames();
    }

    public UserDto findByUsername(String selectedUsername) throws SQLException {
        return userDAO.findByUsername(selectedUsername);
    }

    public boolean delete(String selectedUsername) throws SQLException {
        return userDAO.delete(selectedUsername);
    }

    public String getNextID() throws SQLException, ClassNotFoundException {
        return userDAO.getNextID();
    }

    public UserDto find(String id) throws SQLException, ClassNotFoundException {
        return userDAO.find(id);
    }

    public ArrayList<String> getAllIds() throws SQLException {
        return userDAO.getAllIds();
    }

    public ArrayList<UserDto> getAll() throws SQLException, ClassNotFoundException {
        return userDAO.getAll();
    }

    public boolean save(UserDto userDto) throws SQLException {
        return userDAO.save(userDto);
    }

    public boolean update(UserDto Dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(Dto);
    }

    public boolean isUsernameExists(String username) throws SQLException {
        return userDAO.isUsernameExists(username);
    }
}
