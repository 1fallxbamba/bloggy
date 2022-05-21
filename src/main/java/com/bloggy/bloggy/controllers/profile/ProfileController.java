package com.bloggy.bloggy.controllers.profile;

import com.bloggy.bloggy.models.user.BloggyUser;
import com.bloggy.bloggy.utils.Alerter;
import com.bloggy.bloggy.utils.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class ProfileController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    private BloggyUser connectedUser;

    public void setConnectedUser(BloggyUser connectedUser) {
        this.connectedUser = connectedUser;
        this.fullNameField.setText(this.connectedUser.getFullName());
        this.usernameField.setText(this.connectedUser.getUsername());
        this.passwordField.setText(this.connectedUser.getPassword());
    }

    public void editProfile(ActionEvent event) {

        boolean formInvalid = ( fullNameField.getText().isEmpty() || usernameField.getText().isEmpty() || passwordField.getText().isEmpty() )
                || (fullNameField.getText() == this.connectedUser.getFullName() && this.usernameField.getText() == this.connectedUser.getUsername() && this.passwordField.getText() == this.connectedUser.getPassword());

        if(formInvalid) {
            Alerter.showMessage("Nothing changed", "Your profile info is still the same !", "You have to at least edit one thing...");
        } else {

            Connection connection = null;
            PreparedStatement statement = null;

            //String query = "INSERT into article values(?, ?, ?, ?)";

            String query = "UPDATE users set fullName = ?, username = ?, password = ? WHERE id = ?";

            try {

                connection = Database.getConnection();
                statement = connection.prepareStatement(query);

                statement.setString(1, this.fullNameField.getText());
                statement.setString(2, this.usernameField.getText());
                statement.setString(3, this.passwordField.getText());
                statement.setString(4, this.connectedUser.getId());

                int result = statement.executeUpdate();

                if (result == 0) {
                    Alerter.showMessage("Oops...", "Error updating your profile", "My bad, please try again !");
                } else {
                    Alerter.showMessage("Profile Successfuly Edited", "I see you got a new identity huh !" , " Your profile info has been successfully updated");
                    this.connectedUser.setFullName(this.fullNameField.getText());
                    this.connectedUser.setUsername(this.usernameField.getText());
                    this.connectedUser.setPassword(this.passwordField.getText());
                }

            } catch (SQLException exception){
                Alerter.showMessage("Aw...", "An unexpected error occurred, more details below", exception.getMessage());
            }
        }

    }

}
