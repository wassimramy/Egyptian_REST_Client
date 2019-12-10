package com.wrkhalil.egyptian_rest_client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("posts") //Retrieve all posts and store them in a list of type Post
    Call<List<Post>> getPosts();

    @GET("users") //Retrieve all users and store them in a list of type User
    Call<List<User>> getUsers();

    @GET("comments") //Retrieve all comments and store them in a list of type Comment
    Call<List<Comment>> getComments();

    @POST("comments")  //Post a comment
    Call<Comment> postComment(@Body Comment comment);
}
