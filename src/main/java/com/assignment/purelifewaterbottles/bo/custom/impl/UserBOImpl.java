package com.assignment.purelifewaterbottles.bo.custom.impl;

import com.assignment.purelifewaterbottles.bo.custom.UserBO;
import com.assignment.purelifewaterbottles.dao.DAOFactory;
import com.assignment.purelifewaterbottles.dao.custom.impl.UserDAOImpl;
import com.assignment.purelifewaterbottles.dto.UserDto;
import com.assignment.purelifewaterbottles.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAOImpl userDAO = (UserDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);
    public UserDto authenticateUser(String username, String password) {
        User user = userDAO.authenticateUser(username, password);
        if (user != null) {
            return new UserDto(user.getUsername(), user.getPassword());
        }
        return null;
    }

    public ArrayList<String> getAllUsernames() throws SQLException {
        return userDAO.getAllUsernames();
    }

    public UserDto findByUsername(String username) {
        try {
            User user = userDAO.findByUsername(username);
            if (user != null) {
                return new UserDto(user.getUsername(), user.getPassword());
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public boolean delete(String selectedUsername) throws SQLException {
        return userDAO.delete(selectedUsername);
    }

    public String getNextID() throws SQLException, ClassNotFoundException {
        return userDAO.getNextID();
    }

    public UserDto find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public ArrayList<String> getAllIds() throws SQLException {
        return userDAO.getAllIds();
    }

    public ArrayList<UserDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<User> users = userDAO.getAll();
        ArrayList<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(new UserDto(
                    user.getUsername(),
                    user.getPassword()
            ));
        }
        return userDtos;
    }

    public boolean save(UserDto userDto) throws SQLException {
        return userDAO.save(new User(userDto.getUsername(), userDto.getPassword()));
    }

    public boolean update(UserDto Dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(Dto.getUsername(), Dto.getPassword()));
    }

    public boolean isUsernameExists(String username) throws SQLException {
        return userDAO.isUsernameExists(username);
    }
}
