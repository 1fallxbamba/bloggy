package com.bloggy.bloggy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class BloggyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/auth/login-view.fxml"));

        stage.setTitle("Bloggy : J.A.B.B");

        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(new Scene(root, 800, 500));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}