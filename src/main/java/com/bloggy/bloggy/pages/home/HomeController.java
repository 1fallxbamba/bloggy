package com.bloggy.bloggy.pages.home;

import com.bloggy.bloggy.models.user.BloggyUser;
import com.bloggy.bloggy.utils.Alerter;
import com.bloggy.bloggy.utils.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HomeController {

    private BloggyUser connectedUser;

    @FXML
    private Label welcomeLabel;



    public void setConnectedUser(BloggyUser connected) {
        this.connectedUser = connected;
        welcomeLabel.setText("Welcome " + this.connectedUser.getFullName());
    }

}
