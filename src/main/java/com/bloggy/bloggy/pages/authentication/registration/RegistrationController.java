package com.bloggy.bloggy.pages.authentication.registration;

import com.bloggy.bloggy.utils.Alerter;
import com.bloggy.bloggy.utils.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
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


    public void handleRegister(ActionEvent actionEvent) {

        if (fullNameField.getText().isEmpty() || usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            Alerter.showMessage("Invalid form", "Fill all the fields man !", "how do you expect us to create your account ?...");
        } else {

            Connection connection = null;
            PreparedStatement statement = null;

            String query = "INSERT into users values(?, ?, ?, ?)";

            try {
                connection = Database.getConnection();
                statement = connection.prepareStatement(query);

                statement.setString(1, UUID.randomUUID().toString());
                statement.setString(2, fullNameField.getText());
                statement.setString(3, usernameField.getText());
                statement.setString(4, passwordField.getText());

                int result = statement.executeUpdate();

                if (result == 0) {
                    Alerter.showMessage("Oops...", "Error creating your account", "My bad, please try again !");
                } else {
                    Alerter.showMessage("Registration successful", "Welcome to J.A.B.B, " + fullNameField.getText() + " !", " Your account have been successfully created");
                    navigateToLogin(actionEvent);
                }

            } catch (SQLException exception) {
                Alerter.showMessage("Aw...", "An unexpected error occurred, more details below", exception.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
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
