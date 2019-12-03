package com.wrkhalil.egyptian_rest_client;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class BaseApplication extends Application {

    public static List<Post> postsList = new ArrayList<>();
    public static List<User> usersList = new ArrayList<>();
    public static List<Comment> commentsList = new ArrayList<>();
    public static RequestQueue queue;
    private static Context context;

    public void onCreate() {
        super.onCreate();
        BaseApplication.context = getApplicationContext();
        queue = Volley.newRequestQueue(BaseApplication.getAppContext());
    }

    public static Context getAppContext() {
        return BaseApplication.context;
    }

}
