package com.bloggy.bloggy.controllers.authentication.login;

import com.bloggy.bloggy.models.user.BloggyUser;
import com.bloggy.bloggy.controllers.main.HomeController;
import com.bloggy.bloggy.utils.Alerter;
import com.bloggy.bloggy.utils.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void handleLogin(ActionEvent actionEvent) {

        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            Alerter.showMessage("Invalid form", "Fill all the fields man !", "getting in isn't that easy...");
        } else {

            ResultSet result = null;
            Connection connection = null;
            Statement statement = null;

            String givenUsername = usernameField.getText() ;
            String givenPassword = passwordField.getText();

            String query = "SELECT * FROM users WHERE username = '" + givenUsername + "'";

            String fetchedID, fetchedName, fetchedUsername, fetchedPassword;

            try {

                connection = Database.getConnection();
                statement = connection.createStatement();
                result = statement.executeQuery(query);

                if(result.next()) {

                    fetchedID = result.getString("id");
                    fetchedName = result.getString("fullName");
                    fetchedUsername = result.getString("username");
                    fetchedPassword = result.getString("password");

                    if(fetchedPassword.equals(givenPassword)) {

                        BloggyUser connectedUser = new BloggyUser(fetchedID, fetchedName, fetchedUsername);

                        navigateToHome(actionEvent, connectedUser);
                    } else {
                        Alerter.showMessage("Oops...", "Invalid password !", "The password you entered is invalid, please try again.");
                    }

                } else {
                    Alerter.showMessage("Oops...", "User not found !", "J.A.B.B does not recognize the username you entered.");
                }

            } catch (SQLException exception) {
                Alerter.showMessage("Aw...", "An unexpected error occurred, more details below", exception.getMessage());
            } catch (IOException exception) {
                Alerter.showMessage("Aw...", "An unexpected error occurred, more details below", exception.getMessage());
            }

        }

    }


    public void navigateToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/auth/registration-view.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void navigateToHome(ActionEvent event, BloggyUser connectedUser) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/home-view.fxml"));

        Parent root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);

        HomeController homeController = loader.getController();

        homeController.setConnectedUser(connectedUser);

        stage.show();
    }



}
