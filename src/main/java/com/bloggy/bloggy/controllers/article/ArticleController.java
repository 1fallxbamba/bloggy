package com.bloggy.bloggy.controllers.article;

import com.bloggy.bloggy.controllers.main.HomeController;
import com.bloggy.bloggy.models.article.BloggyArticle;
import com.bloggy.bloggy.models.user.BloggyUser;
import com.bloggy.bloggy.utils.Alerter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ArticleController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField articleTitleField;

    @FXML
    private TextArea articleContentField;

    @FXML
    private TableView<BloggyArticle> articleTableView;

    @FXML
    private TableColumn<BloggyArticle, String> titleColumn;

    @FXML
    private TableColumn<BloggyArticle, String> contentColumn;

    @FXML
    private TableColumn<BloggyArticle, String> submitterColumn;

    @FXML
    private TableColumn<BloggyArticle, String> publicationDateColumn;


    private BloggyUser connectedUser;

    public void setConnectedUser(BloggyUser connectedUser) {
        this.connectedUser = connectedUser;
    }

    public void saveArticle(ActionEvent event) throws IOException {

        if (articleContentField.getText().isEmpty() || articleTitleField.getText().isEmpty()) {
            Alerter.showMessage("Invalid form", "Fill all the fields man !", "c'mon you can't create an empty article...");
        } else {

            BloggyArticle newArticle = new BloggyArticle(
                    UUID.randomUUID().toString(),
                    articleTitleField.getText(),
                    articleContentField.getText(),
                    this.connectedUser.getId()
            );

            if (BloggyArticle.createNew(newArticle) == 1) {
                navigateBackToHome(event, this.connectedUser);
            }

        }

    }

    public void navigateBackToHome(ActionEvent event, BloggyUser connectedUser) throws IOException {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<BloggyArticle> articles = BloggyArticle.fetchAll();

        ObservableList<BloggyArticle> bloggyArticles = FXCollections.observableArrayList(articles);

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("Content"));
        submitterColumn.setCellValueFactory(new PropertyValueFactory<>("Submitter"));
        publicationDateColumn.setCellValueFactory(new PropertyValueFactory<>("PublicationDate"));

        articleTableView.setItems(bloggyArticles);
    }
}
