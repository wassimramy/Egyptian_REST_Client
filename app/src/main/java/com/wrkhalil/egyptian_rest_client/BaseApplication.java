package com.wrkhalil.egyptian_rest_client;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApplication extends Application {

    public static List<Post> postsList = new ArrayList<>();
    public static List<User> usersList = new ArrayList<>();
    public static List<Comment> commentsList = new ArrayList<>();
    public static JsonPlaceHolderApi jsonPlaceHolderApi;

    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/") //Set the retrofit variable with the base URL
                .addConverterFactory(GsonConverterFactory.create()) //Parse the JSON object
                .build(); //Build
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class); //Assign the jsonPlaceHolderApi to retrofit
    }
}
