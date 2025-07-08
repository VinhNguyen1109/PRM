package com.example.btvnwebservice;

public class Post {
    public int userId;
    public int id;
    public String title;
    public String body;

    public Post(int userId, String body, String title, int id) {
        this.userId = userId;
        this.body = body;
        this.title = title;
        this.id = id;
    }

    public Post() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}