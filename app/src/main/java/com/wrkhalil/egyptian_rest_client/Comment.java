package com.wrkhalil.egyptian_rest_client;

public class Comment {

    //Attributes
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    //Comment constructor
    Comment (int postId, String name, String email, String body){
        this.postId = postId; //Fetch postId
        this.name = name; //Fetch name
        this.email = email; //Fetch email
        this.body = body; //Fetch body
    }

    //Getters
    public int getPostId() {
        return postId;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getBody() {
        return body;
    }

}
