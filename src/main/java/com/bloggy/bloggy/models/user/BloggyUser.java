package com.bloggy.bloggy.models.user;

public class BloggyUser {

    private String id;
    private String fullName;
    private String username;
    private String password;

    public BloggyUser(String id, String fullName, String username, String password) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public BloggyUser(BloggyUser twin) {
        this.id = twin.getId();
        this.fullName = twin.getFullName();
        this.username = twin.getUsername();
    }

    public BloggyUser() {}

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

    public String getPassword() {return this.password;}

    public void setPassword(String password) {this.password = password;}
}
