package com.bloggy.bloggy.controllers.main;

import com.bloggy.bloggy.models.user.BloggyUser;
import com.bloggy.bloggy.utils.Alerter;
import com.bloggy.bloggy.utils.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class ArticleController {

    @FXML
    private TextField articleTitleField;

    @FXML
    private TextArea articleContentField;

    @FXML
    private Label titleLabel;

    private BloggyUser connectedUser;

    public void setConnectedUser(BloggyUser connectedUser) {
        this.connectedUser = connectedUser;
    }

    public void saveArticle(ActionEvent event) {

        if(articleContentField.getText().isEmpty() || articleTitleField.getText().isEmpty()) {
            Alerter.showMessage("Invalid form", "Fill all the fields man !", "c'mon you can't create an empty article...");
        } else {

            Connection connection = null;
            PreparedStatement statement = null;

            String query = "INSERT into articles values(?, ?, ?, ?)";

            try {

                connection = Database.getConnection();
                statement = connection.prepareStatement(query);

                statement.setString(1, UUID.randomUUID().toString());
                statement.setString(2, articleTitleField.getText());
                statement.setString(3, articleContentField.getText());
                statement.setString(4, this.connectedUser.getId());

                int result = statement.executeUpdate();

                if (result == 0) {
                    Alerter.showMessage("Oops...", "Error creating your article", "My bad, please try again !");
                } else {
                    Alerter.showMessage("Article creation successful", "Keep going !" , " Your boring article have been successfully created");
                }

            } catch (SQLException exception){
                Alerter.showMessage("Aw...", "An unexpected error occurred, more details below", exception.getMessage());
            }
        }

    }

}
