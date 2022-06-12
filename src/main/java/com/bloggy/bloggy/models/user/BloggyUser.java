package com.bloggy.bloggy.models.user;

import com.bloggy.bloggy.utils.Alerter;
import com.bloggy.bloggy.utils.Database;

import java.sql.*;

public class BloggyUser {

    private String id;
    private String fullName;
    private String username;
    private String password;

    private static Connection connection = null;

    public BloggyUser(String id, String fullName, String username, String password) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static BloggyUser authenticate(String username, String password) {
        ResultSet result = null;
        Statement statement = null;


        String query = "SELECT * FROM users WHERE username = '" + username + "'";

        String fetchedID, fetchedName, fetchedUsername, fetchedPassword;

        try {

            connection = Database.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            if (result.next()) {

                fetchedID = result.getString("id");
                fetchedName = result.getString("fullname");
                fetchedUsername = result.getString("username");
                fetchedPassword = result.getString("password");

                if (fetchedPassword.equals(password)) {

                    BloggyUser connectedUser = new BloggyUser(fetchedID, fetchedName, fetchedUsername, fetchedPassword);

                    return connectedUser;

                } else {
                    Alerter.showMessage("Oops...", "Invalid password !", "The password you entered is invalid, please try again.");

                    return null;
                }

            } else {
                Alerter.showMessage("Oops...", "User not found !", "J.A.B.B does not recognize the username you entered.");

                return null;
            }

        } catch (SQLException exception) {
            Alerter.showMessage("Aw...", "An unexpected error occurred, more details below", exception.getMessage());

            return null;
        }
    }

    public static int createNew(BloggyUser newUser) {

        PreparedStatement statement = null;

        String query = "INSERT into users values(?, ?, ?, ?)";

        try {
            connection = Database.getConnection();
            statement = connection.prepareStatement(query);

            statement.setString(1, newUser.getId());
            statement.setString(2, newUser.getFullName());
            statement.setString(3, newUser.getUsername());
            statement.setString(4, newUser.getPassword());

            int result = statement.executeUpdate();

            if (result == 0) {
                Alerter.showMessage("Oops...", "Error creating your account", "My bad, please try again !");
                return 0;
            } else {
                Alerter.showMessage("Registration successful", "Welcome to J.A.B.B, " + newUser.getFullName() + " !", " Your account have been successfully created");
                return result;
            }

        } catch (SQLException exception) {
            Alerter.showMessage("Aw...", "An unexpected error occurred, more details below", exception.getMessage());
            return 0;
        }
    }

    public static BloggyUser updateInfo(BloggyUser existingUser) {
        PreparedStatement statement = null;

        String query = "UPDATE users set fullName = ?, username = ?, password = ? WHERE id = ?";

        try {

            connection = Database.getConnection();
            statement = connection.prepareStatement(query);

            statement.setString(1, existingUser.getFullName());
            statement.setString(2, existingUser.getUsername());
            statement.setString(3, existingUser.getPassword());
            statement.setString(4, existingUser.getId());

            int result = statement.executeUpdate();

            if (result == 0) {
                Alerter.showMessage("Oops...", "Error updating your profile", "My bad, please try again !");
                return null;
            } else {
                Alerter.showMessage("Profile Successfuly Edited", "I see you got a new identity huh !", " Your profile info has been successfully updated");

                return new BloggyUser(
                        existingUser.getId(),
                        existingUser.getFullName(),
                        existingUser.getUsername(),
                        existingUser.getPassword()
                );
            }

        } catch (SQLException exception) {
            Alerter.showMessage("Aw...", "An unexpected error occurred, more details below", exception.getMessage());
            return null;
        }
    }
}
