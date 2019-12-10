package com.wrkhalil.egyptian_rest_client;

public class Post {

    //Attributes
    private int userId;
    private int id;
    private String title;
    private String body;

    //Post Constructor
    Post (int userId, int id, String title, String body){
        this.userId = userId; //Fetch userId
        this.id = id; //Fetch id
        this.title = title; //Fetch title
        this.body = body; //Fetch body
    }

    //Getters
    public int getUserId() {
        return userId;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
}
