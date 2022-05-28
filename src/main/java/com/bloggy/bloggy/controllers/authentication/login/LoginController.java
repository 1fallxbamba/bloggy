package com.bloggy.bloggy.controllers.authentication.login;

import com.bloggy.bloggy.models.user.BloggyUser;
import com.bloggy.bloggy.controllers.main.HomeController;
import com.bloggy.bloggy.utils.Alerter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void handleLogin(ActionEvent actionEvent) throws IOException {

        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            Alerter.showMessage("Invalid form", "Fill all the fields man !", "getting in isn't that easy...");
        } else {

            BloggyUser connectedUser = BloggyUser.authenticate(this.usernameField.getText(), this.passwordField.getText());

            if (connectedUser != null) {
                navigateToHome(actionEvent, connectedUser);
            }

        }

    }


    public void navigateToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/auth/registration-view.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void navigateToHome(ActionEvent event, BloggyUser connectedUser) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/home-view.fxml"));

        Parent root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);

        HomeController homeController = loader.getController();

        homeController.setConnectedUser(connectedUser);

        stage.show();
    }

    public void exitApp() {
        Platform.exit();
    }


}
