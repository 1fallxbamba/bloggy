package com.bloggy.bloggy.models.article;

import com.bloggy.bloggy.utils.Alerter;
import com.bloggy.bloggy.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BloggyArticle {
    private String id;
    private String title;
    private String content;
    private String submitter;
    private String publicationDate;

    private static Connection connection = null;

    public BloggyArticle(String id, String title, String content, String submitter) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.submitter = submitter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public static int createNew(BloggyArticle newArticle)
    {
        PreparedStatement statement = null;

        String query = "INSERT into articles values(?, ?, ?, ?)";

        try {

            connection = Database.getConnection();
            statement = connection.prepareStatement(query);

            statement.setString(1, newArticle.getId());
            statement.setString(2, newArticle.getTitle());
            statement.setString(3, newArticle.getContent());
            statement.setString(4, newArticle.getSubmitter());

            int result = statement.executeUpdate();

            if (result == 0) {
                Alerter.showMessage("Oops...", "Error creating your article", "My bad, please try again !");
                return 0;
            } else {
                Alerter.showMessage("Article creation successful", "Keep going !", " Your boring article have been successfully created");
                return 1;
            }

        } catch (SQLException exception) {
            Alerter.showMessage("Aw...", "An unexpected error occurred, more details below", exception.getMessage());
            return 0;
        }
    }

}
