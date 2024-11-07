package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.UserDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static UserDto authenticateUser(String username, String password) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM user WHERE username = ? AND password = ?", username, password);
            if (resultSet.next()) {
                String userId = resultSet.getString("userId");
                String dbUsername = resultSet.getString("username");
                String dbPassword = resultSet.getString("password");
                return new UserDto(userId, dbUsername, dbPassword);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
