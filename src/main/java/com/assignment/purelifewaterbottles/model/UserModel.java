package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.db.DBConnection;
import com.assignment.purelifewaterbottles.dto.CustomerDto;
import com.assignment.purelifewaterbottles.dto.UserDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserModel {
    public static UserDto authenticateUser(String username, String password) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM user WHERE username = ? AND password = ?", username, password);
            if (resultSet.next()) {
                String dbUsername = resultSet.getString("username");
                String dbPassword = resultSet.getString("password");
                return new UserDto(dbUsername, dbPassword);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<String> getAllUsernames() throws SQLException {
        ResultSet rst = CrudUtil.execute("select username from user");

        ArrayList<String> usernames = new ArrayList<>();

        while (rst.next()) {
            usernames.add(rst.getString(1));
        }

        return usernames;
    }

    public UserDto findByUsername(String selectedUsername) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from user where username=?", selectedUsername);

        if (rst.next()) {
            return new UserDto(rst.getString(1), rst.getString(2));
        }
        return null;
    }

    public boolean deleteUser(String selectedUsername) throws SQLException {
        return CrudUtil.execute("delete from user where username=?", selectedUsername);
    }

    public boolean saveUser(UserDto userDto) throws SQLException {
        return CrudUtil.execute("insert into user values(?,?)", userDto.getUsername(), userDto.getPassword());
    }

    public boolean isUsernameExists(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM user WHERE username = ?";
        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // If the count is greater than 0, username exists
            }
        }
        return false;
    }
}
