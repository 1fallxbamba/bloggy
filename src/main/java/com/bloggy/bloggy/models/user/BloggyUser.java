package com.bloggy.bloggy.models.user;

public class BloggyUser {

    private String id;
    private String fullName;
    private String username;

    public BloggyUser(String id, String fullName, String username) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
