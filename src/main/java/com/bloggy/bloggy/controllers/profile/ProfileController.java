package com.bloggy.bloggy.controllers.profile;

import com.bloggy.bloggy.controllers.main.HomeController;
import com.bloggy.bloggy.models.user.BloggyUser;
import com.bloggy.bloggy.utils.Alerter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class ProfileController {

    private Stage stage;
    private Scene scene;

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

    public void editProfile(ActionEvent event) throws IOException {

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

            this.connectedUser = updatedUser;

            if (updatedUser != null) {
                navigateBackToHome(event);
            }

        }

    }

    public void navigateBackToHome(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/home-view.fxml"));

        Parent root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);

        HomeController homeController = loader.getController();

        homeController.setConnectedUser(connectedUser);

        stage.show();
    }

}
