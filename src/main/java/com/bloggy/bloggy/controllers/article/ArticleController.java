package com.bloggy.bloggy.controllers.article;

import com.bloggy.bloggy.models.article.BloggyArticle;
import com.bloggy.bloggy.models.user.BloggyUser;
import com.bloggy.bloggy.utils.Alerter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.UUID;

public class ArticleController {

    @FXML
    private TextField articleTitleField;

    @FXML
    private TextArea articleContentField;

    private BloggyUser connectedUser;

    public void setConnectedUser(BloggyUser connectedUser) {
        this.connectedUser = connectedUser;
    }

    public void saveArticle(ActionEvent event) {

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

            }

        }

    }


}
