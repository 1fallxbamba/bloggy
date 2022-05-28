package com.bloggy.bloggy.controllers.main;

import com.bloggy.bloggy.controllers.article.ArticleController;
import com.bloggy.bloggy.controllers.profile.ProfileController;
import com.bloggy.bloggy.models.user.BloggyUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    private Stage stage;
    private Scene scene;

    private BloggyUser connectedUser;

    @FXML
    private Label welcomeLabel;


    public void setConnectedUser(BloggyUser connected) {
        this.connectedUser = connected;
        welcomeLabel.setText("Welcome " + this.connectedUser.getFullName());
    }


    public void navigateToNewArticle(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/article/post_article-view.fxml"));

        Parent root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);

        ArticleController articleController = loader.getController();

        articleController.setConnectedUser(this.connectedUser);

        stage.show();
    }

    public void navigateToAllArticles(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/article/all_articles-view.fxml"));

        Parent root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);

        ArticleController articleController = loader.getController();

        articleController.setConnectedUser(this.connectedUser);

        stage.show();
    }

    public void navigateToEditProfile(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile/edit_profile-view.fxml"));

        Parent root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);

        ProfileController profileController = loader.getController();

        profileController.setConnectedUser(this.connectedUser);

        stage.show();
    }

    public void logout(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/auth/login-view.fxml"));

        Parent root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

    public void exitApp() {
        Platform.exit();
    }

}
