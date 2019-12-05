package com.wrkhalil.egyptian_rest_client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("users")
    Call<List<User>> getUsers();

    @GET("comments")
    Call<List<Comment>> getComments();
}
