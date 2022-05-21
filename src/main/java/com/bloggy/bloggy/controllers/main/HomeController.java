package com.bloggy.bloggy.controllers.main;

import com.bloggy.bloggy.models.user.BloggyUser;
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
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private Stage stage;
    private Scene scene;

    private BloggyUser connectedUser;

    @FXML
    private Label welcomeLabel;

    @FXML
    private TableView<String> articlesTable;

    @FXML
    private TableColumn<String, String> articleTitleColumn;

    @FXML
    private TableColumn<String, String> articleContentColumn;

    @FXML
    private TableColumn<String, String> publicationDateColumn;


    public void setConnectedUser(BloggyUser connected) {
        this.connectedUser = connected;
        welcomeLabel.setText("Welcome " + this.connectedUser.getUsername() );
    }


    public void navigateToNewArticle(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/post_article-view.fxml"));

        Parent root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);

        ArticleController articleController = loader.getController();

        articleController.setConnectedUser(this.connectedUser);

        stage.show();

//        Parent root = FXMLLoader.load(getClass().getResource("/main/post_article-view.fxml"));
//
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//
//        stage.setScene(scene);
//        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        articleTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        articleContentColumn.setCellValueFactory(new PropertyValueFactory<>("Content"));
        publicationDateColumn.setCellValueFactory(new PropertyValueFactory<>("Publication Date"));

        articlesTable.setItems(stuff);

    }

    private ObservableList<String> stuff= FXCollections.observableArrayList("Po", "Pas", "323");
}
