package com.bloggy.bloggy.controllers.profile;

import com.bloggy.bloggy.models.user.BloggyUser;
import com.bloggy.bloggy.utils.Alerter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


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

        boolean formInvalid = (fullNameField.getText().isEmpty() || usernameField.getText().isEmpty() || passwordField.getText().isEmpty())
                || (fullNameField.getText() == this.connectedUser.getFullName() && this.usernameField.getText() == this.connectedUser.getUsername() && this.passwordField.getText() == this.connectedUser.getPassword());

        if (formInvalid) {
            Alerter.showMessage("Nothing changed", "Your profile info is still the same !", "You have to at least edit one thing...");
        } else {

            BloggyUser existingUser = new BloggyUser(
                    connectedUser.getId(),
                    fullNameField.getText(),
                    usernameField.getText(),
                    passwordField.getText()
            );

            BloggyUser updatedUser = BloggyUser.updateInfo(existingUser);

            if (updatedUser != null) {
                this.connectedUser = updatedUser;
            }

        }

    }

}
