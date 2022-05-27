package com.bloggy.bloggy.controllers.authentication.registration;

import com.bloggy.bloggy.models.user.BloggyUser;
import com.bloggy.bloggy.utils.Alerter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;

public class RegistrationController {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;


    public void handleRegister(ActionEvent actionEvent) throws IOException {

        if (fullNameField.getText().isEmpty() || usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            Alerter.showMessage("Invalid form", "Fill all the fields man !", "how do you expect us to create your account ?...");
        } else {

            BloggyUser newUser = new BloggyUser(
                    UUID.randomUUID().toString(),
                    fullNameField.getText(),
                    usernameField.getText(),
                    passwordField.getText()
            );

            if (BloggyUser.createNew(newUser) != 0) {
                navigateToLogin(actionEvent);
            }

        }

    }

    public void navigateToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/auth/login-view.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}
