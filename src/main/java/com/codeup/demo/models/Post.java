package com.codeup.demo.models;

public class Post {
    private long id;
    private String title;
    private String postBody;

    public Post() {

    }

    public Post(long id, String title, String postBody) {
        this.id = id;
        this.title = title;
        this.postBody = postBody;
    }
    public Post(String title, String postBody) {
        this.title = title;
        this.postBody = postBody;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }
}
