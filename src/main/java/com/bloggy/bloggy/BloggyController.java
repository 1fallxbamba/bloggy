package com.bloggy.bloggy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BloggyController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}